package main.java.telas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.java.utilidades.GeraXpath;

public class TelaEdicaoParceiro {
	
	String prefixoIds =  "formularioDeCrud:";
	
	GeraXpath geraXpath = null;
	
	public TelaEdicaoParceiro() {
		geraXpath = new GeraXpath();
	}
	
	public void preencherCamposOSC(WebDriver driver, WebDriverWait wait, Boolean osc){
		
		WebElement campo = null;
		if(osc){
			campo = driver.findElement(By.id("formularioDeCrud:oscDecorate:categoriaOsc:0"));
			campo.click();
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id("formularioDeCrud:tipoCategoriaOsc:idTipoCategoriaOscSelect")));
			Select seletorTipoCategoriaOSC = new Select(driver.findElement(By.id("formularioDeCrud:tipoCategoriaOsc:idTipoCategoriaOscSelect")));
			seletorTipoCategoriaOSC.selectByVisibleText("Entidade Privada sem Fins Lucrativos (art. 2.º, I, “a”, Lei n.º 13.019/2014)");
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id("formularioDeCrud:areaAtuacaoOSC:idAreaAtuacaoOscSelect")));
			Select seletorAreaAtuacaoOSC = new Select(driver.findElement(By.id("formularioDeCrud:areaAtuacaoOSC:idAreaAtuacaoOscSelect")));
			seletorAreaAtuacaoOSC.selectByVisibleText("Artes e Cultura");
			
		} else {
			campo = driver.findElement(By.id("formularioDeCrud:oscDecorate:categoriaOsc:1"));
			campo.click();
		}
		
	}

	public void preencherCamposPessoaJuridica(WebDriver driver, WebDriverWait wait, String[] pessoaJuridica){
		
		preencherCampoNomeFantasia(driver, wait, pessoaJuridica[0]);
		preencherCampoNomeRazaoSocial(driver, wait, pessoaJuridica[1]);
		preencherCampoDataAbertura(driver, wait, pessoaJuridica[2]);
		preencherCampoSelecionarAtividadeEconomica(driver, wait, pessoaJuridica[3]);
		preencherCampoWebsiteParceiro(driver, wait, pessoaJuridica[4]);
		preencherCampoEmailParceiro(driver, wait, pessoaJuridica[5]);
		
		clicarIncluirEndereco(driver, wait, "linkEndereco");
		TelaModalEndereco telaModalEndereco = new TelaModalEndereco("modalEnderecoParceiro");
		telaModalEndereco.esperarModalAparecer(wait);
		
		
		
		telaModalEndereco.preencherCampoEnderecoPrincipal(driver, wait, true);
		telaModalEndereco.preencherCampoCEP(driver, wait, "60840285");
		telaModalEndereco.preencherCampoNumero(driver, wait, "9999");
		telaModalEndereco.preencherCampoTipo(driver, wait, "FILIAL");
		telaModalEndereco.clicarSalvarEdicaoEndereco(driver, wait);
		telaModalEndereco.esperarModalDesaparecer(wait);
		
//		clicarIncluirMeioContato(driver, wait);
//		TelaModalMeioContato telaModalMeioContato = new TelaModalMeioContato("modalMeioContato");
//		telaModalMeioContato.esperarModalAparecer(wait);
//		
//		telaModalMeioContato.preencherCampoTipo(driver, wait, "Telefone Fixo");
//		telaModalMeioContato.preencherCampoMeioContatoPrincipal(driver, wait, true);
//		telaModalMeioContato.preencherCampoNumero(driver, wait, "57657567567");
//		telaModalMeioContato.clicarSalvarEdicaoMeioContato(driver, wait);
//		telaModalMeioContato.esperarModalDesaparecer(wait);
		
	}
	
	public void esperarProcessamento(WebDriverWait wait){
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("processandoTransparente")));
	}
	
	public void preencherCampoNomeFantasia(WebDriver driver, WebDriverWait wait, String novoValorCampo){
		WebElement campo = driver.findElement(By.id(prefixoIds+"nomeFantasiaDecorate:nomeFantasia"));
		campo.clear();
		campo.sendKeys(novoValorCampo);
	}
	
	public void preencherCampoNomeRazaoSocial(WebDriver driver, WebDriverWait wait, String novoValorCampo){
		WebElement campo = driver.findElement(By.id(prefixoIds+"razaoSocialDecorate:razaoSocial"));
		campo.clear();
		campo.sendKeys(novoValorCampo);
	}

	public void preencherCampoDataAbertura(WebDriver driver, WebDriverWait wait, String novoValorCampo){
		WebElement campo = driver.findElement(By.id(prefixoIds+"dataAberturaDecorate:dataAberturaInputDate"));
		campo.clear();
		campo.sendKeys(novoValorCampo);
	}
	
	public void preencherCampoSelecionarAtividadeEconomica(WebDriver driver, WebDriverWait wait, String novoValorCampo){
		Select seletorAtividade = new Select(driver.findElement(By.id(prefixoIds+"atividadeEconomicaDecorate:atividadeEconomicaPrincipal")));
		seletorAtividade.selectByVisibleText(novoValorCampo);
	}
	
	public void preencherCampoWebsiteParceiro(WebDriver driver, WebDriverWait wait, String novoValorCampo){
		WebElement campo = driver.findElement(By.id(prefixoIds+"enderecoEletronicoDivulgacaoDecorate:enderecoEletronicoDivulgacaoInput"));
		campo.clear();
		campo.sendKeys(novoValorCampo);
	}
	
	public void preencherCampoEmailParceiro(WebDriver driver, WebDriverWait wait, String novoValorCampo){
		WebElement campo = driver.findElement(By.id(prefixoIds+"emailDecorate:emailParceirInput"));
		campo.clear();
		campo.sendKeys(novoValorCampo);
	}
	
	public void selecionarEndereco(WebDriver driver, WebDriverWait wait, String[] filtro){
		String idTabela = prefixoIds + "enderecoDataTable";
		WebElement linhaEndereco = null;
		if(filtro != null){
			linhaEndereco = driver.findElement(By.xpath(this.geraXpath.geraXpathParaObterLinhaTabelaCorrespondenteAoFiltro(idTabela, filtro)));
		} else{
			linhaEndereco = driver.findElement(By.xpath(this.geraXpath.geraXpathParaObterPrimeiraLinhaTabela(idTabela)));
		}
		linhaEndereco.click();
	}
	
	public void clicarEditarEndereco(WebDriver driver, WebDriverWait wait){
		String idBotaoSalvar = prefixoIds + "btnEditarEndereco";
		wait.until(ExpectedConditions.elementToBeClickable(By.id(idBotaoSalvar)));
		driver.findElement(By.id(idBotaoSalvar)).click();
	}
	
	public void clicarIncluirEndereco(WebDriver driver, WebDriverWait wait, String nomeCampo){
		esperarProcessamento(wait);
		
		String idBotaoIncluir = prefixoIds + nomeCampo;
		wait.until(ExpectedConditions.elementToBeClickable(By.id(idBotaoIncluir)));
		driver.findElement(By.id(idBotaoIncluir)).click();
		System.out.println("Clicou endereco");
	}
	
	public void clicarIncluirMeioContato(WebDriver driver, WebDriverWait wait){
		
		String idBotaoIncluir = prefixoIds + "linkContato";
		wait.until(ExpectedConditions.elementToBeClickable(By.id(idBotaoIncluir)));
		driver.findElement(By.id(idBotaoIncluir)).click();
	}

	public void preencherCamposResponsavel(WebDriver driver, WebDriverWait wait, String[] responsavel){
		
		driver.findElement(By.id("formularioDeCrud:cpfDecorate:cpf")).sendKeys(responsavel[0]);
		
		driver.findElement(By.id("formularioDeCrud:nomeDecorate:nome")).click();
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("processandoTransparente")));
		
		driver.findElement(By.id("formularioDeCrud:nomeDecorate:nome")).sendKeys(responsavel[1]);
		
		Select seletorEstadoCivil = new Select(driver.findElement(By.id("formularioDeCrud:estadocivilDecorate:estado_civil")));
		seletorEstadoCivil.selectByVisibleText(responsavel[2]);
		
		Select seletorSexo = new Select(driver.findElement(By.id("formularioDeCrud:sexoDecorate:sexo")));
		seletorSexo.selectByVisibleText(responsavel[3]);
		
		driver.findElement(By.id("formularioDeCrud:dataNascimentoDecorate:calendarNascimentoInputDate")).sendKeys(responsavel[4]);
	
		preencherCamposDocumentoIdentificacaoResponsavel(driver, wait, responsavel);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("processandoTransparente")));
		Select seletorProfisao = new Select(driver.findElement(By.id("formularioDeCrud:ocupacaoProfissionalDecorate:ocupacao_profissional")));
		seletorProfisao.selectByVisibleText(responsavel[10]);
				
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("processandoTransparente")));
		driver.findElement(By.id("formularioDeCrud:nomeMaeDecorate:nome_mae")).sendKeys(responsavel[11]);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("processandoTransparente")));
		driver.findElement(By.id("formularioDeCrud:nomePaiDecorate:nome_pai")).sendKeys(responsavel[12]);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("processandoTransparente")));
		//formularioDeCrud:linkEnderecoRepr
		//modalEnderecoResponsavelContentDiv
		//modalEnderecoResponsavelContentDiv
		clicarIncluirEndereco(driver, wait, "linkEnderecoRepr");
		TelaModalEndereco telaModalEndereco = new TelaModalEndereco("modalEnderecoResponsavel");
		telaModalEndereco.esperarModalAparecer(wait);
		telaModalEndereco.preencherCampoEnderecoPrincipal(driver, wait, true);
		telaModalEndereco.preencherCampoCEP(driver, wait, "60840285");
		telaModalEndereco.preencherCampoNumero(driver, wait, "9999");
		telaModalEndereco.preencherCampoTipo(driver, wait, "FILIAL");
		
		telaModalEndereco.clicarSalvarEdicaoEndereco(driver, wait);
		telaModalEndereco.esperarModalDesaparecer(wait);
		
		clicarIncluirMeioContato(driver, wait);
		//modalMeioContatoReprContentDiv
		TelaModalMeioContato telaModalMeioContato = new TelaModalMeioContato("modalMeioContatoRepr");
		telaModalMeioContato.esperarModalAparecer(wait);
		
		telaModalMeioContato.preencherCampoTipo(driver, wait, "Telefone Fixo");
		telaModalMeioContato.preencherCampoMeioContatoPrincipal(driver, wait, true);
		telaModalMeioContato.preencherCampoNumero(driver, wait, "57657567567");
		telaModalMeioContato.clicarSalvarEdicaoMeioContato(driver, wait);
		telaModalMeioContato.esperarModalDesaparecer(wait);
		
	}
	
	public void preencherCamposDocumentoIdentificacaoResponsavel(WebDriver driver, WebDriverWait wait, String[] responsavel){
		
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
	
	public void prepararEdicaoDirigente(WebDriver driver, WebDriverWait wait, String arquivo){
		String textoDocumento = "Ata de eleição do quadro dirigente atual";
		selecionarDocumentoObrigatorio(driver, wait, textoDocumento);
		clicarEditarDocumentoObrigatorio(driver, wait);
		
		TelaModalDocumento telaModalDocumento = new TelaModalDocumento(driver, "modalDocObrigatorio",arquivo);
		telaModalDocumento.esperarModalAparecer(wait);
		
		telaModalDocumento.editarCampoNumero(driver, wait);
		telaModalDocumento.clicarSalvarEdicaoDocumento(driver, wait);
		 
		telaModalDocumento.esperarModalDesaparecer(wait);
	}
	
	public void selecionarDirigente(WebDriver driver, WebDriverWait wait, String[] filtro){
		String idTabela = prefixoIds + "dataTableDirigente";
		WebElement linhaParceiro = null;
		if(filtro != null){
			linhaParceiro = driver.findElement(By.xpath("//*[@id='"+idTabela+"']/tbody/tr/td[contains(text(), '"+filtro[1]+"')]"));
		} else{
			linhaParceiro = driver.findElement(By.xpath("//*[@id='"+idTabela+"']/tbody/tr[1]/td[1]"));
		}
		linhaParceiro.click();
	}
	
	public void clicarEditarDirigente(WebDriver driver, WebDriverWait wait){
		String idBotaoSalvar = prefixoIds + "btnEditarDirigente";
		wait.until(ExpectedConditions.elementToBeClickable(By.id(idBotaoSalvar)));
		driver.findElement(By.id(idBotaoSalvar)).click();
	}
	
	public void clicarIncluirDirigente(WebDriver driver, WebDriverWait wait){
		
		String idBotaoIncluir = prefixoIds + "linkIncluirDirigente";
		wait.until(ExpectedConditions.elementToBeClickable(By.id(idBotaoIncluir)));
		driver.findElement(By.id(idBotaoIncluir)).click();
	}
	
	public void selecionarDocumentoObrigatorio(WebDriver driver, WebDriverWait wait, String textoDocumento){
		String idTabela = prefixoIds + "documentacaoObrigatoriaDataTable";
		WebElement linhaDocumento =  driver.findElement(By.xpath("//*[@id='"+idTabela+"']/tbody/tr/td[contains(text(), '"+textoDocumento+"')]"));
		linhaDocumento.click();
	}
	
	public void selecionarDocumentoObrigatorio(WebDriver driver, WebDriverWait wait, int ordemDocumentoNaLista){
		String idTabela = prefixoIds + "documentacaoObrigatoriaDataTable";
		WebElement bolinhaSelecaoDocumento =  driver.findElement(By.xpath("//*[@id='"+idTabela+"']/tbody/tr["+ordemDocumentoNaLista+"]/td[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(bolinhaSelecaoDocumento));
		bolinhaSelecaoDocumento.click();
	}
	
	public void clicarEditarDocumentoObrigatorio(WebDriver driver, WebDriverWait wait){
		wait.until(ExpectedConditions.elementToBeClickable(By.id("formularioDeCrud:btEditarDocObrigatorio")));
		driver.findElement(By.id("formularioDeCrud:btEditarDocObrigatorio")).click();
	}
	
	public int contarQtdDocumentoObrigatorio(WebDriver driver, WebDriverWait wait){
		String idTabela = prefixoIds + "documentacaoObrigatoriaDataTable";
		return driver.findElements(By.xpath("//*[@id='"+idTabela+"']/tbody/tr")).size();
	}
	
	public void preencherListaDocumentos(WebDriver driver, WebDriverWait wait, String arquivoPadrao){
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("processandoTransparente")));
		
		String idTabela = "formularioDeCrud:documentacaoObrigatoriaDataTable";
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(idTabela)));
		
		int qtdDocumentosObrigatorios = driver.findElements(By.xpath("//*[@id='"+idTabela+"']/tbody/tr")).size();

		String idModalDocObrigatorio = "modalDocObrigatorio";
		String prefixoIdsElementosModal = idModalDocObrigatorio + "Formulario:" + idModalDocObrigatorio;
		TelaModalDocumento telaModalDocumentos = new TelaModalDocumento(driver, "modalDocObrigatorio", arquivoPadrao);
		
		for(int linha = 1; linha <= qtdDocumentosObrigatorios; linha++){
		    
			 WebElement bolinhaSelecaoDocumento =  driver.findElement(By.xpath("//*[@id='"+idTabela+"']/tbody/tr["+linha+"]/td[1]"));
			 wait.until(ExpectedConditions.elementToBeClickable(bolinhaSelecaoDocumento));
			 bolinhaSelecaoDocumento.click();
			
			 wait.until(ExpectedConditions.elementToBeClickable(By.id("formularioDeCrud:btEditarDocObrigatorio")));
			 driver.findElement(By.id("formularioDeCrud:btEditarDocObrigatorio")).click();
		      
			 telaModalDocumentos.preencherModalDocumento(driver, wait, prefixoIdsElementosModal);
		}
	}
}

