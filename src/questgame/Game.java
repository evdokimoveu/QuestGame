
package questgame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
    private final JTextArea mainWindow;
    private final JTextField userAnswer;
    private final JLabel errorMessage;
    private String fileId;
    private String actionId;
    private String newFileId;
    private String newActionId;
    private Action action;
    private ParseFile parseFile;
    
    
    private final Map<String, File> mapFiles;
    private Map<String, Action> mapActions;
    
    public Game(Map<String, File> mapFiles, JTextArea mainWindow, JTextField userAnswer, JLabel errorMessage) {
        this.mapFiles = mapFiles;
        this.userAnswer = userAnswer;
        this.mainWindow = mainWindow;
        this.errorMessage = errorMessage;
    }
               
    public void start(){
        
        preStart();
        
        userAnswer.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    String answer = userAnswer.getText().trim();
                    userAnswer.setEditable(false);
                    userAnswer.setText("");
                    mainWindow.setText("");
                    errorMessage.setText("*");
                    if(!answer.isEmpty()){
                        Map<String, Var> varMap = action.getMapVar();
                        if(varMap != null && varMap.size() > 0){
                            if(varMap.containsKey(answer)){
                                Var var = varMap.get(answer);
                                String moveTo = checkAnswer(var);
                                if(moveTo.equalsIgnoreCase(PLAYER_HEALTH_ZERO)){
                                    newActionId = PLAYER_HEALTH_ZERO;
                                }
                                else if(moveTo.equalsIgnoreCase(PLAYER_OVERDOSE)){
                                    newActionId = PLAYER_OVERDOSE;
                                }
                                else{
                                     if(moveTo.contains("a")
                                             && moveTo.contains("f")
                                             && moveTo.length() >= 4){
                                         
                                         String[] move = moveTo.replaceAll("f", "").trim().split("a");
                                         newFileId = String.valueOf(move[0]);
                                         newActionId = String.valueOf(move[1]);
                                     }
                                     else{
                                         JOptionPane.showConfirmDialog(null, "Ошибка сруктуры файла. Неверный параметр move_to.", "Error", JOptionPane.ERROR_MESSAGE);
                                         System.out.println();
                                         System.exit(0);
                                     }
                                }
                                if(!fileId.equals(newFileId) && !newFileId.isEmpty()){
                                    fileId = newFileId;
                                    mapActions = parseFile.parse(mapFiles.get(fileId));
                                    action = mapActions.get(actionId);
                                }
                                if(!actionId.equals(newActionId) && !newActionId.isEmpty()){
                                    actionId = newActionId;
                                    action = mapActions.get(actionId);
                                }
                            }
                            else{
                                errorMessage.setText(errorMessage.getText() + "Выбран не коректный вариант ответа");
                            }
                        }
                        else{
                            if(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("д")){
                                preStart();
                            }
                            else if(answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("н")){
                                System.exit(0);
                            }
                            else{
                                errorMessage.setText(errorMessage.getText() + "Выбран не верный вариант ответа");
                            }
                        }
                    }
                }
                mainWindow.setText(printScreen(action));
                userAnswer.setEditable(true);                
            }
        });
    }
    
    private void preStart(){
        actionId = "1";
        fileId = "1";
        newActionId = "1";
        newFileId = "1";
        health = 100;
        parseFile = new ParseFile();
        mapActions = parseFile.parse(mapFiles.get(fileId));
        action = mapActions.get(actionId);
        mainWindow.setText(printScreen(action));
    }
    
    private String printScreen(Action action){        
        StringBuilder strAction = new StringBuilder();
        strAction.append(action.getText()).append("\n").append("\n");
        
        Map<String, Var> varMap = action.getMapVar();
        if(varMap != null && varMap.size() > 0){
            for(Map.Entry<String, Var> entry : varMap.entrySet()){
                Var var = entry.getValue();
                strAction
                        .append(var.getId())
                        .append(". ")
                        .append(var.getText())
                        .append("\n");
            }
            strAction.append("\n").append("Выберите ответ : ");
        }
        else{
            strAction.append("Вы хотите начать игру заново? Y/N : ");
        }
        return strAction.toString();
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
