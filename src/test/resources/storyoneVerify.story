Narrative: As A Hero I Am Running It.

Lifecycle: 
Before:
Scope: STORY
!-- Given i am running before story
!-- Scope: SCENARIO
!-- Given i am running before scenario

Scenario:  A scenario is a collection of executable steps of different type

!-- GivenStories: storyTwo.story

Given step represents a precondition to an event: General.table
When step represents the occurrence of the event: General2.table
Then step represents the outcome of the second event data:
|data|
|one|
|two|
Then step represents the outcome of the first event rollNo:
Given the stock changes now
Given a stock of <symbol> and a <threshold>
Examples:
|rollNo|symbol|threshold|
|1	   |apple |boy		|
|2	   |cat   |dog		|
