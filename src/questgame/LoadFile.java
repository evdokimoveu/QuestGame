
package questgame;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author evdokimoveu
 */
public class LoadFile {
    
    public static Map<String, File> loadFile(){
        Map<String, File> mapFiles = new HashMap<>();
        File dir = new File("game");
        File[] files = dir.listFiles();
        if(files.length > 0){
            for (File file : files) {
                String keyFile = "";
                String nameFile = file.getName();
                
                if(nameFile.contains("_")){
                    keyFile = nameFile.split("_")[0].trim();
                    if(keyFile.matches("[0-9]+") && keyFile.length() > 0){
                        mapFiles.put(keyFile, file);
                    }
                    else{
                        System.out.println("Ошибка. Начало имени файла должно содержать его id.");
                    }
                }
                else{
                    System.err.println("Ошибка. Имя файла должно содержать символ \"_\".");
                    System.err.println("Press any key!");
                    Scanner scanner = new Scanner(System.in);
                    scanner.nextLine();
                    System.exit(0);
                }
            }
        }
        return mapFiles;
    }
}
