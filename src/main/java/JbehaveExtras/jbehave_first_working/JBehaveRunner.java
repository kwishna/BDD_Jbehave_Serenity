package JbehaveExtras.jbehave_first_working;

import java.util.Arrays;
import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

public class JBehaveRunner extends JUnitStories{

	@Override
	protected List<String> storyPaths() {
		
		return new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(this.getClass()), "**/*.story", "**/*.java");
	}
		
	
	public JBehaveRunner() {
        configuredEmbedder().embedderControls().doGenerateViewAfterStories(true).doIgnoreFailureInStories(true)
                .doIgnoreFailureInView(true).useThreads(2).useStoryTimeouts("10000");
        // Uncomment to set meta filter, which can also be set via Ant or Maven
         configuredEmbedder().useMetaFilters(Arrays.asList("+theme parametrisation"));
    }

	 @Override
	    public Configuration configuration() {
	        return new MostUsefulConfiguration()
	        		
	            // where to find the stories
	            .useStoryLoader(new LoadFromClasspath(this.getClass()))  
	            // CONSOLE and TXT reporting
	            .useStoryReporterBuilder(new StoryReporterBuilder().withDefaultFormats().withFormats(Format.CONSOLE, Format.TXT, Format.HTML));
	    }
	 
	    // Here we specify the steps classes
	    @Override
	    public InjectableStepsFactory stepsFactory() {        
	        // varargs, can have more that one steps classes
	        return new InstanceStepsFactory(configuration(), new JbehaveImplementationStep());
	    }
	
}