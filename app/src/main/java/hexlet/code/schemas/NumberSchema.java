package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer> {

    @Override
    protected boolean isInvalidInput(Object input) {
        return !(input instanceof Integer);
    }

    @Override
    protected boolean isEmptyValue(Integer input) {
        return input == null;
    }

    public final NumberSchema positive() {
        Predicate<Integer> validationValue = input -> input > 0;
        addConditions("positive", validationValue);
        return this;
    }

    public final NumberSchema range(int fromNumber, int toNumber) {
        Predicate<Integer> validation = input -> input >= fromNumber && input <= toNumber;
        addConditions("range", validation);
        return this;
    }
}
