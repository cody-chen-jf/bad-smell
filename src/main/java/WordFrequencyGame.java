import java.util.*;
import java.util.stream.Collectors;

class WordFrequencyGame {

    String play(String inputStr) {
        List<String> buildTexts = getBuildTexts(inputStr);
        List<Word> wordList = getWords(getStringListMap(inputStr));
        return getOutputString(wordList);
    }

    private List<String> getBuildTexts(String inputStr) {
        return Arrays.asList(inputStr.split("\\s+"));
    }

    private List<Word> getWords(Map<String, List<Word>> map) {
        return map.entrySet().stream()
                .map(entry -> new Word(entry.getKey(), entry.getValue().size()))
                .collect(Collectors.toList());
    }

    private Map<String, List<Word>> getStringListMap(String inputStr) {
        return Arrays.stream(inputStr.split("\\s+")).map(s -> new Word(s, 1)).collect(Collectors.toList()).stream().collect(Collectors.groupingBy(Word::getText));
    }

    private String getOutputString(List<Word> wordList) {
        return wordList.stream()
                .sorted((w1, w2) -> w2.getCount() - w1.getCount())
                .map(word -> word.getText() + " " + word.getCount())
                .collect(Collectors.joining("\n"));
    }
}
