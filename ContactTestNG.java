package VtigerCRM;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import CommonUtils.ExcelUtil;
import CommonUtils.JavaUtil;
import CommonUtils.PropertyFileUtil;
import CommonUtils.WebDriverUtil;

public class ContactTestNG {

	  PropertyFileUtil putil = new PropertyFileUtil();
	  WebDriverUtil wutil = new WebDriverUtil();
	  ExcelUtil eutil = new ExcelUtil();
	  JavaUtil jutil = new JavaUtil();

	@Test
	    public void OrganizationTest() throws IOException, InterruptedException {
	    ChromeDriver dc = new ChromeDriver();
        wutil.maximize(dc);
	    
      //to apply wait for findelement()
	    wutil.implicitWait(dc);
	    
	    //to read data from the property file
	    String url = putil.getDataFromPropertyFile("Url");
	    String Username = putil.getDataFromPropertyFile("Username");
	    String Password = putil.getDataFromPropertyFile("Password");
	    
	    String FirstName= eutil.getDataFromExcel("Contact", 0, 1);
	    String LastName = eutil.getDataFromExcel("Contact", 1, 1);
	    String Group    = eutil.getDataFromExcel("Contact", 2, 1);
	    String OrgName  = eutil.getDataFromExcel("Contact", 3, 1);
	    
	    
	    
      dc.get(url);
	    
	    //login to application
	    dc.findElement(By.name("user_name")).sendKeys(Username);
	    dc.findElement(By.name("user_password")).sendKeys(Password);
	    dc.findElement(By.id("submitButton")).click();
	  
	    dc.findElement(By.xpath("//a[text()='Contacts']")).click();
	    dc.findElement(By.cssSelector("img[alt='Create Contact...']")).click();
	    dc.findElement(By.name("firstname")).sendKeys(FirstName);
	    dc.findElement(By.name("lastname")).sendKeys(LastName);
	    Thread.sleep(2000);
	
	   String actualurl = dc.getCurrentUrl();
	   String expectedurl = "";
	   Assert.assertEquals(actualurl, expectedurl);
	    
	    
	    dc.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
	    WebElement dropdown = dc.findElement(By.name("assigned_group_id"));
	    wutil.handeldropdown(dropdown, Group);
      
       dc.findElement(By.xpath("(//img[@alt='Select'])[1]")).click();
	    wutil.switchwindow(dc, "http://localhost:8888/index.php?module=Accounts&action=Popup&popuptype=specific_contact_account_address&form=TasksEditView&form_submit=false&fromlink=&recordid=");
       
	    /*Set<String> ids = dc.getWindowHandles();		  
       System.out.println(ids);
          for(String e: ids) {
	   String actualurl = dc.switchTo().window(e).getCurrentUrl();
	   System.out.println(actualurl);
		  
		String Childurl = "http://localhost:8888/index.php?module=Accounts&action=Popup&popuptype=specific_contact_account_address&form=TasksEditView&form_submit=false&fromlink=&recordid=";
		    if(actualurl.contains(Childurl)) {
			  
		  }
      }*/
      
        dc.findElement(By.id("search_txt")).sendKeys(OrgName);  
	    dc.findElement(By.name("search")).click();
		dc.findElement(By.xpath("//a[text()='Qspider3']")).click();
		Thread.sleep(2000);
		
		wutil.switchwindow(dc, "");

		/*Set<String> ids1 = dc.getWindowHandles();		  
        System.out.println(ids);
          for(String e: ids1) {
		String actualurl = dc.switchTo().window(e).getCurrentUrl();
		System.out.println(actualurl);
		  
		String Parenturl = "http://localhost:8888/index.php?module=Contacts&action=EditView&return_action=DetailView&parenttab=Marketing";
		  if(actualurl.contains(Parenturl)) {
			  
		  }
      }*/

		
	    dc.findElement(By.xpath("(//input[@name='button'])[1]")).click(); 
      Thread.sleep(2000);
		    //mouseover on image
		WebElement image = dc.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));
		wutil.mouseover(dc, image);
		    
		dc.findElement(By.xpath("//a[text()='Sign Out']")).click();
}
}