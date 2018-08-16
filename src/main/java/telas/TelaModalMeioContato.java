package main.java.telas;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TelaModalMeioContato {

	private String nomeModal = null;
	private String prefixoIdsElementosModal = null;
	
	public TelaModalMeioContato(String nomeModal) {
		this.nomeModal = nomeModal;
		this.prefixoIdsElementosModal = this.nomeModal + "Formulario:" + this.nomeModal;
	}
	
	public void esperarModalAparecer(WebDriverWait wait){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(nomeModal+"ContentDiv")));
	}
	
	public void esperarModalDesaparecer(WebDriverWait wait){
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(nomeModal+"ContentDiv")));
	}
	
	public void preencherCampoTipo(WebDriver driver, WebDriverWait wait, String novoValorCampo){
		Select seletorTipo = new Select(driver.findElement(By.id(prefixoIdsElementosModal+"TipoContatoDecorate:"+this.nomeModal+"TipoContatoSelect")));
		seletorTipo.selectByVisibleText(novoValorCampo);
		
//		JavascriptExecutor jsexec = (JavascriptExecutor) driver;
//		jsexec.executeScript("!!document.activeElement ? document.activeElement.blur() : 0");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(nomeModal+"PainelOverlayProcessando")));
		
		System.out.println("Fim preencherCampoTipo" );
	}
	
	public void preencherCampoMeioContatoPrincipal(WebDriver driver, WebDriverWait wait, Boolean novoValorCampo){

		String idMeioContatoPrincipal = prefixoIdsElementosModal+"ContatoPrincipalDecorate:"+this.nomeModal+"ContatoPrincipalSelect";
		wait.until(ExpectedConditions.elementToBeClickable(By.id(idMeioContatoPrincipal)));
		WebElement campoMeioContatoPricipal = driver.findElement(By.id(idMeioContatoPrincipal));
		
		if(novoValorCampo==true && !campoMeioContatoPricipal.isSelected()){
			campoMeioContatoPricipal.click();
		} else if(novoValorCampo==false && campoMeioContatoPricipal.isSelected()){
			campoMeioContatoPricipal.click();
		}
		
//		JavascriptExecutor jsexec = (JavascriptExecutor) driver;
//		jsexec.executeScript("!!document.activeElement ? document.activeElement.blur() : 0");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(nomeModal+"PainelOverlayProcessando")));
		System.out.println("Fim preencherCampoMeioContatoPrincipal" );
	}
	
	public void preencherCampoNumero(WebDriver driver, WebDriverWait wait, String novoValorCampo){
		WebElement campoNumero = driver.findElement(By.id(prefixoIdsElementosModal+"NumeroDecorate:"+this.nomeModal+"NumeroInput"));
		campoNumero.clear();
		campoNumero.sendKeys(novoValorCampo);
		
//		JavascriptExecutor jsexec = (JavascriptExecutor) driver;
//		jsexec.executeScript("!!document.activeElement ? document.activeElement.blur() : 0");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(nomeModal+"PainelOverlayProcessando")));
		System.out.println("Fim preencherCampoNumero" );
	}
	
	public void clicarSalvarEdicaoMeioContato(WebDriver driver, WebDriverWait wait){
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(nomeModal+"PainelOverlayProcessando")));

		String idBotaosSalvar = prefixoIdsElementosModal + "BotaoSalvar";
		wait.until(ExpectedConditions.elementToBeClickable(By.id(idBotaosSalvar)));
		driver.findElement(By.id(idBotaosSalvar)).click();
		System.out.println("Fim clicarSalvarEdicaoMeioContato" );
	}
	
	
	public void clicarCancelarEdicaoMeioContato(WebDriver driver, WebDriverWait wait){
		String idBotaoCancelar = prefixoIdsElementosModal + "LinkCancelar";
		wait.until(ExpectedConditions.elementToBeClickable(By.id(idBotaoCancelar)));
		driver.findElement(By.id(idBotaoCancelar)).click();
	}
}
