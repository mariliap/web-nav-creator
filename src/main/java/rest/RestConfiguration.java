package rest;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ServerProperties;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ApplicationPath("resources")
public class RestConfiguration extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap();
        properties.put(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        return properties;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(JacksonFeature.class);
        resources.add(ExecucaoResource.class);
        resources.add(VersionUpdateResource.class);
    }
}
