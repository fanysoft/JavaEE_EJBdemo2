package bean;

import javax.ejb.Remote;
import javax.ejb.Stateless;



@Stateless
@Remote(CalculatorRemote.class)
public class CalculatorBean implements CalculatorRemote {

	   @Override
	    public int add(int a, int b) {
	        return a + b;
	    }
	 
	    @Override
	    public int subtract(int a, int b) {
	        return a - b;
	    }
	}
