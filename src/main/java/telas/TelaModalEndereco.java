package main.java.telas;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TelaModalEndereco {

	private String nomeModal = null;
	private String prefixoIdsElementosModal = null;
	
	public TelaModalEndereco(String nomeModal) {
		this.nomeModal = nomeModal;
		this.prefixoIdsElementosModal = this.nomeModal + "Formulario:" + this.nomeModal;
	}
	
	public void esperarModalAparecer(WebDriverWait wait){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(nomeModal+"ContentDiv")));
	}
	
	public void esperarModalDesaparecer(WebDriverWait wait){
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(nomeModal+"ContentDiv")));
	}
	
	public void preencherCampoCEP(WebDriver driver, WebDriverWait wait, String novoValorCampo){
		WebElement campoLogradouro = driver.findElement(By.id(prefixoIdsElementosModal+"LogradouroDecorate:"+this.nomeModal+"LogradouroInput"));
		String textoAntigoCampoLogradouro = campoLogradouro.getText();
		
		WebElement campoCEP = driver.findElement(By.id(prefixoIdsElementosModal+"CepDecorate:"+this.nomeModal+"CepInput"));
		campoCEP.clear();
		campoCEP.sendKeys(novoValorCampo);
		
		JavascriptExecutor jsexec = (JavascriptExecutor) driver;
		jsexec.executeScript("!!document.activeElement ? document.activeElement.blur() : 0");


		wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(campoLogradouro, textoAntigoCampoLogradouro)));

	}
	
	public void preencherCampoNumero(WebDriver driver, WebDriverWait wait, String novoValorCampo){
		
		WebElement campoNumero = driver.findElement(By.id(prefixoIdsElementosModal+"NumeroDecorate:"+this.nomeModal+"NumeroInput"));
		campoNumero.clear();
		campoNumero.sendKeys(novoValorCampo);
	}
	
	public void preencherCampoTipo(WebDriver driver, WebDriverWait wait, String novoValorCampo){
		Select seletorTipo = new Select(driver.findElement(By.id(prefixoIdsElementosModal+"TipoEnderecoDecorate:"+this.nomeModal+"TipoEnderecoSelect")));
		seletorTipo.selectByVisibleText(novoValorCampo);
	}
	
	public void preencherCampoEnderecoPrincipal(WebDriver driver, WebDriverWait wait, Boolean novoValorCampo){
		
		
		
		WebElement campoEnderecoPricipal = driver.findElement(By.id(prefixoIdsElementosModal+"SelecaoEnderecoPrincipal:"+this.nomeModal+"AtributoEndPrincipal"));
		
		if(novoValorCampo==true && !campoEnderecoPricipal.isSelected()){
			campoEnderecoPricipal.click();
		} else if(novoValorCampo==false && campoEnderecoPricipal.isSelected()){
			campoEnderecoPricipal.click();
		}
	}
	
	public void clicarSalvarEdicaoEndereco(WebDriver driver, WebDriverWait wait){
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(nomeModal+"PainelOverlayProcessando")));
		

		String idBotaosSalvar = prefixoIdsElementosModal + "BotaoSalvar";
		wait.until(ExpectedConditions.elementToBeClickable(By.id(idBotaosSalvar)));
		driver.findElement(By.id(idBotaosSalvar)).click();
	}
	
	public void clicarCancelarEdicaoEndereco(WebDriver driver, WebDriverWait wait){
		String idBotaoCancelar = prefixoIdsElementosModal + "LinkCancelar";
		wait.until(ExpectedConditions.elementToBeClickable(By.id(idBotaoCancelar)));
		driver.findElement(By.id(idBotaoCancelar)).click();
	}
}
