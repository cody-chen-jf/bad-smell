import java.util.*;
import java.util.stream.Collectors;

class WordFrequencyGame {

    public static final String SPACE_REGEX = "\\s+";
    public static final String DELIMITER = "\n";

    String play(String inputStr) {
        List<String> buildTexts = getBuildTexts(inputStr);
        List<Word> wordList = getWords(buildTexts);
        return getOutputString(wordList);
    }

    private List<String> getBuildTexts(String inputStr) {
        return Arrays.asList(inputStr.split(SPACE_REGEX));
    }

    private List<Word> getWords(List<String> texts) {
        return texts.stream()
                .map(s -> new Word(s, 1))
                .collect(Collectors.toList())
                .stream()
                .collect(Collectors.groupingBy(Word::getText))
                .entrySet()
                .stream()
                .map(entry -> new Word(entry.getKey(), entry.getValue().size()))
                .collect(Collectors.toList());
    }

    private String getOutputString(List<Word> wordList) {
        return wordList.stream()
                .sorted((w1, w2) -> w2.getCount() - w1.getCount())
                .map(word -> word.getText() + " " + word.getCount())
                .collect(Collectors.joining(DELIMITER));
    }
}
