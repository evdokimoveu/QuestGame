
package questgame;

import java.io.File;
import java.util.Map;
import java.util.Scanner;
import questgame.model.Action;
import questgame.model.Var;

/**
 *
 * @author evdokimoveu
 */
public class Game {

    private final int MAX_OVERDOSE = 150;
    private final String PLAYER_HEALTH_ZERO = "-1";
    private final String PLAYER_OVERDOSE = "9999";
    private int health;
    
    
    private final Map<String, File> mapFiles;
    private Map<String, Action> mapActions;
    
    public Game(Map<String, File> mapFiles) {
        this.mapFiles = mapFiles;
        this.health = 100;
    }
        
    public void start(){
        
        String fileId = "1";
        String actionId = "1";
        String newFileId = fileId;
        String newActionId = actionId;
        Action action;
        
        ParseFile parseFile = new ParseFile();
        mapActions = parseFile.parse(mapFiles.get(fileId));
        action = mapActions.get(actionId);
                
        do {
            if(!fileId.equals(newFileId) && !newFileId.isEmpty()){
                fileId = newFileId;
                mapActions = parseFile.parse(mapFiles.get(fileId));
                action = mapActions.get(actionId);
            }
            if(!actionId.equals(newActionId) && !newActionId.isEmpty()){
                actionId = newActionId;
                action = mapActions.get(actionId);
            }
            
            Var var = printScreen(action);
            if(var == null){
                Scanner scanner = new Scanner(System.in);
                do {
                    if(scanner.hasNext()){
                        String startGame = scanner.next();
                        if(startGame.equalsIgnoreCase("y") || startGame.equalsIgnoreCase("д")){                            
                            this.start();
                        }
                        else if(startGame.equalsIgnoreCase("n") || startGame.equalsIgnoreCase("н")){
                            System.exit(0);
                        }
                    }
                } while(true);
            }
            else{
                String moveTo = checkAnswer(var);
                if(moveTo.equalsIgnoreCase(PLAYER_HEALTH_ZERO)){
                    newActionId = PLAYER_HEALTH_ZERO;
                }
                else if(moveTo.equalsIgnoreCase(PLAYER_OVERDOSE)){
                    newActionId = PLAYER_OVERDOSE;
                }
                else{
                    if(
                        moveTo.contains("a") && 
                        moveTo.contains("f") && 
                        moveTo.length() >= 4){
                        
                        String[] move = moveTo.replaceAll("f", "").trim().split("a");                        
                        newFileId = String.valueOf(move[0]);
                        newActionId = String.valueOf(move[1]);
                    }
                    else{
                        System.out.println("Ошибка сруктуры файла. Неверный параметр move_to.");
                        System.exit(0);
                    }
                }
            }            
        } while(true);
    }
    
    private Var printScreen(Action action){
        String varId = "";
        System.out.println(action.getText() + "\n");
        Map<String, Var> varMap = action.getMapVar();
        if(varMap != null && varMap.size() > 0){
            for(Map.Entry<String, Var> entry : varMap.entrySet()){
                Var var = entry.getValue();            
                System.out.println(var.getId() + ". " + var.getText());
            }
            System.out.println("Выберите ответ:");
            Scanner scanner = new Scanner(System.in);
            do {
                if(scanner.hasNextInt()){
                    varId = String.valueOf(scanner.nextInt());
                    if(varMap.containsKey(varId)){
                        break;
                    }
                    else{
                        System.out.println("Ошибка. Не верный ответ.");
                    }
                }
                else{
                    System.out.println("Ошибка. Должно быть число.");
                }
            } while(true);
        }
        else{
            System.out.println("Вы хотите начать игру заново? Y/N : ");
            return null;
        }
        return varMap.get(varId);
    }
    
    private String checkAnswer(Var answer){
        int damage;
        if(answer.getDamage().isEmpty()){
            damage = 0;
        }
        else{
            damage = Integer.valueOf(answer.getDamage());
        }
        health = health + damage;
        if(health < 0){
            return PLAYER_HEALTH_ZERO;
        }
        else if(health > MAX_OVERDOSE){
            return PLAYER_OVERDOSE;
        }
        else{
            return answer.getMoveTo();
        }
    }
}
