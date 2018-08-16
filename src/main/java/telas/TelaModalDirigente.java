package main.java.telas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TelaModalDirigente {
	
	String prefixoIds =  "formularioDeCrud:";

	public void adicionarDirigente(WebDriver driver, WebDriverWait wait, String[] dirigente){
		
		preencherCamposDirigente(driver, wait, dirigente);
	}

	public void editarDirigente(WebDriver driver, WebDriverWait wait, String[] dirigente){
		
		preencherCamposDirigente(driver, wait, dirigente);
	}
	
	public void preencherCamposDirigente(WebDriver driver, WebDriverWait wait, String[] dirigente){
		driver.findElement(By.id("formularioDeCrud:cpfDecorate:cpf")).sendKeys(dirigente[0]);
		
		driver.findElement(By.id("formularioDeCrud:nomeDecorate:nome")).click();
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("processandoTransparente")));
		
		driver.findElement(By.id("formularioDeCrud:nomeDecorate:nome")).sendKeys(dirigente[1]);
		
		Select seletorEstadoCivil = new Select(driver.findElement(By.id("formularioDeCrud:estadocivilDecorate:estado_civil")));
		seletorEstadoCivil.selectByVisibleText(dirigente[2]);
		
		Select seletorSexo = new Select(driver.findElement(By.id("formularioDeCrud:sexoDecorate:sexo")));
		seletorSexo.selectByVisibleText(dirigente[3]);
		
		driver.findElement(By.id("formularioDeCrud:dataNascimentoDecorate:calendarNascimentoInputDate")).sendKeys(dirigente[4]);
	
		preencherCamposDocumentoIdentificacaoDirigente(driver, wait, dirigente);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("processandoTransparente")));
		Select seletorProfisao = new Select(driver.findElement(By.id("formularioDeCrud:ocupacaoProfissionalDecorate:ocupacao_profissional")));
		seletorProfisao.selectByVisibleText(dirigente[10]);
				
		
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("processandoTransparente")));

		clicarIncluirEndereco(driver, wait, "linkEnderecoRepr");
		TelaModalEndereco telaModalEndereco = new TelaModalEndereco("modalEnderecoDirigente");
		telaModalEndereco.esperarModalAparecer(wait);
		telaModalEndereco.preencherCampoEnderecoPrincipal(driver, wait, true);
		telaModalEndereco.preencherCampoCEP(driver, wait, "60840285");
		telaModalEndereco.preencherCampoNumero(driver, wait, "9999");
		telaModalEndereco.preencherCampoTipo(driver, wait, "FILIAL");
		
		telaModalEndereco.clicarSalvarEdicaoEndereco(driver, wait);
		telaModalEndereco.esperarModalDesaparecer(wait);
		
	
	}
	
	public void preencherCamposDocumentoIdentificacaoDirigente(WebDriver driver, WebDriverWait wait, String[] responsavel){
		
		Select seletorTipoDocumentoIdentificacao = new Select(driver.findElement(By.id("formularioDeCrud:tipoDocumentoDecorate:tipo_documento")));
		seletorTipoDocumentoIdentificacao.selectByVisibleText(responsavel[5]);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("processandoTransparente")));
		driver.findElement(By.id("formularioDeCrud:numeroDocumentoDecorate:numero_documento")).sendKeys(responsavel[6]);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("processandoTransparente")));
		driver.findElement(By.id("formularioDeCrud:orgaoExpedidorDecorate:orgao_expedidor")).sendKeys(responsavel[7]);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("processandoTransparente")));
		Select seletorUF = new Select(driver.findElement(By.id("formularioDeCrud:ocupacaoUF:uf")));
		seletorUF.selectByVisibleText(responsavel[8]);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("processandoTransparente")));
		driver.findElement(By.id("formularioDeCrud:dataEmissaoDecorate:calendarEmissaoIdentRespParceiroInputDate")).sendKeys(responsavel[9]);
		
	}
	
	public void clicarIncluirEndereco(WebDriver driver, WebDriverWait wait, String nomeCampo){
		esperarProcessamento(wait);
		
		String idBotaoIncluir = prefixoIds + nomeCampo;
		wait.until(ExpectedConditions.elementToBeClickable(By.id(idBotaoIncluir)));
		driver.findElement(By.id(idBotaoIncluir)).click();
		System.out.println("Clicou endereco");
	}
	
	public void esperarProcessamento(WebDriverWait wait){
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("processandoTransparente")));
	}
}
