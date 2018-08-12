package com.samoht;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// Upon my initial reading of the requirements, I thought "dependency tree"
// (or graph, if you wanted to know the other direction, too).  So I built this
// out test-first (pretty much everything was done that way).
//
// Later into the process I started thinking there was /probably/ no need to
// model it thus.  Probably could have just kept a Map<String,Set<String>>,
// for the dependency "table", and have written code to essentially "interpret"
// that construct when implementing the higher-level aspects of the problem.
// If I wasn't working in a time-limited way, I'd have probably spiked that.

public class Component {
    private final String name;
    private final Set<Component> dependencies = new HashSet<>();

    public Component(String name, Set<Component> dependencies) {
        this.name = name;
        this.dependencies.addAll(dependencies);
    }

    public Component(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<Component> getDependencies() {
        return dependencies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Component)) return false;
        Component component = (Component) o;
        return Objects.equals(name, component.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Component{" +
                "name='" + name + '\'' +
                ", dependencies=" + dependencies +
                '}';
    }

    public boolean dependsOn(final Component component) {
        if (dependencies.contains(component)) {
            return true;
        }
        for (Component c : dependencies) {
            if (c.dependsOn(component)) {
                return true;
            }
        }
        return false;
    }

    public void addDependency(Component component) {
        dependencies.add(component);
    }

    public Set<Component> getAllDependencies() {
        HashSet<Component> components = new HashSet<>();
        getAllDependencies(components);
        return components;
    }

    public Set<Component> getAllDependencies(Set<Component> soFar) {
        for (Component c : dependencies) {
            soFar.add(c);
            soFar.addAll(c.getAllDependencies(soFar));
        }
        return soFar;
    }
}
