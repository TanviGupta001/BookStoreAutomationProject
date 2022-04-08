package tests;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class BookStore {

	public WebDriver driver;
	public String extenstion = ".png";
	public String ExpectedTitle = "Git";
	public String ExpectedAuthor = "Addy";
	@Test
	public void search() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//Drivers//latest//chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://demoqa.com/books");
		driver.manage().window().maximize();
		driver.findElement(By.id("searchBox")).sendKeys("Git Pocket Guide");
		WebElement ActualTitle = driver.findElement(By.xpath("//*[@id=\"see-book-Git Pocket Guide\"]/a"));
		System.out.println("Title value is:- " + ActualTitle.getText());
		String AT = ActualTitle.getText().toString();
		driver.findElement(By.id("searchBox")).clear();
		driver.findElement(By.id("searchBox")).sendKeys("Addy Osmani");
		WebElement ActualAuthor = driver.findElement(By.xpath("(//div[@role=\"gridcell\"])[3]"));
		System.out.println("Author value is:- " + ActualAuthor.getText());
		String AA = ActualAuthor.getText().toString();
		driver.findElement(By.id("searchBox")).clear();
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
		Date date2 = new Date();
		String date3 = dateFormat.format(date2);
		System.out.println("Current date and time is " + date3);
		String bs = "BookStore";
		String bs_capture = bs + "_" + date3;
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile,
				new File(System.getProperty("user.dir") + "//ScreenShots//" + bs_capture + extenstion));
		if(AT.contains(ExpectedTitle) && AA.contains(ExpectedAuthor))
		{
		System.out.println("Test case is verified");	
		}
		else
		{
		System.out.println("Test case is not verified");
		}
		driver.quit();
	}
}
