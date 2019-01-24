package com.lay.javaweb.chapter2.helper;

import com.lay.javaweb.chapter2.model.Customer;
import com.lay.javaweb.chapter2.util.BeanUtil;
import com.lay.javaweb.chapter2.util.PropsUtil;
import com.lay.javaweb.chapter2.util.StringUtil;
import com.lay.javaweb.chapter2.util.support.BeanKit;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Description: 数据库操作助手类
 * @Author: lay
 * @Date: Created in 16:49 2019/1/23
 * @Modified By:IntelliJ IDEA
 */
public final class DatabaseHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);


    private static final ThreadLocal<Connection> CONNECTION_HOLDER;
    private static final QueryRunner QUERY_RUNNER;
    private static final BasicDataSource DATA_SOURCE;

    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        CONNECTION_HOLDER = new ThreadLocal<Connection>();
        QUERY_RUNNER = new QueryRunner();
        DATA_SOURCE = new BasicDataSource();


        Properties conf = PropsUtil.loadProps("config.properties");
        DRIVER = conf.getProperty("jdbc.driver");
        URL = conf.getProperty("jdbc.url");
        USERNAME = conf.getProperty("jdbc.username");
        PASSWORD = conf.getProperty("jdbc.password");

        DATA_SOURCE.setDriverClassName(DRIVER);
        DATA_SOURCE.setUrl(URL);
        DATA_SOURCE.setUsername(USERNAME);
        DATA_SOURCE.setPassword(PASSWORD);


/*        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.error("can not load jdbc driver",e);
        }*/
    }

    //获取数据库连接

    public static Connection getConnection() {

/*        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            LOGGER.error("get connection failure", e);
        }
        return connection;*/


        Connection conn=CONNECTION_HOLDER.get();
        if(conn==null){
            try {
                conn=DATA_SOURCE.getConnection();
            } catch (SQLException e) {
                LOGGER.error("get connection failure",e);
                throw new RuntimeException();
            }finally {
                CONNECTION_HOLDER.set(conn);
            }
        }
        return conn;
    }

/*    //关闭数据库连接
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("close connection failure", e);
                throw new RuntimeException(e);
            }finally {
                CONNECTION_HOLDER.remove();
            }
        }
    }*/

    //执行sql文件  //初始化数据库
    public static void executeSqlFile(String filePath){
        //初始化数据库
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        BufferedReader reader=new BufferedReader(new InputStreamReader(is));
        try {
            String sql;
            while ((sql=reader.readLine())!=null){
                executeUpdate(sql);
            }
        } catch (IOException e) {
            LOGGER.error("execute sql file failure",e);
            throw new RuntimeException(e);
        }
    }

    //查询实体列表
    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
        List<T> entityList;
        Connection conn = getConnection();
        try {
            entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(entityClass), params);
        } catch (SQLException e) {
            LOGGER.error("query entity list failure", e);
            throw new RuntimeException();
        }
/*        finally {
            closeConnection(conn);
        }*/
        return entityList;
    }

    //执行查询语句
    public static List<Map<String,Object>> executeQuery(String sql,Object...params){
        List<Map<String,Object>> result;
        try {
            Connection conn=getConnection();
            result=QUERY_RUNNER.query(conn,sql,new MapListHandler(),params);

        } catch (SQLException e) {
            LOGGER.error("execute query failure",e);
            throw new RuntimeException(e);
        }
        return result;
    }

    //执行更新语句
    public static int executeUpdate(String sql,Object...params){
        int rows=0;
        try {
            Connection conn=getConnection();
            rows=QUERY_RUNNER.update(conn,sql,params);

        } catch (SQLException e) {
            LOGGER.error("execute update failure",e);
            throw new RuntimeException(e);
        }
        return rows;
    }

    //插入实体
    public static <T>boolean insertEntity(T t){
        if(t==null){
            LOGGER.error("can not insert entity : object is null");
            return false;
        }
        String tableName=getTableName(t.getClass());
        StringBuilder sqlBuilder=new StringBuilder("INSERT INTO ").append(tableName);
        StringBuilder columns=new StringBuilder("(");
        StringBuilder values=new StringBuilder("(");
        Map<String, Object> objectMap = BeanKit.beanToMap(t);
        for (String fieldName : objectMap.keySet()) {
            columns.append(fieldName).append(", ");
            values.append("?, ");
        }
        columns.replace(columns.lastIndexOf(", "),columns.length(),")");
        values.replace(values.lastIndexOf(", "),values.length(),")");
        String sql=sqlBuilder.append(columns).append("VALUES ").append(values).toString();
        Object[] params=objectMap.values().toArray();
        return executeUpdate(sql, params)==1;
    }

    //更新实体(仅更新非null字段)
    public static <T>boolean updateEntityById(T t){
        if(t==null){
            LOGGER.error("can not update entity : object is null");
            return false;
        }
        Map<String, Object> objectMap = BeanKit.beanToMap(t);
        Object id = objectMap.get("id");
        if(id==null){
            LOGGER.error("can not update entity : the primary key is null");
            return false;
        }
        String tableName=getTableName(t.getClass());
        StringBuilder sqlBuilder=new StringBuilder("UPDATE ").append(tableName).append(" SET ");
        StringBuilder columns=new StringBuilder();
        List<Object> paramList=new ArrayList<>();
        for (String fieldName : objectMap.keySet()) {
            if(!"id".equalsIgnoreCase(fieldName)) {
                columns.append(fieldName).append("=?, ");
                paramList.add(objectMap.get(fieldName));
            }
        }

        sqlBuilder.append(columns.substring(0,columns.lastIndexOf(", "))).append(" WHERE id=?");
        paramList.add(id);
        String sql=sqlBuilder.toString();
        Object[] params=paramList.toArray();
        return executeUpdate(sql, params)==1;
    }

    //更新全部字段
    public static <T>boolean updateAllEntityById(T t){
        if(t==null){
            LOGGER.error("can not update entity : object is null");
            return false;
        }
        Map<String, Object> objectMap = BeanUtil.beanToMapWithNull(t);
        Object id = objectMap.get("id");
        if(id==null){
            LOGGER.error("can not update entity : the primary key is null");
            return false;
        }
        String tableName=getTableName(t.getClass());
        StringBuilder sqlBuilder=new StringBuilder("UPDATE ").append(tableName).append(" SET ");
        StringBuilder columns=new StringBuilder();
        List<Object> paramList=new ArrayList<>();
        for (String fieldName : objectMap.keySet()) {
            if(!"id".equalsIgnoreCase(fieldName)) {
                columns.append(fieldName).append("=?, ");
                paramList.add(objectMap.get(fieldName));
            }
        }
        sqlBuilder.append(columns.substring(0,columns.lastIndexOf(", "))).append(" WHERE id=?");
        paramList.add(id);
        String sql=sqlBuilder.toString();
        Object[] params=paramList.toArray();
        return executeUpdate(sql, params)==1;
    }

    //删除实体
    public static<T> boolean deleteEntity(Class<T> entityClass,Long id){
        if(id==null){
            LOGGER.error("can not delete entity : the primary key is null");
            return false;
        }
        String tableName=getTableName(entityClass);
        StringBuilder sqlBuilder=new StringBuilder("DELETE FROM ").append(tableName).append(" WHERE id=?");
        String sql=sqlBuilder.toString();
        return executeUpdate(sql,id)==1;

    }

    //获得表名
    private static String getTableName(Class<?> c) {
        return StringUtil.toLowerCaseFirstOne(c.getSimpleName());
    }



    public static void main(String[] args) throws IntrospectionException {
        Customer customer=new Customer();
        BeanInfo beanInfo = Introspector.getBeanInfo(customer.getClass());
        BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
        Field[] fields = customer.getClass().getFields();
        System.out.println(customer.getClass().getSimpleName());
    }


}
