package withExcel;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DDT {

	WebDriver driver;
	Workbook wb;
	Sheet sh1;
	int numrow;
	String username;
	String password;
	
	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Bala\\Downloads\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://selfservice.adappt.co.uk/login");	
	}
	
	@Test
	public void testselfservice(String uname, String pass) throws InterruptedException {
		
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(5000);
	}
	
	@DataProvider(name="testdata")
	public Object[][] Testdata(){
		
	try {
		
		wb=Workbook.getWorkbook(new File("location of excel file"));
		sh1=wb.getSheet(0);
		numrow=sh1.getRows();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	
	Object [][] data=new Object[numrow][sh1.getColumns()];
	 
	// This will run a loop and each iteration  it will fetch new row
	for(int i=0;i<numrow;i++){
	 
	// Fetch first row username
	data[i][0]=sh1.getCell(0,i).getContents();
	// Fetch first row password
	data[i][1]=sh1.getCell(1,i).getContents();
	}
	
	return data;
}

}
