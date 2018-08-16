package main.java.telas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TelaPrincipal {

	public void selecionarItemMenuCadastro(WebDriver driver, String textoMenu){
		
		Actions builder = new Actions(driver);
		WebElement hoverCadastro = driver.findElement(By.id("menu:form_menu_corporativo:menuCadastro"));
		builder.moveToElement(hoverCadastro).perform();
		driver.findElement(By.linkText(textoMenu)).click();
	}
	
	
}
