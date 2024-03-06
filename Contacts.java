package VtigerCRM;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import CommonUtils.ExcelUtil;
import CommonUtils.ListenerImplementation;
import CommonUtils.PropertyFileUtil;
import CommonUtils.WebDriverUtil;

@Listeners(ListenerImplementation.class)
public class Contacts {
	
	WebDriverUtil wutil = new WebDriverUtil();
	PropertyFileUtil putil = new PropertyFileUtil();
	ExcelUtil eutil = new ExcelUtil();
	
	
	@Test
	public void ContactsTest() throws IOException, InterruptedException {
		
		//To Launch empty browser
		WebDriver dc = new ChromeDriver();
		//To maximize the browser window
		wutil.maximize(dc);
		//To apply implicit wait for findelement()
		wutil.implicitWait(dc);
		
		//To read data from Property File
		String URL = putil.getDataFromPropertyFile("Url");
		String USERNAME = putil.getDataFromPropertyFile("Username");
		String PASSWORD = putil.getDataFromPropertyFile("Password");
		
		//To read data from Excel File
		String FIRSTNAME = eutil.getDataFromExcel("Contacts", 0, 1);
		String LASTNAME = eutil.getDataFromExcel("Contacts", 1, 1);
		String GROUP = eutil.getDataFromExcel("Contacts", 2, 1);
		String ORGNAME = eutil.getDataFromExcel("Contacts", 3, 1);
		
		
		
		
		//To launch application
		dc.get(URL);
		
		//Login to application
		dc.findElement(By.name("user_name")).sendKeys(USERNAME);
		dc.findElement(By.name("user_password")).sendKeys(PASSWORD);
		dc.findElement(By.id("submitButton")).click();
		
		//To Click on Contacts
		dc.findElement(By.xpath("//a[text()='Contacts']")).click();
		
		//To Click on Create Contacts..(+)
		dc.findElement(By.cssSelector("img[alt='Create Contact...']")).click();
		
		//Enter Firstname
		dc.findElement(By.name("firstname")).sendKeys(FIRSTNAME);
		
		//Enter LastName
		dc.findElement(By.name("lastname")).sendKeys(LASTNAME);
		
		//To fail the testscript
		String actualurl = dc.getCurrentUrl();
		String expectedurl="http://localhost:8888/pune/index.php?actions=index&module=Home/Pune";
		Assert.assertEquals(actualurl, expectedurl);
		
		//Click Group radio button
		dc.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
		
		//In dropdown select Team selling
		WebElement dropdown = dc.findElement(By.name("assigned_group_id"));
		wutil.handeldropdown(dropdown, GROUP);

		//Click on select(+)in Organization Name text feild
		dc.findElement(By.xpath("(//img[@alt='Select'])[1]")).click();
		
		//Transfer the driver control from Parent window to Child Window
		wutil.switchwindow(dc, "http://localhost:8888/index.php?module=Accounts&action=Popup&popuptype=specific_contact_account_address&form=TasksEditView&form_submit=false&fromlink=&recordid=");
		

		//To Enter Organization name in searchtf
		dc.findElement(By.id("search_txt")).sendKeys(ORGNAME);
		
		//To Click on search now button
		dc.findElement(By.name("search")).click();
		
		//Click on Organization name
		dc.findElement(By.xpath("//a[text()='Qspider3']")).click();
	
	//To Transfer control from Child window to Parent window
		wutil.switchwindow(dc, "http://localhost:8888/index.php?module=Contacts&action=EditView&return_action=DetailView&parenttab=Marketing");
		
		//Click on Save button
		dc.findElement(By.xpath("(//input[@name='button'])[1]")).click();
	
		Thread.sleep(2000);
		
		//To Take screenshot of contact
		//wutil.screenshot(dc, "Contact");
		
		Thread.sleep(2000);
		
		//Mousehover on image
		WebElement image = dc.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));
		wutil.mouseover(dc, image);
		
		//Click on Signout
		dc.findElement(By.xpath("//a[text()='Sign Out']")).click();
	
	
	}

}