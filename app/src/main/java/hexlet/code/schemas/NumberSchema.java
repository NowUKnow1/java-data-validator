package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {
    public NumberSchema() {
    }

    public final NumberSchema required() {
        addThings("required", input -> input instanceof Integer);
        setRequired(true);
        return this;
    }

    public final NumberSchema positive() {
        Predicate<?> validationValue = input -> {
            if (isRequired() || input instanceof Integer) {
                return input instanceof Integer && (Integer) input > 0;
            }
            return true;
        };
        addThings("positive", validationValue);
        return this;
    }

    public final NumberSchema range(int fromNumber, int toNumber) {
        Predicate<?> validation = input -> input instanceof Integer
                && (Integer) input >= fromNumber
                && (Integer) input <= toNumber;
        addThings("range", validation);
        return this;
    }
}
