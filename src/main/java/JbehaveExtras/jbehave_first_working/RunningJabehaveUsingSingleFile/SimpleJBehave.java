package JbehaveExtras.jbehave_first_working.RunningJabehaveUsingSingleFile;

import java.util.List;
 
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;
 
public class SimpleJBehave {
 
	private static Embedder embedder = new Embedder();
	
	public List<String> allStoryPath(){
		
		return new StoryFinder().
	            findPaths(CodeLocations.codeLocationFromClass(this.getClass()), "**/*.story", null);
	}

	public static void main(String[] args) {
		
		SimpleJBehave j = new SimpleJBehave();
		embedder.candidateSteps().add(new JbehaveImplStep());
		embedder.runStoriesAsPaths(j.allStoryPath());
	}
}