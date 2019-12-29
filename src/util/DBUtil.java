package util;
  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import constant.TagXml;

public class DBUtil {
	
    private static String ip = TagXml.ip;
    private static int port = TagXml.port;
    private static String database = TagXml.database;
    private static String encoding = TagXml.encoding;
    private static String loginName = TagXml.loginName;
    private static String password = TagXml.password;
    
    //静态代码块,只执行一次
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String createDatabase = "create database if not exists " + database + " default charset = utf8;";
            String url = "jdbc:mysql://localhost:3306/";
            String createUserTable = 
            		"Create Table If Not Exists starbill.users(" + 
            		"  id int AUTO_INCREMENT," + 
            		"  account int," + 
            		"  password varchar(255)," + 
            		"  PRIMARY KEY (id)" + 
            		")DEFAULT CHARSET=utf8;";
            
            String createConfigTable = 
            		"Create Table If Not Exists starbill.config (" + 
            		"  id int AUTO_INCREMENT," + 
            		"  uid int," + 
            		"  key_ varchar(255) ," + 
            		"  value varchar(255) ," + 
            		"  PRIMARY KEY (id)," + 
            		"  CONSTRAINT `fk_config_users` FOREIGN KEY (`uid`) REFERENCES `users` (`id`)" + 
            		")DEFAULT CHARSET=utf8;";
            
            String createCostCategoryTable = 
            		"Create Table If Not Exists starbill.costCategory(" + 
            		"  id int AUTO_INCREMENT," + 
            		"  uid int," + 
            		"  name varchar(255)," + 
            		"  PRIMARY KEY (id)," + 
            		"  CONSTRAINT `fk_costCategory_users` FOREIGN KEY (`uid`) REFERENCES `users` (`id`)" + 
            		")DEFAULT CHARSET=utf8;";
            
            String createIncomeCategoryTable = 
            		"Create Table If Not Exists starbill.incomeCategory(" + 
            		"  id int AUTO_INCREMENT," + 
            		"  uid int," + 
            		"  name varchar(255)," + 
            		"  PRIMARY KEY (id)," + 
            		"  CONSTRAINT `fk_incomeCategory_users` FOREIGN KEY (`uid`) REFERENCES `users` (`id`)" + 
            		")DEFAULT CHARSET=utf8;";
            
            String createCostRecordTable = 
            		"Create Table If Not Exists starbill.costRecord(" + 
            		"  id int AUTO_INCREMENT," + 
            		"  uid int," + 
            		"  cid int," + 
            		"  cost double," + 
            		"  comment varchar(255)," + 
            		"  date Date," + 
            		"  PRIMARY KEY (id)," + 
            		"  CONSTRAINT `fk_costRecord_category` FOREIGN KEY (`cid`) REFERENCES `costCategory` (`id`)," + 
            		"  CONSTRAINT `fk_costRecord_users` FOREIGN KEY (`uid`) REFERENCES `users` (`id`)" + 
            		")DEFAULT CHARSET=utf8;";
            
            String createIncomeRecordTable = 
            		"Create Table If Not Exists starbill.incomeRecord (" + 
            		"  id int AUTO_INCREMENT," + 
            		"  uid int," + 
            		"  cid int," + 
            		"  income double," + 
            		"  comment varchar(255)," + 
            		"  date Date," + 
            		"  PRIMARY KEY (id)," + 
            		"  CONSTRAINT `fk_incomeRecord_category` FOREIGN KEY (`cid`) REFERENCES `incomeCategory` (`id`)," + 
            		"  CONSTRAINT `fk_incomeRecord_users` FOREIGN KEY (`uid`) REFERENCES `users` (`id`)" + 
            		")DEFAULT CHARSET=utf8;";
            
            String createCostPlanTable = 
            		"Create Table If Not Exists starbill.costPlan (" + 
            		"  id int AUTO_INCREMENT," + 
            		"  uid int," + 
            		"  cid int," + 
            		"  spend double," + 
            		"  comment varchar(255)," + 
            		"  date Date," + 
            		"  PRIMARY KEY (id)," + 
            		"  CONSTRAINT `fk_costPlan_costCategory` FOREIGN KEY (`cid`) REFERENCES `costCategory` (`id`)," + 
            		"  CONSTRAINT `fk_costPlan_users` FOREIGN KEY (`uid`) REFERENCES `users` (`id`)" + 
            		")DEFAULT CHARSET=utf8;";
            
            try {
				Connection conn = DriverManager.getConnection(url, loginName, password);
				Statement stat = conn.createStatement();
				stat.executeUpdate(createDatabase);
				stat.executeUpdate(createUserTable);
				stat.executeUpdate(createConfigTable);
				stat.executeUpdate(createCostCategoryTable);
				stat.executeUpdate(createIncomeCategoryTable);
				stat.executeUpdate(createCostRecordTable);
				stat.executeUpdate(createIncomeRecordTable);
				stat.executeUpdate(createCostPlanTable);
				stat.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getIp() {
		return ip;
	}

	public static int getPort() {
		return port;
	}

	public static String getDatabase() {
		return database;
	}

	public static String getEncoding() {
		return encoding;
	}

	public static String getLoginName() {
		return loginName;
	}

	public static String getPassword() {
		return password;
	}

	public static void setIp(String ip) {
		DBUtil.ip = ip;
	}

	public static void setPort(int port) {
		DBUtil.port = port;
	}

	public static void setDatabase(String database) {
		DBUtil.database = database;
	}

	public static void setEncoding(String encoding) {
		DBUtil.encoding = encoding;
	}

	public static void setLoginName(String loginName) {
		DBUtil.loginName = loginName;
	}

	public static void setPassword(String password) {
		DBUtil.password = password;
	}

	public static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
        return DriverManager.getConnection(url, loginName, password);
    }

}