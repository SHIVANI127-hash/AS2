package VtigerCRM;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import CommonUtils.BaseClass;
import CommonUtils.ExcelUtil;
import CommonUtils.JavaUtil;
import CommonUtils.PropertyFileUtil;
import CommonUtils.WebDriverUtil;

public class OrganizationTestNG extends BaseClass {
	
	  PropertyFileUtil putil = new PropertyFileUtil();
	  WebDriverUtil wutil = new WebDriverUtil();
	  ExcelUtil eutil = new ExcelUtil();
	  JavaUtil jutil = new JavaUtil();
	  @Test
	  public void OrganizationTest() throws IOException, InterruptedException  {
	    
	    /*ChromeDriver dc = new ChromeDriver();
		dc.manage().window().maximize();
		//dc.manage().timeouts().implicitlyWait(Duration.ofMinutes(10));

      //to maximize the window
	    
	    wutil.maximize(dc);
	    
      //to apply wait for findelement()
	    wutil.implicitWait(dc);
	    
	   //to read data from the property file
	   String url = putil.getDataFromPropertyFile("Url");
	   String Username = putil.getDataFromPropertyFile("Username");
	   String Password = putil.getDataFromPropertyFile("Password");*/
	    
	    //to read the data from property file

           String OrgName = eutil.getDataFromExcel("Organizations", 0, 1);
           String Group = eutil.getDataFromExcel("Organizations", 1, 1);	    

	    //to launch the application
	    //dc.get(url);
	    
	    //login to application
	   /* dc.findElement(By.name("user_name")).sendKeys(Username);
	    dc.findElement(By.name("user_password")).sendKeys(Password);
	    dc.findElement(By.id("submitButton")).click();*/
	    
	    //to click on organization
	    dc.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
	    //click on create Organization...(+)
	    dc.findElement(By.cssSelector("img[alt='Create Organization...']")).click();
	    
	    //click on organization name tf pass company name in the tf
	    dc.findElement(By.name("accountname")).sendKeys(OrgName+jutil.getRandomNumber());
	    //dc.findElement(By.name("accountname")).sendKeys("Qspiders");
	   
	    //delect assign to --->Group
	    dc.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
	    
	    //click on dropdown
	    WebElement dropdown = dc.findElement(By.name("assigned_group_id"));
	    //Select s = new Select(dropdown);
	    //s.selectByVisibleText(Group);
	    wutil.handeldropdown(dropdown, Group);
	    
	    //select support from the dropddown
	    //dc.findElement(By.xpath("//option[text()='Support Group']")).click();
	    
	    //click on save button
	    dc.findElement(By.xpath("(//input[@name='button'])[1]")).click(); 
	    Thread.sleep(2000);
	    //mouseover on image
	    //WebElement image = dc.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));
	    //Actions a = new Actions(dc);
	    //a.moveToElement(image);
	    //a.perform();
	    //wutil.mouseover(dc, image);
	    
	    //dc.findElement(By.xpath("//a[text()='Sign Out']")).click();
}

	  
}