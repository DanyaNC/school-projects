import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TransitionRule {
    private List<String> words;
    private Random rand;

    public TransitionRule() {
        words = new ArrayList<>();
        rand = new Random();
    }

    public void addWord(String word) {
        words.add(word);
    }

    public List<String> getWords() {
        return words;
    }

    public String chooseRandom() {
       int index = rand.nextInt(words.size());
       return words.get(index);
    }

}
