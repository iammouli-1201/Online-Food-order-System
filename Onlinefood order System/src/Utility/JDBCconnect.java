import java.sql.*;
public class JDBCconnect {
      public Connection Connect()
      {
          String url = "jdbc:mysql://localhost:3306/Project";
          String name = "root";
          String password = "1234";
          Connection con=null;
          try{
              con = DriverManager.getConnection(url, name, password);
          }catch (Exception e)
          {
             System.out.println("Exception");
          }
          return con;
      }
}
