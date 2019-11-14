package JbehaveExtras.jbehave_first_working;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.PropertyBasedEmbedderControls;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.model.TableTransformers;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterControls;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.ParameterConverters.DateConverter;
import org.jbehave.core.steps.ParameterConverters.ExamplesTableConverter;
import org.jbehave.core.steps.PrintStreamStepMonitor;

public class JbehaveRunner2 extends JUnitStories {

	private final CrossReference xref = new CrossReference();

	public JbehaveRunner2() {

		configuredEmbedder().embedderControls().doGenerateViewAfterStories(true).doIgnoreFailureInStories(false)
				.doIgnoreFailureInView(true).doVerboseFailures(true).useThreads(2).useStoryTimeouts("60000").useStoryTimeoutInSecs(30000)
				.useStoryTimeoutInSecsByPath("40000");

		configuredEmbedder().useEmbedderControls(new PropertyBasedEmbedderControls());
	}

	@Override
	public Configuration configuration() {

		Class<? extends Embeddable> embeddableClass = this.getClass();

		
		 Properties viewResources = new Properties();
		 viewResources.put("decorateNonHtml", "false");
//		viewResources.put("reports", "ftl/jbehave-reports-with-totals.ftl");
		 
// 		Start from default ParameterConverters instance
		ParameterConverters parameterConverters = new ParameterConverters();
		
// 		factory to allow parameter conversion and loading from external resources (used by StoryParser too)
		ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(new LocalizedKeywords(), new LoadFromClasspath(embeddableClass),
				parameterConverters, new ParameterControls(), new TableTransformers());
		
// 		add custom converters
		parameterConverters.addConverters(new DateConverter(new SimpleDateFormat("yyyy-MM-dd")), new ExamplesTableConverter(examplesTableFactory));

		return new MostUsefulConfiguration()
						.useStoryLoader(new LoadFromClasspath(embeddableClass))
						.useStoryParser(new RegexStoryParser(examplesTableFactory))
						.useStoryReporterBuilder(new StoryReporterBuilder()
						.withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass)).withDefaultFormats()
						.withRelativeDirectory("my-output")
						.withViewResources(viewResources)
						.withFormats(Format.CONSOLE, Format.TXT, Format.HTML)
						.withFailureTrace(true)
						.withFailureTraceCompression(true).withCrossReference(xref))
						.useParameterConverters(parameterConverters)
						.usePendingStepStrategy(new FailingUponPendingStep())
						.useStepMonitor(new PrintStreamStepMonitor()) // default is SilentStepMonitor()
						.doDryRun(false)
// 				 		use '%' instead of '$' to identify parameters
						.useStepPatternParser(new RegexPrefixCapturingPatternParser("%")).useStepMonitor(xref.getStepMonitor());
	}

	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration(), new JbehaveImplementationStep());
	}

	@Override
	protected List<String> storyPaths() {
		// Specify story paths as URLs
		return new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(this.getClass()).getFile(),
				Arrays.asList("**/*.story"), Arrays.asList(""));
	}
}
