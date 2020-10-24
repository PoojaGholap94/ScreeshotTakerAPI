package JavaAPI.rest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import javax.imageio.ImageIO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;

import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ScreenShotTaker {

	static WebDriver driver;

	public static void takeScreenshot(String Url) {
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();

		// Access the targetted URL
		driver.get(Url);
		BufferedImage scr = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(3000))
				.takeScreenshot(driver).getImage();

		// Capture screenshot of the web page using TakesScreenshot method
		final File screenShotOutputFile = new File("C:\\Users\\Shailesh\\eclipse-workspace\\ScreeshotAPI\screenshot_"
				+ generatetimeStampBasedRandomNumber() + ".png");
		try {
			ImageIO.write(scr, "png", screenShotOutputFile);

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Took Screenshot for " + Url + " saved at " + screenShotOutputFile + " path is : "
				+ screenShotOutputFile.getPath());

		// Quit the driver
		driver.quit();
	}

	// Method to generate Random number based on DateTimeStamp i am using this so
	// that we can change the name of images and every image has unique name & can
	// be recognized easily
	private static String generatetimeStampBasedRandomNumber() {

		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);

		String tst = ts.toString();

		try {
			tst = tst.substring(0, tst.length() - 4);
			tst = tst.replace("-", "");
			tst = tst.replace(" ", "");
			tst = tst.replace(":", "");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage() + "something went wrong");
		}

		return tst;
	}
}