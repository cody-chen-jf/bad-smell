import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class WordFrequencyGame {
    String play(String inputStr) {
        Map<String, List<Word>> map = getStringListMap(inputStr);
        List<Word> wordList = getWords(map);
        return getWordList(wordList);
    }

    private String getWordList(List<Word> wordList) {
//        wordList;
//        StringJoiner joiner = new StringJoiner("\n");
//
//        for (Word w : wordList) {
//            String s = w.getText() + " " + w.getCount();
//            joiner.add(s);
//        }
        return wordList.stream().sorted((w1, w2) -> w2.getCount() - w1.getCount()).map(w -> w.getText() + " " + w.getCount()).collect(Collectors.joining("\n"));
    }

    private List<Word> getWords(Map<String, List<Word>> map) {
        return map.entrySet().stream().map(entry -> new Word(entry.getKey(), entry.getValue().size())).collect(Collectors.toList());
    }

    private Map<String, List<Word>> getStringListMap(String inputStr) {
        //            if (map.containsKey(word.getText())) {
//                map.get(word.getText()).add(word);
//            } else {
//                ArrayList arr = new ArrayList<>();
//                arr.add(word);
//                map.put(word.getText(), arr);
//            }
        return Arrays.stream(inputStr.split("\\s+")).map(s -> new Word(s, 1)).collect(Collectors.toList()).stream().collect(Collectors.groupingBy(Word::getText));
    }

}
