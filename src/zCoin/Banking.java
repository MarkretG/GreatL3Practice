package zCoin;
import java.util.ArrayList;
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
    public User covertRcToZc(User user)
    {
        double conversionRate=cache.getRcToZcConversionRate();
        double zc=user.getRc()/conversionRate;
        user.setRc(0.0);
        user.setZc(zc);
        return user;
    }
    public User covertZcToRc(User user)
    {
        double conversionRate=cache.getRcToZcConversionRate();
        double rc=user.getZc()*conversionRate;
        user.setRc(rc);
        user.setZc(0.0);
        return user;
    }
    public void changePassword(User user,String password)
    {
        user.setPassword(password);
    }
    public Map<Long,List<RCTransaction>> getRcTransactionHistory()
    {
        return cache.getRcTransactionMap();
    }
    public Map<Long,List<ZCTransaction>> getZcTransactionHistory()
    {
        return cache.getZcTransactionMap();
    }
    public void setConversionRate(double conversionRate)
    {
      cache.setConversionRate(conversionRate);
    }
    public void updateTransferAmount(User user,double amount,long receiverZId)
    {
        if(user.getZc()>amount)
        {
            user.setZc(user.getZc()-amount);
        }
        User receiver=cache.getAccountDetails().get(receiverZId);
        receiver.setZc(user.getZc()+amount);
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
        cache.addRcTransactionInMap(z_id,transaction);
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
