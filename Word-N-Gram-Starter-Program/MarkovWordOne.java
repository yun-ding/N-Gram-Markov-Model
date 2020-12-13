
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int idx = indexOf(myText, key, 0);
        while (idx < myText.length-1){
            follows.add(myText[idx+1]);
            idx = indexOf(myText, key, idx+1);
            if (idx == -1){break;}
        }
        return follows;
    }
    
    private int indexOf(String[] words, String target, int start){
        for (int i=start; i<words.length; i++){
            if (words[i].equals(target)){
                return i;
            }
        }
        return -1;
    }
    
    public void testIndexOf(){
        String s = "this is just a test yes this is a simple test";
        String[] list = s.split("\\s+");
        System.out.println(indexOf(list, "this", 0));
        System.out.println(indexOf(list, "this", 3));
        System.out.println(indexOf(list, "frog", 0));
        System.out.println(indexOf(list, "frog", 5));
        System.out.println(indexOf(list, "simple", 2));
        System.out.println(indexOf(list, "test", 5));
    }
    public String toString(){
        return "MarkovWordOne";
    }
}
