package com.sk.mbc.business.repository.dao;

import com.sk.mbc.business.domain.Info;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class DAO {

    /*
    * DAO(Data Access Object - 데이터 접근 객체) 데이터베이스에 관련된 작업을 전문적으로 담당하는 객체이다.
    */
    private Connection con;
    private Statement stmt;
    private ResultSet rs;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/test_db";
    private static final String USER = "root";
    private static final String PW = "admin1214";

    public DAO() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void select1() {
        String sql = "select * from customers";
        try {
            con = DriverManager.getConnection(URL, USER, PW);
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) { System.out.print(rs.getString("id") + " ");
                System.out.print(rs.getString("pass") + " ");
                System.out.print(rs.getString("name") + " ");
                System.out.print(rs.getString("phone") + " ");
                System.out.println(rs.getString("email") + " ");
            } } catch (SQLException e) { e.printStackTrace();
        } finally {
            close(con, stmt, rs);
        }
    }

    public ArrayList<Info> select2() {
        ArrayList<Info> list = new ArrayList<Info>();
        String sql = "select * from customers";
        try {
            con = DriverManager.getConnection(URL, USER, PW);
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Info info = new Info();
                info.setId(rs.getString("id"));
                info.setPass(rs.getString("pass"));
                info.setName(rs.getString("name"));
                info.setPhone(rs.getString("phone"));
                info.setEmail(rs.getString("email"));
                list.add(info);
            } return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, stmt, rs);
        } return null;
    }

    public void close(Connection con, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            }
            catch (SQLException e) {

            }
        } if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {

            }
        }
        if (con != null) {
            try { con.close();
            }
            catch (SQLException e) {

            }
        }
    }
}


