package textdecorators.Enums;

/**
 * @author Rashmi Badadale
 * Enum to store the prefixes
 */
public enum Prefixes {

    BEGIN_SENTENCE("BEGIN_SENTENCE__"),
    SPELLCHECK("SPELLCHECK_"),
    MOST_FREQUENT("MOST_FREQUENT_"),
    KEYWORD("KEYWORD_");

    private final String string;

    /**
     * Constructor to initialize the String
     *
     * @param string String value
     */
    Prefixes(String string) {
        this.string = string;
    }

    /**
     * Function to get the prefix
     *
     * @return String prefix
     */
    public String getPrefix(){
        return string;
    }

}
