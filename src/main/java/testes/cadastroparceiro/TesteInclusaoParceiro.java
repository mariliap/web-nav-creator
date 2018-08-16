package main.java.testes.cadastroparceiro;

import java.io.IOException;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.java.telas.TelaEdicaoParceiro;
import main.java.telas.TelaModalDirigente;
import main.java.telas.TelaModalDocumento;
import main.java.utilidades.GeraCpfCnpj;
import main.java.utilidades.GeraNomes;
import main.java.utilidades.Utils;

public class TesteInclusaoParceiro extends AbstractTesteInclusaoParceiro{

	private GeraNomes geradorNomes;
	private GeraCpfCnpj geradorCpfCnpj;
	private String arquivoPadrao;
	private long tempoInicial;
	
	public TesteInclusaoParceiro(long tempoInicial){
		try{
			this.geradorCpfCnpj = new GeraCpfCnpj();
			
			this.geradorNomes = new GeraNomes("recursos/roman.txt");
			
			this.arquivoPadrao = "/recursos/documento_teste1.pdf";
			
			this.tempoInicial = tempoInicial;
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	public String[] infoMockPreCadastroParceiro(){
		String tipoCadastroPF = "PESSOA FISICA";
		String tipoCadastroPJ = "PESSOA JURIDICA";
		
		String cpnj = this.geradorCpfCnpj.cnpj();
		String cpf = this.geradorCpfCnpj.cnpj();
		
		String razaoSocialPJ = ("RAZAO SOCIAL PARCEIRO PJ - " + this.geradorNomes.compose(2) + " " + this.geradorNomes.compose(2)).toUpperCase();
		String nomePF = ("NOME PARCEIRO PF - " + this.geradorNomes.compose(3) + " " + this.geradorNomes.compose(4)).toUpperCase();
		
		String email = "gtreinamento@cge.ce.gov.br";
		
		String[] infoParceiroPJ = new String[5];
		infoParceiroPJ[0] = tipoCadastroPJ;
		infoParceiroPJ[1] = cpnj;
		infoParceiroPJ[2] = razaoSocialPJ;
		infoParceiroPJ[3] = email;
		infoParceiroPJ[4] = "OSC";
		
		String[] infoParceiroPF = new String[5];
		infoParceiroPF[0] = tipoCadastroPF;
		infoParceiroPF[1] = cpf;
		infoParceiroPF[2] = nomePF;
		infoParceiroPF[3] = email;
		infoParceiroPF[4] = "NÃO OSC";
		
		return infoParceiroPJ;
	}


	public void preencherInfoParceiro(WebDriver driver, WebDriverWait wait){
		try{
			String[] pessoaJuridica =infoMockInfoPessoaJuridica();
			String[] responsavel = infoMockInfoResponsavel();
			TelaEdicaoParceiro telaEdicaoParceiro = new TelaEdicaoParceiro();
			
			telaEdicaoParceiro.preencherCamposOSC(driver, wait, true);
			
			telaEdicaoParceiro.preencherCamposPessoaJuridica(driver, wait, pessoaJuridica);
			
			telaEdicaoParceiro.preencherListaDocumentos(driver, wait, this.arquivoPadrao);
			
			telaEdicaoParceiro.preencherCamposResponsavel(driver, wait, responsavel);
			
			
			telaEdicaoParceiro.clicarIncluirDirigente(driver, wait);
			
			String[] dirigente = infoMockInfoDirigente();
			TelaModalDirigente telaModalDirigente = new TelaModalDirigente();
			telaModalDirigente.preencherCamposDirigente(driver, wait, dirigente);
			
		}catch(Exception te){
			System.err.println("\n\nErro no teste\n");
			Utils.temporizar(this.tempoInicial);
		}
		
		
	}
	
	

	
	private String[] infoMockInfoPessoaJuridica(){
		
		String nomeEmpresa = this.geradorNomes.compose(2) + " " + this.geradorNomes.compose(2);
		String[] pessoaJuridica = new String[10];
		pessoaJuridica[0] = nomeEmpresa;
		pessoaJuridica[1] = ("GERACAO AUTOMATICA PARCEIRO - " + nomeEmpresa).toUpperCase();;
		pessoaJuridica[2] = "01/01/2000";
		pessoaJuridica[3] = "Abate de aves";
		pessoaJuridica[4] = "wwww.umWebSite.com";
		pessoaJuridica[5] = "emailDoParceiro@parceiro.com";
		
		return pessoaJuridica;
	}
	
	private String[] infoMockInfoResponsavel(){
		
		String[] responsavel = new String[13];
		responsavel[0] = this.geradorCpfCnpj.cpf();
		responsavel[1] = this.geradorNomes.compose(3) + " " + this.geradorNomes.compose(4);
		responsavel[2] = "CASADO";
		responsavel[3] = "MASCULINO";
		responsavel[4] = "01/01/1990";
		responsavel[5] = "Documento de identidade";
		responsavel[6] = "423423423";
		responsavel[7] = "SDS";
		responsavel[8] = "CE";
		responsavel[9] = "01/01/2017";
		responsavel[10] = "Advogado";
		responsavel[11] = "Este e o nome da mae mock";
		responsavel[12] = "Este e o nome do pai mock";
		
		return responsavel;
	}
	
	private String[] infoMockInfoDirigente(){
		
		String[] dirigente = new String[10];
		dirigente[0] = this.geradorCpfCnpj.cpf();
		dirigente[1] = this.geradorNomes.compose(3) + " " + this.geradorNomes.compose(4);
		dirigente[2] = "CASADO";
		dirigente[3] = "MASCULINO";
		dirigente[4] = "05/05/1995";
		dirigente[5] = "Documento de identidade";
		dirigente[6] = "123123123";
		dirigente[7] = "SDS";
		dirigente[8] = "CE";
		dirigente[9] = "01/01/2018";
		dirigente[10] = "Advogado";
		return dirigente;
	}
}
