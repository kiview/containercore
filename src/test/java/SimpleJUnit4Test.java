import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.testing.junit4.ContainerTestRule;

/**
 * TODO: Javadocs
 */
public class SimpleJUnit4Test {

    @Rule
    public ContainerTestRule alpine = ContainerTestRule.builder()
            .withImage("alpine:3.5")
            .withExposedPort(1234)
            .build();

    @Test
    public void doNothingTest() {
        alpine.exec("true");
    }
}
