import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


public class GamePlay {
    static final int HEADS = 0;
    static final int TAILS = 1;
    
    public static void main( String[] args ) throws IOException {

        if (args.length < 1) {
            System.out.println("Filename expected as argument");
            return;
        }

        String fileName = args[0];
        
        // The below class takes the file from user and extracts the players' names and teams' names.
        TeamOrganizer teamOrganizer = new TeamOrganizer(fileName);
        
        String team1, team2;
        String[] playersOfTeam1, playersOfTeam2;
        
        team1 = teamOrganizer.getNameOfFirstTeam();
        team2 = teamOrganizer.getNameOfSecondTeam();
        
        playersOfTeam1 = teamOrganizer.getPlayersOfFirstTeam();
        playersOfTeam2 = teamOrganizer.getPlayersOfSecondTeam();
        
        Team[] teams = new Team[2];
        
        int tossWinner = toss();
        
        if(tossWinner == HEADS) { // Player 1 wins the Toss
            System.out.println("Player1 wins the toss and elects to bat first.");
            teams[0] = new Team(team1, playersOfTeam1);
            System.out.println("1st Innings starts...");
            teams[0].playTheInnings();
            System.out.println("1st Innings ends...");
            int target = teams[0].getScore() + 1;
            teams[1] = new Team(team2, playersOfTeam2, target);
            System.out.println("2nd Innings starts...");
            teams[1].playTheInnings();;
            System.out.println("2nd Innings ends...");
        } else { // Player 2 wins the Toss
            System.out.println("Player2 wins the toss and elects to bat first.");
            teams[1] = new Team(team2, playersOfTeam2);
            System.out.println("1st Innings starts...");
            teams[1].playTheInnings();
            System.out.println("1st Innings ends...");
            int target = teams[1].getScore() + 1;
            teams[0] = new Team(team1, playersOfTeam1, target);
            System.out.println("2nd Innings starts...");
            teams[0].playTheInnings();
            System.out.println("2nd Innings ends...");
        }
        
        // Prints the final scorecard into a file
        Scorecard scorecard = new Scorecard(teams, "score.txt"); 
        scorecard.createScorecard(tossWinner);
        
        System.out.println("The final result is stored in score.txt file.");
        
    }
    
    public static int toss() {
        Random rnd = new Random();
        return rnd.nextInt(2); // Generates two values (0 or 1). 0 for heads, 1 for tails
    }
}













