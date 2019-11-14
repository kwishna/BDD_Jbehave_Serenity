package utils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.StringDescription;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.Collections;
import java.util.function.Function;

public class Utils {

	/**
	 * <p></p><br/>
	 * Utility Class Assertion Method. To Be Used Across The Suite.<br/>
	 * It Is Used To Validate Whether The Actual Method Satisfies The Matcher.
	 * <p/>
	 * <i>
	 * Please Import Usable Matchers From :<br/>
	 * static Methods From : {@link org.hamcrest.Matchers} Or,<br/>
	 * Classes/Static Methods From : {@link  org.hamcrest.core}
	 * </i>
	 * <pre>
	 * Ex.
	 * import static org.hamcrest.core.StringContains.containsString;
	 *
	 * To Call Different Matchers For Assertions And Pass As Parameter In This Method.
	 * For example:
	 *        assertWithMatcher("myString", containsString("ring"))
	 *       assertWithMatcher("myName", not(equalToIgnoringCase("Hello")))
	 *       assertWithMatcher("Price Should Match", doublePriceActual, equalTo(doublePriceExpected));
	 *       assertWithMatcher("List Size Should Be Positive", listActual, hasSize(greaterThan(0)));
	 *       assertWithMatcher("Element Should Be Visible", isDisplayed(element), is(true));
	 * </pre>
	 *
	 * @param reason  {@link String} Message To Be Printed With The Assertion.
	 * @param actual  Actual Value To Be Asserted. Any Type <T>.
	 * @param matcher {@link Matcher} Object Of Any Class Which Implements org.hamcrest.Matcher
	 * @param <T>     Any Data Type Or Object.
	 */
	public static <T> void assertWithMatcher(String reason, T actual, Matcher<? super T> matcher) {

		Description description = new StringDescription();
		description.appendText("\nReason: ")
				.appendText(reason)
				.appendText("\nExpected: ")
				.appendDescriptionOf(matcher)
				.appendText("\n     but: ");
		matcher.describeMismatch(actual, description);
		MatcherAssert.assertThat(reason, actual, matcher);
	}

	/**
	 * <pre>
	 * Custom Fluent Wait Method. To Be Used When The User Wants The System To Wait</br>
	 * until The Method Returns <code>true</code> Pr <code><R> Any Return Type<code/></br>
	 * by satisfying the provided conditions.
	 *
	 * This Method Keeps Polling Every 2 Seconds Till Maximum Time If The Provided Conditions Returns null Or false.
	 *
	 * Ex.
	 * fluentWait(getDriver(), 30, 2, "My Own Conditions",(wd) -> wd.getCurrentUrl().contains("https://google.co.uk"));
	 *
	 * <pre/>
	 * @param driver {@link org.openqa.selenium.WebDriver} Instance
	 * @param timeOutInSeconds {@link Long} Maximum Waiting Time In Seconds For Waiting.
	 * @param pollingInterval {@link Long} Time Interval In Seconds For Polling
	 * @param message {@link String} Understandable Message To Be Printed On {@link org.openqa.selenium.TimeoutException}
	 * @param fun {@link Function} Instance For Functional Interface Implementing {@link Function apply(T t)} abstract Method.
	 * @param <WebDriver> {@link WebDriver} Instance As First Argument In {@link Function apply(T t)} abstract Method.
	 * @param <R> {@link R} Instance As Return Type Of {@link Function apply(T t)} abstract Method.
	 * @return <R> Any Type Except null Or false.
	 */
	public static <WebDriver, R> R fluentWait(WebDriver driver, long timeOutInSeconds, long pollingInterval, String message, Function<WebDriver, R> fun) {

		try {
			return new FluentWait<>(driver)
					.withTimeout(Duration.ofSeconds(timeOutInSeconds))
					.pollingEvery(Duration.ofSeconds(pollingInterval))
					.withMessage(message)
					.ignoreAll(Collections.singleton(NotFoundException.class)).until(fun);
		} catch (Exception ignored) {

			return null;
		}
	}

}
