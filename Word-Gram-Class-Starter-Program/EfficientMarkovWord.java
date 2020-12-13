
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class EfficientMarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> myMap;
    
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        myMap = new HashMap<WordGram, ArrayList<String>>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
        printHashMapInfo();
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram kGram = new WordGram(myText, index, myOrder);
        sb.append(kGram.toString()).append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(kGram);
            if (follows == null || follows.size() == 0) {break;}
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next).append(" ");
            kGram = kGram.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        return myMap.get(kGram);
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

    public void buildMap(){
        for (int i=0; i<myText.length-myOrder; i++){
            WordGram key = new WordGram(myText, i, myOrder);
            if (!myMap.containsKey(key)){
                ArrayList<String> follows = new ArrayList<String>();
                follows.add(myText[i+myOrder]);
                myMap.put(key, follows);
            }else{
                String follow = myText[i+myOrder];
                myMap.get(key).add(follow);
            }
        }
        WordGram key = new WordGram(myText, myText.length-myOrder, myOrder);
        ArrayList<String> follows = new ArrayList<String>();
        myMap.put(key, follows);
    }
    
    public void printHashMapInfo(){
        ArrayList<WordGram> max = new ArrayList<WordGram>();
        int maxValue = 0;
        for (WordGram wg: myMap.keySet()){
            if (myMap.get(wg).size() > maxValue){
                maxValue = myMap.get(wg).size();
            }
        }
        
        for (WordGram wg: myMap.keySet()){
            System.out.println(wg+"\t"+myMap.get(wg));
        }
        
        for (WordGram wg: myMap.keySet()){
            if (myMap.get(wg).size() == maxValue){
                max.add(wg);
            }
        }
        System.out.println("number of keys: "+myMap.size());
        System.out.println(max+" have largest value in map: "+maxValue);
    }
}
