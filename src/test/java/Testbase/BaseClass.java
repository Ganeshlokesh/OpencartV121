package Testbase;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
    public static WebDriver driver;
	public Logger logger;  
	public Properties p;    //import Properties
	@BeforeClass(groups= {"sanity","regression","master","datadriven"})
	@Parameters({"os","browser"})
	public void setup(String os , String br) throws IOException 
	{
		//Loading config.properties file
		FileReader file= new FileReader(".\\src\\test\\resources\\config.properties");    //import FileReader
		p=new Properties();
		p.load(file);
		logger=LogManager.getLogger(this.getClass()); 
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) 
		{
			DesiredCapabilities cap=new DesiredCapabilities();
			
		    //taking os and browser value from config.properties file
			
			//os 
			if(os.equalsIgnoreCase("windows"))
			{
			  cap.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac")) 
			{
			  cap.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("Linux")) 
			{
			  cap.setPlatform(Platform.LINUX);
			}
			else 
			{
			  System.out.println("No matching os");
			}
			
		    //browser
			switch(br.toLowerCase()) 
			{
			  case "chrome" : cap.setBrowserName("chrome"); break;
			  case "edge"  : cap.setBrowserName("MicrosoftEdge"); break;
			  case "firefox" : cap.setBrowserName("firefox"); break;
			  default : System.out.println("no matching browser"); return;
			}
			
			driver =new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) 
		{
		   switch(br.toLowerCase())
		   {
		    case "chrome" :driver=new ChromeDriver(); break;
		    case "edge" : driver=new EdgeDriver(); break;
		    case "firefox" : driver=new FirefoxDriver(); break;
		    default: System.out.println("invalid browser name"); return;
		   }
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL")); //Reading url from config.properties file
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"sanity","regression","master","datadriven"})
	public void teardown() 
	{
		driver.quit();
	}
	
	public String randomString() 
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomNumber() {
		String generatedNumber=RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	
	public String randomeAlphaNumberic() {
		String genString = RandomStringUtils.randomAlphabetic(3);
		String genNum= RandomStringUtils.randomNumeric(3);
		return genString+"@"+genNum;    //eg: xyz@657
	}
	
	public String captureScreen(String tname) throws IOException
	{
		String timestamp = new SimpleDateFormat("yyyyMMddmmss").format(new Date());
		
		TakesScreenshot takeshot= (TakesScreenshot) driver;
		File sourcefile =takeshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath= System.getProperty("user.dir")+"\\screenshots\\"+ tname + "_" + timestamp +".png";
		File targetFile = new File(targetFilePath);
		sourcefile.renameTo(targetFile);
		return targetFilePath;
	}
}
