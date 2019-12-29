package dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import daointerface.RecordInteface;
import entity.IncomeRecord;
import util.DBUtil;
import util.DateUtil;
  
public class IncomeRecordDAO implements RecordInteface{
  
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from incomeRecord ";
  
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
  
    public void addIncomeRecord(IncomeRecord record) {
  
    	//1.id 2.uid 3.cid 4.income 5.comment 6.date
    	String closeCheck =  "SET foreign_key_checks = 0";
        String sql = "insert into incomeRecord values(null,?,?,?,?,?)";
        String startCheck =  "SET foreign_key_checks = 1";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, record.getUid());
            ps.setInt(2, record.getCid());
            ps.setDouble(3, record.getIncome());
            ps.setString(4, record.getComment());
            ps.setDate(5, DateUtil.util2sql(record.getDate()));
  
            ps.execute(closeCheck);
            ps.execute();
            ps.execute(startCheck);
  
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                record.setId(id);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }
  
    public void update(IncomeRecord record) {
  
        String sql = "update incomeRecord set income= ?, cid= ?, comment =?, date = ? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
        	ps.setDouble(1, record.getIncome());
            ps.setInt(2, record.getCid());
            ps.setString(3, record.getComment());
            ps.setDate(4, DateUtil.util2sql(record.getDate()));
            ps.setInt(5, record.getId());
            
            ps.execute();
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
  
    }
  
    public void delete(int id) {
  
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
        	
        	String closeCheck =  "SET foreign_key_checks = 0";
            String sql = "delete from incomeRecord where id = " + id;
            String startCheck =  "SET foreign_key_checks = 1";
  
            s.execute(closeCheck);
            s.execute(sql);
            s.execute(startCheck);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }

    public IncomeRecord getById(int id) {
    	IncomeRecord record = null;
  
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select * from incomeRecord where id = " + id;
  
            ResultSet rs = s.executeQuery(sql);
  
            if (rs.next()) {
                record = new IncomeRecord();
                int uid = rs.getInt("uid");
                int cid = rs.getInt("cid");
                double income = rs.getDouble("income");
                String comment = rs.getString("comment");
                Date date = rs.getDate("date");
                
                record.setId(id);
                record.setUid(uid);
                record.setCid(cid);
                record.setIncome(income);
                record.setComment(comment);
                record.setDate(date);
                
            }
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return record;
    }
  
    public List<IncomeRecord> list() {
        return list(0, Integer.MAX_VALUE);
    }
    
    public List<IncomeRecord> list(int start, int count) {
        List<IncomeRecord> records = new ArrayList<IncomeRecord>();
  
        String sql = "select * from incomeRecord order by id desc limit ?,? ";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setInt(1, start);
            ps.setInt(2, count);
 
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
            	IncomeRecord record = new IncomeRecord();
                int id = rs.getInt("id");
                int uid = rs.getInt("uid");
                int cid = rs.getInt("cid");
                double income = rs.getDouble("income");
                String comment = rs.getString("comment");
                Date date = rs.getDate("date");
                
                record.setId(id);
                record.setUid(uid);
                record.setCid(cid);
                record.setIncome(income);
                record.setComment(comment);
                record.setDate(date);
                
                records.add(record);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return records;
    }
    
    public List<IncomeRecord> list(int cid) {
        List<IncomeRecord> records = new ArrayList<IncomeRecord>();
  
        String sql = "select * from incomeRecord where cid = ?";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setInt(1, cid);
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
            	IncomeRecord record = new IncomeRecord();
                int id = rs.getInt("id");
                int uid = rs.getInt("uid");
                double income = rs.getDouble("income");
                String comment = rs.getString("comment");
                Date date = rs.getDate("date");
                
                record.setId(id);
                record.setUid(uid);
                record.setCid(cid);
                record.setIncome(income);
                record.setComment(comment);
                record.setDate(date);
                
                records.add(record);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return records;
    }
    
    public List<IncomeRecord> listToday(){
        return list(DateUtil.today());
    }
    
    public List<IncomeRecord> list(Date day) {
        List<IncomeRecord> records = new ArrayList<IncomeRecord>();
        String sql = "select * from incomeRecord where date =?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setDate(1, DateUtil.util2sql(day));
 
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	IncomeRecord record = new IncomeRecord();
                int id = rs.getInt("id");
                int uid = rs.getInt("uid");
                int cid = rs.getInt("cid");
                double income = rs.getDouble("income");
                String comment = rs.getString("comment");
                Date date = rs.getDate("date");
                  
                record.setId(id);
                record.setUid(uid);
                record.setCid(cid);
                record.setIncome(income);
                record.setComment(comment);
                record.setDate(date);
                
                 records.add(record);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return records;
    }           

    public List<IncomeRecord> listThisMonth(){
        return list(DateUtil.monthBegin(),DateUtil.monthEnd());
    }

    public List<IncomeRecord> list(Date start, Date end) {
        List<IncomeRecord> records = new ArrayList<IncomeRecord>();
        String sql = "select * from incomeRecord where date >=? and date <= ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setDate(1, DateUtil.util2sql(start));
            ps.setDate(2, DateUtil.util2sql(end));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	IncomeRecord record = new IncomeRecord();
                int id = rs.getInt("id");
                int uid = rs.getInt("uid");
                int cid = rs.getInt("cid");
                double income = rs.getDouble("income");
                String comment = rs.getString("comment");
                Date date = rs.getDate("date");
                
                record.setId(id);
                record.setUid(uid);
                record.setCid(cid);
                record.setIncome(income);
                record.setComment(comment);
                record.setDate(date);
                
                records.add(record);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return records;
    }       

}