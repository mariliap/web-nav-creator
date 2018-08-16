package main.java.telas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TelaLogin {

	
	public void logarNoSistema(WebDriver driver, String cpfLogin){
 		
 		if(!driver.findElements(By.id("botaoContinuar")).isEmpty()){
			driver.findElement(By.id("botaoContinuar")).click();
		}
 		
		driver.findElement(By.id("login:username")).sendKeys(cpfLogin);
		driver.findElement(By.id("login:password")).sendKeys("adm");		
		driver.findElement(By.id("login:login")).click();
//		new WebDriverWait(driver, 1200).until(
//		          webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
		
	}
}
