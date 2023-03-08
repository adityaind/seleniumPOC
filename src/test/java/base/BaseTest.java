package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageobjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage lp ;
	public WebDriver initialize() throws IOException {


		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\GlobalConfig.properties");
		prop.load(fis);
		String browserName= System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "E://chromedriver_win32/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		}


		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	public List<HashMap<String, String>> getDataFromJson(String filePath) throws IOException {
		String jsonData = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data =mapper.readValue(jsonData, new TypeReference<List<HashMap<String,String>>>() {
		});
		return data;

	}
	public String takeScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts =(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(src, dest);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	@BeforeMethod(alwaysRun = true)
	public LandingPage launch() throws IOException {
		
		driver = initialize();
		lp = new LandingPage(driver);
		lp.goTo();
		return lp;
		
		
	}
	@AfterMethod(alwaysRun = true)
	public void tearDown() throws InterruptedException {
		
		driver.quit();
	}
}
