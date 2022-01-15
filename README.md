# README #

This is a repo with T-Systems Java School preliminary examination tasks.
Code points where you solution is to be located are marked with TODOs.

The solution is to be written using Java 1.8 only, external libraries are forbidden. 
You can add dependencies with scope "test" if it's needed to write new unit-tests.

The exam includes 3 tasks to be done: [Calculator](/tasks/Calculator.md), [Pyramid](/tasks/Pyramid.md), and 
[Subsequence](/tasks/Subsequence.md)

Calculator

General description
Write a calculator for evaluating arithmetic expressions.
An expression can consist of:
Digits (0-9)
Dots as decimal marks (valid example: 100.02, not valid example : 100..02)
Parentheses
Mathematical symbols (allowed are : "+", "-", "*", "/")
Rounding is to be performed to 4 significant digits, only the final result is to be rounded. Example: 102.12356 -> 102.1236
Input and expected output
Input : String containing arithmetic expression
Output : evaluation result or null if the expression cannot be evaluated
Examples
Calculator c = new CalculatorImpl();
System.out.println(c.evaluate("(1+38)*4-5")); // Result: 151
System.out.println(c.evaluate("7*6/2+8")); // Result: 29
System.out.println(c.evaluate("-12)1//(")); // Result: null

Pyramid

Pyramid builder
Back to readme
General description
Your solution should build a pyramid from given input list of integer values. Numbers are to be sorted ascending from top to bottom, from left to right.
Empty spaces are to be filled with zeros. In order to make the pyramid symmetric input numbers should alternate with zeros.
For example, for input A={2, 4, 3, 5, 6, 1} the pyramid will look like:
[0,0,1,0,0]
[0,2,0,3,0]
[4,0,5,0,6]
Refer to unit-tests for more examples.
Input and expected output
Input : List with integer values
Output : 2D array with the pyramid or CannotBuildPyramidException if it's not possible to build one

Subsequence

Subsequence
Back to readme
General description
Given two sequences {X} and {Y} of arbitrary elements (java.lang.Object).
Determine whether {X} can be built by removing some elements from {Y} without changing the order.
Input and expected output
Input : List X and List Y
Output : boolean, true if {X} can be built from {Y}, false otherwise
Examples
{X} : "A", "B", "C", "D"
{Y} : "BD", "A", "ABC", "B", "M", "D", "M","C", "DC", "D"
Subsequence s = new SubsequenceImpl();
boolean b = s.find(Arrays.asList("A", "B", "C", "D"),
 Arrays.asList("BD", "A", "ABC", "B", "M", "D", "M", "C", "DC", "D"));
System.out.println(b); // Result: true

### Result ###

* Author name : {PUT YOUR NAME HERE}
* Codeship : {PUT YOUR CODESHIP BAGDE HERE}

Example of Codeship badge. Please remove the example before you send us the link. 
[ ![Codeship Status for tschool/javaschoolexam](https://app.codeship.com/projects/a9af8940-d130-0134-89a6-5e8aaaa2a5a2/status?branch=master)](https://app.codeship.com/projects/201451)


### How to start?  ###
* Install [GIT](https://git-scm.com/) and [Maven](https://maven.apache.org)
* [Fork](https://confluence.atlassian.com/bitbucket/forking-a-repository-221449527.html) the repository 
* You're ready to go!

### How can I submit the result?  ###

* Make sure your code can be built and all tests are green (example command: "mvn clean install")
* Commit and push all changes to your repository
* Configure the build on CI server like Codeship
* Add build badge and your name to the README.md under Result section
* Check that the badge shows green build. We will not accept your solution if there is any red badge on the page.
* Send us an email with the link to your repository. Be aware that the build must be green all the time after you send us the link

### Test fails but I'm sure my solution is correct. What to do?  ###
* First of all - **don't** modify the tests. It will be considered as fraud and treated accordingly
+ Send us an email with full details:
    * Link to your repository
    * Name of failing test
    * Explain what is expected behaviour from your point of view
    * Explain why do you think your version is correct
* Wait for the response

### Tips and tricks for Codeship CI  ###
* Codeship uses Java 7 by default. Please refer to [this article](https://documentation.codeship.com/basic/languages-frameworks/java-and-jvm-based-languages/) to set up Java 8
* jdk_switcher is to be used in "Setup Commands" of project configuration
* Test command is "mvn -B test"
* Markdown code to add badge to your README is located in Project Settings -> Notification -> Status images 

### Who do I talk to? ###

* In case of any questions contact the person who sent you the task (first) or [HR department](mailto:job@t-systems.ru) of T-Systems RU (second).

### Useful links ###

* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)
* [Codeship](https://codeship.com)
