
/**
 * Write a description of MarkovFour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
//import edu.duke.*;
public class MarkovFour extends AbstractMarkovModel{
    //private String myText;
    //private Random myRandom;
    
    public MarkovFour() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    /*
    public ArrayList<String> getFollows(String key){
        ArrayList<String> follows = new ArrayList<String>();
        for (int i=0; i<myText.length()-key.length(); i++){
            if (myText.substring(i, i+key.length()).equals(key)){
                    follows.add(myText.substring(i+key.length(), i+key.length()+1));
               }
           }
        return follows;
       }
    */
   
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-(4+1));
        String key = myText.substring(index, index+4);
        sb.append(key);
        
        for(int k=0; k < numChars-4; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0){break;}
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
    
    public String toString(){
        return "MarkovModel of order four,";
    }
}
