package dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import daointerface.CategoryInterface;
import entity.IncomeCategory;
import util.DBUtil;

public class IncomeCategoryDAO implements CategoryInterface{
 
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
 
            String sql = "select count(*) from incomeCategory";
 
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
 
            System.out.println("total:" + total);
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return total;
    }
 
    public void add(IncomeCategory category) {
 
    	String closeCheck =  "SET foreign_key_checks = 0";
    	String sql = "insert into incomeCategory values(null,?,?)";
        String startCheck =  " SET foreign_key_checks = 1";
        
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
        	ps.setInt(1, category.getUid());
            ps.setString(2, category.getName());
 
            ps.execute(closeCheck);
            ps.execute();
            ps.execute(startCheck);
 
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                category.setId(id);
            }
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
    }

    public void update(IncomeCategory category) {
 
        String sql = "update incomeCategory set name= ? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setString(1, category.getName());
            ps.setInt(2, category.getId());
 
            ps.execute();
        } catch (SQLException e) {
 
            e.printStackTrace();
        }

    }

    public void delete(int id) {
 
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
 
        	String closeCheck =  "SET foreign_key_checks = 0";
            String sql = "delete from incomeCategory where id = " + id;
            String startCheck =  "SET foreign_key_checks = 1";
            
            s.execute(closeCheck);
            s.execute(sql);
            s.execute(startCheck);
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
    }
 
    public IncomeCategory getById(int id) {
    	IncomeCategory category = null;
 
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
 
            String sql = "select * from incomeCategory where id = " + id;
 
            ResultSet rs = s.executeQuery(sql);
 
            if (rs.next()) {
                category = new IncomeCategory();
                String name = rs.getString(3);
                int uid = rs.getInt(2);
                category.setName(name);
                category.setId(id);
                category.setUid(uid);
            }
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return category;
    }
 
    public List<IncomeCategory> list() {
        return list(0, Integer.MAX_VALUE);
    }

    public List<IncomeCategory> list(int start, int count) {
        List<IncomeCategory> categorys = new ArrayList<IncomeCategory>();
 
        String sql = "select * from incomeCategory order by id desc limit ?,? ";
 
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setInt(1, start);
            ps.setInt(2, count);
 
            ResultSet rs = ps.executeQuery();
 
            while (rs.next()) {
            	IncomeCategory category = new IncomeCategory();
                int id = rs.getInt(1);
                int uid = rs.getInt(2);
                String name = rs.getString(3);
                category.setId(id);
                category.setName(name);
                category.setUid(uid);
                categorys.add(category);
            }
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
//        System.out.println(categorys.size());
        return categorys;
    }

}