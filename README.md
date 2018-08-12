# Running
Pretty much just tests at this point, though the SolutionTest is really more of a launcher (notes about that in the test).

`mvnw test` (Maven wrapper will install a sandboxed Maven then run the tests)

# Notes
I found this exercise to be interesting and challenging, but also found I just didn't have enough time to finish.  I'd
estimate a couple more hours needed to complete.

I have put comments in various places in the code to communicate my thoughts about where things are at
the moment.

But in a nutshell:
- Probably could have gone with a simpler way to capture the "dependency database", possibly a Map<String, Set<String>>,
rather than the trees, but didn't have time to experiment with that.  Kind of wish I'd started with a simpler data 
structure just using bare Java collections to see if that would have worked.  Would not have captured a "domain model",
but maybe that's not the point of an exercise ;-).
- Should have (and would have if more time) just had the Solution's `doIt()` method delegate immediately to something 
else in order to make things more testable there.  As it is I sketched out the command processing stuff right in the method.
- DELETE command is where things get interesting.  That is somewhat captured in the Configuration class/test, but 
not rigorously (especially since I didn't implement the DELETE command yet).
- With more time I'd have made another couple of passes buffing the code: renaming, adjusting access protections, etc. 