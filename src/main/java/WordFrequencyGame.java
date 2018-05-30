import java.util.*;

public class WordFrequencyGame {
    public String getResult(String inputStr) {

        if (inputStr.split("\\s+").length == 1) {
            return inputStr + " 1";
        } else {

            try {
                String[] arr = inputStr.split("\\s+");

                List<Word> wordList = new ArrayList<>();
                for (String s : arr) {
                    Word word = new Word(s, 1);
                    wordList.add(word);
                }

                Map<String, List<Word>> map = getListMap(wordList);

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
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
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
