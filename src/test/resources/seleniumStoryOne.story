Meta:
@RegressionSuite

Narrative: In Order To Perform Regression Testing.
            As A QA Engineer.
            I Want To Validate Google Homepage.

Scenario: Validate Homepage
Meta:
@Smoke

Given the user open homepage
Then the user verify opens google homepage <url> : General.table
Then the user validates homepage title
And the user enters search keyword <keyword>
When the user hits enter key
Then the search result page should be open
Then the user prints total number of search counts

Examples:
|keyword|
|Krishna|
|Kumar  |
|Singh  |