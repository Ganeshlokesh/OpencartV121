package Utilities;
import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {
   
	//DataProvider 1
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\Opencart_LoginData.xlsx";  // (.\\) indicates "current project"
		ExcelUtility xlutil= new ExcelUtility(path); //creating an object for ExcelUtility
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1", 1);  // 1 means First row
		
		String logindata[][]=new String[totalrows][totalcols];//created for 2D array which can store data
		
		for(int i=1;i<=totalrows;i++)  //1 //read the data from xl storing in 2D array
		{
			for(int j=0;j<totalcols;j++) //0 
			{
			  logindata[i-1][j]= xlutil.getCellData("Sheet1", i, j);//array index starts from 0,hence we store 'i' in 'i-1' position
			}
		}
	   return logindata; //returning two dimension array
	}
	
	//DataProvider 2

}
