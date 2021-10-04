package mailManagementSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public enum MailSystem {
    OBJ;
    public String setUsersInList(User user) throws ExceptionHandler {
        if(user==null) {
            throw new ExceptionHandler(" user  is null");
        }
        Cache.OBJ.addUserInList(user);
        Cache.OBJ.setUserMap(user);
        return "Users added successfully";
    }
    public List<User> getUser()
    {
        return Cache.OBJ.getUsers();
    }
    public String setGroupsInList(Group group) throws ExceptionHandler {
        if(group==null) {
            throw new ExceptionHandler(" group  is null");
        }
        Cache.OBJ.addGroupsInList(group);
        Cache.OBJ.setGroupMap(group);
        return "Users added successfully";
    }
    public List<Group> getGroups()
    {
        return Cache.OBJ.getGroups();
    }
    public boolean validateGroupNameAndEmail(String groupName,String email)
    {
        List<Group> groups=getGroups();
        if (groups.size()==0)
        {
            String gMail=email.substring(0,4);
            String uMail="user";
            if(uMail.equals(gMail))
            {
                return false;
            }
            return true;
        }
        for (Group group:groups)
        {
            String name=group.getGroupName();
            String mail=group.getEmail();
            String gMail=mail.substring(0,5);
            String uMail="user";
            if(name.equals(groupName)||mail.equals(email)||gMail.equals(uMail))
            {
                return false;
            }

        }
        return true;
    }
    public boolean validateUserNameAndEmail(String userName,String email)
    {
      List<User> list=getUser();
      if (list.size()==0)
      {
          String gMail="group";
          String uMail=email.substring(0,5);
          if(uMail.equals(gMail))
          {
              return false;
          }
          return true;
      }
      for(User user:list)
      {
          String name=user.getUserName();
          String mail=user.getEmail();
          String uMail=mail.substring(0,5);
          String gMail="group";
          if(name.equals(userName)||mail.equals(email)||uMail.equals(gMail))
          {
              return false;
          }
      }
      return true;
    }
    public boolean isValidGroup(String groupName)
    {
        Map<String,Group> groupMap=getGroupMap();
        Group group=groupMap.get(groupName);
        if(group==null)
        {
            return false;
        }
        return true;
    }
    public boolean isValidUser(String userName)
    {
        Map<String,User> userMap=getUserMap();
        User user=userMap.get(userName);
        if (user==null)
        {
            return false;
        }
        return true;
    }
    public boolean isValidEmail(String mail)
    {
        Map<String,Group> groupMap=getGroupMap();
        Map<String,User> userMap=getUserMap();
        for (Map.Entry<String,User> k: userMap.entrySet())
        {
            User user= k.getValue();
            if(user.getEmail().equals(mail))
            {
                return true;
            }
        }
        return false;
        /*User user= userMap.get(mail);
        System.out.println(user);
        if(user==null)
        {
            return false;
        }
        return true;*/
    }
    public String addGroup(String groupName,String userName)
    {
        User user= getUserMap().get(userName);
        Group group=getGroupMap().get(groupName);
        List<User> list=group.getMembers();
        if(list==null)
        {
            list=new ArrayList<>();
        }
        if(list.contains(user)) {
           return "you are already exist in the group";
        }
        list.add(user);
        group.setMembers(list);
        Cache.OBJ.setGroupMap(group);
        return "Add user successfully";
    }
    public String removeGroup(String groupName,String userName)
    {
        User user= getUserMap().get(userName);
        Group group=getGroupMap().get(groupName);
        List<User> list=group.getMembers();
        if(list==null)
        {
            list=new ArrayList<>();
        }
        if(!list.contains(user))
        {
            return "Entered user not exit in the group so you can't remove it";
        }
        list.remove(user);
        group.setMembers(list);
        Cache.OBJ.setGroupMap(group);
        return "remove user successfully";
    }
    public String mailSend(Mail mail,String sender,String receiver)
    {
            User user=getUserMap().get(sender);
            User user1=getUserMap().get(receiver);
            Group group=getGroupMap().get(receiver);
            if(user1!=null)
            {
                user=userSendMailUpdate(user,mail);
                user1=userInboxMailUpdate(user1,mail);
                Cache.OBJ.setUserMap(user);
                Cache.OBJ.setUserMap(user1);
            }
            else if(group!=null)
            {
                user=userSendMailUpdate(user,mail);
               List<User> list=group.getMembers();
               for (User users:list)
               {
                   userInboxMailUpdate(users,mail);
               }
               Cache.OBJ.updateListUser(list);
            }

        return "send successfully";
    }
    public User userSendMailUpdate(User user,Mail mail)
    {
        List<Mail> sendList = user.getSendMail();
        if (sendList == null) {
            sendList = new ArrayList<>();
        }
        sendList.add(mail);
        user.setSendMail(sendList);
      return user;
    }
    public User userInboxMailUpdate(User user,Mail mail)
    {
        List<Mail> inbox = user.getInboxMail();
        if (inbox == null) {
            inbox = new ArrayList<>();
        }
        inbox.add(mail);
        user.setInboxMail(inbox);
        return user;
    }
    public boolean mailValidation(String mail)
    {
        String regex="^[A-za-z0-9-._]+@[A-za-z-0-9-._]+$";
        return Pattern.matches(regex, mail);
    }
    public Map<String,Group> getGroupMap()
    {
        return Cache.OBJ.getGroupMap();
    }
    public Map<String,User> getUserMap()
    {
        return Cache.OBJ.getUserMap();
    }
    public String getNameForGivenEmail(String mail)
    {
        Map<String,User> map=getUserMap();
        for (User user:map.values())
        {
            if(user.getEmail().equals(mail))
            {
                return user.getUserName();
            }
        }
        Map<String,Group> groupMap=getGroupMap();
        for (Group group:groupMap.values())
        {
            if(group.getEmail().equals(mail))
            {
                return group.getGroupName();
            }
        }
        return null;
    }
}
