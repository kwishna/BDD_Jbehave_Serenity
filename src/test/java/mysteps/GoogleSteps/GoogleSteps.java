package mysteps.GoogleSteps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import pages.GooglePage;

public class GoogleSteps {

	GooglePage googlePage; // Dependency Auto Injected By Serenity.

	@Given("the user open homepage")
	public void openHomePage(){

		googlePage.open(); // Opens The Base Url Provided In mvn
	}

	@Then("the user verify opens google homepage <$key> : $tableName")
	public void openGoogleHomePage(String key, ExamplesTable table) {

		googlePage.verifyHomePageUrl(table.getRows().get(0).get(key));
	}

	@Then("the user validates homepage title")
	public void validateHomePage(@Named("title") String pageTitle) {
		googlePage.validateHomepage(pageTitle);
	}

	@Then("the user enters search keyword <keyword>")
	public void enterKeyword(@Named("keyword") String key) {
		googlePage.sendSearchKey(key);
	}

	@When("the user hits enter key")
	public void hitEnterKey() {
		googlePage.hitEnterKey();
	}

	@Then("the search result page should be open")
	public void validateSearchResultPage() {
		googlePage.validateSearchResultPage();
	}

	@Then("the user prints total number of search counts")
	public void printTotalSearchCount() {
		googlePage.printSearchResultCount();
	}

}
