package net.slipp.dao.user;

import net.slipp.domain.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

public class UserDaoImpl implements UserDao{
	
	private JdbcTemplate jdbcTemplate;
	
	public UserDaoImpl(){}
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void insert(final User user) {
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql,user.getUserId(),user.getPassword(),user.getName(),user.getEmail());
	}
	
	@Override
    public void update(User user) {
        String sql = "UPDATE USERS SET name=?, email=? WHERE userId = ?";
        jdbcTemplate.update(sql,user.getName(),user.getEmail(),user.getUserId());
    }

	public User findByUserId(final String userId) {
		String sql = "SELECT userId, password, name, email FROM USERS WHERE userId=?";
		try{
			return jdbcTemplate.queryForObject(sql, new Object[]{userId}, ParameterizedBeanPropertyRowMapper.newInstance(User.class) );
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}

	public void deleteAllUser() {
		String sql = "DELETE FROM USERS";
		jdbcTemplate.update(sql);
	}

	@Override
	public int countUser() {
		String sql = "select count(*) FROM USERS";
		return jdbcTemplate.queryForInt(sql);
	}
}
