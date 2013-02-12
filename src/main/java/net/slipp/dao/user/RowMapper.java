package net.slipp.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
	T setResultSet(ResultSet rs) throws SQLException;
}
