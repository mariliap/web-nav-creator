import acoesBasics.Acao;
import acoesBasics.AcaoVazia;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


/**
 * Created by Marilia on 03/03/2018.
 */
public class ChromeTest {

    private WebDriver driver;

    //@BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    //@Before
    public void setupTest() {
        driver = new ChromeDriver();
    }

    //@After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    //@Test
    public void test(){

        //driver.get("http://webmail.cge.ce.gov.br/zimbra/");


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hsqldb");
        EntityManager em = emf.createEntityManager();

        List<Acao> listaAcoes = em.createQuery("SELECT a FROM Acao a where a.id in (9,10,11,12,13,14)", Acao.class).getResultList();

        em.close();
        emf.close();
        try {
            Acao teste = new AcaoVazia();
            for (Acao acao : listaAcoes) {
                acao.setDriver(driver);
                teste.adicionar(acao);
            }
            teste.executar();


            Thread.sleep(10000);

        }catch (InterruptedException ex) {
            ex.printStackTrace();

            teardown();
        }

        System.out.println("\nFIM");
    }

}
