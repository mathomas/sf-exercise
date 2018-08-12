package com.samoht;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class ComponentTest {

    @Test
    void basicDependency() {
        HashSet<Component> dependencies = new HashSet<Component>();
        dependencies.add(new Component("B"));

        Component component = new Component("A", dependencies);
        assertTrue(component.dependsOn(new Component("B")));
    }

    @Test
    void deeperDependency() {
        Component b = new Component("B");
        Component c = new Component("C");
        b.addDependency(c);
        Component d = new Component("D");
        c.addDependency(d);

        Component a = new Component("A");
        a.addDependency(b);

        assertTrue(a.dependsOn(b));
        assertTrue(a.dependsOn(c));
        assertTrue(a.dependsOn(d));
        assertTrue(b.dependsOn(d));

        assertFalse(d.dependsOn(c));

        assertEquals(new HashSet<>(asList(b, c, d)), a.getAllDependencies());
    }

    @Test
    void widerDependency() {
        Component a = new Component("A");
        Component b = new Component("B");
        Component c = new Component("C");
        Component d = new Component("D");

        a.addDependency(b);
        a.addDependency(c);
        c.addDependency(d);

        assertTrue(a.dependsOn(b));
        assertTrue(a.dependsOn(c));
        assertTrue(a.dependsOn(d));
        assertTrue(c.dependsOn(d));

        assertFalse(b.dependsOn(c));
        assertFalse(b.dependsOn(d));
    }
}