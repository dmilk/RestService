package ru.anrusstrans.test.domain;
/**
 * Created by 1 on 11.03.14.
 * Author DMILK
 */

import org.hibernate.Session;
import ru.anrusstrans.test.util.HibernateUtil;

import java.util.Date;

public class EventManager {
    public static void main(String[] args) {
        EventManager mgr = new EventManager();
        if (args[0].equals("store")) {
            mgr.createAndStoreEvent("MyEvent", new Date());
        }
        HibernateUtil.getSessionFactory().close();
    }

    private void createAndStoreEvent(String title, Date theDate) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

//        EventJDBC theEvent = new EventJDBC();
//        theEvent.setDescription("description");
//        session.save(theEvent);
        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(theDate);
        System.out.println(theEvent.getTitle());
        session.save(theEvent);

//        session.getTransaction().commit();
    }

}
//import java.sql.*;
//
//public class EventManager {
//    static Connection connection;
//
//    public static void main(String[] args) {
//        String drv = "com.mysql.jdbc.Driver";
//        String url = "jdbc:mysql://95.143.14.125/restservice";
//        String login = "root";
//        String pass = "password";
//        try {
//            Class.forName(drv);
//            connection = DriverManager.getConnection(url, login, pass);
//            System.out.println("Есть соединение с БД!");
//            Statement stmt = connection.createStatement();
//            ResultSet result = stmt.executeQuery("SELECT * FROM ru.anrusstrans.test");
//            ResultSetMetaData md = result.getMetaData();
//            int cnt = md.getColumnCount(); // получаем число колонок
//            int row = 0;
//            while (result.next()) {
//                row++;
//                System.out.println("Row " + row); // номер строки
//                for (int i = 1; i <= cnt; i++) {
//                    String name = md.getColumnName(i); // имя поля
//                    String val = result.getString(i); // значение
//                    System.out.println(name + "=" + val); // имя и значение
//                }
//            }
//            result.close();
//            stmt.close();
//            connection.close();
//        } catch (ClassNotFoundException ex) {
//            System.err.println("Нет драйвера.");
//            System.err.println(ex);
//        } catch (SQLException ex) {
//            System.err.println("Нет соединения.");
//            System.err.println(ex);
//        }
//        ;
//    }
//}
