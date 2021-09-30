package Zcart;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class ZCartManagement {
    private Scanner sc=new Scanner(System.in);
    private Logical logical =new Logical();
    public static void main(String[] args){
        ZCartManagement obj=new ZCartManagement();
        obj.initialiseCustomerList();
        obj.initialiseInventoryList();
        obj.logical.initialiseAdmin();
        Menu:
        while (true)
        {
            System.out.println("1.Customer sign up");
            System.out.println("2.login");
            System.out.println("3.exit");
            int choice = obj.sc.nextInt();
            switch (choice) {
                case 1:
                    try {
                        obj.newCustomer();
                    } catch (ExceptionHandler e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }
                    continue Menu;
                case 2: {
                    System.out.println("1.customer login");
                    System.out.println("2.admin login");
                    int ch = obj.sc.nextInt();
                    switch (ch) {
                        case 1:
                            String mail = obj.login();
                            int k = 0;
                            while (k == 0) {
                                System.out.println("1.shopping");
                                System.out.println("2.change password");
                                System.out.println("3.Invoice history");
                                System.out.println("4.ext");
                                int option = obj.sc.nextInt();
                                if (option == 1) {
                                    List<Inventory> shoppingList = new ArrayList<>();
                                    Inventory inventory = obj.shopping();
                                    shoppingList.add(inventory);
                                    boolean end = true;
                                    while (end) {
                                        System.out.println("1.do you want to add cart");
                                        System.out.println("1.check out");
                                        int c = obj.sc.nextInt();
                                        if (c == 1) {
                                            shoppingList.add(obj.shopping());
                                        } else if (c == 2) {
                                            try {
                                                List<Inventory> list = obj.logical.addInventoryListToCart(shoppingList);
                                                obj.logical.addInvoiceList(mail);
                                                System.out.println(list);
                                            } catch (ExceptionHandler e) {
                                                e.printStackTrace();
                                            }
                                            end = false;
                                        }
                                    }
                                } else if (option == 2) {
                                    String res = obj.changePassword(mail);
                                    System.out.println(res);
                                } else if (option == 3) {
                                    List<Invoice> invoiceList = obj.logical.getInvoiceList(mail);
                                    for (Invoice invoice : invoiceList) {
                                        System.out.println(invoice);
                                    }
                                } else {
                                    k++;
                                    continue Menu;

                                }
                            }
                            break;
                        case 2:
                            obj.adminLogin();
                            continue Menu;
                    }

                }
                break;
                case 3:
                    System.exit(0);
            }
        }
    }
    private List<Customer> initialiseCustomerList() {
        try{
            List<String> list= logical.readCustomerDetailsFromFile();
            List<Customer> customers=logical.initialiseCustomerList(list);
            return customers;
        }
        catch (ExceptionHandler e) {
            System.out.println("Initialise customerList:" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    private List<Inventory> initialiseInventoryList() {
        try {
            List<String> list= logical.readInventoryDetailsFromFile();
            List<Inventory>inventories=logical.initialiseInventoryList(list);
            return inventories;
        } catch (ExceptionHandler e) {
            System.out.println("Initialise customerList:"+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    private void newCustomer() throws ExceptionHandler {
        String c = "";
        System.out.println("enter mail id");
        String mail = sc.next();
        c += mail+" ";
        System.out.println("enter password");
        String pass = sc.next();
        pass = logical.encryptPassword(pass);
        c += pass + " ";
        System.out.println("enter name");
        String name = "" + sc.next();
        c += name+" ";
        System.out.println("enter mobile no");
        long mobileNo = sc.nextLong();
        c += mobileNo+" ";
        Customer customer=logical.getCustomerObject(mail,pass,name,mobileNo);
        logical.addCustomerIntoFile(c);
        String status = logical.addNewCustomer(customer);
        System.out.println(status);
    }
    private String login()
    {
        System.out.println("enter mail id");
        String mail = sc.next();
        System.out.println("enter password");
        String pass = sc.next();
        pass = logical.encryptPassword(pass);
        if(logical.validateCustomer(mail,pass)) {
            System.out.println("login successfully");
        }else {
            System.out.println("please enter valid user name and password");
            login();
        }
        return mail;
    }
    private Inventory shopping()
    {
        int i=1;
        Map<String,Map<String,List<Inventory>>> inventory=logical.getInventoryMap();
        for (String k:inventory.keySet())
        {
            System.out.println(i+""+k);
        }
        System.out.println("enter category");
        String category= sc.next();
        Map<String,List<Inventory>> map=inventory.get(category);
        if (map==null)
        {
            System.out.println("enter category is not available reenter");
            shopping();
        }
        for (Map.Entry<String,List<Inventory>> entry:map.entrySet())
        {
            System.out.print(entry.getKey()+" ");
            List<Inventory> inventories=entry.getValue();
            for (Inventory n:inventories)
            {
                System.out.print(n);
            }
        }
        Inventory inventory1=getItem(category);
        if (inventory1==null)
        {
            System.out.println("enter valid category and model");
            shopping();
        }
        return inventory1;

    }
    private Inventory getItem(String category)
    {
        System.out.println("enter brand");
        String brand=sc.next();
        System.out.println("enter model");
        String model=sc.next();
        Inventory inventory=new Inventory();
        inventory.setCategory(category);
        inventory.setBrand(brand);
        inventory.setModel(model);
        if(logical.validateInventoryStock(inventory))
        {
            return inventory;
        }
        return null;

    }
    private String changePassword(String mail)
    {
        System.out.println("enter new  password");
        String pass=sc.next();
        pass=logical.encryptPassword(pass);
        return logical.changePassword(mail,pass);
    }
    public void adminLogin()
    {
        System.out.println("enter admin name");
        String name=sc.next();
        System.out.println("enter admin password");
        String password=sc.next();
        if(logical.validateAdmin(name,password))
        {
            System.out.println("login successfully");
             int k=0;
             if(k==0)
             {
                 System.out.println("please change your  password");
                 System.out.println("enter your new password");
                 String newPassword=sc.next();
                 newPassword=logical.encryptPassword(newPassword);
                 String res=logical.adminChangePassword(name,password,newPassword);
                 System.out.println(res);
                 k++;
             }
             while (k==1) {
                 System.out.println("1.change password");
                 System.out.println("2.reorder");
                 System.out.println("3.exit");
                 int ch=sc.nextInt();
                 if(ch==1)
                 {
                     System.out.println("enter your new password");
                     String newPassword=sc.next();
                     newPassword=logical.encryptPassword(newPassword);
                     String res=logical.adminChangePassword(name,logical.getAdmin().getAdminPassword(),newPassword);
                     System.out.println(res);
                 }
                 else if(ch==2)
                 {
                     System.out.println("reorder stock list <=10");
                     List<Inventory> list=logical.reOrderStockList();
                     for (Inventory inventory:list)
                     {
                         System.out.println(inventory);
                         System.out.println("Enter update stock count");
                         int count=sc.nextInt();
                         String res=logical.updateCount(inventory,count);
                         System.out.println(res);
                     }
                 }
                 else if(ch==3)
                 {
                     k=0;
                 }
             }
        }
        else {
            System.out.println("please enter valid username and password");
            adminLogin();
        }

    }

   /* public void printCustomerMap(Map<String,Customer> customerMap)
    {
        System.out.println("-------------------------------------------------");
        System.out.printf("%7s %7s %7s %7s","USERNAME","PASSWORD","NAME","MOBILE NO");
        System.out.println();
        System.out.println("---------------------------------------------------");
        for (Map.Entry<String,Customer> entry:customerMap.entrySet())
        {
            Customer customer= entry.getValue();
            System.out.format("%7s %7s %7s %7s",entry.getKey(),customer.getPassword(),customer.getName(),customer.getMobile());
            System.out.println();
        }
    }
    public void printInventoryMap(Map<String,Map<String,List<Inventory>>> inventoryMap)
    {
        System.out.println("-------------------------------------------------");
        System.out.printf("%7s %7s %7s %7s %7s","CATEGORY","BRAND","MODEL","PRICE","STOCK");
        System.out.println();
        System.out.println("---------------------------------------------------");
        for (Map.Entry<String,Map<String,List<Inventory>>>)
    }*/
}
