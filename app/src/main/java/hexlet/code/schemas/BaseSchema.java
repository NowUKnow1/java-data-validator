package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    //private Predicate<Object> inputCheck;
    private final Map<String, Predicate<T>> validatorMap = new LinkedHashMap<>();
    private boolean required = false;

    protected final void setRequired() {
        required = true;
    }

    public abstract BaseSchema<T> required();

    protected abstract boolean isInvalidInput(Object input);

    protected abstract boolean isEmptyValue(T input);

    public final void addConditions(String checkValue, Predicate<T> validationValue) {
        validatorMap.put(checkValue, validationValue);
    }

    public final boolean isValid(Object input) {
        if (input != null && isInvalidInput(input)) {
            return false;
        }
        @SuppressWarnings("unchecked")
        T castedInput = (T) input;
        // Если значение обязательно и инпут пустой
        if (required && isEmptyValue(castedInput)) {
            return false;
        }
        // Если значение не обязательно и инпут пустой
        if (!required && isEmptyValue(castedInput)) {
            return true;
        }
        return validatorMap.values().stream().allMatch(value -> value.test(castedInput));
    }
}
