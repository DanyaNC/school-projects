import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;


public class NGram {
    private Map<String, TransitionRule> wordMap;
    private Random rand;
    private String gram;
    private List<String> keysAsArray;
    private int order;

    public NGram(int order, List<String> wordsList) {
        wordMap = new HashMap<>();
        rand = new Random();
        this.order = order;
        for(int i = 0; i < wordsList.size() - order; i++) {
            String gram = "";
            for(int j = i; j < i + order; j++) {
                if(j == i) {
                    gram += wordsList.get(j);
                } else {
                    gram += " " + wordsList.get(j);
                }
            }
            if(!wordMap.containsKey(gram)) {
                wordMap.put(gram, new TransitionRule());
            }
                wordMap.get(gram).addWord(wordsList.get(i+order));
        }
        chooseRandomKey();
    }

    private void chooseRandomKey() {
        rand.nextInt(wordMap.keySet().size());
        keysAsArray = new ArrayList<String>(wordMap.keySet());
        gram = keysAsArray.get(rand.nextInt(keysAsArray.size()));
    }

    public void createMarkov(int length) {
        System.out.print(gram + " ");
        for(int i = 0; i < length-order; i++) {
            while(wordMap.get(gram) == null || wordMap.get(gram).getWords().isEmpty()) {
                chooseRandomKey();
            }
            String addedWord = wordMap.get(gram).chooseRandom();
            String addToGram = gram + " " + addedWord;
            String[] combined = addToGram.split(" ");
            gram = "";
            for(int j = 1; j < combined.length; j++) {
                if(j == combined.length - 1) {
                    gram += combined[j];
                } else {
                    gram += combined[j] + " ";
                }
            }
            System.out.print(addedWord + " ");

        }
    }



}
