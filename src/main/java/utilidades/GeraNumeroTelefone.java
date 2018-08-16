package main.java.utilidades;

import java.util.Random;

public class GeraNumeroTelefone {
	
	  private String compl[] = {"51", "59", "05"};
	  private Random r = new Random();
	  
	  public String gerarFixo(boolean comMascara) {
		  
		  if(comMascara){
			  return "("+ nDDD()  +") " + nPrefixo() + " - " + nFinal();
		  } else {
			  return nDDD() + nPrefixo() + nFinal();
		  }
	  }
	
	  //Gera o prefixo do telefone.
	  private String nDDD() {   
		  return "" + (19 - r.nextInt(9));
	  }
	
	  //Gera os primeiros Numeros
	  private String nPrefixo() {     
		  return "32" + compl[r.nextInt(3)];
	  }
	
	  //Gera a Numeracao final baseando-se em numeros aleatorios 
	  private String nFinal() {
		  String digitos = "";
		  for (int i=0; i<4;i++){
			  digitos += r.nextInt(10);
		  }
		  return digitos;
	  }
}
