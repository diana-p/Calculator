import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

public class Gui {
	
	private JTextField input = new JTextField();
	private JLabel output = new JLabel("Ответ: ");
	private JTextField input1 = new JTextField();
	private JButton b1 = new JButton("=");
	private JButton b2 = new JButton("+");
	private JButton b3 = new JButton("-");
	private JButton b4 = new JButton("*");
	private JButton b5 = new JButton("/");
	private JButton b6 = new JButton("C");
	private JButton[] mas = new JButton[10];
	private JButton skob1 = new JButton("(");
	private JButton skob2 = new JButton(")");
	
	Gui(){
		
		JFrame jfrm = new JFrame("Калькулятор");
		jfrm.setSize (400, 350);
		jfrm.setLocationRelativeTo(null);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel p = new JPanel(); 
		jfrm.add(p); 
		p.setLayout(new BorderLayout());
		
		JPanel p2 = new JPanel();
		p.add(p2, BorderLayout.NORTH);
		p2.setLayout(new BoxLayout(p2, BoxLayout.PAGE_AXIS));
		p2.add(input);
		p2.add(output);
		p2.add(input1);
		
		ActionListener actionListener = new TestActionListener();
		
		JPanel ps = new JPanel();
		p.add(ps, BorderLayout.WEST);
		ps.setLayout(new GridLayout(2,1));
		ps.add(skob1);
		ps.add(skob2);
		skob1.addActionListener(actionListener);
		skob2.addActionListener(actionListener);
		
		JPanel p1 = new JPanel(); 
		p.add(p1, BorderLayout.CENTER); 
		p1.setLayout(new GridLayout(4,3));
		
		
		for (int i=9; i>=0; i--) {
			Integer name = i;
			mas[i] = new JButton(name.toString());
			mas[i].addActionListener(actionListener);
			p1.add(mas[i]);
		}
		
		p1.add(b1);
		p1.add(b6);
		b1.addActionListener(actionListener);
		b6.addActionListener(actionListener);
		
		JPanel p3 = new JPanel(); 
		p.add(p3, BorderLayout.EAST); 
		p3.setLayout(new GridLayout(4,1));
		p3.add(b2);
		p3.add(b3);
		p3.add(b4);
		p3.add(b5);
		b2.addActionListener(actionListener);
		b3.addActionListener(actionListener);
		b4.addActionListener(actionListener);
		b5.addActionListener(actionListener);
		
		
		
		jfrm.setVisible(true);
	}
	
	
	public class TestActionListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	    	
	    	if (e.getSource() == b1) {
	    		
	    		double answer = inputString(input.getText());
	    		input1.setText(Double.toString(answer));
	    		
	    	}
	    	if (e.getSource() == b2) {	
	    		input.setText(input.getText() + "+");
	    	}
	    	if (e.getSource() == b3) {
	    		input.setText(input.getText() + "-");
	    	}
	    	if (e.getSource() == b4) {
	    		input.setText(input.getText() + "*");
	    	}
	    	if (e.getSource() == b5) {
	    		input.setText(input.getText() + "/");
	    	}
	    	if (e.getSource() == b6) {
	    		input.setText("");
	    	}
	    	if (e.getSource() == skob1) {
	    		input.setText(input.getText() + "(");
	    	}
	    	if (e.getSource() == skob2) {
	    		input.setText(input.getText() + ")");
	    	}
	    	if (e.getSource() == mas[0]) {
	    		input.setText(input.getText() + "0");
	    	}
	    	if (e.getSource() == mas[1]) {
	    		input.setText(input.getText() + "1");
	    	}
	    	if (e.getSource() == mas[2]) {
	    		input.setText(input.getText() + "2");
	    	}
	    	if (e.getSource() == mas[3]) {
	    		input.setText(input.getText() + "3");
	    	}
	    	if (e.getSource() == mas[4]) {
	    		input.setText(input.getText() + "4");
	    	}
	    	if (e.getSource() == mas[5]) {
	    		input.setText(input.getText() + "5");
	    	}
	    	if (e.getSource() == mas[6]) {
	    		input.setText(input.getText() + "6");
	    	}
	    	if (e.getSource() == mas[7]) {
	    		input.setText(input.getText() + "7");
	    	}
	    	if (e.getSource() == mas[8]) {
	    		input.setText(input.getText() + "8");
	    	}	
	    	if (e.getSource() == mas[9]) {
	    		input.setText(input.getText() + "9");
	    	}	

	    	
	    }
	}
	
	
	public static Double inputString(String input) {
		Stack<Double> stn = new Stack<>();
		Stack<Character> st = new Stack<>();
		int k = 0;
		
		String s = new String();
		
		for (int i=0; i<input.length(); i++) {
			
			if (input.charAt(i) == '-' && i == 0 && input.charAt(i+1) == '(') {
				k = -1;
				continue;
			}
			if (input.charAt(i) == '-' && i == 0) {
				s+= String.valueOf(input.charAt(i));
			}
			
			if (getPriority(input.charAt(i)) == 4) {
				
				s += String.valueOf(input.charAt(i));

			}
			
			
			
			if (getPriority(input.charAt(i)) != 4) {
				if (input.charAt(i) == '-' && i == 0) {
					continue;
				}
				
				if (!s.isEmpty()) stn.push(Double.parseDouble(s));
				s = new String();
				
				if (st.isEmpty()) st.push(input.charAt(i)); 
				else {
					if ((getPriority(st.peek()) >= getPriority(input.charAt(i))) && (st.peek() != '(')){
						double a = stn.pop();
						double b = stn.pop();
						
						if (st.peek() == '+') stn.push(b+a);
						if (st.peek() == '-') stn.push(b-a);
						if (st.peek() == '*') stn.push(b*a);
						if (st.peek() == '/') stn.push(b/a);
						
						st.pop();
						st.push(input.charAt(i));
	
					} 
					
					
					if (getPriority(st.peek()) < getPriority(input.charAt(i)) && (input.charAt(i) != '(') 
							&& (input.charAt(i) != ')')){
						st.push(input.charAt(i));
					}
					
					if (st.peek() == '(') st.push(input.charAt(i));
					
					if (input.charAt(i) == '(') st.push(input.charAt(i));
					if (input.charAt(i) == ')') {
						while (st.peek() != '(') {
							double a = stn.pop();
							double b = stn.pop();
							
							if (st.peek() == '+') stn.push(b+a);
							if (st.peek() == '-') stn.push(b-a);
							if (st.peek() == '*') stn.push(b*a);
							if (st.peek() == '/') stn.push(b/a);
							
							st.pop();
							
						}
					st.pop();
					}
				}

			}
			
		}
		
		if (!s.isEmpty() ) stn.push(Double.parseDouble(s));
		
		if (k == -1) {
			
			return 0-stn.peek();
		}
		else {
			while (!st.isEmpty()) {
			double a = stn.pop();
			double b = stn.pop();
			
			if (st.peek() == '+') stn.push(b+a);
			if (st.peek() == '-') stn.push(b-a);
			if (st.peek() == '*') stn.push(b*a);
			if (st.peek() == '/') stn.push(b/a);
				
			st.pop();
				
			}
			
			return stn.peek();
		}
			
			
			
		

	}
	
	//возвращаем приоритеты операций
	public static int getPriority(char elem) {
		if (elem == '*' || elem == '/') return 3;
		else if (elem == '+' || elem == '-') return 2;
		else if (elem == '(' || elem == ')') return 5;
		else return 4;
	}
	
}


