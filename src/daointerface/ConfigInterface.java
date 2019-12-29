package daointerface;

import java.util.List;
import entity.Config;

public interface ConfigInterface {
	public int getTotal();
	public void add(Config config);
	public void update(Config config);
	public void delete(int id);
	public Config getById(int id);
	public List<Config> list();
	public List<Config> list(int start, int count);
	public Config getByKey(String key);
	
}
