package net.yoooum.stu.database;

import net.yoooum.stu.view.Console;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Yoooum
 */
public class SqlExec extends JdbcConnect {

    /**
     * 数据库-查询
     * @param sql 只能是查询类型的SQL语句
     * @return 该SQL语句查询到的结果集对象
     */
    public ResultSet query(String sql) {
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet;
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    /**
     * 数据库-增、删、改
     * @param sql 增删改类型的SQL语句
     * @return 该SQL语句影响的记录数
     */
    public int update(String sql) {
        try {
            Statement statement = getConnection().createStatement();
            int resultSet = statement.executeUpdate(sql);
            return Math.max(resultSet, 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return 0;
    }

    //TODO: exec()方法 可以直接 return 结果集 和 受影响的行数
    /**
     * 通用方法-用于执行任何SQL语句,控制台输出结果集或受影响的记录数
     * @param sql 增删改查类型的SQL语句
     */
    public void exec(String sql) {
        try {
            Statement statement = getConnection().createStatement();
            boolean hasResult = statement.execute(sql);
            if (hasResult) {
                ResultSet resultSet = statement.getResultSet();
                printTable(resultSet);
            } else {
                System.out.println("本次修改影响行数: " + statement.getUpdateCount());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

    }

    public static
    void printTable(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            System.out.print(metaData.getColumnName(i) + "\t");
        }
        System.out.println();
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(resultSet.getString(i) + "\t");
            }
            System.out.println();
        }
    }
}

