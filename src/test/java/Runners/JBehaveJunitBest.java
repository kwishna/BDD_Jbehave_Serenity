package Runners;

import org.jbehave.core.Embeddable;
import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.annotations.UsingSteps;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.AnnotatedEmbedderRunner;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.ParameterControls;
import org.jbehave.core.steps.ParameterConverters.DateConverter;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@RunWith(AnnotatedEmbedderRunner.class)
@Configure(storyLoader = JBehaveJunitBest.MyStoryLoader.class, storyReporterBuilder = JBehaveJunitBest.MyReportBuilder.class, parameterConverters = { JBehaveJunitBest.MyDateConverter.class }, parameterControls = JBehaveJunitBest.MyParameterControl.class)
@UsingEmbedder(embedder = Embedder.class, generateViewAfterStories = true, ignoreFailureInStories = true, ignoreFailureInView = true, storyTimeouts = "1d", threads = 2, failOnStoryTimeout = false)
@UsingSteps(packages = {"JbehaveExtras/jbehave_first_working/jbehaveTempRunners", "mysteps" }, matchingNames = ".*Steps", notMatchingNames = ".*SkipSteps") // instances ={ JbehaveImpl.class },
/**
 * This Class Is Independent Runner Using JUnit For JBehave
 */
public class JBehaveJunitBest implements Embeddable {

	private Embedder embedder;

	public void useEmbedder(Embedder embedder) {
		this.embedder = embedder;
	}

	@Test
	public void run() {
		embedder.runStoriesAsPaths(
				new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(this.getClass()).getFile(),
						Arrays.asList("**/*Verify.story"), Arrays.asList("")));
	}

	public static class MyStoryControls extends StoryControls {
		public MyStoryControls() {
			doDryRun(false);
			doSkipScenariosAfterFailure(false);
		}
	}

	public static class MyStoryLoader extends LoadFromClasspath {
		public MyStoryLoader() {
//			super(Thread.currentThread().getClass().getClassLoader());
//			super(JBehaveJunit.class.getClassLoader());
			super(Thread.currentThread().getContextClassLoader());
			
		}
	}

	public static class MyReportBuilder extends StoryReporterBuilder {
		public MyReportBuilder() {
			this.withFormats(org.jbehave.core.reporters.Format.CONSOLE, org.jbehave.core.reporters.Format.TXT, org.jbehave.core.reporters.Format.HTML, org.jbehave.core.reporters.Format.XML).withDefaultFormats();
		}
	}

	public static class MyParameterControl extends ParameterControls {
		public MyParameterControl() {
			super(ParameterControls.DEFAULT_NAME_DELIMITER_LEFT, ParameterControls.DEFAULT_NAME_DELIMITER_RIGHT, true);
		}
	}

	public static class MyRegexPrefixCapturingPatternParser extends RegexPrefixCapturingPatternParser {
		public MyRegexPrefixCapturingPatternParser() {
			super("%");
		}
	}

	public static class MyDateConverter extends DateConverter {
		public MyDateConverter() {
			super(new SimpleDateFormat("yyyy-MM-dd"));
		}
	}

}
