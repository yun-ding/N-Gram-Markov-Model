
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
//import edu.duke.*;
public class EfficientMarkovModel extends AbstractMarkovModel{
    //private String myText;
    //private Random myRandom;
    private int N;
    private HashMap<String, ArrayList<String>> myMap;
    
    public EfficientMarkovModel(int n) {
        myRandom = new Random();
        N = n;
        myMap = new HashMap<String, ArrayList<String>>();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
        buildMap();
        printHashMapInfo();
    }
    
    public ArrayList<String> getFollows(String key){
        return myMap.get(key);
       }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-(N+1));
        String key = myText.substring(index, index+N);
        sb.append(key);
        
        for(int k=0; k < numChars-N; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows == null){break;}
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
    
    public void buildMap(){
        for (int i=0; i<myText.length()-N+1; i++){
            String key = myText.substring(i, i+N);
            if (!myMap.containsKey(key)){
                ArrayList<String> follows = new ArrayList<String>();
                int pos = 0;
                while (pos<myText.length()){
                    int foundKey = myText.indexOf(key, pos);
                    if (foundKey == -1){break;}
                    if (foundKey + N >= myText.length()-1){break;}
                    follows.add(myText.substring(foundKey, foundKey+N));
                    pos = foundKey+1;
                }
                /*
                for (int j=0; j<myText.length()-N+1; j++){
                    if (myText.substring(j, j+N).equals(key)){
                        if (j == myText.length()-N){
                            follows.add(myText.substring(j+N));
                        }else{
                            follows.add(myText.substring(j+N, j+N+1));
                        }
                    }
                }
                */
                myMap.put(key, follows);
            }
        }
    } 
    
    public void printHashMapInfo(){
        ArrayList<String> max = new ArrayList<String>();
        int maxValue = 0;
        for (String s: myMap.keySet()){
            if (myMap.get(s).size()>maxValue){
                maxValue = myMap.get(s).size();
            }
        }
        
        for (String s: myMap.keySet()){
            if (myMap.get(s).size() == maxValue){
                max.add(s);
            }
        }
        System.out.println("number of keys: "+myMap.size());
        System.out.println(" have largest value in map: "+maxValue);
    }
    
    public String toString(){
        return "this is the EfficientMarkovModel class of "+N+", ";
    }
}
