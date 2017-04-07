# SpringerNature
QA assessment task

Scope
------
This is part of a QA assesment task, which required to automation some of the home page search functionalty:
> Create a minimal set of automation tests using any specific language and tools of your preference to validate the search functionality for the happy and unhappy path.

Environment setup and tools
---------------------------
* page object module was chosen to write the tests. Each page has their own elements and relevant methods but inherits from the same abstract page. Test page is then using the page object methods.
* intellij
* Java SDK
* webdriver.gecko.driver
* RemoteWebDriver
* Selenium
* TestNG
Tests are aimed to run on Firefox only, to demostrate the test, however other browsers (and then more drivers should be installed) should be used for better test coverage.

Note
------
* As no specification documention were provided, tests were written based on my website exploritory assuptions. For Example search limitation:
```
public static final int MIN_SEARCH_LENGTH = 1;
public static final int MAX_SEARCH_LENGTH = 10000;
```
Maximume search value is undefined, or it's value is incorrect, but as it's a common test case I wished to include it in the test.

```
Thread.sleep(LONG_SLEPP_TIME);
```
* The above method is used to slow down selenium, as page loading time is slower.


