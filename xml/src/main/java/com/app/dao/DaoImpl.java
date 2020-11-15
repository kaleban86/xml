package com.app.dao;

import com.app.xml.CreateXmlFIle;
import com.app.xml.EntryProducer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoImpl implements Dao {

    private static final int BATCH_SIZE = 50;
    private EntryProducer entryProducer = new EntryProducer();
    private int n;
    private String connectionUrl;
    private String user;
    private String password;


    public DaoImpl(int n, String connectionUrl, String user, String password) {
        this.n = n;
        this.connectionUrl = connectionUrl;
        this.user = user;
        this.password = password;
    }

    public DaoImpl() {
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }


    @Override
    public void find() {


        StringBuilder content = new StringBuilder();
        try (Connection c = getConnection()) {
            String sql = "select * from test";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Integer> list = new ArrayList<>();


            content.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<entries>");
            while (rs.next()) {

                int value = rs.getInt("FIELD");

                String entry = entryProducer.produceItem(value);
                content.append(entry);

            }


            content.append("</entries>");

            CreateXmlFIle.createXml(content.toString());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    @Override
    public void clear() {

        try (Connection c = getConnection()) {
            c.prepareStatement("DELETE FROM test").execute();


        } catch (Exception ex) {
            throw new RuntimeException(ex);

        }


    }


    @Override
    public void create() {
        try (Connection c = getConnection()) {
            c.setAutoCommit(false);
            try (PreparedStatement pstmt = c.prepareStatement("INSERT INTO test (FIELD) VALUES (?)")) {
                for (int i = 1; i <= n; i++) {
                    pstmt.setInt(1, i);
                    pstmt.addBatch();
                    if (i % BATCH_SIZE == 0 || i == n) {
                        try {
                            int[] result = pstmt.executeBatch();
                            c.commit();
                        } catch (BatchUpdateException ex) {

                            c.rollback();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }




    private Connection getConnection() throws SQLException {


        return DriverManager.getConnection(connectionUrl, user, password);

    }

}
