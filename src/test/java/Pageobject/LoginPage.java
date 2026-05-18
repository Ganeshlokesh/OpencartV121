package Pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{
   public LoginPage(WebDriver driver) {
	   super(driver);
   }
   @FindBy(xpath="//input[@id='input-email']") WebElement Txtemail;
   @FindBy(xpath="//input[@id='input-password']") WebElement Txtpassword;
   @FindBy(xpath="//input[@value='Login']") WebElement btnlogin;
   
   public void setEmail(String email) 
   {
	   Txtemail.sendKeys(email);
   }
   public void setPassword(String pwd) {
	   Txtpassword.sendKeys(pwd);
   }
   public void clickLogin() {
	   btnlogin.click();
   }
}
