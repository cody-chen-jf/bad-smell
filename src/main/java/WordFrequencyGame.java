import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public String play(String inputStr) {
        Map<String, List<Word>> map = getStringListMap(inputStr);
        List<Word> wordList = getWords(map);
        return getOutputString(wordList);
    }

    private String getOutputString(List<Word> wordList) {
        wordList.sort((w1, w2) -> w2.getCount() - w1.getCount());

        StringJoiner joiner = new StringJoiner("\n");
        for (Word w : wordList) {
            String s = w.getText() + " " + w.getCount();
            joiner.add(s);
        }
        return joiner.toString();
    }

    private List<Word> getWords(Map<String, List<Word>> map) {
        return map.entrySet().stream().map(entry -> new Word(entry.getKey(), entry.getValue().size())).collect(Collectors.toList());
    }

    private Map<String, List<Word>> getStringListMap(String inputStr) {
        //get the map for the next step of sizing the same word
        return Arrays.stream(inputStr.split("\\s+")).map(s -> new Word(s, 1)).collect(Collectors.toList()).stream().collect(Collectors.groupingBy(Word::getText));
    }

}
