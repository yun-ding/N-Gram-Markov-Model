
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;
import java.util.*;

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        //for(int k=0; k < 3; k++){
        //    String st= markov.getRandomText(size);
        //    printOut(st);
        //}
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource("data/romeo.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 10;
        
        //MarkovZero mz = new MarkovZero();
        //runModel(mz, st, size, seed);

        //MarkovOne mOne = new MarkovOne();
        //runModel(mOne, st, size, seed);
        
        //MarkovModel mThree = new MarkovModel(3);
        //runModel(mThree, st, size, seed);
        
        //MarkovFour mFour = new MarkovFour();
        //runModel(mFour, st, size, seed);

    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
    public void testHashMap(){
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        EfficientMarkovModel m = new EfficientMarkovModel(5);
        runModel(m, st, 100, 531);
    }
    
    public void compareMethods(){
        FileResource fr = new FileResource("data/romeo.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //MarkovModel m = new MarkovModel(2);
        EfficientMarkovModel m = new EfficientMarkovModel(5);
        runModel(m, st, 1000, 615);
        System.out.println(System.nanoTime());
    }
}
