package zCoin;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Banking {
    Cache cache=new Cache();
    int zid=101;
   /* public boolean validateEmail(String email)
    {
        String regex="^[a-z0-9+_.-]+@[a-z0-9.-]+$";
        return Pattern.matches(regex,email);
    }*/
    public User getUserObject(String name,String email,String password,long mobileNo,double rc)
    {
        User user=new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRc(mobileNo);
        user.setRc(rc);
        return user;
    }
    public void createAccount(User user)
    {
        cache.addCreationAccount(user);
    }
    public boolean isCheckExistingUser(String email,String password)
    {
        List<User> accountList= cache.getAllAccountCreationList();
        for(User user:accountList)
        {
           if(user.getEmail().equals(email)&&user.getPassword().equals(password))
           {
               return true;
           }
        }
        return  false;
    }
    public boolean isCheckApprovalUser(String email,String password)
    {
        List<User> accountList= cache.getApprovalAccountList();
        for (User user:accountList)
        {
            if(user.getEmail().equals(email)&&user.getPassword().equals(password))
            {
                return true;
            }
        }
        return false;
    }
    public  void generateZid(User user)
    {
        user.setZ_Id(zid);
        cache.addApprovalAccount(user);
        RemoveUserFromAccountCreationList(user);
    }
    public boolean isValidateWithDrawAmount(long z_id,double withDrawAmount)
    {
        User user=cache.getAccountDetails().get(z_id);
        if(user.getRc()>withDrawAmount)
        {
            return true;
        }
        return false;
    }
    public void updateWithDrawAmount(User user,double amount)
    {
       user.setRc(user.getRc()-amount);
    }
    public void updateDepositAmount(User user,double amount)
    {
        user.setRc(user.getRc()+amount);
    }
    public void addRcTransaction(long z_id,RCTransaction transaction)
    {
        cache.addRcTransactionList(z_id,transaction);
    }
    private void RemoveUserFromAccountCreationList(User user)
    {
        List<User> list=cache.getAllAccountCreationList();
        list.remove(user);
    }
    public User getAccountDetails(User user)
    {
       return cache.getAccountDetails().get(user.getZ_Id());
    }
    public List<User> getCreationAccountList()
    {
        return cache.getAllAccountCreationList();
    }
    public List<User> getApprovalAccountList()
    {
        return cache.getApprovalAccountList();
    }
}
