package net.yoooum.stu.database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 学生表 tb_student 的 CRUD 操作
 * @author Yoooum
 */
public class StudentDao {
    public SqlExec sqlExec;
    public StudentDao() {
        sqlExec = new SqlExec();
    }

    /**
     * 添加学生数据
     * @param sid  学号
     * @param name 姓名
     * @param age  年龄
     * @param sex  性别( 男 or 女 )
     */
    public void create(String sid, String name, int age, String sex) {
        //SqlExec sqlExec = new SqlExec();
        String sql = "insert into tb_student values ('" + sid + "','" + name + "'," + age + ",'" + sex + "')";
        int resultSet = sqlExec.update(sql);
        if (resultSet > 0) {
            System.out.println("本次修改影响行数: " + resultSet);
        } else {
            System.out.println("插入失败!");
        }
    }

    /**
     * 删除学生数据
     * @param sid 学号
     */
    public void delete(String sid) {
        //SqlExec sqlExec = new SqlExec();
        String sql = "delete from tb_student where sid = '" + sid + "'";
        int resultSet = sqlExec.update(sql);
        if (resultSet > 0) {
            System.out.println("本次修改影响行数: " + resultSet);
        } else {
            System.out.println("删除失败!");
        }
    }

    /**
     * 根据学号或姓名查询学生信息
     * @param any 学号 或 姓名
     */
    public void retrieve(String any) {
        //SqlExec sqlExec = new SqlExec();
        String sql = "select * from tb_student where sid = '" + any + "' or name = '" + any + "'";
        ResultSet resultSet = sqlExec.query(sql);
        if (resultSet != null) {
            System.out.println("学号\t姓名\t年龄\t性别");
            try {
                System.out.print(resultSet.getString("sid") + "\t");
                System.out.print(resultSet.getString("name") + "\t");
                System.out.print(resultSet.getInt("age") + "\t");
                System.out.println(resultSet.getString("sex"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("没有查询到该数据");
        }

    }

    /**
     * 修改学生数据
     * @param sid  需要条件
     * @param name 修改姓名
     * @param age  修改年龄
     * @param sex  修改性别
     */
    public void update(String sid, String name, int age, String sex) {
        //SqlExec sqlExec = new SqlExec();
        String sql = "update tb_student set name = '" + name + "',age=" + age + ",sex='" + sex + "' " + "where sid = '" + sid + "'";
        int resultSet = sqlExec.update(sql);
        if (resultSet > 0) {
            System.out.println("本次修改影响行数: " + resultSet);
        } else {
            System.out.println("修改失败!");
        }
    }
}
