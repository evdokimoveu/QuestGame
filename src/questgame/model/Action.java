
package questgame.model;

import java.util.Map;
import java.util.Objects;

/**
 *
 * @author evdokimoveu
 */
public class Action {
    private String id;
    private String text;
    private Map<String, Var> mapVar;

    public Action(String id, String text, Map<String, Var> mapVar) {
        this.id = id;
        this.text = text;
        this.mapVar = mapVar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, Var> getMapVar() {
        return mapVar;
    }

    public void setMapVar(Map<String, Var> mapVar) {
        this.mapVar = mapVar;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.text);
        hash = 89 * hash + Objects.hashCode(this.mapVar);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Action other = (Action) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        if (!Objects.equals(this.mapVar, other.mapVar)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Action{" + "id=" + id + ", text=" + text + ", mapVar=" + mapVar + '}';
    }
    
    
}
