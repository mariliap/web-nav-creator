package main.java.telas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TelaPesquisaParceiro {

	public void realizarPesquisa(WebDriver driver, WebDriverWait wait, String[] filtro){
		
		String tipoPessoa = filtro[0];
		String cpfCnpj = filtro[1];
		
		Select seletorTipoPessoa = new Select(driver.findElement(By.id("formularioDeCrud:tipo_decorate:tipo_pessoa")));
		seletorTipoPessoa.selectByVisibleText(tipoPessoa);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("processandoTransparente")));
		
		driver.findElement(By.id("formularioDeCrud:cpf_cnpj_decorate:cpf_cnpj_input")).sendKeys(cpfCnpj);
		driver.findElement(By.id("formularioDeCrud:pesquisar")).click();
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("processandoTransparente")));
		
	}
	
	public void selecionarResultadoESeguirParaTelaEdicao(WebDriver driver, WebDriverWait wait, String[] filtro){
		
		WebElement tabelaResultadoPesquisa =  driver.findElement(By.id("formularioDeCrud:pagedDataTable"));
		WebElement linhaParceiro = tabelaResultadoPesquisa.findElement(By.xpath("//tr/td[contains(text(), '"+filtro[1]+"')]"));
		linhaParceiro.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Visualizar']")));
		
		driver.findElement(By.xpath("//input[@value='Visualizar']")).click();;
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("formularioDeCrud:buttonEdits")));
		
		driver.findElement(By.id("formularioDeCrud:buttonEdits")).click();
	}
}
