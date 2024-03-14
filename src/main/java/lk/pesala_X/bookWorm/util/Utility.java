package lk.pesala_X.bookWorm.util;

import java.io.IOException;
import java.util.Properties;

public class Utility {
    public static Properties getProperties(){
        Properties properties=new Properties();
        try{
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));
        }catch (IOException e){
            System.out.println("Property files unavailable");
            e.printStackTrace();
        }
        return properties;
    }
}
