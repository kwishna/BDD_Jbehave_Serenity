package Runners;

import com.github.valfirst.jbehave.junit.monitoring.JUnitReportingRunner;
import net.serenitybdd.core.SerenitySystemProperties;
import net.serenitybdd.jbehave.SerenityStories;
import net.thucydides.core.ThucydidesSystemProperty;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.ScanningStepsFactory;
import org.junit.runner.RunWith;
import org.testng.annotations.Test;
import utils.Locators;

import java.util.Arrays;
import java.util.Collections;

/**
 * Serenity Jbehave Runner.
 */
@RunWith(JUnitReportingRunner.class)
public class SerenityJbehaveSelenium extends SerenityStories{

	public SerenityJbehaveSelenium() {

		// Getting All Environment Variable/Delimiters From Maven.
		String driver = getSystemConfiguration().getEnvironmentVariables().getProperty("webdriver.driver");
		String baseUrl = getSystemConfiguration().getEnvironmentVariables().getProperty("webdriver.base.url");

		SerenitySystemProperties.getProperties().setValue(ThucydidesSystemProperty.WEBDRIVER_DRIVER, driver);
		SerenitySystemProperties.getProperties().setValue(ThucydidesSystemProperty.CONTEXT, driver);
		SerenitySystemProperties.getProperties().setValue(ThucydidesSystemProperty.WEBDRIVER_BASE_URL, baseUrl);
		SerenitySystemProperties.getProperties().setValue(ThucydidesSystemProperty.CHROME_SWITCHES, "--headless");

		findStoriesCalled("seleniumStory*.story"); // Given Story Name Will Name. Semi-colon Seperated If Multiple.
		findStoriesIn(System.getProperty("user.dir")+"/src/test/resources"); // Story Directory.
//		useDriver("chrome");

		Locators.loadAllPropertyFiles();

	}

	@Test
	public void runner() {
		
		configuredEmbedder().runStoriesAsPaths(new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(this.getClass()).getFile(),
				Collections.singletonList("**/*Verify.story"), Collections.singletonList("")));
		// configuredEmbedder().configuration().parameterControls().useDelimiterNamedParameters(true);
	}
	
    @Override
    public InjectableStepsFactory stepsFactory() {

    	return new ScanningStepsFactory(configuration(), "mysteps/GoogleSteps", "anyOtherStepsDir" ).matchingNames("*.Steps").notMatchingNames(".*Skip*");
    }
}
