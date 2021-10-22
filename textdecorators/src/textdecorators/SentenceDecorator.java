package textdecorators;

import textdecorators.Enums.Prefixes;
import textdecorators.Enums.Suffixes;
import textdecorators.util.InputDetails;
import textdecorators.util.MyLogger;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class for Keyword Decorator, implements AbstractTextDecorator.
 *
 * @author Rashmi Badadale
 */
public class SentenceDecorator extends AbstractTextDecorator {
    private final AbstractTextDecorator adt;
    private final InputDetails id;
    private final MyLogger myLogger = MyLogger.getInstance();

    /**
     * Constructor for SentenceDecorator Decorator
     * @param adtIn Abstract Data type NULL
     * @param idIn Instance of inputDetails class
     */
    public SentenceDecorator(AbstractTextDecorator adtIn, InputDetails idIn) {
        adt = adtIn;
        id = idIn;
        myLogger.writeMessage("Constructor of Sentence Decorator was called.", MyLogger.DebugLevel.SENTENCE_DECORATOR);

    }

    /**
     * Overriding the toString() method
     * @return String
     */
    public String toString() {
        return "Sentence Decorator";
    }

    /**
     * Method override from AbstractTextDecorator
     */
    @Override
    public void processInputDetails() {
        StringBuilder stringBuilder = new StringBuilder();
        String retrievedString = id.getStringBuilderInput().toString().trim();
        Queue<String> sentences = new LinkedList<>(Arrays.asList(retrievedString.split("\\.")));
        myLogger.writeMessage("The input file was split into chunks with respect to period character.", MyLogger.DebugLevel.SENTENCE_DECORATOR);
        for (String string : sentences) {
            stringBuilder.append(Prefixes.BEGIN_SENTENCE.getPrefix()).append(string).append(Suffixes.END_SENTENCE.getSuffix()).append(".");
        }
        id.storeInput(stringBuilder);
        myLogger.writeMessage("The beginning and end of sentences marked with respective tags.", MyLogger.DebugLevel.SENTENCE_DECORATOR);
        if(adt != null){
            adt.processInputDetails();
        }
    }
}
