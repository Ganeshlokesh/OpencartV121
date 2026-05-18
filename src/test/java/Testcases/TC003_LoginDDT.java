package Testcases;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pageobject.Homepage;
import Pageobject.LoginPage;
import Pageobject.MyAccountPage;
import Testbase.BaseClass;
import Utilities.DataProviders;
public class TC003_LoginDDT extends BaseClass
{
	     //"dataProviderClass=DataProviders.class" means we get "LoginData" from "DataProviders" class, and it is in different package
   @Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups="datadriven")
	public void verify_loginDDT(String email, String pwd, String res) 
    {
	   logger.info("*** TC003_LoginDDT started ***");
	 try 
	 {
	    //HomePage
        Homepage hp=new Homepage(driver);
        hp.clickMyAccount();
        hp.clickLogin();
     
        //LoginPage
        LoginPage lp=new LoginPage(driver);
        lp.setEmail(email);
        lp.setPassword(pwd);
        lp.clickLogin();
    
        //MyAccountPage
        MyAccountPage mcc= new MyAccountPage(driver);
        boolean targetPage=mcc.isMyAccountPageExists();
      
        /*Data is valid---login success - test pass - logout
          Data is valid -- login failed -- test fail
        
          Data is invalid -- login success - test fail - logout (negative testing)
          Data is invalid -- login failed - test pass*/
      
         if(res.equalsIgnoreCase("Valid")) 
          {
    	    if(targetPage==true)
    	    {
    		  mcc.clickLogout();
    		  Assert.assertTrue(true);
    	    }
    	    else 
    	    {
    		  Assert.assertTrue(false);
    	    }
          }
      
        if(res.equalsIgnoreCase("Invalid")) 
         {
    	    if(targetPage==true) 
    	    {
    		  mcc.clickLogout();
    		  Assert.assertTrue(false);
    	    }
    	    else 
    	    {
    		  Assert.assertTrue(true);
    	    }
         }
	 } 
	 catch(Exception e) 
	 {
		 Assert.fail();
	 }
      
	 logger.info("*** TC003_LoginDDT Finished ***");
   }
}
