package jmxbook.ch4;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBSource implements DBSourceMBean {
	private DataSource ds = null;
	private boolean commit = false;
	
	static final Logger LOG = LoggerFactory.getLogger(DBSource.class);

	public DBSource(String JNDIName) {
		try {
			// looup data source using JNDI
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(JNDIName);
		} catch (Exception ex) {
			LOG.error("Failed to load JNDIName: " + JNDIName, ex);
		}
		
	}

	@Override
	public void resetDataSource(String name) {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(name);
		} catch (Exception ex) {
			LOG.error("Failed to load name: " + name, ex);
		}

	}

	@Override
	public void setAutoCommit(boolean commit) {
		this.commit = commit;

	}

	@Override
	public boolean getAutoCommit() {
		return commit;
	}

	@Override
	public Connection getConnection() {
		Connection con = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(commit);
			return con;
		} catch (Exception ex) {
			LOG.error("Failed to get connection", ex);
			con = null;
			return null;
		}
	}

}
