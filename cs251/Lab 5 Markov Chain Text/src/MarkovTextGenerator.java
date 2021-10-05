import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MarkovTextGenerator {

    public static void main(String [] args) throws Exception {
        String path = args[2];
        NgramCaller(Integer.parseInt(args[0]), Integer.parseInt(args[1]), path);
    }

    public static void NgramCaller(int order, int length, String path) throws Exception {
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        List<String> lineList = new ArrayList<>();
        List<String> wordList = new ArrayList<>();
        int count = 0;
        while((line = br.readLine()) != null) {
            lineList.add(line);
            String[] words = (lineList.get(count).trim().split("[-:\\]\\[_()*^{}\" ]"));
            for(String word : words) {
                if(word.length() > 0) {
                    wordList.add(word);
                }
            }
            count++;
        }



        NGram n = new NGram(order, wordList);
        n.createMarkov(length);
    }

}
