package project;

import java.awt.EventQueue;

public class Main { 
		    /** Creates a new instance of Main */
		    public Main() {}
		    
		    public static void main(String[] args) {
				  EventQueue.invokeLater(new Runnable() { 
					  public void run()
					  {
						  new App();
					  }
					  });
				 		  
		    	}

}
