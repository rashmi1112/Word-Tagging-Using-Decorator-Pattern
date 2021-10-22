package textdecorators;

import textdecorators.Enums.Prefixes;
import textdecorators.Enums.Suffixes;
import textdecorators.util.InputDetails;
import textdecorators.util.MyLogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for MostFrequentWordDecorator, implements AbstractTextDecorator.
 *
 * @author Rashmi Badadale
 */
public class MostFrequentWordDecorator extends AbstractTextDecorator {
    private final Map<String, Integer> frequencyMap = new HashMap<>();
    private final AbstractTextDecorator adt;
    private final InputDetails id;
    private final MyLogger myLogger = MyLogger.getInstance();

    /**
     * Constructor for MostFrequentWordDecorator
     * @param adtIn Abstract Data type Keyword decorator
     * @param idIn Instance of inputDetails class
     */
    public MostFrequentWordDecorator(AbstractTextDecorator adtIn, InputDetails idIn) {
        adt = adtIn;
        id = idIn;
        myLogger.writeMessage("Constructor of Most Frequent Word Decorator was called.", MyLogger.DebugLevel.MOST_FREQUENT_DECORATOR);
    }

    /**
     * Overriding the toString() method
     * @return String
     */
    public String toString() {
        return "Most Frequent Decorator";
    }

    /**
     * Method to check the complete word in the input string
     * @param inputString input string
     * @param word Word to be found in the input String
     * @param start Integer Starting index of the string in which we need to find the word
     * @return List containing the start and end index of the word to be replaced
     */
    private List<Integer> checkCompleteWord(String inputString, String word, int start) {
        List<Integer> list = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\b" + "(?i)" + word + "\\b");
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.find(start)) {
            list.add(matcher.start());
            list.add(matcher.start() + word.length());
            return list;
        }
        return null;
    }

    /**
     * Method to replace all occurrence of the word to be replaced
     * @param inputString input string
     * @param word Word to be replaced
     * @return StringBuilder Processed string replaced words with tags
     */
    private StringBuilder replaceAllOccurrence(String inputString, String word) {
        StringBuilder stringBuilder = new StringBuilder(inputString);
        int startIndex = 0;
        int endIndex;
        List<Integer> list = checkCompleteWord(stringBuilder.toString(), word, startIndex);
        startIndex = list.get(0);
        while (startIndex < inputString.length()) {
            if (startIndex == -1)
                break;
            endIndex = startIndex + word.length();
            inputString = inputString.substring(0, startIndex) + Prefixes.MOST_FREQUENT.getPrefix() + inputString.substring(startIndex, endIndex) + Suffixes.MOST_FREQUENT.getSuffix() + inputString.substring(endIndex);
            stringBuilder.replace(0, inputString.length(), inputString);
            list = checkCompleteWord(stringBuilder.toString(), word, endIndex);
            if (list != null) {
                startIndex = list.get(0);
            } else {
                break;
            }
        }
        myLogger.writeMessage("Most frequent word: \"" + word + "\" was replaced with the Decorator tags.", MyLogger.DebugLevel.MOST_FREQUENT_DECORATOR);
        return stringBuilder;
    }

    /**
     * Method override from AbstractTextDecorator
     */
    @Override
    public void processInputDetails() {
        StringBuilder frequentStringBuilder;
        String inputString = id.readInputFile().toString();
        String frequentWord = null;
        Queue<String> words = new LinkedList<>(Arrays.asList(inputString.toLowerCase().replaceAll("\\p{Punct}", " ").split("\\s+")));
        for (String string : words) {
            if (frequencyMap.containsKey(string.toLowerCase())) {
                int frequency = frequencyMap.get(string.toLowerCase());
                frequencyMap.replace(string, frequency + 1);
            } else{
                frequencyMap.put(string,1);
            }
        }
        int max_count = Collections.max(frequencyMap.values());
        for(Map.Entry<String,Integer> entry : frequencyMap.entrySet()){
            if(entry.getValue() == max_count){
                frequentWord = entry.getKey();
            }
        }
        myLogger.writeMessage("Detected the most frequent word in the given input file.", MyLogger.DebugLevel.MOST_FREQUENT_DECORATOR);
        frequentStringBuilder = replaceAllOccurrence(inputString, frequentWord);
        id.storeInput(frequentStringBuilder);
        if(adt != null){
            myLogger.writeMessage("Proceeding with the next Decorator: " + adt.toString(), MyLogger.DebugLevel.MOST_FREQUENT_DECORATOR);
            adt.processInputDetails();
        }
    }
}



