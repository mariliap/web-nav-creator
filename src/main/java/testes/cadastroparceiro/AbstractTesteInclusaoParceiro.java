package main.java.testes.cadastroparceiro;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractTesteInclusaoParceiro {
	
	private String urlSistema;
	private Boolean preCadastar;
	
	public abstract String[] infoMockPreCadastroParceiro();
	public abstract void preencherInfoParceiro(WebDriver driver, WebDriverWait wait);
	
	public void testar(WebDriver driver, String urlSitema, Boolean preCadastar){
		this.urlSistema = urlSitema;
		this.preCadastar = preCadastar;
		String[] infoParceiro = infoMockPreCadastroParceiro();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		testarInclusaoParceiro(driver, wait, infoParceiro);
		
	}
	
	private void testarInclusaoParceiro(WebDriver driver, WebDriverWait wait, String[] infoNovoParceiro){
		
		try {
			
			driver.get(this.urlSistema);
			
			System.out.println(driver.getTitle() + " - " + driver.getCurrentUrl());
			
			if(preCadastar){
				preCadastrarParceiro(driver, wait, infoNovoParceiro);
			}
			lerEmailParaCadastro(driver, wait, infoNovoParceiro);
			
			preencherInfoParceiro(driver, wait);
	
		} catch(NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException e) {
			System.err.println("Impossivel acessar elemento. Razão: "  + e.toString());
		} 
	}
	
	private void preCadastrarParceiro(WebDriver driver, WebDriverWait wait, String[] infoParceiro){
		
		if(!driver.findElements(By.id("botaoContinuar")).isEmpty()){
			driver.findElement(By.id("botaoContinuar")).click();
		}
		
		driver.findElement(By.linkText("Pré-cadastro")).click();
		
		String tipoPessoa = infoParceiro[0];
		String cpfCnpj = infoParceiro[1];
		String nomeRazaoSocial = infoParceiro[2];
		String email = infoParceiro[3];
		String osc = infoParceiro[4];
		
		driver.findElement(By.id("formularioDeCrud:tipoCadastroDecorate:tipoCadastro")).sendKeys(tipoPessoa);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("processandoTransparente")));
		
		if(tipoPessoa.equals("PESSOA FISICA")) {
			driver.findElement(By.id("formularioDeCrud:cpfDecorate:cpf")).sendKeys(cpfCnpj);
			
			driver.findElement(By.id("formularioDeCrud:nomeRazaoSocialPFDecorate:nomeRazaoSocialPF")).sendKeys(nomeRazaoSocial);
		
			driver.findElement(By.id("formularioDeCrud:emailDecoratePF:emailPrincipalPF")).sendKeys(email);
		
			WebElement natJuridica = driver.findElement(By.name("formularioDeCrud:naturezaJuridicaDecorate:arvorePF:PESSOAS FÍSICAS::radioArvorePFTreeNode"));
			natJuridica.sendKeys("true");
		} else {
			driver.findElement(By.id("formularioDeCrud:cnpjDecorate:cnpj")).sendKeys(cpfCnpj);
			
			driver.findElement(By.id("formularioDeCrud:nomeRazaoSocialPJDecorate:nomeRazaoSocialPJ")).sendKeys(nomeRazaoSocial);
			
			driver.findElement(By.id("formularioDeCrud:emailDecoratePJ:emailPrincipalPJ")).sendKeys(email);
	
			if(osc.equals("OSC")){
				
				WebElement grupoNatJuridica = driver.findElement(By.id("formularioDeCrud:naturezaJuridicaDecorate:arvorePJ:ENTIDADES SEM FINS LUCRATIVOS::arvorePJTreeNode:handle:img:collapsed"));
				grupoNatJuridica.click();
				
				WebElement natJuridica = driver.findElement(By.id("formularioDeCrud:naturezaJuridicaDecorate:arvorePJ:ENTIDADES SEM FINS LUCRATIVOS:Organizaccedil;atilde;o Social::radioArvorePJTreeNode:0"));
				natJuridica.click();
				
			} else {
				
				WebElement grupoNatJuridica = driver.findElement(By.id("formularioDeCrud:naturezaJuridicaDecorate:arvorePJ:ADMINISTRACcedil;Atilde;O PUacute;BLICA::arvorePJTreeNode:handle:img:collapsed"));
				grupoNatJuridica.click();
				
				WebElement natJuridica = driver.findElement(By.id("formularioDeCrud:naturezaJuridicaDecorate:arvorePJ:ADMINISTRACcedil;Atilde;O PUacute;BLICA:Oacute;rgatilde;o Puacute;blico do Poder Executivo Federal::radioArvorePJTreeNode:0"));
				//WebElement natJuridica = driver.findElement(By.name("formularioDeCrud:naturezaJuridicaDecorate:arvorePJ:ADMINISTRAÇÃO PÚBLICA:Órgão Público do Poder Executivo Federal::radioArvorePJTreeNode"));
				//natJuridica.sendKeys("true");
				natJuridica.click();
			}
			
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("formularioDeCrud:botaoSalvar")));
		driver.findElement(By.id("formularioDeCrud:botaoSalvar")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("formulariomodalConfirmacaoEnvioDeEmail:buttonAceito")));		
		driver.findElement(By.id("formulariomodalConfirmacaoEnvioDeEmail:buttonAceito")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='OK']")));
		driver.findElement(By.xpath("//input[@value='OK']")).click();;
		//
		
		
		
		
	}
	
	private void lerEmailParaCadastro(WebDriver driver, WebDriverWait wait, String[] infoParceiro){
		
		driver.get("http://webmail.cge.ce.gov.br/zimbra/");	
		driver.findElement(By.id("username")).sendKeys("gtreinamento");
		driver.findElement(By.id("password")).sendKeys("Treina12345");
		driver.findElement(By.xpath("//input[@value='Login']")).click();;
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("ImgAppBanner")));
		
		WebElement painel = driver.findElement(By.className("ZmConvDoublePaneView"));
		WebElement subPainel = painel.findElement(By.className("DwtListView"));
		//span[contains(@class, 'myclass') and text() = 'qwerty']
		
		//String tituloEmail = "Quota warning";
		String tituloEmail = "Email de Confirmação de Solicitação de Cadastro";
		WebElement emailConfirmacao = subPainel.findElement(By.xpath("//tr/td[contains(text(), '"+tituloEmail+"')]"));
		emailConfirmacao.click();
		
		//ZmMailMsgView
		WebElement subPainelMensagem = painel.findElement(By.className("MsgBody"));
		WebElement iframe = subPainelMensagem.findElement(By.xpath(".//iframe"));
		//corpo_email MsgBody
		driver.switchTo().frame(iframe);
		WebElement corpoEmailConfirmacao = driver.findElement(By.id("corpo_email"));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[starts-with(@href, 'http:')]")));
		//WebElement linkAlterarParceiro = driver.findElement(By.xpath("//a[starts-with(@href, 'http://localhost:8080/e-parcerias-web/paginas/parceiro/')]"));
		WebElement linkAlterarParceiro = driver.findElement(By.xpath("//a[starts-with(@href, '"+this.urlSistema+"paginas/parceiro/')]"));
		System.out.println(linkAlterarParceiro.getText());
		driver.get(linkAlterarParceiro.getText());	
	}
}
