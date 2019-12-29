package dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import daointerface.ConfigInterface;
import entity.Config;
import util.DBUtil;

/*
 * ConfigDao
 * TODO: change the configRecord to an record into database
 * add()  delete()  update()  get()
 * it do not close the resource because use try-with-source struct
 * @Time: 2019/11/24
*/

public class ConfigDAO implements ConfigInterface{

    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
 
            String sql = "select count(*) from config";
 
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

    public void add(Config config) {
 
        String sql = "insert into config values(null,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
        	ps.setInt(1, config.getUid());
            ps.setString(2, config.getKey());
            ps.setString(3, config.getValue());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                config.setId(id);
            }
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
    }

    public void update(Config config) {
 
        String sql = "update config set key_= ?, value=? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setString(1, config.getKey());
            ps.setString(2, config.getValue());
            ps.setInt(3, config.getId());
 
            ps.execute();
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
 
    }

    public void delete(int id) {
 
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String closeCheck =  "SET foreign_key_checks = 0";
            String sql = "delete from config where id = " + id;
            String startCheck =  " SET foreign_key_checks = 1";
            
            s.execute(closeCheck);
            s.execute(sql);
            s.execute(startCheck);
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
    }

    public Config getById(int id) {
        Config config = null;
 
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
 
            String sql = "select * from config where id = " + id;
 
            ResultSet rs = s.executeQuery(sql);
 
            if (rs.next()) {
                config = new Config();
                int uid = rs.getInt("uid");
                String key = rs.getString("key_");
                String value = rs.getString("value");
                config.setUid(uid);
                config.setKey(key);
                config.setValue(value);
                config.setId(id);
            }
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return config;
    }
 
    public List<Config> list() {
        return list(0, Integer.MAX_VALUE);
    }

    public List<Config> list(int start, int count) {
        List<Config> configs = new ArrayList<Config>();
 
        String sql = "select * from config order by id desc limit ?,? ";
 
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setInt(1, start);
            ps.setInt(2, count);
 
            ResultSet rs = ps.executeQuery();
 
            while (rs.next()) {
                Config config = new Config();
                int id = rs.getInt(1);
                int uid = rs.getInt(2);
                String key = rs.getString("key_");
                String value = rs.getString("value");
                config.setId(id);
                config.setUid(uid);
                config.setKey(key);
                config.setValue(value);
                configs.add(config);
            }
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return configs;
    }

    public Config getByKey(String key) {
        Config config = null;
        String sql = "select * from config where key_ = ?" ;
        try (Connection c = DBUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql);) 
        {
            ps.setString(1, key);
            ResultSet rs =ps.executeQuery();
 
            if (rs.next()) {
                config = new Config();
                int id = rs.getInt("id");
                int uid = rs.getInt("uid");
                String value = rs.getString("value");
                config.setUid(uid);
                config.setKey(key);
                config.setValue(value);
                config.setId(id);
            }
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return config;
    }
 
}