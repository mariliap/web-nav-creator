package main.java.testes.cadastroparceiro;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.java.telas.TelaEdicaoParceiro;
import main.java.telas.TelaModalDocumento;

public class Cartao9321Teste01 extends AbstractTesteAlteracaoParceiro{
	
	private String arquivoPadrao;
	
	public Cartao9321Teste01(){
		
		this.arquivoPadrao = "/recursos/documento_teste2.pdf";

	}
	
	public String[] infoMockInfoPesquisaParceiro(){
		String[] filtro = new String[2];
		
		filtro[0] = "Jurídica";
		filtro[1] = "76781881000197";
		
		
		return filtro;
	}


	public void preencherInfoParceiro(WebDriver driver, WebDriverWait wait){
		
		TelaEdicaoParceiro telaEdicaoParceiro = new TelaEdicaoParceiro();
		
		String textoDocumento = "Documento de Identidade do Responsável pela Entidade";
		telaEdicaoParceiro.selecionarDocumentoObrigatorio(driver, wait, textoDocumento);
		telaEdicaoParceiro.clicarEditarDocumentoObrigatorio(driver, wait);
		
		TelaModalDocumento telaModalDocumento = new TelaModalDocumento(driver, "modalDocObrigatorio", this.arquivoPadrao);
		telaModalDocumento.esperarModalAparecer(wait);
		
		telaModalDocumento.trocarAnexo(driver, wait);
		telaModalDocumento.clicarSalvarEdicaoDocumento(driver, wait);
		 
		telaModalDocumento.esperarModalDesaparecer(wait);
		
	}
	
	

	

}
