package mysteps;

import org.jbehave.core.annotations.*;

public class OneSteps{

	@BeforeScenario
	public void beforeScenario(){
		System.out.println("beforeScenario");
	}

	@AfterScenario
	public void afterScenario(){
		System.out.println("afterScenario");
	}

	@BeforeStory
	public void beforeStory(){
		System.out.println("beforeStory");
	}

	@AfterStory
	public void afterStory(){
		System.out.println("afterStory");
	}

	@BeforeStories
	public void beforeStories(){
		System.out.println("beforeStories");
	}

	@AfterStories
	public void afterStories(){
		System.out.println("afterStories");
	}
	
	@Given("i am running before scenario")
	public void iAmBeforeScenario() {
		System.out.println("\n------ i am running before scenario -------");
	}

	@Given("i am running before step")
	public void iAmbeforeStep() {
		System.out.println("\n------ i am running before steps -------");
	}
	
	@Given("i am running before story")
	public void iAmBeforeStory() {
		System.out.println("\n------ i am running before story -------");
	}
}
