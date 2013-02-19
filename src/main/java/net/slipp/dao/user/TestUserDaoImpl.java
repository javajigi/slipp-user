package net.slipp.dao.user;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import net.slipp.domain.user.User;

@Repository
public class TestUserDaoImpl implements UserDao{
	HashMap<String, User> db = new HashMap<String, User>();

	@Override
	public void insert(User user) {
		db.put(user.getUserId(), user);
	}

	@Override
	public User findByUserId(String userId) {
		return db.get(userId);
	}

	@Override
	public void deleteAllUser() {
		db.clear();
	}

	@Override
	public int countUser() {
		return 0;
	}

    @Override
    public void update(User user) {
        db.put(user.getUserId(), user);
    }

	@Override
	public void setDataSource(DataSource dataSource) {
	}
}
