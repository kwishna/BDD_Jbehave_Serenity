package mysteps;

import org.jbehave.core.annotations.*;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.steps.Parameters;
import org.jbehave.core.steps.Steps;

public class JbehaveImplSteps{
	
	@Given("step represents a precondition to an event: $table")
	public void first(ExamplesTable table) {	
		System.out.println("---- one table ---- "+table.getHeaders());
//		System.out.println(table.getHeaders());
	}
	
	@When("step represents the occurrence of the event: $table")
	public void second(ExamplesTable table) {
		System.out.println("---- two table ---- "+table.getHeaders());
//		System.out.println(table.getHeaders());
	} 
	
	@Then("step represents the outcome of the second event data: $table")
	public void four(ExamplesTable table) {
		System.out.println("---- three table ---- "+table.getHeaders());
//		Parameters row = table.getRowAsParameters(0);
//	    String name = row.valueAs("data", String.class);
//		System.out.println("-------->"+name);
	}
	
	
	@Then("step represents the outcome of the first event rollNo:")
	public void five(@Named("rollNo") int i) {
		System.out.println("---- Four Named ---- "+i);
	}
	
	@Given("a stock of <symbol> and a <threshold>")
	public void six(@Named("symbol") String s, @Named("threshold") String t) {
		System.out.println("---- six names ---- "+s+" - "+t);
	}

	@Given("the stock changes now")
	public void seven(){
		System.out.println("---- five ---- ");
	}
	
	@Given("a username precondition password $table")
	public void fourthh(ExamplesTable table) {
		System.out.println("--- Username : --- password --- ".toUpperCase());
	}
	
	@When("a negative event <random>")
	public void fifthh(String random) {
		System.out.println("----- "+random+" -----");
	}
	
	@Then("a the outcome should be capture")
	public void sixthh() {
		System.out.println("----- a the outcome should be captured -----");
	}

	@When("the {item |}{price|cost} is $price")
	@Composite(steps = {
			"Given a username precondition password Hello.table"
	//		"When a negative event occur <random>"
	})
	public void theItemPriceIs(double price) {
		System.out.println(price);
	}

}
