package Testcases;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pageobject.Homepage;
import Pageobject.LoginPage;
import Pageobject.MyAccountPage;
import Testbase.BaseClass;
public class TC002_LoginTest extends BaseClass    //imported BaseClass from Testbase package
{
    @Test(groups={"sanity","master"})
    public void verify_login() 
    {
      logger.info("****TC002_LoginTest Started****");
      try 
      {
    	//HomePage
    	Homepage hp=new Homepage(driver);
    	hp.clickMyAccount();
    	hp.clickLogin();
    	
    	//LoginPage
    	LoginPage lp=new LoginPage(driver);
    	lp.setEmail(p.getProperty("email"));         //taking email from "config.properties" file
    	lp.setPassword(p.getProperty("password"));   //taking password from "config.properties" file
    	lp.clickLogin();
    	
    	//MyAccount Page
    	MyAccountPage macc= new MyAccountPage(driver);
    	boolean targetPage= macc.isMyAccountPageExists();
    	
    	Assert.assertEquals(targetPage, true ,"login failed"); //you can add extra-msg(eg:"login failed) in assertEquals()
    	//Assert.assertTrue(targetPage);  //approach 2
      }
      catch(Exception e) {
    	 Assert.fail();
      }
      logger.info("***TC002_LoginTest Finished***");
   }
}
