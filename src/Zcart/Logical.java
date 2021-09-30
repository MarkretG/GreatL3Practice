package Zcart;
import java.io.*;
import java.util.*;

public class Logical {
    long invoiceNo=1000;
    Admin admin=new Admin();
    public Customer getCustomerObject(String email,String password,String name,long mobileNo)
    {
        Customer customer=new Customer();
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setName(name);
        customer.setMobile(mobileNo);
        return customer;
    }
    public Inventory getInventoryObject(String category,String brand,String model,double price,int stock)
    {
        Inventory inventory=new Inventory();
        inventory.setCategory(category);
        inventory.setBrand(brand);
        inventory.setModel(model);
        inventory.setPrice(price);
        inventory.setStock(stock);
        return inventory;
    }
    public List<String> readCustomerDetailsFromFile(){
        List<String> list=new ArrayList<>();
        File file=new File("C:\\Users\\inc4\\IdeaProjects\\GreatL3Practice\\src\\Zcart\\user.txt");
        try(FileReader fr=new FileReader(file);
        BufferedReader br=new BufferedReader(fr)) {
            String line;
            while ((line=br.readLine())!=null)
            {
                list.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(list);
        return list;
    }
    public void addCustomerIntoFile(String str) {
          File file=new File("C:\\Users\\inc4\\IdeaProjects\\GreatL3Practice\\src\\Zcart\\user.txt");
          try (FileWriter fw=new FileWriter(file,true);
          BufferedWriter bw=new BufferedWriter(fw))
         {
            bw.write(str);
            bw.newLine();
            bw.flush();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<String> readInventoryDetailsFromFile(){
        List<String> list=new ArrayList<>();
        File file=new File("C:\\Users\\inc4\\IdeaProjects\\GreatL3Practice\\src\\Zcart\\inventory.txt");
        try(FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr)) {
            String line;
            while ((line=br.readLine())!=null)
            {
                list.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       // System.out.println(list);
        return list;
    }
    public List<Customer> initialiseCustomerList(List<String> list) throws ExceptionHandler {
        List<Customer> customers=new ArrayList<>();
        if(list.isEmpty())
        {
            throw  new ExceptionHandler("initialise customer list is empty");
        }
        for (String s:list)
        {
            int i=0;
            String[] str=s.split(" ");
            Customer customer=getCustomerObject(str[i++],str[i++],str[i++],Long.parseLong(str[i]));
            customers.add(customer);
        }
        Cache.OBJ.setCustomerMap(customers);
       // System.out.println(customers);
        return customers;
    }
    public List<Inventory> initialiseInventoryList(List<String> list) throws ExceptionHandler {
        List<Inventory> inventories=new ArrayList<>();
        if(list.isEmpty())
        {
            throw  new ExceptionHandler("initialise inventory list is empty");
        }
        for (String s:list)
        {
            int i=0;
            String[] str=s.split("     ");
            Inventory inventory=getInventoryObject(str[i++],str[i++],str[i++],Double.parseDouble(str[i++]),Integer.parseInt(str[i]));
           // System.out.println(i+"i val");
            inventories.add(inventory);
        }
        Cache.OBJ.setInventoryMap(inventories);
       //System.out.println(inventories);
        return inventories;
    }
    public String encryptPassword(String str)
    {
        String encrypted="";
        char[] ch=str.toCharArray();
        for(int i=0;i<ch.length;i++)
        {
            if(ch[i]=='Z')
            {
                ch[i]='A';
            }
            else if(ch[i]=='z')
            {
                ch[i]='a';
            }
            else if(ch[i]=='9')
            {
                ch[i]='0';
            }
            char c=(char) ((int)ch[i]+1);
            encrypted+=c;
        }
        return encrypted;

    }
    public Map<String,Customer> getCustomerMap()
    {
        return Cache.OBJ.getCustomerMap();
    }
    public Map<String,Map<String,List<Inventory>>> getInventoryMap()
    {
        return Cache.OBJ.getInventoryMap();
    }
    public String addNewCustomer(Customer customer) throws ExceptionHandler {
        if (customer==null)
        {
            throw new ExceptionHandler("add customer is null");
        }
        Cache.OBJ.storeCustomerInCustomerMap(customer);
        return "customer added successfully";
    }
    public boolean validateCustomer(String mail,String password)
    {
        Map<String,Customer> map=Cache.OBJ.getCustomerMap();
        Customer customer=map.get(mail);
        if(customer.getPassword().equals(password))
        {
            return true;
        }
        return false;
    }
    public boolean validateInventoryStock(Inventory inventory)
    {
        Map<String,List<Inventory>> map=getInventoryMap().get(inventory.getCategory());
        if(map.size()>0)
        {
            List<Inventory> list=map.get(inventory.getBrand());
            if (list.size()>0)
            {
               for (Inventory inventory1:list)
               {
                   if(inventory1.getModel().equals(inventory.getModel())&&inventory1.getStock()>0)
                   {
                       return true;
                   }
               }
            }
        }
        return false;
    }
    public List<Inventory> addInventoryListToCart(List<Inventory> list) throws ExceptionHandler {
        if (list.isEmpty())
        {
            throw new ExceptionHandler("Add cart list is empty");
        }
        Map<String,Map<String,List<Inventory>>> map= Cache.OBJ.getInventoryMap();
        for (Inventory inventory:list)
        {
           Map<String,List<Inventory>> innerMap=map.get(inventory.getCategory());
           List<Inventory> inventories=innerMap.get(inventory.getBrand());
           for (Inventory i:inventories)
           {
               if(i.getModel().equals(inventory.getModel()))
               {
                  int count= i.getStock();
                  count--;
                  i.setStock(count);
                  inventory.setPrice(i.getPrice());
               }
           }
        }
        Cache.OBJ.addCartList(list);
        return Cache.OBJ.getCartList();
    }
    public String changePassword(String mail,String pass)
    {
        Map<String,Customer> map=getCustomerMap();
       Customer customer= map.get(mail);
       if(customer!=null)
       {
           String password = customer.getPassword();
           List<String> list= customer.getChangePassword();
           if(list==null) {
               list = new ArrayList<>();
           }
           list.add(password);
           customer.setChangePassword(list);
           customer.setPassword(pass);
           return "change password successfully";
       }
       return "password change failed";
    }
    public void addInvoiceList(String mail)
    {
        double amount=0;
        Customer customer=Cache.OBJ.getCustomerMap().get(mail);
        List<Inventory> cartList=Cache.OBJ.getCartList();
        List<Invoice> invoiceList=Cache.OBJ.getInvoiceMap().getOrDefault(customer.getEmail(),new ArrayList<>());
        Invoice invoice=new Invoice();
        List<Inventory> itemList=invoice.getItems();
        if(itemList==null)
        {
            itemList=new ArrayList<>();
        }
        for (Inventory inventory:cartList)
        {
            itemList.add(inventory);
            amount+=inventory.getPrice();
        }
       invoice.setInvoiceNo(invoiceNo++);
        invoice.setTotalAmount(amount);
        invoice.setItems(itemList);
        invoiceList.add(invoice);
        Cache.OBJ.setInvoiceMap(mail,invoiceList);
    }
    public List<Invoice> getInvoiceList(String mail)
    {
        return Cache.OBJ.getInvoiceMap().get(mail);
    }
    public void initialiseAdmin()
    {
        admin.setAdminName("admin@zoho.com");
        admin.setAdminPassword("xyyyz");
    }
    public Admin getAdmin()
    {
        return admin;
    }
    public boolean validateAdmin(String name,String pass)
    {
        if(admin.getAdminName().equals(name)&&admin.getAdminPassword().equals(pass))
        {
            return true;
        }
        return false;
    }
    public String adminChangePassword(String name,String oldPassword,String newPassword)
    {
       List<String> adminPasswords=admin.getPasswords();
       if(adminPasswords==null)
       {
           adminPasswords=new ArrayList<>();
       }
       adminPasswords.add(oldPassword);
       admin.setPasswords(adminPasswords);
       admin.setAdminPassword(newPassword);
       return "change password successfully";
    }
    public List<Inventory> reOrderStockList()
    {
        List<Inventory> stockList=new ArrayList<>();
        Map<String,Map<String,List<Inventory>>> map=getInventoryMap();
        for (Map.Entry<String,Map<String,List<Inventory>>> entry:map.entrySet())
        {
            Map<String,List<Inventory>> innerMap=entry.getValue();
            for (Map.Entry<String,List<Inventory>> i:innerMap.entrySet())
            {
                List<Inventory> list=i.getValue();
                for (Inventory inventory:list)
                {
                    if(inventory.getStock()<=10)
                    {
                        stockList.add(inventory);
                    }
                }
            }
        }
        return stockList;
    }
    public String updateCount(Inventory inventory,int count) {
        List<Inventory> stockList = new ArrayList<>();
        Map<String, Map<String, List<Inventory>>> map = getInventoryMap();
        for (Map.Entry<String, Map<String, List<Inventory>>> entry : map.entrySet()) {
            Map<String, List<Inventory>> innerMap = entry.getValue();
            for (Map.Entry<String, List<Inventory>> i : innerMap.entrySet()) {
                List<Inventory> list = i.getValue();
                for (Inventory inventory1 : list) {
                    if (inventory.equals(inventory1))
                    {
                        inventory1.setStock(inventory1.getStock()+count);

                    }
               }
          }
        }
        return "stock updated successfully";
    }
    public String generateDiscountCode() {
        String disCountCode="";
        String str="AkiuDytp";
        char[] chars=str.toCharArray();
        char[] digit={'1','2','3','4','5','6','7','8','9'};
        double random=Math.random();
        int num=(int)random;
        while (num>0)
        {
            int i=num%10;
            disCountCode+=chars[i];
            num=num/10;
            if(num!=0) {
                i = num % 10;
                disCountCode += digit[i];
            }
        }
        return disCountCode;

    }

    /*public static void main(String[] args) throws ExceptionHandler
        Logical logical =new Logical();
        List<String> list= logical.readCustomerDetailsFromFile();
        //System.out.println(list);
        List<Customer> customers= logical.initialiseCustomerList(list);
        System.out.println(customers);
        List<String> list1= logical.readInventoryDetailsFromFile();
        List<Inventory> inventories= logical.initialiseInventoryList(list1);
        System.out.println(inventories);
    }*/

}
