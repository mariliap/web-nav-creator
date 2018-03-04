import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

    public boolean isNewVersionAvailable2() {
        return false;
    }

    public boolean isNewVersionAvailable() {
        //todo
        boolean needsUpdate = false;

        Properties properties = new Properties();
//        InputStream input = null;


        try {

            properties.load(UpdateApp.class.getResourceAsStream("/main/resources/version.properties"));
            String localVersion = properties.getProperty("version");
            String remoteVersion = "";

            properties.load(UpdateApp.class.getResourceAsStream("/main/resources/server.properties"));
            String remoteAddress = properties.getProperty("remoteAddress");
            String remotePort = properties.getProperty("remotePort");

            // "http://localhost:12346/rest/version";
            URL url = new URL(String.format("%s://%s:%s/rest/version", "http", remoteAddress, remotePort));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                remoteVersion = output;
                System.out.println(output);
            }

            conn.disconnect();


            if(!localVersion.equalsIgnoreCase(remoteVersion)){
                needsUpdate = true;
            }
            System.out.println(localVersion + " | " + remoteVersion );

//            input = new FileInputStream("../resources/version.properties");
//            properties.load(input);
//            System.out.println(properties.getProperty("version"));

        } catch (IOException ex) {
            ex.printStackTrace();
            //System.out.println("Nao achou arquivo de propriedades");
        } finally {
//            if (input != null) {
//                try {
//                    input.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }

        return needsUpdate;
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
