package textdecorators.Enums;

/**
 * @author Rashmi Badadale
 * Enum to store the suffixes
 */
public enum Suffixes {

    END_SENTENCE("__END_SENTENCE"),
    SPELLCHECK("_SPELLCHECK"),
    MOST_FREQUENT("_MOST_FREQUENT"),
    KEYWORD("_KEYWORD");

    private final String string;

    /**
     * Constructor to initialize the String
     * @param stringIn String value
     */
    Suffixes(String stringIn){
        string = stringIn;
    }

    /**
     * Function to get the suffixes
     *
     * @return String suffix
     */
    public String getSuffix(){
        return string;
    }
}
