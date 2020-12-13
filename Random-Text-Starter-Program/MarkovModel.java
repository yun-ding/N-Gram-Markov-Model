
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class MarkovModel{
    private String myText;
    private Random myRandom;
    private int N;
    
    public MarkovModel(int n) {
        myRandom = new Random();
        N = n;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public ArrayList<String> getFollows(String key){
        ArrayList<String> follows = new ArrayList<String>();
        for (int i=0; i<myText.length()-key.length(); i++){
            if (myText.substring(i, i+key.length()).equals(key)){
                    follows.add(myText.substring(i+key.length(), i+key.length()+1));
               }
           }
        return follows;
       }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-N);
        String key = myText.substring(index, index+N);
        sb.append(key);
        
        for(int k=0; k < numChars; k++){
            ArrayList<String> follows = getFollows(key);
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = sb.substring(sb.length()-N);
        }
        
        return sb.toString();
    }
}
