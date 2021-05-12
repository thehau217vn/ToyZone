package com.toyZone.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AbtractDto<T> {
<<<<<<< HEAD
	protected Integer id;
	protected long[] ids;
	protected List<T> listResult = new ArrayList<>();
	private Date createdDate;
	protected Integer page;
	protected Integer maxPageItem;
	protected Integer totalPage;
	protected Integer totalItem;
	protected String sortName;
	protected String sortBy;
	protected String alert;
	protected String message;
	protected String type;
	protected Long totalPrices;
	protected Boolean validated;
	private Map<String, String> errorMessages;
	
	
	public Long getTotalPrices() {
		return totalPrices;
	}
	public void setTotalPrices(Long totalPrices) {
		this.totalPrices = totalPrices;
	}
	public Boolean getValidated() {
		return validated;
	}
	public void setValidated(Boolean validated) {
		this.validated = validated;
	}
	public Map<String, String> getErrorMessages() {
		return errorMessages;
	}
	public void setErrorMessages(Map<String, String> errorMessages) {
		this.errorMessages = errorMessages;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public long[] getIds() {
		return ids;
	}
	public void setIds(long[] ids) {
		this.ids = ids;
	}
	public List<T> getListResult() {
		return listResult;
	}
	public void setListResult(List<T> listResult) {
		this.listResult = listResult;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getMaxPageItem() {
		return maxPageItem;
	}
	public void setMaxPageItem(Integer maxPageItem) {
		this.maxPageItem = maxPageItem;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getTotalItem() {
		return totalItem;
	}
	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public void customPage(int page,int maxPageItem,int totalitem,List<T> list) {
		setPage(page);
		setMaxPageItem(maxPageItem);
		setTotalItem(totalitem);
		setListResult(list);
		setTotalPage((int) Math.ceil((double) getTotalItem() / getMaxPageItem()));
	}
	
=======
    protected Integer id;
    protected long[] ids;
    protected List<T> listResult = new ArrayList<>();
    private Date createdDate;
    protected Integer page;
    protected Integer maxPageItem;
    protected Integer totalPage;
    protected Integer totalItem;
    protected String sortName;
    protected String sortBy;
    protected String alert;
    protected String message;
    protected String type;
    protected Long totalPrices;
    protected Boolean validated;
    private Map<String, String> errorMessages;


    public Long getTotalPrices() {
        return totalPrices;
    }

    public void setTotalPrices(Long totalPrices) {
        this.totalPrices = totalPrices;
    }

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }

    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long[] getIds() {
        return ids;
    }

    public void setIds(long[] ids) {
        this.ids = ids;
    }

    public List<T> getListResult() {
        return listResult;
    }

    public void setListResult(List<T> listResult) {
        this.listResult = listResult;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getMaxPageItem() {
        return maxPageItem;
    }

    public void setMaxPageItem(Integer maxPageItem) {
        this.maxPageItem = maxPageItem;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void customPage(int page, int maxPageItem, int totalitem, List<T> list) {
        setPage(page);
        setMaxPageItem(maxPageItem);
        setTotalItem(totalitem);
        setListResult(list);
        setTotalPage((int) Math.ceil((double) getTotalItem() / getMaxPageItem()));
    }

>>>>>>> develop
}
