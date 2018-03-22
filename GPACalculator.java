import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GPACalculator {
	/**
	 * constructor of calculator
	 */
	public GPACalculator(){
		initialize(); 
	}

	/**
	 * initialization
	 */
	public void initialize() {
		/**
		 * creating the JFrame
		 */
		JFrame frame=new JFrame("GPA Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(650, 700);
		frame.setLayout(new BorderLayout());

		/**
		 * panels
		 */
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		JPanel panel3=new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

		/**
		 * JTable
		 */
		String[] colHeadings = {"Course","Credit","Grade"};
		int numRows = 5 ;
		DefaultTableModel model = new DefaultTableModel(numRows, colHeadings.length) ;
		model.setColumnIdentifiers(colHeadings);
		JTable table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);
		panel1.add(scroll);

		/**
		 * grade arrays
		 */
		String[] gradeoptions = {"A+","A","A-","B+","B","B-","C+","C","C-",
				"D+","D","D-"};
		double[] gradeoptions2= {4.0,4.0,3.7,3.3,3,2.7,2.3,2,1.7,1.3,1,0.7,0};

		/**
		 * label
		 */
		JLabel currentgpa = new JLabel("Current GPA:");
		JLabel targetgpa = new JLabel("Target GPA:");
		JLabel requiredgpa = new JLabel("Required GPA:");
		String note="";

		/**
		 * test field
		 */
		JTextField target=new JTextField(5);

		/**
		 * buttons
		 */
		JButton calculate=new JButton("Calculate");
		JButton reset=new JButton("Reset all");
		JButton quickadd=new JButton("Quick Add");
		JButton addrow=new JButton("Add Row");
		JButton deleterow=new JButton("Delete Row");

		/**
		 * adding labels,textfield, and button to panel2
		 */
		panel2.add(calculate);
		panel2.add(reset);
		panel2.add(quickadd);
		panel2.add(addrow);
		panel2.add(deleterow);
		
		/**
		 * comments
		 */
		String comment="";
		JLabel comments= new JLabel("Advice:  "+note);
		panel3.add(comments);
		panel3.add(currentgpa);
		panel3.add(targetgpa);
		panel3.add(target);
		panel3.add(requiredgpa);

		/**
		 * add panel1 and panel2 to the frame
		 */
		panel1.add(panel2);
		frame.add(panel1, BorderLayout.PAGE_START);
		frame.add(panel3, BorderLayout.CENTER);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GPACalculator();
			}
		});
	}


}
