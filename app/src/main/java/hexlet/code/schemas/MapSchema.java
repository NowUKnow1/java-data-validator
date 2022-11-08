package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema<K, V> extends BaseSchema<Map<K, V>> {

    @Override
    protected boolean isInvalidInput(Object input) {
        return !(input instanceof Map);
    }

    @Override
    protected boolean isEmptyValue(Map<K, V> input) {
        return input == null || input.isEmpty();
    }

    public final MapSchema<K, V> sizeof(int size) {
        Predicate<Map<K, V>> validationValue = input -> input.size() == size;
        addConditions("sizeof", validationValue);
        return this;
    }


    public final MapSchema<K, V> shape(Map<String, BaseSchema> inputSchema) {
        addConditions("shape", input -> input instanceof Map
                && inputSchema.entrySet().stream().allMatch(e ->
                e.getValue().isValid(((Map<?, ?>) input).get(e.getKey()))));
        return this;
    }
}
