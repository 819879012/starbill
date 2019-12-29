package dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import daointerface.UsersInterface;
import entity.Users;
import util.DBUtil;

/*
 * User Dao
 * TODO: change the configRecord to an record into database
 * add()  delete()  update()  get() getByAccount() getById()
 * it do not close the resource because use try-with-source struct
 * @Time: 2019/11/24
*/

public class UsersDAO implements UsersInterface{

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
 
            String sql = "select count(*) from users";
 
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
 
//            System.out.println("total:" + total);

        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return total;
    }

    public void add(Users user) {
 
        String sql = "insert into users values(null,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
        	ps.setInt(1, user.getAccount());
            ps.setString(2, user.getPassword());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                user.setId(id);
            }
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
    }

    public void update(Users user) {
 
        String sql = "update users set account= ?, password=? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setInt(1, user.getAccount());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getId());
 
            ps.execute();
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
 
    }
 
    public void delete(int id) {
 
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
 
            String closeCheck =  "SET foreign_key_checks = 0";
            String sql =  "delete from users where id = " + id ;
            String startCheck =  " SET foreign_key_checks = 1";
            
            s.execute(closeCheck);
            s.execute(sql);
            s.execute(startCheck);
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
    }

    public Users get(int id) {
    	Users user = null;

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
 
            String sql = "select * from users where id = " + id;
 
            ResultSet rs = s.executeQuery(sql);
 
            if (rs.next()) {
            	user = new Users();
                int account = rs.getInt("account");
                String password = rs.getString("password");
                user.setAccount(account);
                user.setPassword(password);
                user.setId(id);
            }
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return user;
    }
 
    public List<Users> list() {
        return list(0, Integer.MAX_VALUE);
    }
 
    public List<Users> list(int start, int count) {
        List<Users> users = new ArrayList<Users>();
 
        String sql = "select * from users order by id desc limit ?,? ";
 
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setInt(1, start);
            ps.setInt(2, count);
 
            ResultSet rs = ps.executeQuery();
 
            while (rs.next()) {
            	Users user = new Users();
                int id = rs.getInt(1);
                int account = rs.getInt(2);
                String password = rs.getString("password");
                user.setId(id);
                user.setAccount(account);
                user.setPassword(password);
                users.add(user);
            }
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return users;
    }

    public Users getByAccount(int account) {
    	Users user = null;
        String sql = "select * from users where account = ?" ;
        try (Connection c = DBUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql);) 
        {
            ps.setInt(1, account);
            ResultSet rs =ps.executeQuery();
 
            if (rs.next()) {
            	user = new Users();
                int id = rs.getInt("id");
                String password = rs.getString("password");
                user.setAccount(account);
                user.setPassword(password);
                user.setId(id);
            }
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return user;
    }
    
}