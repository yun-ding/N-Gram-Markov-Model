
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram kGram = new WordGram(myText, index, myOrder);
        sb.append(kGram.toString()).append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(kGram);
            if (follows.size() == 0) {break;}
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next).append(" ");
            kGram = kGram.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int idx = indexOf(myText, kGram, 0);
        while (idx < myText.length-kGram.length()){
            follows.add(myText[idx + kGram.length()]);
            idx = indexOf(myText, kGram, idx + kGram.length());
            if (idx == -1){break;}
        }
        return follows;
    }
    
    private int indexOf(String[] words, WordGram target, int start){
        for (int i=start; i<words.length-target.length(); i++){
            if (words[i].equals(target.wordAt(0))){
                boolean targetFound = true;
                for (int j=1; j<target.length(); j++){
                    if (!words[i+j].equals(target.wordAt(j))){
                        targetFound = false;
                        break;
                    }
                }
                if (targetFound){
                    return i;
                }
            }
        }
        return -1;
    }
    
    public String toString(){
        return this.getClass().getSimpleName();
    }

}
