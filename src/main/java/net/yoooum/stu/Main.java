package net.yoooum.stu;

import net.yoooum.stu.database.JdbcConnect;
import net.yoooum.stu.view.Console;


/**
 * @author Yoooum
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        new Console().consoleView();
//        JdbcConnect jdbcConnect = new JdbcConnect();
//        boolean isrRegister = jdbcConnect.register("测试","123");
//        System.out.println(isrRegister);
    }
}

