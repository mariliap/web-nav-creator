package main.java;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import main.java.testes.cadastroparceiro.Cartao9321Teste01;
import main.java.testes.cadastroparceiro.Cartao968Teste01;
import main.java.testes.cadastroparceiro.TesteAlteracaoParceiro;
import main.java.testes.cadastroparceiro.TesteComoColaboradorNegocio;
import main.java.testes.cadastroparceiro.TesteInclusaoParceiro;
import main.java.utilidades.Utils;

public class BrowserInvocation {

	public static void main(String[] args) {
		System.out.println("Início do teste");
		long tempoInicial = System.currentTimeMillis();
		
		System.setProperty("webdriver.chrome.driver", "recursos/chromedriver_2.37/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
//		driver.get("http://localhost:8080/e-parcerias-web/");
//		TelaLogin telaLogin = new TelaLogin();
//		telaLogin.logarNoSistema(driver);
		
		
		//driver.get("http://localhost:8080/e-parcerias-web/");
		//driver.get("http://e-parceriasdesenv.cge.local/");
		String url = "http://localhost:8080/e-parcerias-web/";
//		String url = "http://172.24.2.59:8280/e-parcerias-web/";//desenv
		
		
		TesteAlteracaoParceiro testeEdicao = new TesteAlteracaoParceiro();
//		testeEdicao.testar(driver);
		
		TesteInclusaoParceiro testeInculsao = new TesteInclusaoParceiro(tempoInicial);
		Boolean preCadastrar = false;
		testeInculsao.testar(driver, url, preCadastrar);
//		
		
		Cartao9321Teste01 cartao9321Teste01 = new Cartao9321Teste01();
//		cartao9321Teste01.testar(driver);
		
		Cartao968Teste01 cartao968Teste01 = new Cartao968Teste01();
		//cartao968Teste01.testar(driver);
		
		TesteComoColaboradorNegocio testeColaboradorNeg =  new TesteComoColaboradorNegocio();
		//testeColaboradorNeg.testar(driver);//1077878/2015
		
		//driver.close();
		
		
		
		System.out.println("Fim do teste");
		Utils.temporizar(tempoInicial);
	}
	
}
