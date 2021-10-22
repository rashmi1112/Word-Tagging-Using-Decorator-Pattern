package textdecorators;

import textdecorators.Enums.Prefixes;
import textdecorators.Enums.Suffixes;
import textdecorators.util.InputDetails;
import textdecorators.util.MyLogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for Keyword Decorator, implements AbstractTextDecorator.
 *
 * @author Rashmi Badadale
 */
public class KeywordDecorator extends AbstractTextDecorator {

    private final AbstractTextDecorator adt;
    private final InputDetails id;
    private final MyLogger myLogger = MyLogger.getInstance();

    /**
     * Constructor for Keyword Decorator
     * @param adtIn Abstract Data type MostFrequentWords decorator
     * @param idIn Instance of inputDetails class
     */
    public KeywordDecorator(AbstractTextDecorator adtIn, InputDetails idIn) {
        adt = adtIn;
        id = idIn;
        myLogger.writeMessage("Constructor of Keyword Decorator was called.", MyLogger.DebugLevel.KEYWORD_DECORATOR);

    }

    /**
     * Overriding the toString() method
     * @return String
     */
    public String toString() {
        return "Keyword Decorator";
    }

    /**
     * Method to check the complete word in the processed string
     * @param inputString Processed input string
     * @param word Word to be found in the input String
     * @param start Integer Starting index of the string in which we need to find the word
     * @return List containing the start and end index of the word to be replaced
     */
    private List<Integer> checkCompleteWord(String inputString, String word, int start) {
        List<Integer> list = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\b" + "(?i)" + word + "\\b");
        Pattern pattern1 = Pattern.compile("\\b" + "(?i)" + Prefixes.MOST_FREQUENT.getPrefix() + word + Suffixes.MOST_FREQUENT.getSuffix() + "\\b");
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.find(start)) {
            list.add(matcher.start());
            list.add(matcher.start() + word.length());
            return list;
        }
        matcher = pattern1.matcher(inputString);
        if (matcher.find(start)) {
            list.add(matcher.start());
            list.add(matcher.start() + word.length() + Prefixes.MOST_FREQUENT.getPrefix().length() + Suffixes.MOST_FREQUENT.getSuffix().length());
            return list;
        }
        return null;
    }

    /**
     * Method to replace all occurrence of the word to be replaced
     * @param inputString Processed input string
     * @param word Word to be replaced
     * @return StringBuilder Processed string replaced words with tags
     */
    private StringBuilder replaceAllOccurrence(String inputString, String word) {
        StringBuilder stringBuilder = new StringBuilder(inputString);
        int start = 0;
        int endIndex;
        List<Integer> list = checkCompleteWord(stringBuilder.toString(), word, start);
        if (list != null) {
            start = list.get(0);
        } else {
            return stringBuilder;
        }
        while (start < inputString.length()) {
            if (start == -1)
                break;
            endIndex = list.get(1);
            inputString = inputString.substring(0, start) + Prefixes.KEYWORD.getPrefix() + inputString.substring(start, endIndex) + Suffixes.KEYWORD.getSuffix() + inputString.substring(endIndex);
            stringBuilder.replace(0, inputString.length(), inputString);
            list = checkCompleteWord(stringBuilder.toString(), word, endIndex);
            if (list != null) {
                start = list.get(0);
            } else {
                break;
            }
        }
        return stringBuilder;
    }

    /**
     * Method override from AbstractTextDecorator
     */
    @Override
    public void processInputDetails() {
        String inputString = id.getStringBuilderInput().toString();
        String keywordsFromFile = id.readKeywords().toString();
        Queue<String> keywords = new LinkedList<>(Arrays.asList(keywordsFromFile.split("\\s")));
        myLogger.writeMessage("The contents of Keyword File were retrieved into a data structure.", MyLogger.DebugLevel.KEYWORD_DECORATOR);
        for (String keyword : keywords) {
            StringBuilder stringBuilderKeywords = replaceAllOccurrence(inputString, keyword);
            id.storeInput(stringBuilderKeywords);
            myLogger.writeMessage("The keyword: \"" + keyword + "\" was replaced with the respective tags.",
                    MyLogger.DebugLevel.KEYWORD_DECORATOR);
            inputString = id.getStringBuilderInput().toString();

        }
        if (adt != null) {
            myLogger.writeMessage("Proceeding with the next Decorator: " + adt.toString(), MyLogger.DebugLevel.KEYWORD_DECORATOR);
            adt.processInputDetails();
        }
    }
}





















