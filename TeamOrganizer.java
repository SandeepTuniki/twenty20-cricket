import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

// This class extracts the players' names and the teams' names from the file specified.
public class TeamOrganizer {
    private String nameOfFirstTeam, nameOfSecondTeam;
    private String[] playersOfFirstTeam = new String[11];
    private String[] playersOfSecondTeam = new String[11];
    
    public TeamOrganizer(String nameOfFile) throws IOException {
        FileReader fr = new FileReader(nameOfFile);
        BufferedReader br = new BufferedReader(fr);
        String str = "", source = "";
        
        while((str = br.readLine()) != null) {
            source += str;
        }
        fr.close();
        
        Pattern pat = Pattern.compile("[,\n()]");
        String[] strs = pat.split(source);
        
        int i = 0;
        nameOfFirstTeam = strs[i].trim();
        i++;
        for(int j = 0; j < playersOfFirstTeam.length; i++, j++) {
            playersOfFirstTeam[j] = strs[i].trim();
        }
        
        nameOfSecondTeam = strs[i].trim();
        i++;
        for(int j = 0; j < playersOfSecondTeam.length; i++, j++) {
            playersOfSecondTeam[j] = strs[i].trim();
        }
    }

    public String getNameOfFirstTeam() {
        return nameOfFirstTeam;
    }
    
    public String getNameOfSecondTeam() {
        return nameOfSecondTeam;
    }
    
    public String[] getPlayersOfFirstTeam() {
        return playersOfFirstTeam;
    }
    
    public String[] getPlayersOfSecondTeam() {
        return playersOfSecondTeam;
    }
    
}

