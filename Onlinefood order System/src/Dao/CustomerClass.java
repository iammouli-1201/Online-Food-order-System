import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import static java.time.LocalTime.now;

public class CustomerClass extends Customer{
    JDBCconnect c = new JDBCconnect();
    Connection con = c.Connect();

    public CustomerClass(String name, int age, String address, String email) {
        super(name, age, address, email);
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


    public int OrderFood(int product_id,int quantity) throws Exception {
        int q = 0;
        int p = 0;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("Select price,Quantity from Menu_items where id = " + "'" + product_id + "'");
        while (rs.next()) {
            p = rs.getInt(1);
            q = rs.getInt(2);
        }
        q = q - quantity;
        if (q < 0) {
            System.out.println("");
        } else {
            PreparedStatement stm = con.prepareStatement("update Menu_items set Quantity=? where id= " + "'" + product_id + "'");
            if (q >= 0)
                stm.setInt(1, q);
            else
                stm.setInt(1, 0);
            stm.executeUpdate();
        }
        return p;
    }

    public void Account(String name,String number) throws Exception
    {
        PreparedStatement stm = con.prepareStatement("insert into customerd values(?,?,?)");
        stm.setInt(1 ,1);
        stm.setString(2,name);
        stm.setString(3,number);
        stm.executeUpdate();
    }

    public void Order(int customer_id,int order_id,int Price) throws Exception
    {
        PreparedStatement stm = con.prepareStatement("insert into Order_history values(?,?,?,?)");
        stm.setInt(1,customer_id);
        stm.setInt(2,order_id);
        stm.setDouble(3,Price);
        stm.setString(4,String.valueOf(now()));
        stm.executeUpdate();

    }
    public void displayHistory(String num) throws Exception
    {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("Select FoodName,Price from orderD where phoneN = " + "'" + num + "'");
        while (rs.next()) {
            String foodname = rs.getString(1);
            int amount = rs.getInt(2);
            System.out.println(foodname+" "+amount);
        }
    }

    public int validation(String userName,String Password) throws Exception
    {
        Statement st = con.createStatement();
        String query = "Select * from  Customer_details Where Customer_email='" + userName + "' and Customer_password ='" + Password + "'";
        ResultSet stm1 = st.executeQuery(query);
        if(stm1.next())
        {
            return stm1.getInt(1);
        }
        return 0;
    }

    public int CustomerLogin() throws Exception {
        Scanner sc=new Scanner(System.in);
        while (true)
        {
            System.out.println("Enter UserName");
            String str=sc.next();
            System.out.println("Enter Password");
            String pass=sc.next();
            int x1=validation(str,pass);
            if(x1!=0)
            {
                return x1;
            }
            else {
                System.out.println("Password or userName is incorrect");
            }
        }
    }

    public void CreateAccount() throws Exception
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Your Name");
        String str=sc.next();
        System.out.println("Enter Your Email");
        String str1=sc.next();
        System.out.println("Enter Password");
        String str2=sc.next();
        PreparedStatement stm = con.prepareStatement("insert into Customer_details (Customer_name,Customer_email,Customer_password) values (?,?,?)");
        stm.setString(1,str);
        stm.setString(2,str1);
        stm.setString(3,str2);
        stm.executeUpdate();
    }
}
