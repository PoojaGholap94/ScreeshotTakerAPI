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

	public static void main(String[] args) throws IOException {

		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();

		// Specify the targetted URL
		String testURL = "https://clang.llvm.org/docs/LibASTMatchersTutorial.html";

		// Access the targetted URL
		driver.get(testURL);
		BufferedImage scr = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(3000))
				.takeScreenshot(driver).getImage();
		
		// Capture screenshot of the web page using TakesScreenshot method
		final File screenShotOutputFile = new File("screenshot_" + generatetimeStampBasedRandomNumber() + ".png");
		try {
			ImageIO.write(scr, "jpg", screenShotOutputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Took Screenshot for " + testURL + " saved at " + screenShotOutputFile);

		// Quit the driver
		driver.quit();
	}

	// Method to generate Random number based on DateTimeStamp i am using this so that we can change the name of images and every image has unique name
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