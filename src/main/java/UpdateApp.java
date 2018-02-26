import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Created by Marilia on 25/02/2018.
 */
public class UpdateApp {

    public static void main(String[] args){
        if(isUpdateVersionAvailable()) { //first check update from database
            try{
                Thread.sleep(5000);
            } catch(Exception e){
                e.printStackTrace();
            }
            if(copyMainJarFileFromServer()){ //copy newMain.jar from server and paste
                new File("main.jar").delete();
                rename(new File("newMain.jar"));

                finishUpdate();
            }
        }


    }

    public boolean isNewVersionAvailable() {
        //todo


        Properties properties = new Properties();
        InputStream input = null;

        try {

            properties.load(this.getClass().getResourceAsStream("version.properties"));
            System.out.println(properties.getProperty("pversion"));

//            input = new FileInputStream("../resources/version.properties");
//            properties.load(input);
//            System.out.println(properties.getProperty("version"));

        } catch (IOException ex) {
            //ex.printStackTrace();
            System.out.println("Nao achou arquivo de propriedades");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    public static boolean isUpdateVersionAvailable() {
        //todo
        return true;
    }

    static boolean copyMainJarFileFromServer() {
        //todo

        String workDir = System.getProperty("user.dir");
        Path mainJar = Paths.get(workDir + "...");
        Path nextMainJar = Paths.get(workDir + "...");
        if (Files.exists(nextMainJar)) {
            //Files.copy(nextMainJar, mainJar, StandardCopyAction.REPLACE_EXISTING);
        }

        return true;
    }

    static void rename(File file){
        file.renameTo(new File("main.jar"));
    }

    public static void initUpdate(){
        if(Desktop.isDesktopSupported()){
            try {
                Desktop.getDesktop().open(new File("update.jar"));
                System.exit(0);
            } catch (IOException ex) {}
        }
    }

    public static void finishUpdate(){
//        URLClassLoader classLoader = new URLClassLoader(new URL[] {mainJar.toURL()});
//        Class<?> appClass = classLoader.find("mainjar.MainApp");
//        ... instantiate the app

        if(Desktop.isDesktopSupported()){
            try {
                Desktop.getDesktop().open(new File("main.jar"));
                System.exit(0);
            } catch (IOException ex) {}
        }
    }
}
