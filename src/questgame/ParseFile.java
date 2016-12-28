
package questgame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import questgame.model.Action;
import questgame.model.Var;

/**
 *
 * @author evdokimoveu
 */
public class ParseFile {
    
    private Map<String, Action> mapAction;

    public ParseFile() {        
    }
    
    public Map<String, Action> parse(File file){
        try {
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(new FileInputStream(file));
            Element root = doc.getRootElement();
            getActionMap(root.getChildren());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ParseFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(ParseFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapAction;
        
    }
    
    private Map<String, Action> getActionMap(List<Element> actions){
        mapAction = new HashMap<>();
        for(int i = 0; i < actions.size(); i++){
            Element elAction = actions.get(i);
            Action action = new Action(
                    elAction.getAttributeValue("id"),
                    elAction.getAttributeValue("text"),
                    getVarMap(elAction.getChildren())
            );
            mapAction.put(elAction.getAttributeValue("id"), action);
        }        
        return mapAction;
    }
    
    private Map<String, Var> getVarMap(List<Element> vars){
        Map<String, Var> mapVar = new HashMap<>();
        if(vars != null && vars.size() > 0){
            for(int i = 0; i < vars.size(); i++){
                Element elVar = vars.get(i);
                Var var = new Var(
                        elVar.getAttributeValue("id"),
                        elVar.getText(),
                        elVar.getAttributeValue("move_to"),
                        elVar.getAttributeValue("damage"),
                        elVar.getAttributeValue("result"));
                mapVar.put(elVar.getAttributeValue("id"), var);
            }
        }        
        return mapVar;
    }
    
}