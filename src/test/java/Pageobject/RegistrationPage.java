package Pageobject;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage extends BasePage
{
   
   public RegistrationPage(WebDriver driver)  //constructor
   {
	   super(driver);   //invokes immediate parent class constructor means BasePage driver
   }
   @FindBy(xpath="//input[@id='input-firstname']") WebElement txtFirstname;
   @FindBy(xpath="//input[@id='input-lastname']") WebElement txtLastname;
   @FindBy(xpath="//input[@id='input-email']") WebElement txtemail;
   @FindBy(xpath="//input[@id='input-telephone']") WebElement txtTelephone;
   @FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
   @FindBy(xpath="//input[@id='input-confirm']") WebElement txtconfirmPassword;
   @FindBy(xpath="//input[@name='agree']") WebElement chechPolicy;
   @FindBy(xpath="//input[@value='Continue']") WebElement btncontinue;
   @FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgConfirmation;
   public void setfname(String fname) {
	   txtFirstname.sendKeys(fname);
   }
   public void setLname(String lname) {
	   txtLastname.sendKeys(lname);
   }
   public void setEmail(String email) {
	   txtemail.sendKeys(email);
   }
   public void setTelephone(String phnum) {
	   txtTelephone.sendKeys(phnum);
   }
   public void setPassword(String pwd) {
	   txtPassword.sendKeys(pwd);
   }
   public void confirmPassword(String pwd)   // password and confirm password is same hence we take "pwd" in both method
   {
	   txtconfirmPassword.sendKeys(pwd);
   }
   public void checkPolicy() {
	   chechPolicy.click();
   }
   public void continuebtn() {
	   //sol1
	   btncontinue.click();
	   
	   //sol2
	   //btncontinue.submit();
	   
	   //sol3
	   //Actions act=new Actions(driver);
	   //act.moveToElement(btncontinue).click().perform();
	   
	   //sol4
	   //JavascriptExecutor js=(JavascriptExecutor)driver;
	   //js.executeScript("arguments[0].click();", btncontinue);
	   
	   //sol5
	   //btncontinue.sendKeys(Keys.RETURN);
	   
	   //sol6
	   //WebDriverWait mywait= new WebDriverWait(driver,Duration.ofSeconds(10));
	   //mywait.until(ExpectedConditions.elementToBeClickable(btncontinue)).click();
	   
   }
   public String GetConfirmationMSG() {
	   try {
		   return (msgConfirmation.getText());
	   } catch(Exception e) {
		   return (e.getMessage());
	   }
   }
}
