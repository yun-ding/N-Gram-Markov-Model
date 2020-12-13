
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class Tester {
    public void testGetFollows(){
        MarkovOne mo = new MarkovOne();
        String s = "this is a test yes this is a test.";
        mo.setTraining(s);
        ArrayList<String> followT = mo.getFollows("t");
        System.out.println(followT.size()+" chars following t: "+Arrays.toString(followT.toArray()));
        ArrayList<String> followE = mo.getFollows("e");
        System.out.println(followE.size()+" chars following t: "+Arrays.toString(followE.toArray()));
        ArrayList<String> followES = mo.getFollows("es");
        System.out.println(followES.size()+" chars following t: "+Arrays.toString(followES.toArray()));  
        ArrayList<String> followPeriod = mo.getFollows(".");
        System.out.println(followPeriod.size()+" chars following t: "+Arrays.toString(followPeriod.toArray()));  
        ArrayList<String> followTPeriod = mo.getFollows("t.");
        System.out.println(followTPeriod.size()+" chars following t: "+Arrays.toString(followTPeriod.toArray()));  
    }
    
    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        ArrayList<String> follow = markov.getFollows("he");
        System.out.println(follow.size()+" chars following th"); 
    }
}
