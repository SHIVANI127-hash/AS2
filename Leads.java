package VtigerCRM;


import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import CommonUtils.ExcelUtil;
import CommonUtils.JavaUtil;
import CommonUtils.PropertyFileUtil;
import CommonUtils.WebDriverUtil;

public class Leads {
	  PropertyFileUtil putil = new PropertyFileUtil();
	  WebDriverUtil wutil = new WebDriverUtil();
	  ExcelUtil eutil = new ExcelUtil();
	  JavaUtil jutil = new JavaUtil();

	@Test
	    public void OrganizationTest() throws EncryptedDocumentException, IOException  {
	    ChromeDriver dc = new ChromeDriver();
        wutil.maximize(dc);
	    
        //to apply wait for findelement()
	    wutil.implicitWait(dc);
	    
	    //to read data from the property file
	    String url = putil.getDataFromPropertyFile("Url");
	    String Username = putil.getDataFromPropertyFile("Username");
	    String Password = putil.getDataFromPropertyFile("Password");
	    
	    String FirstName = eutil.getDataFromExcel("Leads", 0, 1);
	    String LastName = eutil.getDataFromExcel("Leads", 1, 1);
	    String OrgName = eutil.getDataFromExcel("Leads", 2, 1);
	    String Group = eutil.getDataFromExcel("Leads", 3, 1);
	    
        dc.get(url);
	    
	    //login to application
	    dc.findElement(By.name("user_name")).sendKeys(Username);
	    dc.findElement(By.name("user_password")).sendKeys(Password);
	    dc.findElement(By.id("submitButton")).click();
	    
	    dc.findElement(By.xpath("(//a[text()='Leads'])[1]")).click();
	    dc.findElement(By.cssSelector("img[alt='Create Lead...']")).click();
	    dc.findElement(By.name("firstname")).sendKeys(FirstName);
	    dc.findElement(By.name("lastname")).sendKeys(LastName);
	    dc.findElement(By.name("company")).sendKeys(OrgName);
	    dc.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
	    WebElement dropdown = dc.findElement(By.name("assigned_group_id"));
	    wutil.handeldropdown(dropdown, Group);
	    
	}
}
