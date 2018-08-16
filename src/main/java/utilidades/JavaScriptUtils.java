package main.java.utilidades;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JavaScriptUtils {

	public Boolean verificarSeIdExiste(WebDriver driver, String idCampo){
		return ((JavascriptExecutor) driver).executeScript("return document.getElementById('"+idCampo+"')") != null;
	}
	
	public Boolean verificarSeXpathExiste(WebDriver driver, String xpath){
		//return document.evaluate(xpath, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;
		//document.evaluate('//*[contains(@id, "AlterarPrazoBotao")]', document, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null).snapshotItem(0)
		String script = "return document.evaluate('"+xpath+"', document, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null).snapshotLength";
		System.out.print(script);
		Object ob = ((JavascriptExecutor) driver).executeScript(script);
		System.out.println(" = " + ob);
		return (Long) ob > 0;
	}
	
	public void executarAcaoRetirarFoco(WebDriver driver, String idCampo){
		((JavascriptExecutor) driver).executeScript("return document.getElementById('"+idCampo+"').blur()");
	}
	
	public void executarAcaoColocararFoco(WebDriver driver, String idCampo){
		((JavascriptExecutor) driver).executeScript("return document.getElementById('"+idCampo+"').focus()");
	}
}
