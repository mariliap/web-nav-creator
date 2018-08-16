package main.java.testes.cadastroparceiro;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.java.telas.TelaEdicaoParceiro;
import main.java.telas.TelaModalDocumento;

public class Cartao968Teste01 extends AbstractTesteAlteracaoParceiro{
	
	private String arquivoPadrao;
	
	public Cartao968Teste01(){
		
		this.arquivoPadrao = "/recursos/documento_teste2.pdf";

	}
	
	public String[] infoMockInfoPesquisaParceiro(){
		String[] filtro = new String[2];
		
		filtro[0] = "Jurídica";
		filtro[1] = "58615842000138";//"45676310000169";//"76781881000197";
		//02653231697
		//54756838000139
		
		return filtro;
	}


	public void preencherInfoParceiro(WebDriver driver, WebDriverWait wait){
		
		TelaEdicaoParceiro telaEdicaoParceiro = new TelaEdicaoParceiro();
		
		telaEdicaoParceiro.preencherCamposOSC(driver, wait, true);
		
		preencherDocs(driver, wait, telaEdicaoParceiro);
		
	}
	
	public void preencherDocs(WebDriver driver, WebDriverWait wait, TelaEdicaoParceiro telaEdicaoParceiro){
		telaEdicaoParceiro.esperarProcessamento(wait);
		
		int qtdDocumentosObrigatorios = telaEdicaoParceiro.contarQtdDocumentoObrigatorio(driver, wait);
		
		TelaModalDocumento telaModalDocumentos = new TelaModalDocumento(driver, "modalDocObrigatorio", this.arquivoPadrao);
		
		for(int linha = 1; linha <= qtdDocumentosObrigatorios; linha++){
		    
			telaEdicaoParceiro.selecionarDocumentoObrigatorio(driver, wait, linha);
			telaEdicaoParceiro.clicarEditarDocumentoObrigatorio(driver, wait);		
			
			telaModalDocumentos.esperarModalAparecer(wait);
			
			telaModalDocumentos.editarCampoNumero(driver, wait);
			telaModalDocumentos.editarCampoUFEmissao(driver, wait);
			telaModalDocumentos.editarCampoDataEmissao(driver, wait);
			telaModalDocumentos.editarCampoDataValidade(driver, wait);
			telaModalDocumentos.trocarAnexo(driver, wait);
			telaModalDocumentos.clicarSalvarEdicaoDocumento(driver, wait);
	 
			telaModalDocumentos.esperarModalDesaparecer(wait);
		}
	}

}
