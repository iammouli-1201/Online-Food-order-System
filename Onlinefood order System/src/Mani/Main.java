//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
         Table t=new Table();
         CustomerClass cus=new CustomerClass();
         Scanner sc=new Scanner(System.in);
         boolean b=true;
         System.out.println("1.Table");
         System.out.println("2.OrderFood");
         int num=sc.nextInt();
         if(num==1) {
             while (b) {
                 System.out.println("1.insert");
                 System.out.println("2.delete");
                 System.out.println("3.exist");
                 System.out.println("4.display");
                 System.out.println("5.UpdateQuantity");
                 System.out.println("6 UpdatePrice");
                 int n = sc.nextInt();
                 switch (n) {
                     case 1:
                         System.out.println("Enter number of food Name Quantity and Price");
                         int n1 = sc.nextInt();
                         ArrayList<Pair> list = new ArrayList<>();
                         sc.nextLine();
                         int i;
                         for (i = 0; i < n1; i++) {
                             String str = sc.nextLine();
                             int qua=sc.nextInt();
                             int price=sc.nextInt();
                             sc.nextLine();
                             Pair p=new Pair(str,qua,price);
                             list.add(p);
                         }
                         t.insert(list,list.size());
                         System.out.println("Inserted Successfully");
                         break;
                     case 2:
                         t.delete();
                         System.out.println("Delete successfully");
                         break;
                     case 3:
                         b = false;
                         break;
                     case 4:
                         t.display();
                         break;
                     case 5:
                         System.out.println("Enter the item_id to update");
                         int id=sc.nextInt();
                         System.out.println("Enter The New Quantity");
                         int quan=sc.nextInt();
                         t.updateQuantity(id,quan);
                     case 6:
                         System.out.println("Enter the item_id to update");
                         int id1=sc.nextInt();
                         System.out.println("Enter The New Price");
                         int quan1=sc.nextInt();
                         t.updatePrice(id1,quan1);
                         break;
                     default:
                         System.out.println("Enter correct number");
                 }
             }
         }
         else {
             int sum=0;
             System.out.println("1.Login");
             System.out.println("2.create new Account");
             int n=sc.nextInt();
             int x=0;
             if(n==1)
             {
                x=cus.CustomerLogin();
             }
             else {
                 cus.CreateAccount();
                 System.out.println("Account created successfull");
                 x=cus.CustomerLogin();
             }
             while(b)
             {
                 System.out.println("1.OrderFood");
                 System.out.println("");
                 int choice=sc.nextInt();
                 switch (choice)
                 {
                     case 1:
                         cus.display();
                         System.out.println("Enter order_id to make order");
                         int product_id=sc.nextInt();
                         System.out.println("please! Enter number of quantity you want:");
                         int qua=sc.nextInt();
                         int totel=cus.OrderFood(product_id,qua)*qua;
                         cus.Order(x,product_id,totel);
                         sum=sum+totel;
                         break;
                     case 2:
                         b=false;
                         break;
                     case 3:
                         cus.display();
                         break;
                     case 4:
                         System.out.println("Enter Mobile Number");
                         String number=sc.next();
                         cus.displayHistory(number);
                         break;
                     default:
                         System.out.println("Invalid choice");
                 }
             }
             System.out.println("Total Bill amount for your order:"+sum);
         }
    }
}