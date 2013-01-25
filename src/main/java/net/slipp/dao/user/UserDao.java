package net.slipp.dao.user;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import net.slipp.domain.user.User;

public interface UserDao {
	public void insert(User user) throws SQLException, PropertyVetoException;
	public User findByUserId(String userId) throws SQLException, PropertyVetoException;
	public void deleteAllUser() throws SQLException, PropertyVetoException;
	public Connection getConnection() throws SQLException, PropertyVetoException;
	public int countUser() throws SQLException, PropertyVetoException;
    public void update(User user) throws SQLException, PropertyVetoException;
}
