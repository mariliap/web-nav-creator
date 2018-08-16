package main.java.testes.cadastroparceiro;

import java.util.NoSuchElementException;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.java.telas.TelaLogin;
import main.java.telas.TelaPesquisaParceiro;
import main.java.telas.TelaPrincipal;



public abstract class AbstractTesteAlteracaoParceiro {
	
	public abstract String[] infoMockInfoPesquisaParceiro();
	public abstract void preencherInfoParceiro(WebDriver driver, WebDriverWait wait);

	public void testar(WebDriver driver){
		
		String[] filtro = infoMockInfoPesquisaParceiro();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		testarEdicaoParceiro(driver, wait, filtro);
		
	}
	
	private void testarEdicaoParceiro(WebDriver driver, WebDriverWait wait, String[] filtro){
		
		try {
			driver.get("http://localhost:8080/e-parcerias-web/");
			
			System.out.println(driver.getTitle() + " - " + driver.getCurrentUrl());
			
						
			TelaLogin telaLogin = new TelaLogin();
			telaLogin.logarNoSistema(driver, "01295290367");
			
			TelaPrincipal telaPrincipal = new TelaPrincipal();
			telaPrincipal.selecionarItemMenuCadastro(driver, "Consultar Parceiro");
				
			TelaPesquisaParceiro telaPesquisaParceiro = new TelaPesquisaParceiro();
			telaPesquisaParceiro.realizarPesquisa(driver, wait, filtro);		
			telaPesquisaParceiro.selecionarResultadoESeguirParaTelaEdicao(driver, wait, filtro);

			preencherInfoParceiro(driver, wait);
	
		} catch(NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException e) {
			System.err.println("Impossivel acessar elemento. Razão: "  + e.toString());
		} 
	}
	
}
