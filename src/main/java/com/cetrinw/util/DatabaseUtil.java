package com.cetrinw.util;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class
DatabaseUtil {

	private static final QueryRunner runner = new QueryRunner();

	public static Connection getMySQLConnection() { 
		//return openConn("MySQL", "192.168.0.189", "3306", "BDIA?useUnicode=true&amp;characterEncoding=gbk", "root", "123456");
		return openConn("MySQL", "192.168.0.189", "3306", "BDIA?useUnicode=true&characterEncoding=utf-8", "root", "123456");
	}

	public static Connection getOracleConnection() {
		
		return openConn("Oracle", "192.168.0.168", "1521", "ideadata", "yyfx", "yyfx");
	}
	
	public static Connection openConn(String type, String host, String port,
			String name, String username, String password) {
		Connection conn = null;
		try {
			String driver;
			String url;
			if (type.equalsIgnoreCase("MySQL")) {
				driver = "com.mysql.jdbc.Driver";
				url = "jdbc:mysql://" + host + ":" + port + "/" + name;

// connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123456");
				
			} else if (type.equalsIgnoreCase("Oracle")) {
				driver = "oracle.jdbc.driver.OracleDriver";
				url = "jdbc:oracle:thin:@" + host + ":" + port + ":" + name;
			} else if (type.equalsIgnoreCase("SQLServer")) {
				driver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
				url = "jdbc:sqlserver://" + host + ":" + port
						+ ";databaseName=" + name;
			} else {
				throw new RuntimeException();
			}
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeConn(Connection conn) {
		try {
			if (conn != null) {
				// conn.close();
				DbUtils.closeQuietly(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 数据库回滚
	 */
	public static void rollback(Connection conn){
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Object[] queryArray(Connection conn, String sql,
			Object... params) {
		Object[] result = null;
		try {
			result = runner.query(conn, sql, new ArrayHandler(), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static List<Object[]> queryArrayList(Connection conn, String sql,
			Object... params) {
		List<Object[]> result = null;
		try {
			result = runner.query(conn, sql, new ArrayListHandler(), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 查询（返回Map结果）
	public static Map<String, Object> queryMap(Connection conn, String sql,
			Object... params) {
		Map<String, Object> result = null;
		try {
			result = runner.query(conn, sql, new MapHandler(), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 查询（返回MapList结果）
	public static List<Map<String, Object>> queryMapList(Connection conn,
			String sql, Object... params) {
		List<Map<String, Object>> result = null;
		try {
			result = runner.query(conn, sql, new MapListHandler(), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 查询（返回Bean结果）
	public static <T> T queryBean(Class<T> cls, Map<String, String> map,
			Connection conn, String sql, Object... params) {
		T result = null;
		try {
			if (!MapUtil.isEmpty(map)) {
				result = runner.query(conn, sql, new BeanHandler<T>(cls,
						new BasicRowProcessor(new BeanProcessor(map))), params);
			} else {
				result = runner.query(conn, sql, new BeanHandler<T>(cls),
						params);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 查询（返回BeanList结果）
	public static <T> List<T> queryBeanList(Class<T> cls,
			Map<String, String> map, Connection conn, String sql,
			Object... params) {
		List<T> result = null;
		try {
			if (MapUtil.isEmpty(map)) {
				result = runner.query(conn, sql, new BeanListHandler<T>(cls,
						new BasicRowProcessor(new BeanProcessor(map))), params);
			} else {
				result = runner.query(conn, sql, new BeanListHandler<T>(cls),
						params);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 查询指定列名的值（单条数据）
	public static <T> T queryColumn(String column, Connection conn, String sql,
			Object... params) {
		T result = null;
		try {
			result = runner.query(conn, sql, new ScalarHandler<T>(column),
					params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 查询指定列名的值（多条数据）
	public static <T> List<T> queryColumnList(String column, Connection conn,
			String sql, Object... params) {
		List<T> result = null;
		try {
			result = runner.query(conn, sql, new ColumnListHandler<T>(column),
					params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 查询指定列名对应的记录映射
	public static <T> Map<T, Map<String, Object>> queryKeyMap(String column,
			Connection conn, String sql, Object... params) {
		Map<T, Map<String, Object>> result = null;
		try {
			result = runner.query(conn, sql, new KeyedHandler<T>(column),
					params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 更新（包括UPDATE、INSERT、DELETE，返回受影响的行数）
	public static int update(Connection conn, String sql, Object... params) {
		int result = 0;
		try {
			result = runner.update(conn, sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//批量更新操作
	public static void bitchUpdate(Connection conn, String sql, Object[][] params){
		try {
			conn.setAutoCommit(false);
			runner.batch(conn, sql, params);
			conn.commit();
		} catch (SQLException e) {
			rollback(conn);//失败后回滚数据库操作
			e.printStackTrace();
		}
	}

	//获取所有字段
	
	public static void main(String[] args) {
		Connection conn = getMySQLConnection();

		try {
			String sql = "SELECT * FROM BD_ANALY_PA_WORKING01 WHERE id = ?";
			Map<String, Object> map = queryMap(conn, sql, 1);
			Iterator<Entry<String, Object>> iter = map.entrySet()
					.iterator();
			while (iter.hasNext()) {
				Entry<String, Object> entry = iter
						.next();
				Object key = entry.getKey();
				Object val = entry.getValue();
				System.out.println(key + " : " + val);
			}
		} finally {
			closeConn(conn);
		}
	}
}

class MapUtil {
	public static boolean isEmpty(Map<?, ?> map) {
		return map.isEmpty();
	}

}
