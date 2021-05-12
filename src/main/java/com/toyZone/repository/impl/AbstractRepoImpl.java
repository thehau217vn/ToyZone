package com.toyZone.repository.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.toyZone.repository.AbstractRepo;
import com.toyZone.utils.Constant;

import javassist.tools.rmi.ObjectNotFoundException;

<<<<<<< HEAD
public class AbstractRepoImpl<ID extends Serializable,T> implements AbstractRepo<ID,T>{
	private Class<T> perClass;
	public AbstractRepoImpl()
	{
		this.perClass = (Class<T>) (((ParameterizedType) getClass().getGenericSuperclass())).getActualTypeArguments()[1];
	}
	public String getPerClassName()
	{
		return this.perClass.getSimpleName();
	}
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	protected Transaction transaction;
	
	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		List<T> list = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			
			StringBuilder sql = new StringBuilder();
			sql.append("from ");
			sql.append(getPerClassName());
			list = session.createQuery(sql.toString()).list();
			
			transaction.commit();
			return list;
		} catch (HibernateException e) {
				// TODO: handle exception
			if (transaction != null) {
    			transaction.rollback();
    		}
			System.out.println(e.getMessage());
		}finally {
			session.flush();
    		session.close();
		}
		return null;
	}

	@Override
	public T insert(T entity) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			
			session.persist(entity);
		
			transaction.commit();
			
			return entity;
		} catch (HibernateException e) {
				// TODO: handle exception
			if (transaction != null) {
    			transaction.rollback();
    		}
			System.out.println(e.getMessage());
		}finally {
			session.flush();
    		session.close();
		}
		return null;
		
		
	}
	@Override
	public T update(T entity) {
		// TODO Auto-generated method stub
		T result = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			
			Object ob = (T) session.merge(entity);
			result = (T) ob;
			transaction.commit();
			return result;
		} catch (HibernateException e) {
				// TODO: handle exception
			if (transaction != null) {
    			transaction.rollback();
    		}
			System.out.println(e.getMessage());
		}finally {
			session.flush();
    		session.close();
		}
		return null;
	}

	@Override
	public boolean delete(ID id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			T t = (T) session.get(perClass, id);
			session.delete(t);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
				// TODO: handle exception
			if (transaction != null) {
    			transaction.rollback();
    		}
			System.out.println(e.getMessage());
		}finally {
			session.flush();
    		session.close();
		}
		return false;
	}

	@Override
	public T updateByProperty(SessionFactory sessionFactory, Map<String, Object> property,
			Map<String, Object> condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T findById(ID var1) {
		// TODO Auto-generated method stub
		T result = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			
			result = (T) session.get(perClass,var1);
			if(result == null)
			{
				throw new ObjectNotFoundException("NOT FOUNT" +var1, null);
			}
			transaction.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			if (transaction != null) {
    			transaction.rollback();
    		}
			System.out.println(e.getMessage());
		}finally {
			session.flush();
    		session.close();
		}
		return result;
	}

	@Override
	public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection,
			Integer offset, Integer limit, String[] filter) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
				List<T> list = null;
				Object totalItems = 0;
				String[] param = null ;
				Object[] value = null;
				
				Session session = sessionFactory.openSession();
				try {
					transaction = session.beginTransaction();
					
					StringBuilder sql = new StringBuilder();
					sql.append("from ");
					sql.append(getPerClassName());
					
					if(property!=null&&property.size()>0)
					{
						sql.append(" ");
						// thuộc tính
						param = new String[property.size()];
						// giá trị của thuộc tính
						value = new Object[property.size()];
						int i = 0;
						
						for(Map.Entry<String, Object> mapEntry : property.entrySet())
						{
							param[i] = mapEntry.getKey();
							value[i] = mapEntry.getValue();
							i++;
						}
						for(int k = 0;k<param.length;k++)
						{
							if(k==0)
							{
								sql.append("where ").append(param[k]).append("=:").append(param[k]);
							}
							else
							{
								sql.append(" and ").append(param[k]).append("=:").append(param[k]);
							}
						}
					}
					if(filter!=null) {
						if(property!=null&&property.size()>0) {
							sql.append(" and ");
						}else {
							sql.append(" where ");
						}
						sql.append(filter[0]);
						sql.append(" like :value");
					}
					if(sortExpression!=null && sortDirection!=null)
					{
						sql.append(" order by ");
						sql.append(sortExpression);
						sql.append(" "+(sortDirection.equals(Constant.ASC) ? "asc" : "desc"));
					}
					Query query = session.createQuery(sql.toString());
					if(property!=null&&property.size()>0)
					{
						for(int l = 0 ; l<property.size();l++)
						{
							query.setParameter(param[l],value[l]);
						}
					}
					if(filter!=null) {
						query.setString("value", filter[1]+"%");
					}
					if(offset !=null && offset >=0)
					{
						query.setFirstResult(offset);
					}
					if(limit !=null && limit>0)
					{
						query.setMaxResults(limit);
					}
					
					list = query.list();
					
					StringBuilder sql2 = new StringBuilder();
					sql2.append("select count(*) from ").append(getPerClassName());
					if(property!=null&&property.size()>0)
					{
						for(int k = 0;k<param.length;k++)
						{
							if(k==0)
							{
								sql2.append(" where ").append(param[k]).append("=:").append(param[k]);
							}
							else
							{
								sql2.append(" and ").append(param[k]).append("=:").append(param[k]);
							}
						}
					}
					if(filter!=null) {
						if(property!=null&&property.size()>0) {
							sql2.append(" and ");
						}else {
							sql2.append(" where ");
						}
						sql2.append(filter[0]);
						sql2.append(" like :value");
					}
					Query query2 = session.createQuery(sql2.toString());
					if(property!=null&&property.size()>0)
					{
						for(int l = 0 ; l<param.length;l++)
						{
							query2.setParameter(param[l],value[l]);
						}
					}
					if(filter!=null) {
						query2.setString("value", filter[1]+"%");
					}
					totalItems = query2.list().get(0);
					
					transaction.commit();
				} catch (HibernateException e) {
					// TODO: handle exception
					
					if (transaction != null) {
		    			transaction.rollback();
		    		}
					System.out.println(e.getMessage());
				}finally {
					session.flush();
		    		session.close();
				}
				
				return new Object[]{totalItems,list};
	}

	
=======
public class AbstractRepoImpl<ID extends Serializable, T> implements AbstractRepo<ID, T> {
    private Class<T> perClass;

    public AbstractRepoImpl() {
        this.perClass = (Class<T>) (((ParameterizedType) getClass().getGenericSuperclass())).getActualTypeArguments()[1];
    }

    public String getPerClassName() {
        return this.perClass.getSimpleName();
    }

    @Autowired
    protected SessionFactory sessionFactory;

    protected Transaction transaction;

    @Override
    public List<T> findAll() {
        // TODO Auto-generated method stub
        List<T> list = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();

            StringBuilder sql = new StringBuilder();
            sql.append("from ");
            sql.append(getPerClassName());
            list = session.createQuery(sql.toString()).list();

            transaction.commit();
            return list;
        } catch (HibernateException e) {
            // TODO: handle exception
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
        return null;
    }

    @Override
    public T insert(T entity) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();

            session.persist(entity);

            transaction.commit();

            return entity;
        } catch (HibernateException e) {
            // TODO: handle exception
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
        return null;


    }

    @Override
    public T update(T entity) {
        // TODO Auto-generated method stub
        T result = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();

            Object ob = (T) session.merge(entity);
            result = (T) ob;
            transaction.commit();
            return result;
        } catch (HibernateException e) {
            // TODO: handle exception
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
        return null;
    }

    @Override
    public boolean delete(ID id) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            T t = (T) session.get(perClass, id);
            session.delete(t);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            // TODO: handle exception
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
        return false;
    }

    @Override
    public T updateByProperty(SessionFactory sessionFactory, Map<String, Object> property,
                              Map<String, Object> condition) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T findById(ID var1) {
        // TODO Auto-generated method stub
        T result = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();

            result = (T) session.get(perClass, var1);
            if (result == null) {
                throw new ObjectNotFoundException("NOT FOUNT" + var1, null);
            }
            transaction.commit();
        } catch (HibernateException e) {
            // TODO: handle exception
        } catch (ObjectNotFoundException e) {
            // TODO Auto-generated catch block
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
        return result;
    }

    @Override
    public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection,
                                   Integer offset, Integer limit, String[] filter) {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub

        List<T> list = null;
        Object totalItems = 0;
        String[] param = null;
        Object[] value = null;

        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();

            StringBuilder sql = new StringBuilder();
            sql.append("from ");
            sql.append(getPerClassName());

            if (property != null && property.size() > 0) {
                sql.append(" ");
                // thuộc tính
                param = new String[property.size()];
                // giá trị của thuộc tính
                value = new Object[property.size()];
                int i = 0;

                for (Map.Entry<String, Object> mapEntry : property.entrySet()) {
                    param[i] = mapEntry.getKey();
                    value[i] = mapEntry.getValue();
                    i++;
                }
                for (int k = 0; k < param.length; k++) {
                    if (k == 0) {
                        sql.append("where ").append(param[k]).append("=:").append(param[k]);
                    } else {
                        sql.append(" and ").append(param[k]).append("=:").append(param[k]);
                    }
                }
            }
            if (filter != null) {
                if (property != null && property.size() > 0) {
                    sql.append(" and ");
                } else {
                    sql.append(" where ");
                }
                sql.append(filter[0]);
                sql.append(" like :value");
            }
            if (sortExpression != null && sortDirection != null) {
                sql.append(" order by ");
                sql.append(sortExpression);
                sql.append(" " + (sortDirection.equals(Constant.ASC) ? "asc" : "desc"));
            }
            Query query = session.createQuery(sql.toString());
            if (property != null && property.size() > 0) {
                for (int l = 0; l < property.size(); l++) {
                    query.setParameter(param[l], value[l]);
                }
            }
            if (filter != null) {
                query.setString("value", filter[1] + "%");
            }
            if (offset != null && offset >= 0) {
                query.setFirstResult(offset);
            }
            if (limit != null && limit > 0) {
                query.setMaxResults(limit);
            }

            list = query.list();

            StringBuilder sql2 = new StringBuilder();
            sql2.append("select count(*) from ").append(getPerClassName());
            if (property != null && property.size() > 0) {
                for (int k = 0; k < param.length; k++) {
                    if (k == 0) {
                        sql2.append(" where ").append(param[k]).append("=:").append(param[k]);
                    } else {
                        sql2.append(" and ").append(param[k]).append("=:").append(param[k]);
                    }
                }
            }
            if (filter != null) {
                if (property != null && property.size() > 0) {
                    sql2.append(" and ");
                } else {
                    sql2.append(" where ");
                }
                sql2.append(filter[0]);
                sql2.append(" like :value");
            }
            Query query2 = session.createQuery(sql2.toString());
            if (property != null && property.size() > 0) {
                for (int l = 0; l < param.length; l++) {
                    query2.setParameter(param[l], value[l]);
                }
            }
            if (filter != null) {
                query2.setString("value", filter[1] + "%");
            }
            totalItems = query2.list().get(0);

            transaction.commit();
        } catch (HibernateException e) {
            // TODO: handle exception

            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            session.flush();
            session.close();
        }

        return new Object[]{totalItems, list};
    }

>>>>>>> develop

}
