package hexlet.code;

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
