package JbehaveExtras.jbehave_first_working.jbehaveTempRunners;

import java.util.List;

import JbehaveExtras.jbehave_first_working.RunningJabehaveUsingSingleFile.JbehaveImplStep;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.junit.Test;

public class JBehaveTwo extends JUnitStories{
	
	
	List<String> s = new StoryFinder().
            findPaths(CodeLocations.codeLocationFromClass(this.getClass()), "**/*.story", null);


	@Override
    protected List<String> storyPaths(){
//		String codeLocation = CodeLocations.codeLocationFromClass(this.getClass()).getFile();
 //       return new StoryFinder().findPaths(codeLocation, Arrays.asList("**/*.story"), Arrays.asList(""));
    	return s;
    }
	
	 @Override
	    @Test
	    public void run() {
	        Embedder embedder = configuredEmbedder();
	        try {
	            embedder.runStoriesAsPaths(storyPaths());
	            embedder.candidateSteps().add(new JbehaveImplStep());
	        } finally {
	            embedder.generateCrossReference();
	            embedder.generateSurefireReport();
	        }
	    }

}
