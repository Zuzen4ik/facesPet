package md.faces.backend.model;

public enum Commands {

    SAVE("save"),
    FIND_BY_ID("findById"),
    FIND_ALL("findAll"),
    DELETE("delete"),
    UPDATE("update");

    private final String prefix;

    Commands(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
