package farklescene;
import java.util.ArrayList;

/**
 * @author Wilson Krueger
 */

public class Dice {
    public boolean success;
    public ArrayList<Die> dice;
    
    public Dice() {
        success = true;
        dice = new ArrayList();
        Die die1 = new Die();
        Die die2 = new Die();
        Die die3 = new Die();
        Die die4 = new Die();
        Die die5 = new Die();
        Die die6 = new Die();
        
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);
        dice.add(die4);
        dice.add(die5);
        dice.add(die6);
    }
    
    public boolean getSuccess() {
        return success;
    }
    
    public ArrayList getDice() {
        return dice;
    }
    
    @Override
    public String toString() {
        return "Dice{" + "dice=" + dice.size() + '}';
    }
}