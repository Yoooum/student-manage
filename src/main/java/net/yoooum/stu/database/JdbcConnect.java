package net.yoooum.stu.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Yoooum
 */
public class JdbcConnect {
    private Connection connection = null;
    private ResultSet resultSet = null;

    public Connection getConnection() {
        return connection;
    }


    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    /**
     * 构造方法
     */
    public JdbcConnect() {
        this.initialize();
    }

    /**
     * 数据库连接初始化
     */
    public void initialize() {
        try {
            //注册驱动
            String driver = "org.mariadb.jdbc.Driver";
            Class.forName(driver);
            //获取数据库连接
            String url = "jdbc:mariadb://localhost:3306/student_sys";
            String username = "root";
            String password = "1234567890dd@";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
        /**
         * 用户登录
         * @param username 用户名
         * @param password 密码
         * @return 登录是否成功
         */
    public boolean login(String username, String password) {
        try {
            String sql = "select * from tb_user where username = '" + username + "' and password = '" + password + "'";
            resultSet = connection.createStatement().executeQuery(sql);
            if (resultSet.next()) {
                System.out.println("登录成功: " + resultSet.getString("username"));
                return true;
            } else {
                System.out.println("登录失败!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return false;
    }

    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码
     * @return 是否注册成功
     */
    public boolean register(String username, String password) {
        try {
            String sql = "insert into tb_user(username, password) values('" + username + "', '" + password + "')";
            connection.createStatement().executeUpdate(sql);
            System.out.println("注册成功!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("注册失败!");
        } finally {
            close();
        }
        return false;
    }

    /**
     * 关闭数据库连接
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
