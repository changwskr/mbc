package com.sk.mba.business.repository;

import com.sk.mba.business.domain.Member;
import com.sk.mba.business.repository.IMemberRepository;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements IMemberRepository {

    private final DataSource dataSource;

    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Member save(Member member) {
        String sql = "insert into member(name) values(?)";
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // id 키값 생성 RETURN_GENERATED_KEYS
            psmt.setString(1, member.getName());
            psmt.executeUpdate();
            rs = psmt.getGeneratedKeys();  // 위에서 Statement.RETURN_GENERATED_KEYS 이것이 선언되었기 때문에 가능하다.

            if( rs.next() ){
                member.setId( rs.getLong(1) );
            }
            else{
                throw new SQLException("id 조회 실패");
            }
            return member;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new IllegalStateException(throwables);
        } finally {
            close(conn, psmt, rs);
        }

    }

    @Override
    public Optional<Member> findById(Long id) {
        String sql = "select * from member where id = ?";
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setLong(1, id);
            rs = psmt.executeQuery();

            if( rs.next() ){
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                member.setJuso(rs.getString("juso"));
                return Optional.of(member); // 혹시 null이면 안된다.
            }
            else{
                return Optional.empty();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new IllegalStateException(throwables);
        } finally {
            close(conn, psmt, rs);
        }
    }

    @Override
    public Optional<Member> findByName(String name) {
        String sql = "select * from member where name = ?";
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;



        try {
            conn = dataSource.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, name);

            rs = psmt.executeQuery();

            if( rs.next() ){
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                //member.setJuso(rs.getString("juso"));
                return Optional.of(member); // 혹시 null이면 안된다.
            }
            else{
                return Optional.empty();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new IllegalStateException(throwables);
        } catch (NullPointerException ex){
            System.out.println("############## 회원이 없으니 등록하자 #############"  );
            throw ex;
        } finally {
            close(conn, psmt, rs);
        }
    }

    @Override
    public List<Member> findAll() {
        String sql = "select id,name from member";
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<Member> list = new ArrayList<>();

        try {
            conn = dataSource.getConnection();
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            while ( rs.next() ){
                Member member = new Member();
                System.out.println("id:" + rs.getLong("id"));
                System.out.println("name:" + rs.getString("name"));

                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                //member.setJuso(rs.getString("juso"));

                list.add(member);
            }
            return list;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new IllegalStateException(throwables);
        } finally {
            close(conn, psmt, rs);
        }
    }

////    @Override
////    public void clear() {
//
//    }

//    @Override
//    public Long remove(String name) {
//        Optional<Member> member = findByName(name);
//        member.get().getName();
//        Long idv = member.get().getId();
//        System.out.println(member.get().getName() + " " + member.get().getId());
//        //////////////////
//        /////////////////
//        return idv;
//    }



    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return;
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource); // 이렇게 하면 sping을 통해서 db connection을 가지고 올수 있다.
    }
}
