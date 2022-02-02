# Launch Interceptor Program

This is a program which primary component is Decide; a function which assesses whether an anti-ballistic missile system
interceptor should be launched or not. The decision is based on radar tracking
information in the form of a list of coordinates and a number of parameters
controlling the calculations.

## Requirement

* JDK 17 or above
* Apache Maven 3.3+

## Build & Test

The project is designed as a module for a larger project, thus it has not main functions to execute directly.

You can run all tests by:
```bash
mvn test
```

Or you can run single test class or methods with:
```bash
mvn -Dtest=LaunchInterceptorTest test  # run all main testcases
mvn -Dtest=LaunchInterceptorTest#givenValidInputAndFUVIsAllTrue_whenDecide_thenLaunch test  # run a single test method
```

## Core Files

Under `src/main`:
* `LaunchInterceptor.java`: main logic of `decide` function. Combine input and functions to make a decision
* `LaunchInterceptorCore.java`: calculation of intermediate values (CMV, PUM, FUV)
* `LaunchInterceptorConditionCollection.java`: logic of 15 LIC functions

Under `src/test`:
* `LaunchInterceptorTest.java`: **main tests** for the decide function
* `LaunchInterceptorCoreTest.java`: tests for intermediate calculation (CMV, PUM, FUV)
* `LaunchInterceptorConditionCollectionTest.java`: tests for 15 LIC functions

## Workflow

### Requirements & Communication
* Use Discord to chat & hold meetings
* Use Google Doc for information sharing/brainstorming
* Use GitHub Issues for issue tracking
* Have meetings to elaborate requirements, clarify issues and share progresses

### Development
* Develop locally and merge into `main` branch. Use pair programming when needed
* Merge code into `main` branch using **Pull Requests** only
* A pull request shall be related to an issue. A pull request shall have 1 commit typically
* Use `fixup` option for commits that are fixing previous commits, and squash them on merge
* The commit message shall follow the standard and include the issue number

### Continuous Integration
* Merge code into `main` branch frequently
* Rebase code locally when there are code conflicts
* Pull request is merged using the `rebase` option to keep a linear history
* Each PR shall be approved by at least 1 reviewer
* Each PR shall include its own test, and shall pass all **GitHub Checks & Action** before merge

## Way of Working
Right now, we are investigating and working with these tools and principles.
For example, the number of required approvals was lowered from two to one,
since we felt it suited our team better after testing it out.
We think we are in the "**In Use**" phase, because we as a team is gradually adapting
our principles and tools, and we are still testing and evaluating them.

## Statement of Contribution

All members are actively involved in this project as cooperate closely. The contributions are:

### Chujie Ni
* Setup GitHub repo and branch settings
* Setup GitHub Actions for test
* LIC 1
* LIC 8
* LIC 13
* Make issues & review pull request

### Mikael Jafari
* LIC 2
* LIC 5
* LIC 11
* CMV calculation
* Reviewed some pull requests

### Gustav Ekner
* LIC 3
* LIC 10
* LIC 14
* FUV calculation
* Inputs and constants
* README File

### Mustafa Dinler:
* Init Maven project
* LIC 0
* LIC 4
* LIC 7
* LIC 12
* PUM calculation
* Decide function (and main test cases)
* Solved two requirement bugs
* Actively reviewed Pull Requests

### Jakob Kratz:
* LIC 6
* LIC 9
* Actively reviewed Pull Requests
* Pointed out inconsistent use of doubleCompare

