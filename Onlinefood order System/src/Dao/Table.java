import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.*;
import java.util.*;
public class Table extends HotelServies{
    JDBCconnect c = new JDBCconnect();
    Connection con = c.Connect();


    public void insert(ArrayList<Pair> list, int n) throws Exception {
        int i;
        PreparedStatement stm = con.prepareStatement("insert into Menu_items (Item_name,price,quantity) values (?,?,?)");
        for (i = 0; i < n; i++) {
            stm.setString(1, list.get(i).str);
            stm.setInt(2, list.get(i).quantity);
            stm.setInt(3, list.get(i).price);
            stm.executeUpdate();
        }
    }


    public void delete() throws Exception {
        PreparedStatement stm = con.prepareStatement("delete from Menu_items");
        stm.executeUpdate();
    }

    public void updateQuantity(int id,int x) throws Exception
    {
        PreparedStatement stm = con.prepareStatement("update Menu_items set quantity = ? where id= ? ");
        stm.setInt(1,x);
        stm.setInt(2,id);
        stm.executeUpdate();
    }
    public void updatePrice(int id,int x) throws Exception
    {
        PreparedStatement stm = con.prepareStatement("update Menu_items set Price = ? where id= ? ");
        stm.setInt(1,x);
        stm.setInt(2,id);
        stm.executeUpdate();
    }


    public void display() throws Exception{
        Statement stm =con.createStatement();
        ResultSet res=stm.executeQuery("select * from Menu_items");
        int i=0;
        System.out.println("Item_id  Item_name  Price");
        while(res.next()){
                System.out.print(res.getInt(1)+" "+res.getString(2)+" "+res.getInt(3));
                System.out.println();
        }
    }

}
