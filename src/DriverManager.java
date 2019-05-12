import java.util.List;

import org.openqa.selenium.WebElement;

/**
 *
 * @author Jorge Lopez Pellicer
 *
 */

public interface DriverManager {

	void openUrl(String url);
	boolean clickElement(String cssSelector);
	String readElement(String cssSelector);
	boolean writeElement(String cssSelector, String message);
	List<WebElement> getElements(String cssSelector);
	WebElement getElement(String cssSelector);
	boolean executeScript(String script);
	boolean closeDriver();
	boolean submit(String cssSelector);
	String getSourceCode();
	String getUrl();
}
