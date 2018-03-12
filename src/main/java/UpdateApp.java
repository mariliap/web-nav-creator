import util.PathEnum;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.*;
import java.util.Properties;

/**
 * Created by Marilia on 25/02/2018.
 */
public class UpdateApp {

    public String localVersion = null;
    public String remoteVersion = null;

    public UpdateApp(){
        loadVersions();
    }

    public static void main(String[] args){



        UpdateApp updateApp = new UpdateApp();

        String workDir = System.getProperty("user.dir");
        System.out.println(workDir+ "      localVersion="+updateApp.localVersion+", remoteVersion="+updateApp.remoteVersion);

        if(updateApp.isNewVersionAvailable3()) { //first check update from database


            if(updateApp.copyMainJarFileFromServer()){ //copy newMain.jar from server and paste
                if(new File(workDir+"/lib/web-nav-creator-"+updateApp.localVersion+".jar").delete()) {
                    //rename(new File("newMain.jar"));
                    System.out.println("DELETOU");
                }
                finishUpdate();
            }

            try{
                Thread.sleep(10000);
            } catch(Exception e){
                e.printStackTrace();
            }
        }


    }

    private void loadVersions(){
        Properties properties = new Properties();

        try {

            //properties.load(UpdateApp.class.getResourceAsStream("/main/resources/version.properties"));
            properties.load(UpdateApp.class.getResourceAsStream(PathEnum.VERSION_POPERTIES.getPath()));
            this.localVersion = properties.getProperty("version");

            String remoteVersion = "";
//            properties.load(UpdateApp.class.getResourceAsStream("/main/resources/server.properties"));
            properties.load(UpdateApp.class.getResourceAsStream(PathEnum.SERVER_POPERTIES.getPath()));
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
                this.remoteVersion = output;
                System.out.println(output);
            }

            conn.disconnect();

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
        }
    }

    public boolean isNewVersionAvailable2() {
        return false;
    }

    public boolean isNewVersionAvailable() {
        //todo
        boolean needsUpdate = false;

        if(!this.localVersion.equalsIgnoreCase(this.remoteVersion)){
            needsUpdate = true;
        }
        System.out.println(localVersion + " | " + remoteVersion );
        return needsUpdate;

//        Properties properties = new Properties();
////        InputStream input = null;
//
//
//        try {
//
//            properties.load(UpdateApp.class.getResourceAsStream("/main/resources/version.properties"));
//            this.localVersion = properties.getProperty("version");
//
//            String remoteVersion = "";
//            properties.load(UpdateApp.class.getResourceAsStream("/main/resources/server.properties"));
//            String remoteAddress = properties.getProperty("remoteAddress");
//            String remotePort = properties.getProperty("remotePort");
//             // "http://localhost:12346/rest/version";
//            URL url = new URL(String.format("%s://%s:%s/rest/version", "http", remoteAddress, remotePort));
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Accept", "application/json");
//
//            if (conn.getResponseCode() != 200) {
//                throw new RuntimeException("Failed : HTTP error code : "
//                        + conn.getResponseCode());
//            }
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(
//                    (conn.getInputStream())));
//
//            String output;
//            System.out.println("Output from Server .... \n");
//            while ((output = br.readLine()) != null) {
//                this.remoteVersion = output;
//                System.out.println(output);
//            }
//
//            conn.disconnect();
//
//
//            if(!localVersion.equalsIgnoreCase(remoteVersion)){
//                needsUpdate = true;
//            }
//            System.out.println(localVersion + " | " + remoteVersion );
//
////            input = new FileInputStream("../resources/version.properties");
////            properties.load(input);
////            System.out.println(properties.getProperty("version"));
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            //System.out.println("Nao achou arquivo de propriedades");
//        } finally {
////            if (input != null) {
////                try {
////                    input.close();
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            }
//        }


    }

    public boolean isNewVersionAvailable3() {

        return true;
    }

    public boolean copyMainJarFileFromServer() {
        //todo

        //complete absolute path from where your application was initialized
        String workDir = System.getProperty("user.dir");
        Path mainJar = Paths.get(workDir + "/lib/web-nav-creator-"+this.localVersion+".jar");
        Path nextMainJar = Paths.get(workDir + "/lib/web-nav-creator-1.1-SNAPSHOT.jar");
        CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
        };
        //if (Files.exists(nextMainJar)) {
            try {
                Files.copy(mainJar, nextMainJar, options);
            } catch (IOException e) {
                e.printStackTrace();
            }
        //}
        //web-nav-creator-1.0-SNAPSHOT
        System.out.println(mainJar);
        System.out.println(nextMainJar);

        return true;
    }

    static void rename(File file){
        file.renameTo(new File("main.jar"));
    }

    public static void initUpdate(){
        if(Desktop.isDesktopSupported()){
            try {
                String workDir = System.getProperty("user.dir");
                Desktop.getDesktop().open(new File(workDir + "/bin/update.bat"));
            } catch (IOException ex) {}
        }
    }

    public static void finishUpdate(){
//        URLClassLoader classLoader = new URLClassLoader(new URL[] {mainJar.toURL()});
//        Class<?> appClass = classLoader.find("mainjar.MainApp");
//        ... instantiate the app

        if(Desktop.isDesktopSupported()){
            try {
                String workDir = System.getProperty("user.dir");
                Desktop.getDesktop().open(new File(workDir + "/bin/main.bat"));
                System.exit(0);
            } catch (IOException ex) {}
        }
    }
}
