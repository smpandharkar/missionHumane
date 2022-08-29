package Hospitals;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class covidGurugram {
	
	static WebDriver driver;
	public static void main(String args[]) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		
		//Headless chrome
		/*ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--headless");
		driver = new ChromeDriver(opt);*/
		
		driver = new ChromeDriver();
		driver.get("https://covidggn.com/public/pages/gurugram-hospitals");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		
		List<WebElement> list = driver.findElements(By.xpath("//tr[@class='ng-scope']"));
		int size = list.size();
		
		String totalObeds = driver.findElement(By.xpath("//div[@class='content-info-in row']/div[1]/p[2]")).getText()+":"+
		driver.findElement(By.xpath("//div[@class='content-info-in row']/div[1]/p[1]")).getText();
		String totalICUbeds = driver.findElement(By.xpath("//div[@class='content-info-in row']/div[2]/p[2]")).getText()+":"+
				driver.findElement(By.xpath("//div[@class='content-info-in row']/div[2]/p[1]")).getText();
		String totalVbeds = driver.findElement(By.xpath("//div[@class='content-info-in row']/div[3]/p[2]")).getText()+":"+
		driver.findElement(By.xpath("//div[@class='content-info-in row']/div[3]/p[1]")).getText();
		String totalEbeds = driver.findElement(By.xpath("//div[@class='content-info-in row']/div[4]/p[2]")).getText()+":"+
		driver.findElement(By.xpath("//div[@class='content-info-in row']/div[4]/p[1]")).getText();
		
		System.out.println("No. of Covid hospitals in Gurugram: "+size);
		
		String path = ".\\datafiles\\hospitals.xlsx";
		WriteXLSUtility util = new WriteXLSUtility(path);
	
		List<WebElement> hlist = driver.findElements(By.xpath("//tr[@class='ng-scope']//h6[@class='ng-binding']"));
		List<WebElement> address = driver.findElements(By.xpath("//tr[@class='ng-scope']//div[@class='ng-binding']"));
		List<WebElement> contact = driver.findElements(By.xpath("//tr[@class='ng-scope']/td[4]"));
		List<WebElement> oxybed = driver.findElements(By.xpath("//tr[@class='ng-scope']/td[3]//div/div[1]"));
		List<WebElement> icubed = driver.findElements(By.xpath("//tr[@class='ng-scope']/td[3]//div/div[2]"));
		List<WebElement> ventbed = driver.findElements(By.xpath("//tr[@class='ng-scope']/td[3]//div/div[3]"));
		
		util.setCellData("Summary", 0, 0, driver.findElement(By.xpath("//div[@class='container']//h3")).getText());
		util.setCellData("Summary", 1, 0, totalObeds);
		util.setCellData("Summary", 1, 1, driver.findElement(By.xpath("//div[@class='content-info-in row']/div[1]/h4")).getText());
		util.setCellData("Summary", 2, 0, totalICUbeds);
		util.setCellData("Summary", 2, 1,driver.findElement(By.xpath("//div[@class='content-info-in row']/div[2]/h4")).getText());
		util.setCellData("Summary", 3, 0, totalVbeds);
		util.setCellData("Summary", 3, 1,driver.findElement(By.xpath("//div[@class='content-info-in row']/div[3]/h4")).getText());
		util.setCellData("Summary", 4, 0, totalEbeds);
		util.setCellData("Summary", 4, 1,driver.findElement(By.xpath("//div[@class='content-info-in row']/div[4]/h4")).getText());
		
		util.setCellData("HospitalsData", 0, 0, "Hospital Name");
		util.setCellData("HospitalsData", 0, 1, "Address");
		util.setCellData("HospitalsData", 0, 2, "Contact");
		util.setCellData("HospitalsData", 0, 3, "Oxygen Bed");
		util.setCellData("HospitalsData", 0, 4, "ICU Bed");
		util.setCellData("HospitalsData", 0, 5, "Ventilator Bed");
		
		for(int i=1; i<size;i++) {
			/*System.out.println("***********************************************");
			System.out.println(hlist.get(i).getText());
			System.out.println(address.get(i).getText());
			System.out.println(contact.get(i).getText());
			System.out.println(oxybed.get(i).getText());
			System.out.println(icubed.get(i).getText());
			System.out.println(ventbed.get(i).getText());
			System.out.println("***********************************************"); */
			util.setCellData("HospitalsData", i, 0, hlist.get(i).getText());
			util.setCellData("HospitalsData", i, 1, address.get(i).getText());
			util.setCellData("HospitalsData", i, 2, contact.get(i).getText());
			util.setCellData("HospitalsData", i, 3, oxybed.get(i).getText());
			util.setCellData("HospitalsData", i, 4, icubed.get(i).getText());
			util.setCellData("HospitalsData", i, 5, ventbed.get(i).getText());
			
		}
		
		System.out.println("Webscraping is successful");
		driver.quit();
	}

}
