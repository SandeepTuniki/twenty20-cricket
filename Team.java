
// This class stores the team records and rules for playing the match.
public class Team implements BasicRules {
    private String name;
    private int totalScore = 0;
    private int numOfOversPlayed = 0;
    public Player[] players = new Player[11];
    private int target = Integer.MAX_VALUE;
    private int numOfWicketsFallen = 0;
    private int strikerIndex = 0;
    private int nonStrikerIndex = 1;
    
    public Team(String name, String[] playersNames) {
        this.name = name;
        
        for(int i = 0; i < numOfPlayers; i++) {
            players[i] = new Player(playersNames[i]);
        }
    }
    
    public Team(String name, String[] playersNames, int targetScore) {
        this(name, playersNames);
        target = targetScore;
    }
    
    public int getScore() {
        return totalScore;
    }
    
    // Return the best player from the team after the match.
    // Used as a nominee for Man of the Match. 
    public Player getBestPlayer() {
        Player bestPlayer = players[0];
        
        for(int i = 1; i < numOfPlayers; i++) {
            if(players[i].getRuns() > bestPlayer.getRuns())
                bestPlayer = players[i];
            else if(players[i].getRuns() == bestPlayer.getRuns()) {
                if(players[i].getStrikeRate() > bestPlayer.getStrikeRate())
                    bestPlayer = players[i];
            }
                
        }
        
        return bestPlayer;
    }
    
    // The team plays the innings until either overs are consumed, all wickets fall or 
    // the target is chased(for 2nd batting.)
    public void playTheInnings() {
        for(int overs = 1; overs <= totalOvers; overs++, numOfOversPlayed = overs) {
            for(int balls = 1; balls <= numOfBallsInOver; balls++) {
                int val = players[strikerIndex].playABall();
                
                if(val == 0 || val == 2 || val == 4 || val == 6) {
                    totalScore += val;
                    if( wonTheGame() )
                        return;
                } else if(val == 1 || val == 3) {
                    totalScore += val;
                    if( wonTheGame() )
                        return;
                    
                    int temp = strikerIndex;
                    strikerIndex = nonStrikerIndex;
                    nonStrikerIndex = temp;
                } else if(val == -1) {
                    numOfWicketsFallen++;
                    if( numOfWicketsFallen == numOfWicketsInInnings )
                        return;
                    else {
                        strikerIndex = Math.max(strikerIndex, nonStrikerIndex) + 1;
                    }
                } else if(val == 5) {
                    numOfWicketsFallen++;
                    if(numOfWicketsFallen == numOfWicketsInInnings)
                        return;
                    else
                        strikerIndex = Math.max(strikerIndex, nonStrikerIndex) + 1;
                    
                    int temp = strikerIndex;
                    strikerIndex = nonStrikerIndex;
                    nonStrikerIndex = temp;
                }
            }
            
            int temp = strikerIndex;
            strikerIndex = nonStrikerIndex;
            nonStrikerIndex = temp;
        }
    }

    private boolean wonTheGame() {
        if(totalScore > target)
            return true;
        else
            return false;
    }

    public int getNumOfWickets() {
        return numOfWicketsFallen;
    }

    public String getTeamName() {
        return name;
    }

}

