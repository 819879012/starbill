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
import entity.CostRecord;
import util.DBUtil;
import util.DateUtil;
  
public class CostRecordDAO implements RecordInteface{
  
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from costRecord ";
  
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
  
    public void addCostRecord(CostRecord record) {
  
    	//1.id 2.uid 3.cid 4.cost 5.comment 6.date
    	String closeCheck =  "SET foreign_key_checks = 0";
        String sql = "insert into costRecord values(null,?,?,?,?,?)";
        String startCheck =  " SET foreign_key_checks = 1";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, record.getUid());
            ps.setInt(2, record.getCid());
            ps.setDouble(3, record.getCost());
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
  
    public void update(CostRecord record) {
  
        String sql = "update costRecord set cost= ?, cid= ?, comment =?, date = ? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
        	ps.setDouble(1, record.getCost());
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
            String sql = "delete from costRecord where id = " + id;
            String startCheck =  " SET foreign_key_checks = 1";
            
            s.execute(closeCheck);
            s.execute(sql);
            s.execute(startCheck);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }

    public CostRecord getById(int id) {
    	CostRecord record = null;
  
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select * from costRecord where id = " + id;
  
            ResultSet rs = s.executeQuery(sql);
  
            if (rs.next()) {
                record = new CostRecord();
                int uid = rs.getInt("uid");
                int cid = rs.getInt("cid");
                double cost = rs.getDouble("cost");
                String comment = rs.getString("comment");
                Date date = rs.getDate("date");
                
                record.setId(id);
                record.setUid(uid);
                record.setCid(cid);
                record.setCost(cost);
                record.setComment(comment);
                record.setDate(date);
                
            }
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return record;
    }
  
    public List<CostRecord> list() {
        return list(0, Integer.MAX_VALUE);
    }
    
    public List<CostRecord> list(int start, int count) {
        List<CostRecord> records = new ArrayList<CostRecord>();
  
        String sql = "select * from costRecord order by id desc limit ?,? ";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setInt(1, start);
            ps.setInt(2, count);
 
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
            	CostRecord record = new CostRecord();
                int id = rs.getInt("id");
                int uid = rs.getInt("uid");
                int cid = rs.getInt("cid");
                double cost = rs.getDouble("cost");
                String comment = rs.getString("comment");
                Date date = rs.getDate("date");
                
                record.setId(id);
                record.setUid(uid);
                record.setCid(cid);
                record.setCost(cost);
                record.setComment(comment);
                record.setDate(date);
                
                records.add(record);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return records;
    }
    
    public List<CostRecord> list(int cid) {
        List<CostRecord> records = new ArrayList<CostRecord>();
  
        String sql = "select * from costRecord where cid = ?";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setInt(1, cid);
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
            	CostRecord record = new CostRecord();
                int id = rs.getInt("id");
                int uid = rs.getInt("uid");
                double cost = rs.getDouble("cost");
                String comment = rs.getString("comment");
                Date date = rs.getDate("date");
                  
                record.setId(id);
                record.setUid(uid);
                record.setCid(cid);
                record.setCost(cost);
                record.setComment(comment);
                record.setDate(date);
                
                records.add(record);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return records;
    }
    
    public List<CostRecord> listToday(){
        return list(DateUtil.today());
    }
    
    public List<CostRecord> list(Date day) {
        List<CostRecord> records = new ArrayList<CostRecord>();
        String sql = "select * from costRecord where date =?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setDate(1, DateUtil.util2sql(day));
 
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	CostRecord record = new CostRecord();
                int id = rs.getInt("id");
                int uid = rs.getInt("uid");
                int cid = rs.getInt("cid");
                double cost = rs.getDouble("cost");
                String comment = rs.getString("comment");
                Date date = rs.getDate("date");
                  
                record.setId(id);
                record.setUid(uid);
                record.setCid(cid);
                record.setCost(cost);
                record.setComment(comment);
                record.setDate(date);
                
                 records.add(record);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return records;
    }           

    public List<CostRecord> listThisMonth(){
        return list(DateUtil.monthBegin(),DateUtil.monthEnd());
    }

    public List<CostRecord> list(Date start, Date end) {
        List<CostRecord> records = new ArrayList<CostRecord>();
        String sql = "select * from costRecord where date >=? and date <= ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setDate(1, DateUtil.util2sql(start));
            ps.setDate(2, DateUtil.util2sql(end));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	CostRecord record = new CostRecord();
                int id = rs.getInt("id");
                int uid = rs.getInt("uid");
                int cid = rs.getInt("cid");
                double cost = rs.getDouble("cost");
                String comment = rs.getString("comment");
                Date date = rs.getDate("date");
                
                record.setId(id);
                record.setUid(uid);
                record.setCid(cid);
                record.setCost(cost);
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