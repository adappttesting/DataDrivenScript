package withoutExcel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class testDDT {
	
	WebDriver driver;
	
	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Bala\\Downloads\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://selfservice.adappt.co.uk/login");	
	}
	
	@Test(dataProvider="logindata")
	public void loginselfservice(String username, String password) throws InterruptedException {
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(5000);
		
//		Assert.assertTrue(driver.getTitle().contains("SELFSERVICE"),"User is not able to login-invalid credentials");
		Assert.assertTrue(driver.getTitle().contains("Feeds List"), "User login is successful");
		
	}

	
	@DataProvider(name="logindata")
	public Object[][] passData(){
		
		Object[][] data=new Object[3][2];
		
		/*invalid credential*/
		data[0][0]="admin1";
		data[0][1]="password1";
		
		/*invalid credential*/
		data[1][0]="admin2";
		data[1][1]="password2";
		
		/*valid credential*/
		data[2][0]="Admin";
		data[2][1]="E69@hz8yu";
		
		return data;
	}
	
	@AfterTest
	public void teardown() {
		driver.quit();
	}
	
}
