package pages;

import net.serenitybdd.core.pages.PageObject;
import utils.Locators;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.StringContains.containsString;

public class GooglePage extends PageObject {

	public void openHomepage() {

	}

	public void verifyHomePageUrl(String s) {

		String currentUrl = getDriver().getCurrentUrl();
		assertThat("Matching Current Url", currentUrl, containsString("google.co.in"));
	}

	public void validateHomepage(String title) {

		assertThat("Matching Current Url", getDriver().getTitle(), containsString(title));
	}

	public void sendSearchKey(String key) {

		$(Locators.GOOGLE_HOME_PAGE.getProperty("searchBox")).sendKeys(key);
	}

	public void hitEnterKey() {

		$(Locators.GOOGLE_HOME_PAGE.getProperty("searchBtn")).submit();
	}

	public void validateSearchResultPage() {

		assertThat($$(Locators.GOOGLE_HOME_PAGE.getProperty("searchResults")), hasSize(greaterThan(5)));
	}

	public void printSearchResultCount() {

		assertThat($$(Locators.GOOGLE_HOME_PAGE.getProperty("searchResultLinks")), hasSize(greaterThan(5)));
	}
}
