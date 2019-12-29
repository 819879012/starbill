package service;
 
import java.util.List;

import dao.ConfigDAO;
import entity.Config;

public class ConfigService {
	
    public static final String BUDGET = "BUDGET";
    public static final String MYSQLPATH = "MYSQLPATH";
    public static final String DEFAULT_BUDGET = "500";
    private static ConfigDAO dao= new ConfigDAO();

    static{
        init();
    }

    public static void init(){
        init(BUDGET, DEFAULT_BUDGET,1);
        init(MYSQLPATH, "",2);
    }

    private static void init(String key, String value,int order) {
    	int uid = UsersService.getNowUid();
    	Config nowUsersConfig = dao.getById(uid*2-1);
    	Config nowUsersConfigNext = dao.getById(uid*2);
    	if( nowUsersConfig == null && order == 1)
    	{
          Config c = new Config();
          c.setUid(UsersService.getNowUid());
          c.setKey(key);
          c.setValue(value);
          dao.add(c);
    	}
    	if( nowUsersConfigNext == null && order == 2)
    	{
          Config c = new Config();
          c.setUid(UsersService.getNowUid());
          c.setKey(key);
          c.setValue(value);
          dao.add(c);
    	}
    }

    public String getByKey(String key) {
        Config config= dao.getByKey(key);
        if( config != null )
        	return config.getValue();
        return null;
    }

    public void update(String key, String value,int order){
    	int uid = UsersService.getNowUid();
    	if(order == 1)
    	{
    		Config nowUsersConfig = dao.getById(uid*2-1);
    		nowUsersConfig.setValue(value);
    		dao.update(nowUsersConfig);
    	}
    	else
    	{
    		Config nowUsersConfigNext = dao.getById(uid*2);
    		nowUsersConfigNext.setValue(value);
    		dao.update(nowUsersConfigNext);
    	} 
    }

    public double getDoubleBudget() {
    	if( getByKey(BUDGET) != null )
    		return Double.parseDouble(getByKey(BUDGET));
    	return 0;
    }

    public String getNowUsersBudget()
    {
    	int uid = UsersService.getNowUid();
    	List<Config> list = dao.list();
    	for (Config config : list) {
			if( config.getUid() == uid && config.getKey().equals(BUDGET) )
				return config.getValue();
		}
    	return "";
    }

    public String getNowUsersMysqlPath()
    {
    	int uid = UsersService.getNowUid();
    	List<Config> list = dao.list();
    	for (Config config : list) {
			if( config.getUid() == uid && config.getKey().equals(MYSQLPATH) )
				return config.getValue();
		}
    	return "";
    }

}