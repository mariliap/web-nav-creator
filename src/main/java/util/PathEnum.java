package util;

/**
 * Created by Marilia on 11/03/2018.
 */
public enum PathEnum {

    WEBROOT_INDEX("/"),//webapp/"),
    REST_API_INDEX("/rest/"),
    VERSION_POPERTIES("version.properties"),//"/main/resources/version.properties"
    SERVER_POPERTIES("server.properties");//"/main/resources/server.properties"



    private final String path;


    PathEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
