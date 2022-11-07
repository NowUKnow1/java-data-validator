package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public class Validator {

    public Validator() {
    }

    public final StringSchema stringSchema() {
        return new StringSchema();
    }

    public final NumberSchema numberSchema() {
        return new NumberSchema();
    }

    public final MapSchema mapSchema() {
        return new MapSchema();
    }
}
