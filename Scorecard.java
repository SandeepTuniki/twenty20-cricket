import java.io.FileNotFoundException;
import java.util.Formatter;

// This class stores the final score of the match in a file
public class Scorecard {
    private Team[] teams;
    private String fileName;
    private Formatter score_fmt;
    
    public Scorecard(Team[] teams, String fileName) throws FileNotFoundException {
        this.teams = teams;
        this.fileName = fileName;
        
        score_fmt = new Formatter(fileName);
    }
    
    public void close() {
        score_fmt.close();
    }
    
    public void createScorecard(int tossWinner) {
        // Decides whose score goes into the scorecard depending on who won the toss
        if( tossWinner == 1 ) {
            Team temp = teams[0];
            teams[0] = teams[1];
            teams[1] = temp;
        }
        
        for(Team team : teams) { // Runs the loop for both the teams
            score_fmt.format("%s\n",team.getTeamName());
            int counter;
            if( team.getNumOfWickets() == team.numOfWicketsInInnings )
                counter = 10;
            else
                counter = team.getNumOfWickets() + 1;
            
            for(int i = 0; i <= counter; i++) {
                Player player = team.players[i];
                
                score_fmt.format("%s, ",player.getName());
                int[] runsForEachBall = player.getRunsForEachBall();
                
                for( int run : runsForEachBall ) {
                    if( run == -1 ) {
                        score_fmt.format("OUT,");
                        break;
                    }
                    else
                        score_fmt.format("%d,",run);
                }
                
                if(player.isNotOut())
                    score_fmt.format("NOT OUT,%d\n",player.getRuns());
                else
                    score_fmt.format("%d\n",player.getRuns());
            }
            
            score_fmt.format("Total,%d\n",team.getScore());
        }
        
        if(teams[0].getScore() > teams[1].getScore())
            score_fmt.format("%s WINS\n",teams[0].getTeamName());
        else if(teams[1].getScore() > teams[0].getScore()) {
            score_fmt.format("%s WINS\n",teams[1].getTeamName());
        } else {
            score_fmt.format("Match TIED\n");
        }
        
        Player p1,p2;
        p1 = teams[0].getBestPlayer();
        p2 = teams[1].getBestPlayer();
        
        // Decides the Man of the Match based on runs and strikerate
        if(p1.getRuns() > p2.getRuns()) {
            score_fmt.format("MOM, %s, %s", teams[0].getTeamName(), p1.getName() );
        }
        else if(p2.getRuns() > p1.getRuns()) {
            score_fmt.format("MOM, %s, %s", teams[1].getTeamName(), p2.getName() );
        }
        else { // Both players have equal runs. So check their strike rates.
            if(p1.getStrikeRate() > p2.getStrikeRate())
                score_fmt.format("MOM, %s, %s", teams[0].getTeamName(), p1.getName() );
            else
                score_fmt.format("MOM, %s, %s", teams[1].getTeamName(), p2.getName() );
        }
        
        close(); // closes the file.
        
    }
}

