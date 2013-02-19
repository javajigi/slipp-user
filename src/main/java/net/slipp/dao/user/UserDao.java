package net.slipp.dao.user;

import javax.sql.DataSource;

import net.slipp.domain.user.User;

public interface UserDao {
	public void setDataSource(DataSource dataSource);
	public void insert(User user);
	public User findByUserId(String userId);
	public void deleteAllUser();
	public int countUser();
    public void update(User user);
}
