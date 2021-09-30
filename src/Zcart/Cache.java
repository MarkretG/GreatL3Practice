package Zcart;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public enum Cache {
    OBJ;
    private Map<String,Customer> customerMap=new HashMap<>();
    private Map<String,Map<String,List<Inventory>>> inventoryMap=new HashMap<>();
    private List<Inventory> cartList=new ArrayList<>();
    private Map<String,List<Invoice>> invoiceMap=new HashMap<>();
    public void setCustomerMap(List<Customer> customers) throws ExceptionHandler {
        if(customers.isEmpty())
        {
            throw  new ExceptionHandler("SET CUSTOMER MAP:customer list is empty");
        }
        for (Customer customer:customers)
        {
            customerMap.put(customer.getEmail(),customer);
        }
    }
    public void setInventoryMap(List<Inventory> inventories) throws ExceptionHandler {
        if(inventories.isEmpty())
        {
            throw new ExceptionHandler("SET INVENTORIES MAP:inventory list is empty");
        }
        for (Inventory inventory:inventories)
        {
            Map<String,List<Inventory>> innerMap=inventoryMap.getOrDefault(inventory.getCategory(),new HashMap<>());
            List<Inventory> list=innerMap.getOrDefault(inventory.getBrand(),new ArrayList<>());
            list.add(inventory);
            innerMap.put(inventory.getBrand(),list);
            inventoryMap.put(inventory.getCategory(),innerMap);
        }
    }
    public void   storeCustomerInCustomerMap(Customer customer) throws ExceptionHandler {
        if(customer==null)
        {
            throw new ExceptionHandler("store customer is null");
        }
        customerMap.put(customer.getEmail(),customer);
    }
    public void setInvoiceMap(String mail,List<Invoice> invoiceList)
    {
     invoiceMap.put(mail,invoiceList);
    }
    public void addCartList(List<Inventory> inventories)
    {
        cartList=inventories;
    }
    public List<Inventory> getCartList()
    {
        return cartList;
    }
    public Map<String,Customer> getCustomerMap()
    {
        return customerMap;
    }
    public Map<String,Map<String,List<Inventory>>> getInventoryMap()
    {
        return inventoryMap;
    }
    public Map<String,List<Invoice>> getInvoiceMap()
    {
        return invoiceMap;
    }

}
