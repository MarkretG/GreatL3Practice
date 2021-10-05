package properties;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class createProperties {
    public static void main(String[] args)  {
        String path=System.getProperty("user.dir")+File.separator+"src"+File.separator+"properties"+File.separator+"user.properties";
        File file=new File(path);
        try {
            file.createNewFile();
            FileWriter fileWriter=new FileWriter(file);
            Properties p=new Properties();
            p.setProperty("jguk","u9ou");
            p.setProperty("mark","1234");
            p.setProperty("gtrty","rtg");
            p.setProperty("vino","5467");
            p.store(fileWriter,"***");
            FileReader fr=new FileReader(file);
            p.load(fr);
            System.out.println(p.getProperty("mark"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
