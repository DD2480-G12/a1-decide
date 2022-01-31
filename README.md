# Launch Interceptor Program

This is a program which primary component is Decide; a function which assesses whether an anti-ballistic missile system
interceptor should be launched or not. The decision is based on radar tracking
information in the form of a list of coordinates and a number of parameters
controlling the calculations.

## Way of working

We use Discord for communication and GitHub's issue tracker for issues.
We work mainly on our own environment and pair programming remotely when needed,
documentation as a markdown file in the repo, and one leader.
We try to have one commit for each pull request and have one required reviewer.
Also, issues should be linked to in commit messages.
When fixing code after a review comment on a pull request,
fixup commits should be made. If the branch that is being merged to main is
behind main, git rebase is used and not git merge. Right now,
we are investigating and working with these tools and principles.
For example, the number of required approvals was lowered from two to one,
since we felt it suited our team better after testing it out.
We think we are in the In Use phase, because we as a team is gradually adapting
our principles and tools, and we are still testing and evaluating them.