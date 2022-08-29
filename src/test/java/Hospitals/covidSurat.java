package Hospitals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class covidSurat {
	
	static WebDriver driver;
	public static void main(String args[]) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		
		// Headless chrome
		 
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--headless");
		driver = new ChromeDriver(opt);
		
		
		//driver = new ChromeDriver();
		driver.get("http://office.suratsmartcity.com/SuratCOVID19/Home/COVID19BedAvailabilitydetails");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		
		By zone = By.id("ddlZone");
		By facility = By.id("ddlFacilityType");
		
		String[] zonelist = {"West Zone","Central Zone","North Zone","East Zone - A",
				"South Zone","South West Zone","South East Zone","East Zone - B","South Zone-B"};
		
		for(String zl:zonelist) {
		
		Select select1 = new Select(driver.findElement(zone));
		select1.selectByVisibleText(zl);
		
		Select select2 = new Select(driver.findElement(facility));
		select2.selectByVisibleText("Hospital");
		System.out.println("***********Hospitals in "+zl+"******************");
		
		//List<WebElement> list = driver.findElements(By.xpath("//a[@class=' hospital-info']"));
		List<WebElement> list = driver.findElements(By.xpath("//div[@class='card custom-card']//a"));
		int size = list.size();
		List<WebElement> beds = driver.findElements(By.xpath("//div[@class='card custom-card']//div//span[@class='count-text']"));
		List<WebElement> vacant = driver.findElements(By.xpath("//div[@class='card custom-card']//div//span[@class='count-text pr-2']"));
		List<WebElement> wards = driver.findElements(By.xpath("//div[contains(@id,'collapseOne')]//li[1]/div/div[2]"));
		List<WebElement> O2ward = driver.findElements(By.xpath("//div[contains(@id,'collapseOne')]//li[2]/div/div[2]"));
		List<WebElement> bipap = driver.findElements(By.xpath("//div[contains(@id,'collapseOne')]//li[3]/div/div[2]"));
		List<WebElement> vent = driver.findElements(By.xpath("//div[contains(@id,'collapseOne')]//li[4]/div/div[2]"));
		System.out.println("No. of Hospitals: "+list.size());
		System.out.println("***************************************************************************************");
		
		for(int i=0;i<size;i++) {
		
			
		list.get(i).click();
		Thread.sleep(500);
		WebElement popup = driver.findElement(By.xpath("//div[@class='modal-content']"));
		if(popup.isDisplayed()== true) {
		String HospitalName = driver.findElement(By.xpath("//div[@class='modal-content']//label[@id='lblhosname']")).getText();
		System.out.println("Name of Hospital: "+HospitalName);
		String address = driver.findElement(By.xpath("//div[@class='modal-content']//label[@id='lblhosaddress']")).getText();
		System.out.println("Address: "+address);
		String contact = driver.findElement(By.xpath("//div[@class='modal-content']//span[@id='lblhosCno']")).getText();
		System.out.println("Contact Number: "+contact);
		Thread.sleep(500);
		System.out.println(beds.get(i).getText());
		System.out.println(vacant.get(i).getText());
		
		driver.findElement(By.xpath("//div[@class='modal-content']//button")).click();
		Thread.sleep(200);}
		
		else { 
			System.out.println("Name of Hospital: "+list.get(i).getText());
			System.out.println(beds.get(i).getText());
			System.out.println(vacant.get(i).getText());
		}
		//String Ward = driver.findElement(By.xpath("//div[@id='collapseOne-"+j+"']//li[1]/div/div[2]")).getText();
		System.out.println("Ward: "+wards.get(i).getText());
		System.out.println("HDU(O2): "+O2ward.get(i).getText());
		System.out.println("BiPap: "+bipap.get(i).getText());
		System.out.println("Ventilator: "+vent.get(i).getText());
		
		
		
		System.out.println("***************************************************************************************");
		Thread.sleep(500);
	}}
	driver.quit();
	}
	

}
