package main.java.telas;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.java.utilidades.GeraData;
import main.java.utilidades.GeraXpath;
import main.java.utilidades.JavaScriptUtils;
import main.java.utilidades.Utils;

public class TelaModalDocumento {
	
	private JavaScriptUtils javaScriptUtils = null;
	private String nomeModal = null;
	private String prefixoIdsElementosModal = null;
	private String arquivoPadrao;
	private GeraXpath geraXpath = null;
	private GeraData geraData = null;
	
	public TelaModalDocumento(WebDriver driver, String nomeModal,String arquivoPadrao){
		this.arquivoPadrao = System.getProperty("user.dir") + arquivoPadrao;
		System.out.println(this.arquivoPadrao);
		this.nomeModal = nomeModal;
		this.prefixoIdsElementosModal = this.nomeModal + "Formulario:" + this.nomeModal;
		this.javaScriptUtils = new JavaScriptUtils();
		
		geraXpath = new GeraXpath();
		geraData = new GeraData();
		
	}
	
	public void esperarModalAparecer(WebDriverWait wait){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modalDocObrigatorioContentDiv")));
	}
	
	public void esperarModalDesaparecer(WebDriverWait wait){
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("modalDocObrigatorioContentDiv")));
	}
	
	public void preencherModalDocumento(WebDriver driver, WebDriverWait wait, String prefixoIdsElementosModal){
		
		esperarModalAparecer(wait);
		
		editarCampoNumero(driver, wait);
		editarCampoUFEmissao(driver, wait);
		editarCampoDataEmissao(driver, wait);
		editarCampoDataValidade(driver, wait);
		anexar(driver, wait);
		clicarSalvarEdicaoDocumento(driver, wait);
 
		esperarModalDesaparecer(wait);
	}
	
	public void editarCampoNumero(WebDriver driver, WebDriverWait wait){
		try{
			WebElement modalForm = driver.findElement(By.id("modalDocObrigatorioFormulario"));
			String idCampoNumero = prefixoIdsElementosModal + "NumeroDocumentoDecorate:"+this.nomeModal+"NumeroDocumentoInput";
			//if(!modalForm.findElements(By.id(idCampoNumero)).isEmpty()){
			if(this.javaScriptUtils.verificarSeIdExiste(driver, idCampoNumero)){	
				WebElement campoNumero = modalForm.findElement(By.id(idCampoNumero));
				campoNumero.clear();
				campoNumero.sendKeys("05012018");
				this.javaScriptUtils.executarAcaoRetirarFoco(driver, idCampoNumero);
				
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(nomeModal+"PainelOverlayProcessando")));
			}
				
		}catch(NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException e) {
			System.err.println("Impossivel acessar elemento CampoNumero.");
		} 
	}
	
	public void editarCampoUFEmissao(WebDriver driver, WebDriverWait wait){
		try{
			WebElement modalForm = driver.findElement(By.id("modalDocObrigatorioFormulario"));
			String idCampoUFEmissao = "modalDocObrigatorioFormulario:modalDocObrigatorioUfDecorate:modalDocObrigatorioUfSelect";
			//if(!modalForm.findElements(By.id(idCampoUFEmissao)).isEmpty()){
			if(this.javaScriptUtils.verificarSeIdExiste(driver, idCampoUFEmissao)){	
				Select seletorUF = new Select(driver.findElement(By.id(idCampoUFEmissao)));
				seletorUF.selectByVisibleText("AC");
				
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(nomeModal+"PainelOverlayProcessando")));
			}
				
			
		
		}catch(NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException e) {
			System.err.println("Impossivel acessar elemento CampoUFEmissao.");
		} 
	}
	
	public void editarCampoDataEmissao(WebDriver driver, WebDriverWait wait){
		try{
			long tempoInicial = System.currentTimeMillis();
			
			String idCampoDataEmissao = prefixoIdsElementosModal+"DataEmissaoDecorate:modalDocObrigatorioDataEmissaoCalendarInputDate";
			//if(!driver.findElements(By.id(idCampoDataEmissao)).isEmpty()){
			if(this.javaScriptUtils.verificarSeIdExiste(driver, idCampoDataEmissao)){	
				WebElement campoEmissao = driver.findElement(By.id(idCampoDataEmissao));
				campoEmissao.clear();
				campoEmissao.sendKeys(this.geraData.obterDataHoje());
				
				this.javaScriptUtils.executarAcaoRetirarFoco(driver, idCampoDataEmissao);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(nomeModal+"PainelOverlayProcessando")));
			
				String xpath = geraXpath.geraXpathParaObterElementoComIdCamuflado("AlterarPrazoBotao");
				System.out.println(xpath);
				if(this.javaScriptUtils.verificarSeXpathExiste(driver, xpath)){	
					driver.findElement(By.xpath(xpath)).click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(nomeModal+"PainelOverlayProcessando")));
					this.javaScriptUtils.executarAcaoColocararFoco(driver, idCampoDataEmissao);
					this.javaScriptUtils.executarAcaoRetirarFoco(driver, idCampoDataEmissao);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(nomeModal+"PainelOverlayProcessando")));
					
					xpath = geraXpath.geraXpathParaObterElementoComIdCamuflado("PeriodoSelect");
					System.out.println(xpath);
					Select seletorPeriodo = new Select(driver.findElement(By.xpath(xpath)));
					seletorPeriodo.selectByVisibleText("dias");
					
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(nomeModal+"PainelOverlayProcessando")));
				}
				//modalDocObrigatorioFormulario:modalDocObrigatorioAlterarPrazoBotao
				//modalDocObrigatorioFormulario:modalDocObrigatorioPeriodoDecorate:modalDocObrigatorioPeriodoSelect
			}
			
			System.out.println("Tempo execução método: editarCampoDataEmissao");
			Utils.temporizar(tempoInicial);
			
			
		}catch(NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException e) {
			System.err.println("Impossivel acessar elemento CampoDataEmissao.");
		} 
	}
	
	public void editarCampoDataValidade(WebDriver driver, WebDriverWait wait){
		
		try{
			String idCampoDataValidade = prefixoIdsElementosModal+"DataValidadeDecorate:modalDocObrigatorioDataValidadeCalendarInputDate";
			String idCamposValidade = "modalDocObrigatorioCamposValidade";
			WebElement fieldSetCamposDeValidade = driver.findElement(By.id(idCamposValidade));
			//if(!fieldSetCamposDeValidade.findElements(By.id(idCampoDataValidade)).isEmpty()){
			if(this.javaScriptUtils.verificarSeIdExiste(driver, idCampoDataValidade)){	
				WebElement campoValidade = driver.findElement(By.id(idCampoDataValidade));
				
				if(campoValidade.isEnabled()){
					campoValidade.clear();
					campoValidade.sendKeys(this.geraData.obterDataHojeMaisUmAno());
					
					this.javaScriptUtils.executarAcaoRetirarFoco(driver, idCampoDataValidade);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(nomeModal+"PainelOverlayProcessando")));
				} 
//				else {
//					String textoAntigoCampoValidade = campoValidade.getText();
//					
//					JavascriptExecutor jsexec = (JavascriptExecutor) driver;
//					jsexec.executeScript("!!document.activeElement ? document.activeElement.blur() : 0");
//						
//					wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(campoValidade, textoAntigoCampoValidade)));
//				}
			}
		}catch(NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException e) {
			System.err.println("Impossivel acessar elemento CampoDataValidade.");
		} 
	}
	
	public void trocarAnexo(WebDriver driver, WebDriverWait wait){
	
		long tempoInicial = System.currentTimeMillis();
		
//		wait.until(ExpectedConditions.elementToBeClickable(By.id(idFileInput)));
		
		WebElement modalForm = driver.findElement(By.id("modalDocObrigatorioFormulario"));
		List<WebElement> listaAnexos = modalForm.findElements(By.xpath("//img[contains(@src,'/e-parcerias-web/imagens/deletar.png')]"));
		
		//Remove anexos atuais
		for (WebElement anexo : listaAnexos) {
			anexo.click();
		}
		wait.until(ExpectedConditions.invisibilityOfAllElements(listaAnexos));
		
		//Adiciona novo anexo
		anexar(driver, wait);
		
		System.out.println("Tempo execução método: trocarAnexo");
		Utils.temporizar(tempoInicial);
	}
	
	public void anexar(WebDriver driver, WebDriverWait wait){
		String idFileInput = prefixoIdsElementosModal+"FileUploadComponente:file";
		driver.findElement(By.id(idFileInput)).sendKeys(arquivoPadrao);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src,'/e-parcerias-web/imagens/deletar.png')]")));		
	}
	
	public void clicarSalvarEdicaoDocumento(WebDriver driver, WebDriverWait wait){
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(nomeModal+"PainelOverlayProcessando")));
		
		String idBotaoSalvar = prefixoIdsElementosModal + "SalvarBotao";
		wait.until(ExpectedConditions.elementToBeClickable(By.id(idBotaoSalvar)));
		driver.findElement(By.id(idBotaoSalvar)).click();
	}
	
	public void clicarCancelarEdicaoDocumento(WebDriver driver, WebDriverWait wait,String prefixoIdsElementosModal){
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(nomeModal+"PainelOverlayProcessando")));
		
		String idBotaoCancelar = prefixoIdsElementosModal + "CancelarLink";
		wait.until(ExpectedConditions.elementToBeClickable(By.id(idBotaoCancelar)));
		driver.findElement(By.id(idBotaoCancelar)).click();
	}
	
}
