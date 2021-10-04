package mailManagementSystem;
import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Scanner;
public class MailManagementSystem {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        MailManagementSystem obj=new MailManagementSystem();
        //List<User> users=new ArrayList<>();
        Menu:
        while (true)
        {
            System.out.println("1.USER CREATION");
            System.out.println("2.GROUP CREATION");
            System.out.println("3.Group Assignment");
            System.out.println("4.Compose mail");
            System.out.println("5.Exit");
            int choice=sc.nextInt();
            switch (choice)
            {
                case 1: {
                    System.out.println("Enter user name");
                    String userName = sc.next();
                    System.out.println("Enter email");
                    String email = sc.next();
                    boolean result=MailSystem.OBJ.mailValidation(email);
                    while (result==false)
                    {
                        System.out.println("enter email in this format XXX@gmail.com");
                        email=sc.next();
                        result= MailSystem.OBJ.mailValidation(email);
                    }
                        System.out.println("Enter password");
                    String password = sc.next();
                    if (MailSystem.OBJ.validateUserNameAndEmail(userName, email)) {
                        System.out.println("create user successfully");
                    } else {
                        System.out.println("please enter unique user name  and password");
                        System.out.println("Go to user creation again");
                        continue Menu;
                    }
                    User user = obj.getUser(userName, email, password);
                    try {
                        MailSystem.OBJ.setUsersInList(user);
                    } catch (ExceptionHandler e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }
                    List<User> users = MailSystem.OBJ.getUser();
                    obj.printUser(users);
                }
                    break;
                case 2: {
                    System.out.println("Enter group name");
                    String groupName = sc.next();
                    System.out.println("Enter group email");
                    String email = sc.next();
                    obj.validateMailAddress(email);
                    System.out.println("enter group password");
                    String pass = sc.next();
                    System.out.println("enter group description");
                    String description = sc.next();
                    if (MailSystem.OBJ.validateGroupNameAndEmail(groupName, email)) {
                        System.out.println("crete group successfully");
                    } else {
                        System.out.println("please enter unique group name  and password");
                        System.out.println("Go to group creation again");
                        continue Menu;
                    }
                    Group group = obj.getGroup(groupName, email, pass, description);
                    try {
                        MailSystem.OBJ.setGroupsInList(group);
                    } catch (ExceptionHandler e) {
                        e.printStackTrace();
                    }
                    List<Group> groups = MailSystem.OBJ.getGroups();
                    obj.printGroup(groups);
                }
                break;
                case 3: {
                    System.out.println("Enter group name");
                    String groupName = sc.next();
                    boolean result = MailSystem.OBJ.isValidGroup(groupName);
                    while (result == false) {
                        System.out.println("Entered group name not exist\nplease enter valid group name");
                        groupName = sc.next();
                        result = MailSystem.OBJ.isValidGroup(groupName);
                    }
                    System.out.println("Enter user name");
                    String userName = sc.next();
                    boolean result1 = MailSystem.OBJ.isValidUser(userName);
                    while (result1 == false) {
                        System.out.println("Entered user name not exist\nplease enter valid user name");
                        userName = sc.next();
                        result1 = MailSystem.OBJ.isValidUser(userName);
                    }

                    boolean end=true;
                    while (end)
                    {
                        System.out.println("1.Add\n2.Remove\n3.exit");
                        int ch=sc.nextInt();
                        if(ch==1)
                        {
                            String res=MailSystem.OBJ.addGroup(groupName,userName);
                            System.out.println(res);
                            Group group=MailSystem.OBJ.getGroupMap().get(groupName);
                            System.out.println(group);
                            continue Menu;
                        }
                        if(ch==2)
                        {
                            String res=MailSystem.OBJ.removeGroup(groupName,userName);
                            System.out.println(res);
                            Group group=MailSystem.OBJ.getGroupMap().get(groupName);
                            System.out.println(group);
                            continue Menu;
                        }
                        if(ch==3)
                        {
                            end=false;
                            continue Menu;
                        }
                    }
                }
                break;
                case 4:
                {
                    String sender="";
                    String receiver="";
                    System.out.println("Enter from user");
                    String from=sc.next();
                    boolean result1 = MailSystem.OBJ.isValidUser(from);
                    while (result1 == false) {
                        System.out.println("Entered user name not exist\nplease enter valid user name");
                        from = sc.next();
                        result1 = MailSystem.OBJ.isValidUser(from);
                    }
                    sender=from;
                    from= obj.getEmail(from);
                    System.out.println("Enter To Address");
                    String to = sc.next();
                    System.out.println("to"+to);
                    to=obj.validateMailAddress(to);
                    receiver=MailSystem.OBJ.getNameForGivenEmail(to);
                    if(receiver==null)
                    {
                        System.out.println("to mail address does not exit\n please again compose mail");
                        continue Menu;
                    }
                    System.out.println("Enter subject");
                    String subject= sc.next();
                    System.out.println("Enter content");
                    String content=sc.next();
                    System.out.println("content"+content);
                    Mail mail=obj.getMail(from,to,subject,content);
                    //System.out.println("*****"+mail);
                    //System.out.println("after mail send***"+mail);
                    String res=MailSystem.OBJ.mailSend(mail,sender,receiver);
                    System.out.println(res);
                    User user=MailSystem.OBJ.getUserMap().get(sender);
                    User user1=MailSystem.OBJ.getUserMap().get(receiver);
                    System.out.println(user);
                    System.out.println(user1);
                }
                break;
                case 5:
                {
                    System.out.println("Enter user name");
                    String name= sc.next();
                    User user=MailSystem.OBJ.getUserMap().get(name);
                    List<Mail> inboxMail=user.getInboxMail();
                    obj.printInboxList(inboxMail);
                }
                break;
                case 6:
                {
                    System.out.println("Enter user name");
                    String name= sc.next();
                    User user=MailSystem.OBJ.getUserMap().get(name);
                    List<Mail> sendMail=user.getSendMail();
                    obj.printSendMailList(sendMail);

                }
                break;
                case 7:
                {
                    System.out.println("Enter user name");
                    String name= sc.next();
                    System.out.println("1.Delete send Mail\n1.Delete inboxMail\n3.Recall mail");
                    int ch=sc.nextInt();
                    if(ch==1)
                    {
                        System.out.println("Enter serial no");
                        int n=sc.nextInt();
                        List<Mail> sendMail=MailSystem.OBJ.getUserMap().get(name).getSendMail();
                        sendMail.remove(sendMail.size()-n);
                    }
                    else if (ch==2)
                    {
                        System.out.println("Enter serial no");
                        int n=sc.nextInt();
                        List<Mail> inboxMail=MailSystem.OBJ.getUserMap().get(name).getInboxMail();
                        inboxMail.remove(inboxMail.size()-n);
                    }
                    else if(ch==3)
                    {
                        System.out.println("Enter serial no");
                        int n=sc.nextInt();
                        List<Mail> sendMail=MailSystem.OBJ.getUserMap().get(name).getSendMail();
                        sendMail.remove(sendMail.size()-n);
                    }
                    continue Menu;
                }
                case 8:
                {
                    System.out.println("Enter user name");
                    String name= sc.next();
                    System.out.println("Enter shared user name");
                    String sharedUserName=sc.next();
                }
                break;

            }
        }
    }
    public User getUser(String userName,String email,String pass)
    {
        User user=new User();
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassword(pass);
        return user;
    }
    public Group getGroup(String groupName,String mail,String pass,String des)
    {
        Group group=new Group();
        group.setGroupName(groupName);
        group.setEmail(mail);
        group.setPassword(pass);
        group.setDescription(des);
        return group;
    }
    public Mail getMail(String from,String to,String subject,String content)
    {
        Mail mail=new Mail();
        mail.setFrom(from);
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setContent(content);
        return mail;
    }
    public void printUser(List<User> list)
    {
        System.out.println("-----------------------------------------------");
        System.out.printf("%10s %10s %7s","Group NAME","EMAIL","PASSWORD");
        System.out.println();
        System.out.println("------------------------------------------------");
        for (User user:list)
        {
            System.out.format("%10s %10s %10s",user.getUserName(),user.getEmail(),user.getPassword());
            System.out.println();
        }
    }
    public void printGroup(List<Group> list)
    {
        System.out.println("-----------------------------------------------");
        System.out.printf("%10s %10s %7s %7s","USER NAME","EMAIL","PASSWORD","DESCRIPTION");
        System.out.println();
        System.out.println("------------------------------------------------");
        for (Group group:list)
        {
            System.out.format("%10s %10s %10s %10s",group.getGroupName(),group.getEmail(),group.getPassword(),group.getDescription());
            System.out.println();
        }
    }
    private void printInboxList( List<Mail> inbox)
    {

        for (int i=inbox.size()-1;i>=0;i--)
        {
            System.out.println(inbox.get(i));
        }
    }
    private void printSendMailList(List<Mail> sendMail)
    {
        for (int i=sendMail.size()-1;i>=0;i--)
        {
            System.out.println(sendMail.get(i));
        }
    }
    public String  validateMailAddress(String email)
    {
        Scanner scanner=new Scanner(System.in);
        boolean result = MailSystem.OBJ.mailValidation(email);
        while (result == false) {
            System.out.println("enter email in this format XXX@gmail.com");
            email = scanner.next();
            result = MailSystem.OBJ.mailValidation(email);
        }
        return email;
    }
    public String getEmail(String userName)
    {
        String mail=MailSystem.OBJ.getUserMap().get(userName).getEmail();
        return mail;
    }
}
