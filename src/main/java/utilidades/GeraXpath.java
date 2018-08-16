package main.java.utilidades;

public class GeraXpath {
	
	public String geraXpathParaObterElementoComIdCamuflado(String idElemento){
		////div[contains(@id,'test')]
		return "//*[contains(@id, \""+idElemento+"\")]";
	}
	
	public String geraXpathParaObterPrimeiraLinhaTabela(String idTabela){
		return "//*[@id='"+idTabela+"']/tbody/tr[1]/td[1]";
	}

	public String geraXpathParaObterLinhaTabelaCorrespondenteAoFiltro(String idTabela, String[] filtro){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("//*[@id='"+idTabela+"']/tbody/tr[");
		
		for (int i = 0; i < filtro.length; i++) {
			
			stringBuffer.append("(td[");
			stringBuffer.append("text()='");
			stringBuffer.append(filtro[i]);
			stringBuffer.append("'])");
			
			if(i != filtro.length - 1){
				stringBuffer.append(" and ");
			}
		}
		stringBuffer.append("]");
		return stringBuffer.toString();
	}
}
//$x("//*[@id='formularioDeCrud:enderecoDataTable']/tbody/tr[(td[text()='Sim']) and (td[contains(text(), 'MATRIZ')])]")
//tbody/tr[(td[contains(text(), 'Sim')]) and (td[contains(text(), 'MATRIZ')])]
//tr[(td[1] = 'foo') and (td[2] = 'bar') and (td[3] = 'baz')]
//td[contains(text(), '"+filtro[1]+"')
//td[contains,'Car'] and td[contains,'123']/ancestor::tr