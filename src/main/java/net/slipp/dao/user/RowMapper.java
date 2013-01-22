package net.slipp.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.slipp.domain.user.User;

public interface RowMapper {
	User setResultSet(ResultSet rs) throws SQLException;
}
