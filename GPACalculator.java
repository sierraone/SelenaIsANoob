import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class GPACalculator {
	/**
	 * constructor of calculator
	 */
	public GPACalculator(){
		/**
		 * creating the JFrame
		 */
		JFrame calcFrame=new JFrame("GPA Calculator");
		calcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		calcFrame.setSize(650, 700);
		calcFrame.setLayout(new FlowLayout());
		JPanel tablePanel=new JPanel();

		/**
		 * Jtable
		 */
		
		String[] colHeadings = {"Course","Credit","Grade"," Reset Row"};
		int numRows = 1;
		DefaultTableModel model = new DefaultTableModel(numRows, colHeadings.length) ;
		model.setColumnIdentifiers(colHeadings);
		JTable calcTable = new JTable(model);
		JScrollPane scrollTable = new JScrollPane(calcTable);
		tablePanel.add(scrollTable);
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
		calcFrame.add(tablePanel,BorderLayout.NORTH);
		calcFrame.add(pbottom,BorderLayout.SOUTH);
		calcFrame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GPACalculator();
			}
		});
	}


}
