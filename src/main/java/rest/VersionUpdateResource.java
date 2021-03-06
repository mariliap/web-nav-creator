package rest;

import util.PathEnum;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.Serializable;
import java.util.Properties;

@Path("/version")
public class VersionUpdateResource implements Serializable{


    @GET
    @Produces({"application/json"})
    public String obterVersaoAtual() {
        Properties properties = new Properties();
        String localVersion = "";

        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream(PathEnum.VERSION_POPERTIES.getPath()));
            localVersion = properties.getProperty("version");
        } catch (Exception ex) {
            localVersion="ERROR";
            System.out.println("Nao achou arquivo de propriedades");
        }
        return localVersion;
    }





}
