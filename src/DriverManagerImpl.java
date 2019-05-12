import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 * @author Jorge Lopez Pellicer
 *
 */
public class DriverManagerImpl implements DriverManager {

	WebDriver driver ;
	WebDriverWait wait ;
	JavascriptExecutor js;  
	
	public DriverManagerImpl(DRIVER type) {
		createDriver(type);
	}
	
	private void createDriver(DRIVER type) {
		try {
			System.setProperty(type.getName(), type.getPath());
			switch(type) {
				case CHROME_DRIVER: {
					System.out.println(type.getPath());
					driver = new ChromeDriver();
					break;
				}
				case PHANTOM_JS : {
					DesiredCapabilities caps = new DesiredCapabilities();
					caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, type.getPath());
					caps.setJavascriptEnabled(true);   
					driver = new PhantomJSDriver(caps);
					break;
				}
			}
			wait = new WebDriverWait(driver, 30);
			js = (JavascriptExecutor) driver; 
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void openUrl(String url) {
		driver.get(url);
	}

	@Override
	public boolean clickElement(String cssSelector) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector))).click();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String readElement(String cssSelector) {
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector))).getText();		
		}catch(Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	@Override
	public boolean writeElement(String cssSelector, String message) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector))).sendKeys(message);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<WebElement> getElements(String cssSelector) {
		try {
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(cssSelector)));
		}catch(Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public WebElement getElement(String cssSelector) {
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean executeScript(String script) {
		try {
			js.executeScript(script);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean closeDriver() {
		try {
			if(driver != null) {
				driver.close();
				driver.quit();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean submit(String cssSelector) {
		try {
			getElement(cssSelector).submit();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String getSourceCode() {
		return driver.getPageSource();
	}

	@Override
	public String getUrl() {
		return driver.getCurrentUrl();
	}
	
	
}
