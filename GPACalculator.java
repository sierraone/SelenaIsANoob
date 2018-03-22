import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class c {
	/**
	 * constructor of calculator
	 */
	public c(){
		initialize(); 
	}

	/**
	 * initialization
	 */
	public void initialize() {
		/**
		 * creating the JFrame
		 */
		JFrame f=new JFrame(" BoxLayout tutorial");
		f.setTitle("GPA Calculator");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(650, 700);
		f.setLayout(new FlowLayout());
		JPanel p=new JPanel();

		JTextField course1=new JTextField(7);
		JTextField credit1=new JTextField(3);
		JTextField grade1=new JTextField(3);
		JButton reset1=new JButton("Reset");

		JTextField course2=new JTextField(7);
		JTextField credit2=new JTextField(3);
		JTextField grade2=new JTextField(3);
		JButton reset2=new JButton("Reset");

		/**
		 * Jtable
		 */
		String[] columnNames= {"Course","Credit Hour","Grade", "  "};
		Object [][] data= {{course1,credit1,grade1,reset1},
				{course2,credit2,grade2,reset2}
		};
		JTable table=new JTable(data,columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		p.add(table);


		/**
		 * grade arrays
		 */
		String[] gradeoptions = {"A+","A","A-","B+","B","B-","C+","C","C-",
				"D+","D","D-"};
		double[] gradeoptions2= {4.0,4.0,3.7,3.3,3,2.7,2.3,2,1.7,1.3,1,0.7,0};
		
		/**
		 * panel at the bottom
		 */
		JPanel pbottom=new JPanel();
		pbottom.setLayout(new BoxLayout(pbottom, BoxLayout.LINE_AXIS ));
		JButton calculate=new JButton("Calculate");
		JLabel currentgpa = new JLabel("Current GPA:      ");
		JLabel targetgpa = new JLabel("Target GPA: ");
		JTextField target=new JTextField(3);
		JLabel requiredgpa = new JLabel("Required GPA:      ");
		String note="";
		JLabel a = new JLabel("Advice:  "+note);
		JButton reset=new JButton("Reset all");
		JButton a15=new JButton("Add 15 Courses");
		JButton a1=new JButton("Add 1 course");
		JButton d1=new JButton("Delete 1 course");
		
		
		/**
		 * adding labels,textfield, and button to pbottom
		 */
		pbottom.add(calculate);
		pbottom.add(currentgpa);
		pbottom.add(targetgpa);
		pbottom.add(target);
		pbottom.add(requiredgpa);
		pbottom.add(a);
		pbottom.add(reset);
		pbottom.add(a15);
		pbottom.add(a1);
		pbottom.add(d1);

		/**
		 * add panel 1 and panel 2 to the frame
		 */
		f.add(p,BorderLayout.NORTH);
		f.add(pbottom,BorderLayout.SOUTH);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new c();
			}
		});
	}


}
