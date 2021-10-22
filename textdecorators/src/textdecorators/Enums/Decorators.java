package textdecorators.Enums;

/**
 * @author Rashmi Badadale
 * Enum for String tags used in MyLogger
 */
public enum Decorators {
    SENTENCE_DECORATOR(" SENTENCE_DECORATOR "),
    SPELLCHECK_DECORATOR(" SPELLCHECK_DECORATOR "),
    KEYWORD_DECORATOR(" KEYWORD_DECORATOR "),
    MOST_FREQUENT_WORD_DECORATOR(" MOST_FREQUENT_WORD_DECORATOR ");

    private final String string;

    /**
     * Constructor to initialize the String
     *
     * @param string String value
     */
    Decorators(String string) {
        this.string = string;
    }

    /**
     * Function to get the decorator tag
     *
     * @return String tag
     */
    public String getDecorator() {
        return string;
    }
}
