package main.java.testes.cadastroparceiro;

import java.util.NoSuchElementException;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.java.telas.TelaLogin;
import main.java.telas.TelaPesquisaParceiro;
import main.java.telas.TelaPrincipal;



public abstract class AbstractTesteColaboradorNegocio {
	
	public abstract void realizarTeste(WebDriver driver, WebDriverWait wait);

	public void testar(WebDriver driver){
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		realizarTesteLogadoComoColaboradorNegocio(driver, wait);
		
	}
	
	private void realizarTesteLogadoComoColaboradorNegocio(WebDriver driver, WebDriverWait wait){
		
		try {
			driver.get("http://localhost:8080/e-parcerias-web/");
			
			System.out.println(driver.getTitle() + " - " + driver.getCurrentUrl());
			
						
			TelaLogin telaLogin = new TelaLogin();
			telaLogin.logarNoSistema(driver, "04123034349");
			

			realizarTeste(driver, wait);
	
		} catch(NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException e) {
			System.err.println("Impossivel acessar elemento. Razão: "  + e.toString());
		} 
	}
	
}
