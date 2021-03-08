package farklescene;

/*
 * @author Wilson Krueger
 */

public class Die {
    
    public int value;
    private int worth;
    
    Die() {
        value = 0;
        worth = 0;
    }
    
    public int getValue() {
        return value;
    } 
    
    public int getWorth() {
        if (value == 1)
        {
            worth = 100;
        }
        
        else if (value == 5)
        {
            worth = 50;
        }
        
        else 
        {
            worth = 0;
        }
        
        return worth;
    }
      
}

