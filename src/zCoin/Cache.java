package zCoin;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cache {
    private List<User> AllAccountCreationList=new ArrayList<>();
    private List<User> approvalAccountList=new ArrayList<>();
    private Map<Long,User> accountDetails=new HashMap<>();
    private Map<Long,List<RCTransaction>> rcTransactionList=new HashMap<>();

    public List<User> getAllAccountCreationList()
    {
        return AllAccountCreationList;
    }
    public List<User> getApprovalAccountList()
    {
        return approvalAccountList;
    }
    public void addCreationAccount(User user)
    {
       AllAccountCreationList.add(user);
    }
    public void addApprovalAccount(User user)
    {
        approvalAccountList.add(user);
        accountDetails.put(user.getZ_Id(),user);
    }
    public Map<Long,User> getAccountDetails()
    {
        return accountDetails;
    }
    public void addRcTransactionList(long z_id,RCTransaction transaction)
    {
      List<RCTransaction> list=rcTransactionList.getOrDefault(z_id,new ArrayList<>());
      list.add(transaction);
      rcTransactionList.put(z_id,list);
    }
    public  Map<Long,List<RCTransaction>> getRcTransactionList()
    {
        return rcTransactionList;
    }

}
