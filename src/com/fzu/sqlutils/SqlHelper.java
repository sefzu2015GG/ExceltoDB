package com.fzu.sqlutils;

import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SqlHelper {

	private static Connection connection = null;
	private static PreparedStatement preparedStatement = null;
	// private static ResultSet;
	private static ResultSet resultSet = null;
	private static CallableStatement callableStatement = null;
	private static String driver = "";
	private static String url = "";
	private static String uerName = "";
	private static String passWord = "";

	private static Properties properties = null;
	private static FileInputStream fileInputStream = null;

	public static Connection getCon() {

		return connection;
	}

	public static PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}

	public static ResultSet getResultSet() {
		return resultSet;
	}

	public static CallableStatement getCallableStatement() {
		return callableStatement;
	}

	/**
	 * 静态初始化加载配置文件
	 */
	static {

		try {
			properties = new Properties();
			fileInputStream = new FileInputStream("file.properties");
			properties.load(fileInputStream);
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			uerName = properties.getProperty("username");
			passWord = properties.getProperty("password");

			Class.forName(driver);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			fileInputStream = null;
		}

	}

	public static Connection getConnection() {
		try {
			connection = DriverManager.getConnection(url, uerName, passWord);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return connection;

	}

	/**
	 * 处理多条sql语句
	 * 
	 * @param sql
	 * @param parmameters
	 */
	public static void excuteUpdateMultiParams(String[] sql,
			Object[][] parmameters) {
		try {
			connection = getConnection();
			connection.setAutoCommit(false);

			for (int i = 0; i < sql.length; i++) {
				preparedStatement = connection.prepareStatement(sql[i]);
				for (int j = 0; j < parmameters[i].length; j++) {
					preparedStatement.setObject(j + 1, parmameters[i][j]);
				}
				preparedStatement.executeUpdate();
			}
			connection.commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			// throw new RuntimeErrorException(null, e.getMessage());
		} finally {
			close();
			// close(resultSet, preparedStatement, connection);
		}
	}

	/**
	 * 处理多条参数的sql语句
	 * 
	 * @param sql
	 * @param parmameters
	 */
	public static void excuteUpdateMultiParams(String sql,
			Object[][] parmameters) {
		try {
			connection = getConnection();
			connection.setAutoCommit(false);

			for (int i = 0; i < parmameters.length; i++) {
				preparedStatement = connection.prepareStatement(sql);
				for (int j = 0; j < parmameters[i].length; j++) {
					preparedStatement.setObject(j + 1, parmameters[i][j]);
				}
				preparedStatement.executeUpdate();
			}
			connection.commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			// throw new RuntimeErrorException(null, e.getMessage());
		} finally {
			close();
			// close(resultSet, preparedStatement, connection);
		}
	}

	/**
	 * 处理单条sql语句
	 * 
	 * @param sql
	 * @param parameters
	 */
	public static void escuteUpdate(String sql, Object[] parameters) {

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);

			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					preparedStatement.setObject(i + 1, parameters[i]);
				}
			}
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			close();
			// close(resultSet, preparedStatement, connection);
		}

	}

	public static ResultSet executeQuery(String sql, Object[] parameters) {
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					preparedStatement.setObject(i + 1, parameters[i]);
				}
			}
			resultSet = preparedStatement.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return resultSet;

	}

	/**
	 * 调用无返回值存储过程 格式： call procedureName(parameters list)
	 * 
	 * @param sql
	 * @param parameters
	 */

	public static void callProc(String sql, Object[] parameters) {
		try {
			connection = getConnection();
			callableStatement = connection.prepareCall(sql);
			// 给？赋值
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++)
					callableStatement.setObject(i + 1, parameters[i]);
			}
			callableStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			// 关闭资源
			closeproc();
			// close(resultSet, callableStatement, connection);
		}
	}

	/**
	 * 调用带有输入参数且有返回值的存储过程
	 * 
	 * @param sql
	 * @param inparameters
	 * @return
	 */
	public static ResultSet callProcInput(String sql, Object[] inparameters) {
		try {
			connection = getConnection();
			callableStatement = connection.prepareCall(sql);
			if (inparameters != null)
				for (int i = 0; i < inparameters.length; i++)
					callableStatement.setObject(i + 1, inparameters[i]);
			callableStatement.execute();
			resultSet = callableStatement.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			// 使用完result后再调用该函数的地方close();
		}
		return resultSet;
	}

	/**
	 * 带有返回值的储存过程
	 * 
	 * @param sql
	 * @param outparameters
	 * @return
	 */
	public static ResultSet callProcOutput(String sql, Integer[] outparameters) {
		try {
			connection = getConnection();
			callableStatement = connection.prepareCall(sql);
			// 给out参数赋值
			if (outparameters != null)
				for (int i = 0; i < outparameters.length; i++)
					callableStatement.registerOutParameter(i + 1,
							outparameters[i]);
			callableStatement.execute();
			resultSet = callableStatement.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {

		}
		return resultSet;
	}

	public static void close() {
		close(resultSet, preparedStatement, connection);
	}

	public static void closeproc() {
		close(resultSet, callableStatement, connection);
	}

	public static void close(ResultSet rs, Statement ps, Connection conn) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		rs = null;
		if (ps != null)
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		ps = null;
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		conn = null;
	}
}
