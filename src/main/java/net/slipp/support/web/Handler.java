package net.slipp.support.web;

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.naming.ConfigurationException;
import javax.servlet.http.HttpServletRequest;

public interface Handler {

	public String excute(HttpServletRequest request) throws FileNotFoundException, ConfigurationException, SQLException, PropertyVetoException;
}
