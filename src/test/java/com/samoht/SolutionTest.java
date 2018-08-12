package com.samoht;

import org.junit.jupiter.api.Test;

class SolutionTest {

    @Test
    void doIt() {
        String[] commands = {
                "DEPEND TELNET TCPIP NETCARD",
                "DEPEND TCPIP NETCARD",
                "INSTALL NETCARD",
                "LIST",
                "END"
        };
        // This just prints.  Not right.  Would want to
        // receive the output from some component other than the Solution,
        // and assert against that, then integrate that component to the
        // Solution class. If I'd had time I would have taken that approach,
        // and used the example test cases from the HackerRank exercise to
        // accomplish that.
        Solution.doIt(commands);
    }
}