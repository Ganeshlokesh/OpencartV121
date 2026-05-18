package Testcases;
import java.time.Duration;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Pageobject.Homepage;            //imported "PageObject.Homepage" in "Testcases" package
import Pageobject.RegistrationPage;    //imported "PageObject.RegistrationPage"
import Testbase.BaseClass;             //imported "Testbase.BaseClass"
public class TC001_AccountRegistrationTest extends BaseClass 
{
    @Test(groups= {"regression","master"})
	void verify_account_registration() {
		logger.info("*** starting TC001_AccountRegistrationTest ***");
	try {
		Homepage hp= new Homepage(driver);  
		hp.clickMyAccount();
		logger.info("*** clicked on MyAccount link");
		hp.clickRegister();
		logger.info("*** clicked on Register Link ***");
		
		RegistrationPage rp= new RegistrationPage(driver);
		logger.info("providing customer details...");
		rp.setfname(randomString().toUpperCase());
		rp.setLname(randomString().toUpperCase());
		rp.setEmail(randomString()+"@gmail.com");
		rp.setTelephone(randomNumber());
		String password = randomeAlphaNumberic();
		rp.setPassword(password);
		rp.confirmPassword(password);
		rp.checkPolicy();
		rp.continuebtn();
		logger.info("Validating expected message");
		String confirmMSG=rp.GetConfirmationMSG();
		if(confirmMSG.equals("Your Account Has Been Created!"))  //valid title
		  {
			Assert.assertTrue(true);
		  }
		else {
			logger.error("Test failed");
			logger.debug("Debug logs...");
			Assert.assertTrue(false);
		     } 
		} 
	catch(Exception e) {
			Assert.fail();
		}
	    logger.info("*** Finished TC001_AccountRegistrationTest ***");
	}
}
