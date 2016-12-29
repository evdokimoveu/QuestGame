
package questgame.model;

import java.util.Objects;

/**
 *
 * @author evdokimoveu
 */
public class Var {
    
    private String id;
    private String text;
    private String moveTo;
    private String damage;

    public Var() {
    }

    public Var(String id, String text, String moveTo, String damage) {
        this.id = id;
        this.text = text;
        this.moveTo = moveTo;
        this.damage = damage;
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

    public String getMoveTo() {
        return moveTo;
    }

    public void setMoveTo(String moveTo) {
        this.moveTo = moveTo;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.text);
        hash = 89 * hash + Objects.hashCode(this.moveTo);
        hash = 89 * hash + Objects.hashCode(this.damage);
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
        final Var other = (Var) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        if (!Objects.equals(this.moveTo, other.moveTo)) {
            return false;
        }
        if (!Objects.equals(this.damage, other.damage)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Var{" + "id=" + id + ", text=" + text + ", moveTo=" + moveTo + ", damage=" + damage + '}';
    }
    
    
}
