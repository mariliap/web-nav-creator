package main.java.testes.cadastroparceiro;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.java.telas.TelaEdicaoParceiro;
import main.java.telas.TelaModalDocumento;

public class TesteComoColaboradorNegocio extends AbstractTesteColaboradorNegocio{
	
	private String arquivoPadrao;
	
	public TesteComoColaboradorNegocio(){
		
		this.arquivoPadrao = "/recursos/documento_teste2.pdf";

	}

	@Override
	public void realizarTeste(WebDriver driver, WebDriverWait wait) {
		// TODO Auto-generated method stub
		
	}
	




	


}
