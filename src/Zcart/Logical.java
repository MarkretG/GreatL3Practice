package Zcart;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class Logical {
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
                   if(inventory1.getModel().equals(inventory.getModel())&&inventory.getStock()>0)
                   {
                       return true;
                   }
               }
            }
        }
        return false;
    }
    public void addInventoryListToCart(List<Inventory> list) throws ExceptionHandler {
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
        List<Inventory> list=Cache.OBJ.getCartList();
        Long invoiceNumber=(long)Math.random();
        Invoice invoice=new Invoice();
        invoice.setInvoiceNo(invoiceNumber);
        invoice.setItems(list);
        Map<String,Customer> map= Cache.OBJ.getCustomerMap();
        Customer customer=map.get(mail);
        List<Invoice> invoiceList=customer.getInvoiceList();
    }

    /*public static void main(String[] args) throws ExceptionHandler {
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
