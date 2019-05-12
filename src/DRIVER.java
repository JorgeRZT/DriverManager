
/**
 * 
 * @author Jorge Lopez Pellicer
 *
 */

public enum DRIVER {

	PHANTOM_JS ("phantomjs.binary.path"),
	CHROME_DRIVER ("webdriver.chrome.driver");
	
	private final String driverName;
	
	private DRIVER(String name){
		driverName = name;
	}
	
	public String getPath() {
		if(this.driverName.equals(CHROME_DRIVER.getName())) {
			return getClass().getClassLoader().getResource("chromedriver.exe").getPath();
		} else {
			return getClass().getClassLoader().getResource("phantomjs.exe").getPath();
		}
	}
	
	public String getName() {
		return driverName;
	}
	
}
