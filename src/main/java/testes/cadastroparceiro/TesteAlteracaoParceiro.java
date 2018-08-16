package main.java.testes.cadastroparceiro;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.java.telas.TelaEdicaoParceiro;
import main.java.telas.TelaModalEndereco;
import main.java.utilidades.GeraCpfCnpj;
import main.java.utilidades.GeraNomes;

public class TesteAlteracaoParceiro extends AbstractTesteAlteracaoParceiro{
	
	private GeraNomes geradorNomes;
	private GeraCpfCnpj geradorCpfCnpj;
	private String arquivoPadrao;
	
	public TesteAlteracaoParceiro(){
		try{
			this.geradorCpfCnpj = new GeraCpfCnpj();
			
			this.geradorNomes = new GeraNomes("recursos/roman.txt");
			
			this.arquivoPadrao = "recursos/documento_teste.pdf";
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	public String[] infoMockInfoPesquisaParceiro(){
		String[] filtro = new String[2];
		
		
		//String cnpj = "46925365000128";//OSC
		String cnpj = "61155460000183";
		
		//String cnpj = "22278274000180";//Não OSC, mas que pode ser OSC
		//String cnpj = "12291457000181";//Não pode ser OSC
		String cpf = "01295290367";
		
		//filtro[0] = "Física";
		//filtro[1] = cpf;
		filtro[0] = "Jurídica";
		filtro[1] = cnpj;
		
		
		return filtro;
	}


	public void preencherInfoParceiro(WebDriver driver, WebDriverWait wait){
		//String[] pessoaJuridica =infoMockInfoPessoaJuridica();
		String[] responsavel = infoMockInfoResponsavel();
		String[] enderecoPessoaJuridica = infoMockEnderecoPessoaJuridica();
		
		TelaEdicaoParceiro telaEdicaoParceiro = new TelaEdicaoParceiro();
		
		telaEdicaoParceiro.clicarIncluirEndereco(driver, wait, "linkEndereco");
		TelaModalEndereco telaModalEndereco = new TelaModalEndereco("modalEnderecoParceiro");
		telaModalEndereco.esperarModalAparecer(wait);
		WebElement campoCEP = driver.findElement(By.xpath("//*[@data-id-para-teste='"+"modalEnderecoParceiro"+"CepInput']"));
		campoCEP.clear();
		campoCEP.sendKeys("51020021");
		
//		telaEdicaoParceiro.selecionarEndereco(driver, wait, enderecoPessoaJuridica);
//		telaEdicaoParceiro.clicarEditarEndereco(driver, wait);
//		TelaModalEndereco telaModalEndereco = new TelaModalEndereco("modalEnderecoParceiro");
//		telaModalEndereco.esperarModalAparecer(wait);
//		telaModalEndereco.preencherCampoCEP(driver, wait, "60840285");
//		telaModalEndereco.preencherCampoNumero(driver, wait, "9999");
//		telaModalEndereco.preencherCampoTipo(driver, wait, "FILIAL");
//		telaModalEndereco.clicarSalvarEdicaoEndereco(driver, wait);
//		telaModalEndereco.esperarModalDesaparecer(wait);
		
		
		//telaEdicaoParceiro.preencherCamposResponsavel(driver, wait, responsavel);
		
		//telaEdicaoParceiro.prepararEdicaoDirigente(driver, wait);
		//telaEdicaoParceiro.selecionarDirigente(driver, wait, null);
		//telaEdicaoParceiro.clicarEditarDirigente(driver, wait);
		
		//String[] dirigente = infoMockInfoDirigente();
		//TelaModalDirigente telaModalDirigente = new TelaModalDirigente();
		//telaModalDirigente.preencherCamposDirigente(driver, wait, dirigente);
		
		//TelaModalDocumento telaModalDocumentos = new TelaModalDocumento(this.arquivoPadrao);
		//telaModalDocumentos.preencherListaDocumentos(driver, wait);		
	}
	

	
	private String[] infoMockInfoPessoaJuridica(){
		
		String nomeEmpresa = this.geradorNomes.compose(2) + " " + this.geradorNomes.compose(2);
		String[] pessoaJuridica = new String[10];
		pessoaJuridica[0] = nomeEmpresa;
		pessoaJuridica[1] = ("RAZAO SOCIAL PARCEIRO PJ - " + nomeEmpresa).toUpperCase();;
		pessoaJuridica[2] = "01/01/2000";
		pessoaJuridica[3] = "Abate de aves";
		pessoaJuridica[4] = "wwww.umWebSite.com";
		pessoaJuridica[5] = "emailDoParceiro@parceiro.com";
		
		return pessoaJuridica;
	}
	
	private String[] infoMockEnderecoPessoaJuridica(){
		
		String[] pessoaJuridica = new String[4];
		pessoaJuridica[0] = "Sim";
		pessoaJuridica[1] = "MATRIZ";
		pessoaJuridica[2] = "ROBERTO";
		pessoaJuridica[3] = "123";
		
		return pessoaJuridica;
		
	}
	
	private String[] infoMockInfoResponsavel(){
		
		String[] responsavel = new String[10];
		responsavel[0] = this.geradorCpfCnpj.cpf();
		responsavel[1] = this.geradorNomes.compose(3) + " " + this.geradorNomes.compose(4);
		responsavel[2] = "CASADO";
		responsavel[3] = "MASCULINO";
		responsavel[4] = "01/01/1990";
		
		return responsavel;
	}
	
	private String[] infoMockInfoDirigente(){
		
		String[] dirigente = new String[10];
		return dirigente;
	}

}
