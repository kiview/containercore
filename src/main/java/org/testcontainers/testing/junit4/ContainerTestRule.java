package org.testcontainers.testing.junit4;

import lombok.experimental.Delegate;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.MultipleFailureException;
import org.junit.runners.model.Statement;
import org.testcontainers.containercore.Container;
import org.testcontainers.containercore.BaseContainerBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Javadocs
 */
public class ContainerTestRule implements TestRule {

    @Delegate
    private final Container container;

    private ContainerTestRule(Container container) {
        this.container = container;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                List<Throwable> errors = new ArrayList<Throwable>();

                try {
                    container.start();
                    base.evaluate();
                } catch (Throwable e) {
                    errors.add(e);
                } finally {
                    container.stop();
                }

                MultipleFailureException.assertEmpty(errors);
            }
        };
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BaseContainerBuilder<Builder> {
        private Builder() {
            super();
        }

        public ContainerTestRule build() {
            validate();
            return new ContainerTestRule(container);
        }
    }
}
