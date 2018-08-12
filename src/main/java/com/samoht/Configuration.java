package com.samoht;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

// The idea here is a separation from the "dependency model" (a map of trees in this case)
// and an actual configuration built by referring to the dependency model.
// The fact that I started tracking a Map of Trees made me question the tree design
// as noted over in the Component class.  Probably could have just been a map of Sets
// or something similar, used as reference data.
public class Configuration {

    private Map<String, Component> componentDependencies;

    private Set<String> components = new HashSet<>();

    public Configuration(Map<String, Component> componentDependencies) {
        this.componentDependencies = componentDependencies;
    }

    public void install(String componentName) {
        this.components.add(componentName);
        Set<String> dependentNames = getDependentComponentNames(componentName);
        this.components.addAll(dependentNames);
    }

    public Set<String> getComponents() {
        return new HashSet<>(components);
    }

    public void remove(String component) {
        if (!hasDependentComponents(component)) {
            components.remove(component);
            Set<String> dependentNames = getDependentComponentNames(component);
            components.removeAll(dependentNames);
        } else {
            throw new IllegalArgumentException(component + " is still needed");
        }
    }

    private Set<String> getDependentComponentNames(String component) {
        Set<Component> dependentComponents = componentDependencies.get(component).getAllDependencies();

        return dependentComponents.stream()
                .map(Component::getName)
                .collect(Collectors.toSet());
    }

    private boolean hasDependentComponents(String componentName) {
        Component componentInQuestion = componentDependencies.get(componentName);
        for (String other : components) {
            if (!other.equals(componentName)) {
                Component otherComponent = componentDependencies.get(other);
                if (otherComponent != null && otherComponent.dependsOn(componentInQuestion)) {
                    return true;
                }
            }
        }
        return false;
    }
}
