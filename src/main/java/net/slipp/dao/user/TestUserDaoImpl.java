package net.slipp.dao.user;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import net.slipp.domain.user.User;

@Repository("testUserDao")
public class TestUserDaoImpl implements UserDao{
	HashMap<String, User> db = new HashMap<String, User>();

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

	@Override
	public Connection getConnection() throws SQLException,PropertyVetoException {
		return null;
	}

	@Override
	public int countUser() throws SQLException, PropertyVetoException {
		return 0;
	}

    @Override
    public void update(User user) throws SQLException, PropertyVetoException {
        db.put(user.getUserId(), user);
    }
}
