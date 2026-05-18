package Utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Testbase.BaseClass;

public class ExtentReportUtility implements ITestListener
{
   public ExtentSparkReporter sparkReporter;
   public ExtentReports extent;
   public ExtentTest test;
   
   String repName;
   public void onStart(ITestContext testcontext) 
   {
	  
	   /*SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	   Date dt= new Date();
	   String currentdatetimestamp=df.format(dt);
	   */
	   String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); 
	   
	   repName= "Test-Report-" +timeStamp + ".html";
	   sparkReporter = new ExtentSparkReporter(".\\Reports\\"+ repName); //specify the location of the report
	   sparkReporter.config().setDocumentTitle("Opencart Automation Report"); // Title of report
	   sparkReporter.config().setReportName("Opencart Functional testing"); // name of the report
	   sparkReporter.config().setTheme(Theme.DARK);
	   
	   extent =new ExtentReports();
	   extent.attachReporter(sparkReporter);
	   extent.setSystemInfo("Application", "Opencart");
	   extent.setSystemInfo("module", "admin");
	   extent.setSystemInfo("Sub module","Customers");
	   extent.setSystemInfo("User name", System.getProperty("user.name"));
	   extent.setSystemInfo("Environment", "QA");
	   
	   String os=testcontext.getCurrentXmlTest().getParameter("os");
	   extent.setSystemInfo("Operating System", os);
	   
	   String browser=testcontext.getCurrentXmlTest().getParameter("browser");
	   extent.setSystemInfo("Browser", browser);
	   
	   List<String> includedGroups= testcontext.getCurrentXmlTest().getIncludedGroups();
	   if(!includedGroups.isEmpty()) {
		   extent.setSystemInfo("Groups", includedGroups.toString());
	   }
   }
     
   public void onTestSuccess(ITestResult result) 
   {
	  test= extent.createTest(result.getTestClass().getName());
	  test.assignCategory(result.getMethod().getGroups()); //to display groups in report
	  test.log(Status.PASS, result.getName()+" got Successfully executed");
   }
   
   public void onTestFailure(ITestResult result)
   {
	   test=extent.createTest(result.getTestClass().getName());
	   test.assignCategory(result.getMethod().getGroups());
	   test.log(Status.FAIL, result.getName()+ " got failed");
	   try {
		   String imgPath = new BaseClass().captureScreen(result.getName());
		   test.addScreenCaptureFromPath(imgPath);
	   } catch(IOException e1) {
		   e1.printStackTrace();
	   }
   }
    
   public void onTestSkipped(ITestResult result) {
	   test=extent.createTest(result.getTestClass().getName());
	   test.assignCategory(result.getMethod().getGroups());
	   test.log(Status.SKIP, result.getName()+ " got skipped");
	   test.log(Status.INFO, result.getThrowable().getMessage());
   }
   
   public void onFinish(ITestContext context) 
   {
	   extent.flush();
	   
	   String pathOfExtentReport= System.getProperty("user.dir")+"\\Reports\\"+ repName;
	   File extentReport = new File(pathOfExtentReport);
	   
	   try {
		   Desktop.getDesktop().browse(extentReport.toURI());
	   } catch(IOException e) {
		   e.printStackTrace();
	   }
	   
	   /*
	   try {
	   URL url =new
	   URL("file:///"+System.getProperty("user.dir")+"\\Reports\\"+repName);
	   
	   //create the email message
	   ImageHtmlEmail email= new ImageHtmlEmail();
	   //add "apache commons email" dependency in pom.xml
	   email.setDataSourceResolver(new DataSourceUrlResolver(url));
	   email.setHostName("smtp.googleemail.com");
	   email.setSmtpPort(465);
	   email.setAuthenticator(new DefaultAuthenticator("indiacaptain484@gmail.com","password"));
	   email.setSSLOnConnect(true);
	   email.setFrom("indiancaptain484@gmail.com"); //sender
	   email.setSubject("Test Results");
	   email.setMsg("Please find Attached report...");
	   email.addTo("ganeshlgani73533@gmail.com");  //receiver
	   email.attach(url, "Extent report", "please check report..");
	   email.send(); //send the email
	   }
	   catch(Exception e) {
		   e.printStackTrace();
	   }
	   */
	   
   }
}
