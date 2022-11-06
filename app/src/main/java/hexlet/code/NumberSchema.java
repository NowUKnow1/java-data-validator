package hexlet.code;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {
    public NumberSchema() {
    }

    public final NumberSchema required() {
        addThingsToValidate("required", input -> input instanceof Integer);
        setRequired(true);
        return this;
    }

    public final NumberSchema positive() {
        Predicate<?> validation = input -> {
            if (isRequired() || input instanceof Integer) {
                return input instanceof Integer && (Integer) input > 0;
            }
            return true;
        };
        addThingsToValidate("positive", validation);
        return this;
    }

    public final NumberSchema range(int fromNumber, int toNumber) {
        Predicate<?> validation = input -> input instanceof Integer
                && (Integer) input >= fromNumber
                && (Integer) input <= toNumber;
        addThingsToValidate("range", validation);
        return this;
    }
}
