package Pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends BasePage 
{
  //constructor
  public Homepage(WebDriver driver)      
  {
	  super(driver);    //it invokes immediate parent class constructor it means BasePage driver
  }
  //locators
  @FindBy(xpath="//span[normalize-space()='My Account']") WebElement lnkmyaccount;
  @FindBy(xpath="//a[normalize-space()='Register']") WebElement lnkregister;
  @FindBy(linkText="Login") WebElement lnklogin;
 //action methods
  public void clickMyAccount() {
	  lnkmyaccount.click();
  }
  public void clickRegister() {
	  lnkregister.click();
  }
  public void clickLogin() {
	  lnklogin.click();
  }
  
}
