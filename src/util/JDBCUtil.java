package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JDBCUtil {
	private Statement statement;
	private String DRIVER_MYSQL = "com.mysql.jdbc.Driver";    //MySQL JDBC驱动字符串
	private String URL = "jdbc:mysql://localhost:3306/wxtest?"
            + "user=root&password=root&useUnicode=true&characterEncoding=UTF8";
	
	public JDBCUtil(){
		try
        {
            Class.forName(DRIVER_MYSQL);     //加载JDBC驱动
            //System.out.println("Driver Load Success.");

            Connection connection = DriverManager.getConnection(URL);    //创建数据库连接对象
            statement = connection.createStatement();       //创建Statement对象
        } catch (Exception e)
        {
            e.printStackTrace();
        }
	}
	
	public List<Map<String, Object>> query(String sql) {
        ResultSet result = null;
        List<Map<String, Object>> list = null;

        try
        {
            result = statement.executeQuery(sql);
            list = this.resultSetToList(result);
        } 
        
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return list;
    }
	
	public void executeSql(String sql) {
        try
        {
            statement.execute(sql);
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
	
	public List<Map<String, Object>> resultSetToList(ResultSet rs) throws java.sql.SQLException {   
        if (rs == null)   
            return Collections.emptyList();   
        ResultSetMetaData md = rs.getMetaData(); //得到结果集(rs)的结构信息，比如字段数、字段名等   
        int columnCount = md.getColumnCount(); //返回此 ResultSet 对象中的列数   
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();   
        Map<String, Object> rowData = new HashMap<String, Object>();   
        while (rs.next()) {   
        	rowData = new HashMap<String, Object>(columnCount);   
        	for (int i = 1; i <= columnCount; i++) {   
                 rowData.put(md.getColumnName(i), rs.getObject(i));   
        	}   
        	list.add(rowData);   
        	System.out.println("list:" + list.toString());   
        }   
        return list;   
	}
}  
