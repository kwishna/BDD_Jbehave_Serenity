package Runners;

import java.util.Arrays;

import com.github.valfirst.jbehave.junit.monitoring.JUnitReportingRunner;
import net.serenitybdd.core.SerenitySystemProperties;
import net.thucydides.core.ThucydidesSystemProperty;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.ScanningStepsFactory;
import org.junit.runner.RunWith;
import org.testng.annotations.Test;

import net.serenitybdd.jbehave.SerenityStories;

/**
 * Serenity Jbehave Runner.
 */
@RunWith(JUnitReportingRunner.class)
public class SerenityJbehaveJunit extends SerenityStories{

	public SerenityJbehaveJunit() {

	}
	
	@Test
	public void runner() {
		
		configuredEmbedder().runStoriesAsPaths(new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(this.getClass()).getFile(),
						Arrays.asList("**/*Verify.story"), Arrays.asList("")));
		// configuredEmbedder().configuration().parameterControls().useDelimiterNamedParameters(true);
	}
	
    @Override
    public InjectableStepsFactory stepsFactory() {

    	return new ScanningStepsFactory(configuration(), "JbehaveExtras/jbehave_first_working/jbehaveTempRunners", "mysteps" ).matchingNames(".*").notMatchingNames(".*SkipSteps");
    }
}
