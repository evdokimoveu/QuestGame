package questgame;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author evdokimoveu
 */
public class QuestGame {
    
    private static Map<String, File> mapFiles;
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        
        mapFiles = LoadFile.loadFile();
        if(mapFiles.size() > 0){
            Game game = new Game(mapFiles);
            game.start();
        }
        else{
            System.err.println("Error. Cann't find files.");
            System.err.println("Press any key!");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            System.exit(0);
        }        
    }
}
