package org.testcontainers.containercore;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Javadocs
 */
public class Container {

    List<Integer> exposedPorts = new ArrayList<>();
    String image;
    List<Plugin> plugins = new ArrayList<>();

    Container() {
        // package-scoped - instantiate using builder
    }

    public void start() {
        System.out.println("Starting");
    }

    public void stop() {
        System.out.println("Stopping");
    }

    public void exec(String command) {
        System.out.println("Execing " + command);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BaseContainerBuilder<Builder> {
        public Builder() {
            super();
        }

        public Container build() {
            return container;
        }
    }
}
