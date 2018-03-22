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
		frame.setSize(600, 600);
		frame.setMinimumSize(new Dimension(600,600));
		frame.setLayout(new BorderLayout());

		/**
		 * panels
		 */
		JPanel panel1=new JPanel();
		panel1.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JPanel panel2=new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS ));
		JPanel panel3=new JPanel();
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

		/**
		 * Jtable
		 */
		String[] colHeadings = {"Course","Credit","Grade"};
		int numRows = 5 ;
		DefaultTableModel model = new DefaultTableModel(numRows, colHeadings.length) ;
		model.setColumnIdentifiers(colHeadings);
		JTable table = new JTable(model);

		/**
		 * scroll
		 */
		c.gridx = 0;
		c.gridy = 0;
		JScrollPane scroll = new JScrollPane(table);
		panel1.add(scroll);

		/**
		 * grade arrays
		 */
		String[] gradeoptions = {"A+","A","A-","B+","B","B-","C+","C","C-",
				"D+","D","D-"};
		double[] gradeoptions2= {4.0,4.0,3.7,3.3,3,2.7,2.3,2,1.7,1.3,1,0.7,0};


		/**
		 * buttons for panel2
		 */
		JButton calculate=new JButton("Calculate");
		JButton reset=new JButton("Reset all");
		JButton quickadd=new JButton("Quick Add");
		JButton addrow=new JButton("Add Row");
		JButton deleterow=new JButton("Delete Row");

		/**
		 * adding buttons to panel2
		 */
		panel2.add(calculate);
		panel2.add(reset);
		panel2.add(quickadd);
		panel2.add(addrow);
		panel2.add(deleterow);



		/**
		 * comments for panel3
		 */
		String comment="";
		JLabel comments= new JLabel("Advice:  "+comment);

		/**
		 * labels for panel3
		 */
		JLabel currentgpa = new JLabel("Current GPA:     ");
		JLabel targetgpa = new JLabel("Target GPA: ");
		JLabel requiredgpa = new JLabel("Required GPA:     ");

		/**
		 * adding labels and comments to panel3
		 */
		panel3.add(currentgpa);
		panel3.add(targetgpa);
		panel3.add(requiredgpa);
		panel3.add(comments);

		/**
		 * add panel1 to the frame, and panel2 and panel3 to panel1
		 */
		frame.add(panel1);
		c.gridx = 2;
		c.gridy = 0;
		panel1.add(panel2,c);
		c.gridx = 0;
		c.gridy = 2;
		panel1.add(panel3,c);
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
