package zCoin;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cache {
    double rcToZcConversionRate=2;
    private List<User> AllAccountCreationList=new ArrayList<>();
    private List<User> approvalAccountList=new ArrayList<>();
    private Map<Long,User> accountDetails=new HashMap<>();
    private Map<Long,List<RCTransaction>> rcTransactionMap=new HashMap<>();
    private Map<Long,List<ZCTransaction>> zcTransactionMap=new HashMap<>();

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
    public void addRcTransactionInMap(long z_id,RCTransaction transaction)
    {
      List<RCTransaction> list=rcTransactionMap.getOrDefault(z_id,new ArrayList<>());
      list.add(transaction);
      rcTransactionMap.put(z_id,list);
    }
    public  Map<Long,List<RCTransaction>> getRcTransactionMap()
    {
        return rcTransactionMap;
    }

    public void addZcTransactionInMap(long z_id,ZCTransaction transaction)
    {
        List<ZCTransaction> list=zcTransactionMap.getOrDefault(z_id,new ArrayList<>());
        list.add(transaction);
        zcTransactionMap.put(z_id,list);
    }
    public  Map<Long,List<ZCTransaction>> getZcTransactionMap()
    {
        return zcTransactionMap;
    }
    public void setConversionRate(double conversionRate)
    {
        rcToZcConversionRate=conversionRate;
    }
    public double getRcToZcConversionRate()
    {
        return rcToZcConversionRate;
    }

}
