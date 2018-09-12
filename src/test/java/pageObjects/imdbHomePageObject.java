package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class imdbHomePageObject extends BaseClassForPageObjects{

	public WebElement watchList() {
		return driver.findElement(By.xpath(".//*[@id='navWatchlistMenu']/p/a"));
	}
	
	public WebElement top250Movies() {
		return driver.findElement(By.xpath(".//*[@id='navWatchlist']/li[3]/a/img"));
	}
	
}
