package utils;

import java.io.IOException;
import java.util.Properties;

public class Locators {

	public static Properties GOOGLE_HOME_PAGE;

	public static void loadAllPropertyFiles() {

		GOOGLE_HOME_PAGE = new Properties();

		try {
			GOOGLE_HOME_PAGE.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("GooglePage.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
