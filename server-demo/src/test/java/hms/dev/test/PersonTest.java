package hms.dev.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sajith on 10/28/15.
 */
public class PersonTest {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void testPerson() throws Exception {
        final Person person = new Person("Sajith", "Email@Sajith.com");

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("person.json"), Person.class));

        assertThat(MAPPER.writeValueAsString(person)).isEqualTo(expected);

    }
}
