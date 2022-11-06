package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTests {


    private final Validator v = new Validator();

    @Test
    public void testStringSchemaForward() {
        StringSchema schema = v.stringSchema();

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
        StringSchema schema = v.stringSchema();
        String minLength = "4";
        assertThat(schema.minLength(Integer.parseInt(minLength)).isValid("test")).isTrue();
        minLength = "5";
        assertThat(schema.minLength(Integer.parseInt(minLength)).isValid("test")).isFalse();

    }


    @Test
    public void testStringSchemaContainsWithoutRequired() {
        StringSchema schema = v.stringSchema();

        assertThat(schema.contains("wh").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("what").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isFalse();

    }


    @Test
    public void testStringSchemaMinLengthWithoutRequired() {
        StringSchema schema = v.stringSchema();

        String minLength = "4";
        assertThat(schema.minLength(Integer.parseInt(minLength)).isValid("test")).isTrue();
        minLength = "5";
        assertThat(schema.minLength(Integer.parseInt(minLength)).isValid("test")).isFalse();

    }

    @Test
    public void testNumberSchemaForward() {
        NumberSchema schema = v.numberSchema();

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
        NumberSchema schema = v.numberSchema();

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
        NumberSchema schema = v.numberSchema();

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
        MapSchema schema = v.mapSchema();

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
        MapSchema schema = v.mapSchema();

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

    @Test
    public void testMapSchemaSizeofShape() {
        MapSchema schema = v.mapSchema();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.stringSchema().required());
        schemas.put("age", v.numberSchema().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        String testNumber = "100";
        human1.put("age", Integer.parseInt(testNumber));
        assertThat(schema.isValid(human1)).isTrue();

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertThat(schema.isValid(human2)).isTrue();

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertThat(schema.isValid(human3)).isFalse();

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        testNumber = "5";
        human4.put("age", -Integer.parseInt(testNumber));
        assertThat(schema.isValid(human4)).isFalse();
    }
}
