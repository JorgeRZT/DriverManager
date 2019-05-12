/**
 * 
 * @author Jorge Lopez Pellicer
 *
 */

public class DriverManagerImplTest {

	static DriverManager dm;
	
	public static void main (String [] args) {
		dm = new DriverManagerImpl(DRIVER.CHROME_DRIVER);
		dm.closeDriver();
		dm = new DriverManagerImpl(DRIVER.PHANTOM_JS);
		dm.closeDriver();
	}
	
}
