package exercise.salesForce;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForceAutomation {
	
	
	WebDriver driver;
	
	@BeforeClass
	public void setupDriver() {
		//WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", "C:\\selAdvanced\\driver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-notifications");
		driver = new ChromeDriver(options);
	}
	
  @Test
  public void login() {
	  driver.get("https://login.salesforce.com/");
	  driver.manage().window().maximize();
	  driver.findElement(By.id("username")).clear();
	  driver.findElement(By.id("username")).sendKeys("hari.radhakrishnan@testleaf.com");
	  driver.findElement(By.id("password")).clear();
	  driver.findElement(By.id("password")).sendKeys("India@123");
	  driver.findElement(By.id("Login")).click();
	  
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Learn More']")));
	  Actions action = new Actions(driver);
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@title='User']")));
	  action.moveToElement(driver.findElement(By.xpath("//img[@title='User']"))).build().perform();
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='View profile']")));
	  assertEquals("View profile", driver.findElement(By.xpath("//div[text()='View profile']")).getText());
	  
	  //Create Lead
	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//lightning-icon[contains(@class,'slds-button slds-button--icon')]")));
	  action.moveToElement(driver.findElement(By.xpath("//lightning-icon[contains(@class,'slds-button slds-button--icon')]"))).build().perform();
	  driver.findElement(By.xpath("//lightning-icon[contains(@class,'slds-button slds-button--icon')]")).click();
	  driver.findElement(By.xpath("//span[text()='New Lead']")).click();
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='select']")));
	  driver.findElement(By.xpath("//a[@class='select']")).click();
	  driver.findElement(By.xpath("//a[text()='Ms.']")).click();
	  driver.findElement(By.xpath("//input[contains(@class,'lastName compoundBLRadius')]")).clear();
	  driver.findElement(By.xpath("//input[contains(@class,'lastName compoundBLRadius')]")).sendKeys("Velusamy");
	  driver.findElement(By.xpath("(//input[@class=' input'])[3]")).clear();
	  driver.findElement(By.xpath("(//input[@class=' input'])[3]")).sendKeys("GL");
	  driver.findElement(By.xpath("//button[contains(@class,'slds-button slds-button--brand')]")).click();
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'toastMessage slds-text-heading--small')]")));
	  System.out.println(driver.findElement(By.xpath("//span[contains(@class,'toastMessage slds-text-heading--small')]")).getText());
	  assertEquals("Lead Velusamy was created.", driver.findElement(By.xpath("//span[contains(@class,'toastMessage slds-text-heading--small')]")).getText());
	  
	  //Sales
	  driver.findElement(By.xpath("//div[@class='appLauncher slds-context-bar__icon-action']/child::button")).click();
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='View All']")));
	  driver.findElement(By.xpath("//button[text()='View All']")).click();
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span/p[text()='Sales']")));
	  driver.findElement(By.xpath("//span/p[text()='Sales']")).click();
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@title='Sales']")));
	  assertEquals("Sales", driver.findElement(By.xpath("//span[@title='Sales']")).getText());
	  
	  
  }

  @AfterClass
  public void tearDown() {
	  driver.close();
	  driver.quit();
  }
}
