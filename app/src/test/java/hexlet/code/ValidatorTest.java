package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void testNumberSchemaForward() {
        NumberSchema schema = v.number();

        String testNumber = "10";
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(Integer.parseInt(testNumber))).isTrue();

        schema.required();

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(Integer.parseInt(testNumber))).isTrue();
        assertThat(schema.isValid("5")).isFalse();

        assertThat(schema.positive().isValid(Integer.parseInt(testNumber))).isTrue();
        assertThat(schema.positive().isValid(-Integer.parseInt(testNumber))).isFalse();

        String min = "5";
        String  max = "10";
        schema.range(Integer.parseInt(min), Integer.parseInt(max));

        assertThat(schema.isValid(Integer.parseInt(testNumber))).isTrue();
        testNumber = "5";
        assertThat(schema.isValid(Integer.parseInt(testNumber))).isTrue();
        testNumber = "4";
        assertThat(schema.isValid(Integer.parseInt(testNumber))).isFalse();
        testNumber = "11";
        assertThat(schema.isValid(Integer.parseInt(testNumber))).isFalse();
    }

    @Test
    public void testNumberSchemaPositiveWithoutRequired() {
        NumberSchema schema = v.number();

        String testNumber = "10";
        assertThat(schema.positive().isValid(Integer.parseInt(testNumber))).isTrue();
        assertThat(schema.positive().isValid(-Integer.parseInt(testNumber))).isFalse();
        assertThat(schema.isValid(null)).isTrue();

        schema.required();

        assertThat(schema.positive().isValid(Integer.parseInt(testNumber))).isTrue();
        assertThat(schema.positive().isValid(-Integer.parseInt(testNumber))).isFalse();
        assertThat(schema.isValid(null)).isFalse();
    }


    @Test
    public void testNumberSchemaRangeWithoutRequired() {
        NumberSchema schema = v.number();

        String min = "5";
        String max = "10";
        schema.range(Integer.parseInt(min), Integer.parseInt(max));

        String testNumber = "5";
        assertThat(schema.isValid(Integer.parseInt(testNumber))).isTrue();
        testNumber = "10";
        assertThat(schema.isValid(Integer.parseInt(testNumber))).isTrue();
        testNumber = "4";
        assertThat(schema.isValid(Integer.parseInt(testNumber))).isFalse();
        testNumber = "11";
        assertThat(schema.isValid(Integer.parseInt(testNumber))).isFalse();

        assertThat(schema.isValid(null)).isFalse();
        testNumber = "10";
        assertThat(schema.isValid(Integer.parseInt(testNumber))).isTrue();
    }

    @Test
    public void testMapSchemaForward() {
        MapSchema schema = v.map();

        assertThat(schema.isValid(null)).isTrue();

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertThat(schema.isValid(data)).isTrue();

        schema.required();

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap())).isTrue();
        assertThat(schema.isValid(data)).isTrue();

        String testSize = "2";
        schema.sizeof(Integer.parseInt(testSize));

        assertThat(schema.isValid(data)).isFalse();
        data.put("key2", "value2");
        assertThat(schema.isValid(data)).isTrue();
    }


    @Test
    public void testMapSchemaSizeofWithoutRequired() {
        MapSchema schema = v.map();

        String testSize = "1";
        schema.sizeof(Integer.parseInt(testSize));

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertThat(schema.isValid(data)).isTrue();

        testSize = "2";
        schema.sizeof(Integer.parseInt(testSize));
        assertThat(schema.isValid(data)).isFalse();
        data.put("key2", "value2");
        assertThat(schema.isValid(data)).isTrue();

    }
}
