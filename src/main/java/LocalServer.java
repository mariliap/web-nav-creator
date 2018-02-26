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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


    private static final String WEBROOT_INDEX = "/webapp/";

    private static final String REST_API_INDEX = "/rest/";
    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    private static int port = 12345;
    private Server server;
    private URI serverURI;

    public static void main(String[] args) throws Exception
    {
        UpdateApp updateApp = new UpdateApp();
        if(!updateApp.isNewVersionAvailable()) {

            LocalServer main = new LocalServer();
            main.start();


            displayTrayIcon(port, main.getServerWebContextPath());

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI("http://localhost:"+port+"/ui"));
                Desktop.getDesktop().browse(new URI("http://localhost:"+port+"/rest/execucao"));
            }

            main.waitForInterrupt();
        } else {
            UpdateApp.initUpdate();
        }
    }

    public void start() throws Exception
    {
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
                + (String.format("%s://%s:%d", "http", "localhost", port)) + restApi.getContextPath()
        );

        LOG.info("WEB APP URI: "
                + (String.format("%s://%s:%d", "http", "localhost", port))
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
        URL indexUri = this.getClass().getResource(WEBROOT_INDEX);
        if (indexUri == null)
        {
            throw new FileNotFoundException("Unable to find resource " + WEBROOT_INDEX);
        }

        return indexUri.toURI();
    }

    private URI getRestApiResourceUri() throws FileNotFoundException, URISyntaxException
    {

        URL indexUri = this.getClass().getResource(REST_API_INDEX);
        if (indexUri == null)
        {
            throw new FileNotFoundException("Unable to find resource " + REST_API_INDEX);
        }

        return indexUri.toURI();
    }

    private WebAppContext getWebAppContext(URI baseUri, File scratchDir)
    {
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

    private static void displayTrayIcon(final int port, final String webAppContextPath) throws Exception {

        if (!GraphicsEnvironment.isHeadless()) {
            final TrayIcon trayIcon =
                    new TrayIcon(new ImageIcon(Main.class.getResource("/images/pf.png")).getImage());
            trayIcon.setImageAutoSize(true);
            trayIcon.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ev) {
                    try {
                        Desktop.getDesktop().browse(new URI("http://localhost:" + port));
                    } catch (Exception e) {
                    }
                }
            });
            PopupMenu popup = new PopupMenu();
            MenuItem browseAction = new MenuItem("Navegar");
            browseAction.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Desktop.getDesktop().browse(new URI("http://localhost:" + port + webAppContextPath));
                    } catch (Exception ex) {
                    }
                }
            });
            MenuItem quitAction = new MenuItem("Fechar");
            quitAction.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            MenuItem updateAction = new MenuItem("Atualizar");
            quitAction.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    UpdateApp.initUpdate();
                }
            });

            popup.add(browseAction);
            popup.add(quitAction);
            popup.add(updateAction);
            trayIcon.setPopupMenu(popup);
            SystemTray.getSystemTray().add(trayIcon);
            trayIcon.displayMessage(
                    "Jetty Embedded Server (http://localhost:" + port + ")",
                    "Click this icon to open the browser.", TrayIcon.MessageType.INFO);
        }
    }


}
