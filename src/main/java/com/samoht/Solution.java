package com.samoht;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    /*
     * Complete the function below.
     */

    static void doIt(String[] input) {

        // Really intended to break all this out into another class so it could be
        // properly tested.  As it is, the HackerRank environment/design requires that
        // you just print to System.out -- not very testable (as is shown by the test
        // for this class).  This was the last bit I started wiring up as I ran out of
        // time, so this is just a sketch, and where I left off.
        Map<String, Component> componentMap = new HashMap<>();
        List<String> output = new ArrayList<>();
        Configuration configuration = new Configuration(componentMap);


        for (int i = 0; i < input.length; i++) {
            String command = input[i];
            String[] parts = command.split("\\s+");
            String verb = parts[0];

            // Case statement could be replaced some sort of polymorphism, but
            // at this point I was just ensuring I could properly parse the lines, etc.
            switch (verb) {
                case "DEPEND": {
                    // Assuming the command is well-formed
                    String componentName = parts[1];
                    Component component = new Component(componentName);
                    componentMap.put(componentName, component);
                    for (int p = 2; p < parts.length; p++) {
                        String dependencyName = parts[p];
                        Component dependency = new Component(dependencyName);
                        component.addDependency(dependency);
                        componentMap.put(dependencyName, dependency);
                    }
                    output.add(command);
                    break;
                }
                case "INSTALL": {
                    String componentName = parts[1];
                    configuration.install(componentName);
                    output.add(command);
                    break;
                }
                case "LIST": {
                    output.addAll(configuration.getComponents());
                    break;
                }
                default: {

                }
            }
        }
        System.out.println("componentMap = " + componentMap);
        output.forEach(System.out::println);
    }

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//
//        int _input_size = 0;
//        _input_size = Integer.parseInt(in.nextLine().trim());
//        String[] _input = new String[_input_size];
//        String _input_item;
//        for(int _input_i = 0; _input_i < _input_size; _input_i++) {
//            try {
//                _input_item = in.nextLine();
//            } catch (Exception e) {
//                _input_item = null;
//            }
//            _input[_input_i] = _input_item;
//        }
//        doIt(_input);

    }
}
