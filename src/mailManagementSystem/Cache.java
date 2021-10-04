package mailManagementSystem;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Cache {
    OBJ;
    private List<User> users=new ArrayList<>();
    private List<Group> groups=new ArrayList<>();
    Map<String,Group> groupMap=new HashMap<>();
    Map<String,User> userMap=new HashMap<>();
    public void addUserInList(User user)
    {
        users.add(user);
    }
    public List<User> getUsers()
    {
        return users;
    }
    public void addGroupsInList(Group group)
    {
        groups.add(group);
    }
    public List<Group> getGroups()
    {
        return groups;
    }
    public void setGroupMap(Group group)
    {
        groupMap.put(group.getGroupName(),group);
    }
    public Map<String,Group> getGroupMap()
    {
        return groupMap;
    }
    public void updateListUser(List<User> list)
    {
       for (User user:list)
       {
           userMap.put(user.getUserName(),user);
       }
    }
    public void setUserMap(User user)
    {
        userMap.put(user.getUserName(),user);
    }
    public Map<String,User> getUserMap()
    {
        return userMap;
    }
}
