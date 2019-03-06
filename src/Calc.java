import javax.swing.SwingUtilities;

public class Calc {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new  Runnable() {
		      public void run() {
		        new  Gui();
		 
		      }
		    });  

	}

}
