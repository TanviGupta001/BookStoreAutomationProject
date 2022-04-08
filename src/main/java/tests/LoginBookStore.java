package tests;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class LoginBookStore {

	public WebDriver driver;
	public String ExpectedUsername = "tanvi";
	public WebDriverWait wait;
	
	@Test
	public void login() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//Drivers//latest//chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://demoqa.com/books");
		wait = new WebDriverWait(driver, 7000);
		driver.manage().window().maximize();
		driver.findElement(By.id("login")).click();
		driver.findElement(By.id("userName")).sendKeys(decodeandSend("dGFudmk="));
		driver.findElement(By.id("password")).sendKeys(decodeandSend("VGFudml0ZXN0QDEyMw=="));
		driver.findElement(By.id("login")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName-value")));
		WebElement Actualusername = driver.findElement(By.id("userName-value"));
		String AU = Actualusername.getText().toString();
		System.out.println("Your Actual Username is:- "+ AU);
		driver.findElement(By.id("submit")).click();
		driver.quit();
	}
	public static String decodeandSend(String YourEncodedValue)
	{
		byte[] decodeString=Base64.decodeBase64(YourEncodedValue);
		return(new String(decodeString));
	}
}
