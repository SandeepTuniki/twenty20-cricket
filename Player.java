import java.util.ArrayList;
import java.util.Random;


public class Player {
    private String name;
    private int runsScored = 0;
    private int ballsPlayed = 0;
    private double strikeRate;
    private boolean out = false;
    private ArrayList<Integer> runsForEachBall = new ArrayList<Integer>();
    
    // Has the possible outcomes of a ball bowled.
    public static int[] bowlingChoices = {-1, 0, 1, 2, 3, 4, 5, 6};
    
    public Player(String name) {
        this.name = name;
    }
    
    public boolean isOut() {
        if(out == true)
            return true;
        else
            return false;
    }
    
    public boolean isNotOut() {
        if(out == true)
            return false;
        else
            return true;
    }
    
    public int getRuns() {
        return runsScored;
    }
    
    public double getStrikeRate() {
        strikeRate = (runsScored * 100) / ballsPlayed;
        return strikeRate;
    }
    
    public String getName() {
        return name;
    }
    
    // It returns the runs scored for every individual ball in an array.
    public int[] getRunsForEachBall() {
        Integer[] temp1 = new Integer[(runsForEachBall.size())];
        temp1 = runsForEachBall.toArray(temp1);
        int temp2[] = new int[temp1.length];
        for(int i = 0; i < temp2.length; i++) {
            temp2[i] = temp1[i];
        }
        return temp2;
    }
    
    // The player plays the ball and either scores runs or gets out.
    // Also, it return the outcome of the ball played.
    public int playABall() {
        Random rnd = new Random();
        int index = rnd.nextInt(8);
        int val = Player.bowlingChoices[index];
        
        if(val == -1 || val == 5) {
            ballsPlayed++;
            out = true;
            runsForEachBall.add(-1); // -1 signifies OUT
            return val;
        } else {
            ballsPlayed++;
            runsScored += val;
            runsForEachBall.add(val);
            return val;
        }
        
    }
    
    
}

