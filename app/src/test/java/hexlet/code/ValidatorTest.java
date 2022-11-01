package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTests {


    private final Validator v = new Validator();

    @Test
    public void testStringSchemaForward() {
        StringSchema schema = v.string();

        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();

        schema.required();

        assertThat(schema.isValid("what does the fox say")).isTrue();
        assertThat(schema.isValid("hexlet")).isTrue();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("")).isFalse();


        assertThat(schema.contains("wh").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("what").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isFalse();

        assertThat(schema.isValid("what does the fox say")).isFalse();

    }


    @Test
    public void testStringSchemaMinLength() {
        StringSchema schema = v.string();
        String minLength = "4";
        assertThat(schema.minLength(Integer.parseInt(minLength)).isValid("test")).isTrue();
        minLength = "5";
        assertThat(schema.minLength(Integer.parseInt(minLength)).isValid("test")).isFalse();

    }


    @Test
    public void testStringSchemaContainsWithoutRequired() {
        StringSchema schema = v.string();

        assertThat(schema.contains("wh").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("what").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isFalse();

    }


    @Test
    public void testStringSchemaMinLengthWithoutRequired() {
        StringSchema schema = v.string();

        String minLength = "4";
        assertThat(schema.minLength(Integer.parseInt(minLength)).isValid("test")).isTrue();
        minLength = "5";
        assertThat(schema.minLength(Integer.parseInt(minLength)).isValid("test")).isFalse();

    }
}
