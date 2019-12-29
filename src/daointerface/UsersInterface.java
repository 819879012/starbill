package daointerface;

import java.util.List;

import entity.Users;

public interface UsersInterface {
	public int getTotal();
	public void add(Users user);
	public void update(Users user);
	public void delete(int id);
	public Users get(int id);
	public List<Users> list();
	public List<Users> list(int start, int count);
	public Users getByAccount(int account);
	
}
