import java.util.*;

public class WordFrequencyGame {
    public String getResult(String inputStr) {
        Map<String, List<Word>> map = getStringListMap(inputStr);
        List<Word> wordList = getWords(map);
        return getWordList(wordList);
    }

    public String getWordList(List<Word> wordList) {
        wordList.sort((w1, w2) -> w2.getCount() - w1.getCount());

        StringJoiner joiner = new StringJoiner("\n");
        for (Word w : wordList) {
            String s = w.getText() + " " + w.getCount();
            joiner.add(s);
        }
        return joiner.toString();
    }

    public List<Word> getWords(Map<String, List<Word>> map) {
        List<Word> list = new ArrayList<>();
        for (Map.Entry<String, List<Word>> entry : map.entrySet()) {
            Word word = new Word(entry.getKey(), entry.getValue().size());
            list.add(word);
        }
        return list;
    }

    public Map<String, List<Word>> getStringListMap(String inputStr) {
        String[] arr = inputStr.split("\\s+");

        List<Word> wordList = new ArrayList<>();
        for (String s : arr) {
            Word word = new Word(s, 1);
            wordList.add(word);
        }

        return getListMap(wordList);
    }

    private Map<String, List<Word>> getListMap(List<Word> wordList) {
        Map<String, List<Word>> map = new HashMap<>();
        for (Word word : wordList) {
            if (!map.containsKey(word.getText())) {
                ArrayList arr = new ArrayList<>();
                arr.add(word);
                map.put(word.getText(), arr);
            } else {
                map.get(word.getText()).add(word);
            }
        }
        return map;
    }
}
