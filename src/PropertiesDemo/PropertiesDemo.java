package PropertiesDemo;

import java.awt.Font;
import java.io.FileInputStream;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class PropertiesDemo extends JFrame {
    
    public PropertiesDemo()  {
        Properties p = new Properties();
        try{
            p.load(new FileInputStream("src/PropertiesDemo/DemoProperties.properties"));
           // p.loadFromXML(new FileInputStream("src/PropertiesDemo/Settings2.xml"));
        }
        catch (Exception e){
           e.printStackTrace();
        }
        
        String mess = p.getProperty("message", "Hello");
        JLabel l = new JLabel(mess, JLabel.CENTER);
        add(l);
        l.setOpaque(true);
        String fName = p.getProperty("fontName", "Serif");
        String fStyle = p.getProperty("fontStyle", "PLAIN");
        int ifStyle;
        if (fStyle.equals("BOLD")){
            ifStyle = Font.BOLD;
        }
        else if(fStyle.equals("ITALIC")){
            ifStyle = Font.ITALIC;
        }
        else ifStyle = Font.PLAIN;
        
        int fSize = Integer.parseInt(p.getProperty("fontSize", "24"));
        l.setFont(new Font(fName, ifStyle, fSize));
        setSize(400,150);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    
    public static void main(String[] args){
        PropertiesDemo ö = new PropertiesDemo();
    }
    
}
