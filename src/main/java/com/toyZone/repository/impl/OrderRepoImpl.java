package com.toyZone.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.internal.SessionImpl;
import org.springframework.stereotype.Repository;

import com.toyZone.model.Order;
import com.toyZone.repository.OrderRepo;

@Repository
public class OrderRepoImpl extends AbstractRepoImpl<Integer, Order> implements OrderRepo {

    @Override
    public List<String[]> thongKeBanHang(String tuNgay, String denNgay) {
        // TODO Auto-generated method stub
        List<String[]> resuls = new ArrayList<String[]>();
        SessionImpl sessionImpl = (SessionImpl) sessionFactory.openSession();
        Connection conn = sessionImpl.connection();
        ResultSet resultSet = null;
        PreparedStatement pre = null;
        try {
            pre = conn.prepareStatement("EXEC SP_THONG_KE ?,?");
            pre.setString(1, tuNgay);
            pre.setString(2, denNgay);
            resultSet = pre.executeQuery();
            while (resultSet.next()) {
                String[] row = new String[6];
                row[0] = resultSet.getString(1);
                row[1] = resultSet.getString(2);
                row[2] = resultSet.getString(3);
                row[3] = resultSet.getString(4);
                row[4] = resultSet.getString(5);
                row[5] = resultSet.getString(6);
                resuls.add(row);
            }
            pre.close();
            resultSet.close();
            conn.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return resuls;
    }

}
