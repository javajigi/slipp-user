package net.slipp.dao.user;

import java.sql.SQLException;
import java.util.HashMap;

import net.slipp.domain.user.User;
import net.slipp.support.jdbc.ConnectionManager;

public class TestUserDao extends UserDao{
	HashMap<String, User> db = new HashMap<String, User>();
	
	public TestUserDao() {
		super(null);
	}
	
	public TestUserDao(ConnectionManager connectionManager) {
		super(connectionManager);
		// TODO Auto-generated constructor stub
	}	
	
	@Override
	public void insert(User user) throws SQLException {
		db.put(user.getUserId(), user);
	}

	@Override
	public User findByUserId(String userId) throws SQLException {
		return db.get(userId);
	}

	@Override
	public void deleteAllUser() throws SQLException {
		db.clear();
	}
}
