import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import rest.RestConfiguration;
import util.PathEnum;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Logger;

/**
 * Created by Marilia on 25/02/2018.
 */
public class LocalServer {



    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    private int address;
    private int port;// = 12345;
    private Server server;
    private URI serverURI;

    public void start(String address, int port) throws Exception
    {
        this.port = port;
        server = new Server();
        ServerConnector connector = connector();
        server.addConnector(connector);

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));

        URI baseUri = getWebRootResourceUri();
        WebAppContext webAppContext = getWebAppContext(baseUri, null);

        URI baseRestUri = getRestApiResourceUri();
        ServletContextHandler restApi = setupRestApiContextHandler(baseRestUri);


        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(new Handler[]{restApi, webAppContext});

        server.setHandler(contexts);
        server.start();

//        if (LOG.isLoggable(Level.FINE))
//        {
//            LOG.fine(server.dump());
//        }
        //this.serverURI = getServerUri(connector);

        LOG.info("REST URI: "
                + (String.format("%s://%s:%d", "http", address, port)) + restApi.getContextPath()
        );

        LOG.info("WEB APP URI: "
                + (String.format("%s://%s:%d", "http", address, port))
                + webAppContext.getContextPath()
        );
    }

    private ServerConnector connector()
    {
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        return connector;
    }

    private URI getWebRootResourceUri() throws FileNotFoundException, URISyntaxException
    {

        URL indexUri = this.getClass().getResource(PathEnum.WEBROOT_INDEX.getPath());
        if (indexUri == null)
        {
            throw new FileNotFoundException("Unable to find resource " + PathEnum.WEBROOT_INDEX.getPath());
        }

        return indexUri.toURI();
    }

    private URI getRestApiResourceUri() throws FileNotFoundException, URISyntaxException
    {

        URL indexUri = this.getClass().getResource(PathEnum.REST_API_INDEX.getPath());
        if (indexUri == null)
        {
            throw new FileNotFoundException("Unable to find resource " + PathEnum.REST_API_INDEX.getPath());
        }

        return indexUri.toURI();
    }

    private WebAppContext getWebAppContext(URI baseUri, File scratchDir)
    {
//        String rootPath = LocalServer.class.getClassLoader().getResource(".").toString();
//        WebAppContext context = new WebAppContext(rootPath + "../../src/main/webapp", "");

        WebAppContext context = new WebAppContext();
        context.setContextPath("/ui");
        //context.setAttribute("javax.servlet.context.tempdir", scratchDir);
        context.setResourceBase(baseUri.toASCIIString());
        context.setParentLoaderPriority(true);

        return context;
    }

    private ServletContextHandler setupRestApiContextHandler(URI baseRestUri) {

        ResourceConfig config = ResourceConfig.forApplicationClass(RestConfiguration.class);
        config.packages(RestConfiguration.class.getPackage().getName());

        final ServletHolder servletHolder = new ServletHolder(new ServletContainer(config));
        servletHolder.setInitOrder(1);


        final ServletContextHandler context =
                new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/rest");
        context.setResourceBase(baseRestUri.toASCIIString());

        //context.addEventListener(new CdiServletRequestListener());
        context.addServlet(servletHolder, "/*");
        return context;
    }

//    private URI getServerUri(ServerConnector connector) throws URISyntaxException
//    {
//        String scheme = "http";
//        for (ConnectionFactory connectFactory : connector.getConnectionFactories())
//        {
//            if (connectFactory.getProtocol().equals("SSL-http"))
//            {
//                scheme = "https";
//            }
//        }
//        String host = connector.getHost();
//        if (host == null)
//        {
//            host = "localhost";
//        }
//        int port = connector.getLocalPort();
//        serverURI = new URI(String.format("%s://%s:%d/", scheme, host, port));
//        LOG.info("Server URI: " + serverURI);
//        return serverURI;
//    }

    public String getServerWebContextPath() throws InterruptedException
    {
        Handler[] handlers = server.getChildHandlersByClass(WebAppContext.class);
        if(handlers.length != 0) {
            WebAppContext handler = (WebAppContext) handlers[0];
            return  handler.getContextPath();
        }

        return "";
    }

    public void stop() throws Exception
    {
        server.stop();
    }

    /**
     * Cause server to keep running until it receives a Interrupt.
     * <p>
     * Interrupt Signal, or SIGINT (Unix Signal),
     * is typically seen as a result of a kill -TERM {pid} or Ctrl+C
     * @throws InterruptedException if interrupted
     */
    public void waitForInterrupt() throws InterruptedException
    {
        server.join();
    }

}
