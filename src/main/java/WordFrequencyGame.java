import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public String play(String inputStr) {

        if (inputStr.split("\\s+").length == 1) {
            return inputStr + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                Map<String, List<Input>> map = getStringListMap(inputStr);
                List<Input> inputList;

                List<Input> list = new ArrayList<>();
                for (Map.Entry<String, List<Input>> entry : map.entrySet()) {
                    Input input = new Input(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }
                inputList = list;

                inputList.sort((w1, w2) -> w2.getCount() - w1.getCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (Input w : inputList) {
                    String s = w.getText() + " " + w.getCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private Map<String, List<Input>> getStringListMap(String inputStr) {
        String[] arr = inputStr.split("\\s+");

        List<Input> inputList = Arrays.stream(arr).map(s -> new Input(s, 1)).collect(Collectors.toList());

        //get the map for the next step of sizing the same word
        return getListMap(inputList);
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {
//       map.computeIfAbsent(input.getText(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getText())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getText(), arr);
            } else {
                map.get(input.getText()).add(input);
            }
        }
        return map;
    }
}
