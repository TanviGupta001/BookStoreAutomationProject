package Helpers;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;
import org.monte.media.Format;
import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;
import org.monte.media.math.Rational;

public class SetUp {

	public WebDriver driver;
	public Properties prop;
	public WebDriverWait wait;
	public ScreenRecorder screenRecorder;
	
	public WebDriver init_driver(String browserName)
	{
		
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
			if(prop.getProperty("headless").equals("yes"))
			{
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				driver = new ChromeDriver(options);
				wait = new WebDriverWait(driver, 7000);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
			}			
			else
			{
				driver = new ChromeDriver();
				wait = new WebDriverWait(driver, 7000);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
		}else if(browserName.equals("ie")) {
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"//Drivers//IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			wait = new WebDriverWait(driver, 7000);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}else if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"//Drivers//geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
	        options.setBinary("C:\\Users\\ashah\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
	        WebDriver driver =  new FirefoxDriver(options);
			wait = new WebDriverWait(driver, 7000);
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}else if(browserName.equals("chromeoptions")) {
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("user-data-dir=C:\\Users\\ashah\\AppData\\Local\\Google\\Chrome\\User Data");
			driver = new ChromeDriver(options);
			wait = new WebDriverWait(driver, 7000);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}else {
			System.out.println("Instead of Chrome, firefox and ie browsers are selected");
		}
		return driver;
	}
	public Properties init_properties() {
		prop = new Properties();
		try
		{
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"//Properties//config.properties");
		prop.load(ip);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	public void StartRecording() throws Exception{
		GraphicsConfiguration gconfig = GraphicsEnvironment
		         .getLocalGraphicsEnvironment()
		         .getDefaultScreenDevice()
		         .getDefaultConfiguration();
		
		screenRecorder = new ScreenRecorder(gconfig,
		         new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
		         new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey,
		            ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
		            CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
		            DepthKey, (int)24, FrameRateKey, Rational.valueOf(15),
		            QualityKey, 1.0f,
		            KeyFrameIntervalKey, (int) (15 * 60)),
		         new Format(MediaTypeKey, MediaType.VIDEO,
		            EncodingKey,"black", FrameRateKey, Rational.valueOf(30)), null);
		screenRecorder.start();
	}
	public void StopRecording() throws Exception{
		screenRecorder.stop();
	}
}
