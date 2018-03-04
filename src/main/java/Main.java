import acoesBasics.Acao;
import acoesBasics.AcaoVazia;
import acoesVisitors.PaginaAcoesVisitor;
import elementos.Elemento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.*;

/**
 * Created by Marilia on 07/02/2018.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        //initServer();

        ChromeTest.setupClass();
        ChromeTest test = new ChromeTest();
        test.setupTest();
        test.test();
        test.teardown();



        //initFrameworkMockText();
    }

    public static void initServer(){
        try{
            Properties properties = new Properties();
            properties.load(UpdateApp.class.getResourceAsStream("/main/resources/server.properties"));

            initMockRemoteServer(properties);

            UpdateApp updateApp = new UpdateApp();
            if(!updateApp.isNewVersionAvailable()) {

                String localAddress = properties.getProperty("localAddress");
                int localPort = Integer.parseInt(properties.getProperty("localPort"));

                LocalServer main = new LocalServer();
                main.start(localAddress, localPort);

                displayTrayIcon(localPort, main.getServerWebContextPath());

                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(new URI(String.format("%s://%s:%d/ui", "http", localAddress, localPort)));
                    Desktop.getDesktop().browse(new URI(String.format("%s://%s:%d/rest/execucao", "http", localAddress, localPort)));
                }

                //main.waitForInterrupt();
            } else {
                UpdateApp.initUpdate();
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static void initMockRemoteServer(Properties properties) throws Exception {
        String remoteAddress = properties.getProperty("remoteAddress");
        int remotePort = Integer.parseInt(properties.getProperty("remotePort"));
        if(remoteAddress!= null && !remoteAddress.trim().isEmpty()
                && remoteAddress.equals("localhost")) {
            LocalServer remote = new LocalServer();
            remote.start(remoteAddress, remotePort);
        }
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

    private static void initFrameworkMockText(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hsqldb");
        EntityManager em = emf.createEntityManager();

        //PaginaAcoesVisitor pagina1 = new PaginaGenenericaAcoesVisitor();
        //PaginaAcoesVisitor pagina2 = new TestingDecoratorsAcoesVisitor();

        PaginaAcoesVisitor pagina1 = em.createQuery("SELECT b FROM PaginaAcoesVisitor b where b.id = 1", PaginaAcoesVisitor.class).getSingleResult();
        PaginaAcoesVisitor pagina2 = em.createQuery("SELECT b FROM PaginaAcoesVisitor b where b.id = 2", PaginaAcoesVisitor.class).getSingleResult();

        Elemento elemento1 = em.createQuery("SELECT e FROM Elemento e where e.id = 1", Elemento.class).getSingleResult();
        Elemento elemento2 = em.createQuery("SELECT e FROM Elemento e where e.id = 2", Elemento.class).getSingleResult();
        Elemento elemento3 = em.createQuery("SELECT e FROM Elemento e where e.id = 3", Elemento.class).getSingleResult();
        Elemento elemento4 = em.createQuery("SELECT e FROM Elemento e where e.id = 4", Elemento.class).getSingleResult();

        java.util.List<Acao> listaAcoes = em.createQuery("SELECT a FROM Acao a", Acao.class).getResultList();

        em.close();
        emf.close();

//        Elemento pagl_elemento1 = new Elemento();
//        pagl_elemento1.setNome("P1E1");
//        pagl_elemento1.setVisitor(pagina1);
//
//        Elemento pagl_elemento2 = new Elemento();
//        pagl_elemento2.setVisitor(pagina1);
//        pagl_elemento2.setNome("P1E2");
//
//        Elemento pagl_elemento3 = new Elemento();
//        pagl_elemento3.setVisitor(pagina1);
//        pagl_elemento3.setNome("P1E3");
//
//        Elemento pag2_elemento1 = new Elemento();
//        pag2_elemento1.setNome("P2E1");
//        pag2_elemento1.setVisitor(pagina2);


//        List<Acao> listaAcoes = new ArrayList<>();
//
//        Acao acao1 = new AcaoPreencherCampo();
//        acao1.setElemento(elemento1);
//        Acao acao1_1 = new AcaoClicarBotao();
//        acao1_1.setElemento(elemento1);
//
//        Acao acao2 = new AcaoPreencherCampo();
//        acao2.setElemento(elemento2);
//        Acao acao2_1 = new AcaoClicarBotao();
//        acao2_1.setElemento(elemento2);
//
//        Acao acao3 = new AcaoPreencherCampo();
//        acao3.setElemento(elemento3);
//        Acao acao3_1 = new AcaoClicarBotao();
//        acao3_1.setElemento(elemento3);
//
//        Acao acao4 = new AcaoPreencherCampo();
//        acao4.setElemento(elemento4);
//        Acao acao4_1 = new AcaoClicarBotao();
//        acao4_1.setElemento(elemento4);

//        Acao acao5 = new AcaoSelecionarItem();
//        acao5.setElemento(pagl_elemento1);
//        Acao acao6 = new AcaoValidarInformacao();
//        acao6.setElemento(pagl_elemento1);
//        Acao acao7 = new AcaoValidarInformacao();
//        acao7.setElemento(pagl_elemento1);
//        Acao acao8 = new AcaoValidarInformacao();
//        acao8.setElemento(pagl_elemento1);

//        listaAcoes.add(acao1);
//        listaAcoes.add(acao1_1);
//        listaAcoes.add(acao2);
//        listaAcoes.add(acao2_1);
//        listaAcoes.add(acao3);
//        listaAcoes.add(acao3_1);
//        listaAcoes.add(acao4);
//        listaAcoes.add(acao4_1);
//        listaAcoes.add(acao5);
//        listaAcoes.add(acao6);
//        listaAcoes.add(acao7);
//        listaAcoes.add(acao8);



        Acao teste = new AcaoVazia();
        for (Acao acao : listaAcoes) {
            teste.adicionar(acao);
        }
        teste.executar();
    }
}
