import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public String play(String inputStr) {
        Map<String, List<Word>> map = getStringListMap(inputStr);
        List<Word> wordList;

        List<Word> list = new ArrayList<>();
        for (Map.Entry<String, List<Word>> entry : map.entrySet()) {
            Word word = new Word(entry.getKey(), entry.getValue().size());
            list.add(word);
        }
        wordList = list;

        wordList.sort((w1, w2) -> w2.getCount() - w1.getCount());

        StringJoiner joiner = new StringJoiner("\n");
        for (Word w : wordList) {
            String s = w.getText() + " " + w.getCount();
            joiner.add(s);
        }
        return joiner.toString();
    }

    private Map<String, List<Word>> getStringListMap(String inputStr) {
        //get the map for the next step of sizing the same word
        return getListMap(Arrays.stream(inputStr.split("\\s+")).map(s -> new Word(s, 1)).collect(Collectors.toList()));
    }

    private Map<String, List<Word>> getListMap(List<Word> wordList) {
        return wordList.stream().collect(Collectors.groupingBy(Word::getText));
    }
}
