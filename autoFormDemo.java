import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class autoFormDemo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "/selenium/chromedriver.exe");
		//System.setProperty("webdriver.gecko.driver", "/selenium/geckodriver.exe");

		WebDriver driver = new ChromeDriver();
		//WebDriver driver = new FirefoxDriver();
		  
		//hard coded path to csv file.
		CSVreader r = new CSVreader("data.csv");
		String [][] data = r.getData();
	
		//Puts an Implicit wait, Will wait for 10 seconds before throwing exception
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		      
		//hard coded path to html form
		driver.navigate().to("file:///E:/workspace/AutoFormDemo/testForm.html");

		
		for (int i = 0; i < r.getRows(); i ++)
		{
			for (int j = 0; j < r.getColumns(); j++)
			{
				switch (j) {
					case 0:
						driver.findElement(By.name("firstName")).sendKeys(data[i][j]);
						break;
					case 1:
						driver.findElement(By.name("lastName")).sendKeys(data[i][j]);
						break;
					case 2:
						driver.findElement(By.name("address")).sendKeys(data[i][j]);
						break;
					case 3:
						driver.findElement(By.name("city")).sendKeys(data[i][j]);
						break;
					case 4:
						Select dropdown = new Select(driver.findElement(By.id("state")));
						dropdown.selectByValue(data[i][j]);
						break;
					case 5:
						driver.findElement(By.name("zip")).sendKeys(data[i][j]);
						break;
					case 6:
						driver.findElement(By.name("phone")).sendKeys(data[i][j]);
						break;
					case 7:
						driver.findElement(By.name("birthDate")).sendKeys(data[i][j]);
						break;
					case 8:
						if (data[i][j].equals("Y")){
							if (!driver.findElement(By.name("checkBox")).isSelected())
							{
								driver.findElement(By.name("checkBox")).click();
							}
						}
						break;
					default:
						break;
				}
			}
			// Click Submit Button
			driver.findElement(By.id("submit")).click();
		}
		      
		//Close the Browser.
		//driver.close();

	}
}
