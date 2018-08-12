package com.samoht;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ConfigurationTest {

    @Test
    void configurationOperations() {
        Component a = new Component("A");
        Component b = new Component("B");
        Component c = new Component("C");
        Component d = new Component("D");

        a.addDependency(b);
        a.addDependency(c);

        c.addDependency(d);

        //  a --> b
        //   \--> c --> d

        // Reference map so I don't have to search the dependency tree(s) constantly.
        // Would be set up by command list, along with component dependency tree itself.
        // Not loving it right about now because it feels duplicative.  Maybe the
        // tree search would have been fine.
        HashMap<String, Component> componentDependencies = new HashMap<>();
        componentDependencies.put("A", a);
        componentDependencies.put("B", b);
        componentDependencies.put("C", c);
        componentDependencies.put("D", d);

        Configuration configuration = new Configuration(componentDependencies);
        configuration.install("A");
        assertEquals(new HashSet<>(asList("A", "B", "C", "D")), configuration.getComponents());

        try {
            configuration.remove("B");
            fail("expected to fail due to dependencies");
        } catch(IllegalArgumentException e) { }

        try {
            configuration.remove("D");
            fail("expected to fail due to dependencies");
        } catch(IllegalArgumentException e) { }

        configuration.remove("A");
        assertEquals(new HashSet<>(), configuration.getComponents());
    }
}