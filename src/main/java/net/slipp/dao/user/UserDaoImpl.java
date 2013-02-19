package net.slipp.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import net.slipp.domain.user.User;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class UserDaoImpl implements UserDao{

	private JdbcTemplate jdbctemplate;
	
	public UserDaoImpl(){}
	
	@Override
	public void setDataSource(DataSource dataSource) {
		jdbctemplate = new JdbcTemplate(dataSource);
	}
	
	public void insert(final User user) {
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		jdbctemplate.update(sql,user.getUserId(),user.getPassword(),user.getName(),user.getEmail());
	}
	
	@Override
    public void update(User user) {
        String sql = "UPDATE USERS SET name=?, email=? WHERE userId = ?";
        jdbctemplate.update(sql,user.getName(),user.getEmail(),user.getUserId());
    }

	public User findByUserId(final String userId) {
		String sql = "SELECT userId, password, name, email FROM USERS WHERE userId=?";
		try{
			return jdbctemplate.queryForObject(sql, new Object[]{userId}, new RowMapper<User>() {
				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
				}
			});
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}

	public void deleteAllUser() {
		String sql = "DELETE FROM USERS";
		jdbctemplate.update(sql);
	}

	@Override
	public int countUser() {
		String sql = "select count(*) FROM USERS";
		return jdbctemplate.queryForInt(sql);
	}
}
