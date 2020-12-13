
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    abstract public void setTraining(String s);// {
        //myText = s.trim();
    //}
    
    abstract public void setRandom(int seed);//{
        //myRandom = new Random(seed);
    //}
    
    protected ArrayList<String> getFollows(String key){
        ArrayList<String> follows = new ArrayList<String>();
        /*
        int placeHolder = 0;
        while (true){
            int foundKey = myText.indexOf(key, placeHolder);
            if (foundKey == -1){break;}
            if (foundKey + key.length() >= myText.length()){break;}
            follows.add(myText.substring(foundKey+key.length(), foundKey+key.length()+1));
            placeHolder = foundKey+1;
        }
        */
        
        for (int i=0; i < myText.length()-key.length()+1; i++){
            if (myText.substring(i, i+key.length()).equals(key)){
                if (i == myText.length()-key.length()){
                    follows.add(myText.substring(i+key.length()));
                }else{
                    follows.add(myText.substring(i+key.length(), i+key.length()+1));
                }
            }
        }
        
        return follows;
    }
 
    abstract public String getRandomText(int numChars);

}
