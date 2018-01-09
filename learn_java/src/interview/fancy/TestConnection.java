package interview.fancy;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {

	static {
		TestConnection.class.getClassLoader().getResourceAsStream("");
	}

	public static void main(String[] args) throws Exception {

	}

	public Connection getConnection() {
		Connection connection = null;
		// 注册驱动
		try {
			Class.forName("");

			// 获得连接
			connection = DriverManager.getConnection("url",	"username", "password");
		} catch (ClassNotFoundException cnfException) {
			cnfException.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public Properties getProperties() {
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("");
		Properties properties = new Properties();
		try {
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
}
