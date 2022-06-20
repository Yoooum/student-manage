package net.yoooum.stu.view;

import net.yoooum.stu.database.JdbcConnect;
import net.yoooum.stu.database.SqlExec;
import net.yoooum.stu.database.StudentDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 学生管理系统的 Console 控制台视图
 * @author Yoooum
 */
public class Console {
    boolean isLogin = userLogin();
    JdbcConnect jdbcConnect;

    public void consoleView() {
        if (isLogin) {

            while (true) {
                System.out.println("请选择您要进行的操作: ");
                System.out.println("0.命令模式");
                System.out.println("1.查询学生");
                System.out.println("2.添加学生");
                System.out.println("3.修改学生");
                System.out.println("4.删除学生");
                System.out.println("5.退出系统");
                Scanner scanner = new Scanner(System.in);
                int choice = 0;
                choice = scanner.nextInt();
                switch (choice) {
                    case 0 -> consoleExec();
                    case 1 -> studentSearch();
                    case 2 -> studentAdd();
                    case 3 -> studentUpdate();
                    case 4 -> studentDelete();
                    case 5 -> {
                        System.out.println("已退出");
                        System.exit(0);
                    }
                    default -> System.out.println("未指定的操作, 请重新输入！");
                }
            }
        }


    }

    private void todo() {
        System.out.println("暂未实现,进入命令模式");
    }

    private void consoleExec() {
        SqlExec sqlExec = new SqlExec();
        Scanner scan = new Scanner(System.in);
        System.out.print("sql exec: ");
        if (scan.hasNextLine()) {
            String sql = scan.nextLine();
            if (sql.equals("exit")) {
                scan.close();
                consoleView();
            } else {
                sqlExec.exec(sql);
            }
        }
        System.out.println("继续执行命令模式(y/n)");
        String choice = scan.nextLine();
        if (choice.equals("y")) {
            consoleExec();
        } else {

            consoleView();
        }
        scan.close();
    }

    private void studentSearch() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("以学号或名字查询: ");
        if (scanner.hasNext()){
            //new SqlExec().exec(scanner.next());
            new StudentDao().retrieve(scanner.next());
        }
    }

    private void studentAdd(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("添加学生格式: \n学号(String) 姓名(String) 年龄(int) 性别( 男 or 女 )");
        String sid = scanner.next();
        String name = scanner.next();
        int age = Integer.parseInt(scanner.next());
        String sex = scanner.next();
        new  StudentDao().create(sid,name,age,sex);
    }

    private void studentUpdate(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("修改学生格式, 学号指定修改对象: \n指定学号(String) 新姓名(String) 新年龄(int) 新性别( 男 or 女 )");
        String sid = scanner.next();
        String name = scanner.next();
        int age = Integer.parseInt(scanner.next());
        String sex = scanner.next();
        new StudentDao().update(sid,name,age,sex);
    }

    private void studentDelete(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("根据学号删除学生: ");
        new StudentDao().delete(scanner.next());
    }
    private boolean userLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("登录");
        String username = null, password = null;
        int failed = 0;
        while (failed < 3) {
            System.out.println("请输入名称: ");
            if (scanner.hasNext()) {
                username = scanner.next();
            }
            System.out.println("请输入密码: ");
            if (scanner.hasNext()) {
                password = scanner.next();
            }

            jdbcConnect = new JdbcConnect();
            if (jdbcConnect.login(username, password)) {
                return true;
            } else {
                failed++;
                System.out.println("登录失败," + failed + "/3\n");
                System.out.println("用 " + username + ", " + password + " 注册新用户吗?(y/n)");
                if (scanner.next().equals("y")) {
                    boolean isReg = new JdbcConnect().register(username,password);

                }
            }

        }
        scanner.close();
        return false;
    }

    private void printResultSet(ResultSet resultSet) {
        try {
            SqlExec.printTable(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
