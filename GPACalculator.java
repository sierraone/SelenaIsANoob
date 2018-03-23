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
		 * comments for panel3
		 */
		String comment="";
		JLabel comments= new JLabel("Advice:  "+comment);

		/**
		 * text box for panel3
		 */
		JTextField a=new JTextField();
		
		/**
		 * labels for panel3
		 */
		JLabel currentgpa = new JLabel("Current GPA:     "+" ");
		JLabel targetgpa = new JLabel("Target GPA: ");
		JLabel requiredgpa = new JLabel("Required GPA:     ");
		JLabel instruction=new JLabel("Instructions:");
		JLabel instruction1=new JLabel("1.Click on the table above to add courses.");
		JLabel instruction2=new JLabel("2.Manually add rows after reseting all.");

		/**
		 * adding labels and comments to panel3
		 */
		panel3.add(currentgpa);
		panel3.add(targetgpa);
		panel3.add(a);
		panel3.add(requiredgpa);
		panel3.add(comments);
		panel3.add(instruction);
		panel3.add(instruction1);
		panel3.add(instruction2);
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
		 * add row action listener
		 */
		addrow.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				addTableRow(table);

			}
		});

		/**
		 * delete row action listener
		 */
		deleterow.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				deleterow(table);

			}
		});

		/**
		 * quick add action listener
		 */
		quickadd.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				quickadd(table);

			}
		});

		/**
		 *  reset all action listener
		 */
		reset.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				resetall(table);

			}
		});

		/**
		 * calculate action listener
		 */
		calculate.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				calculate(table);
				currentgpa.setText("Current GPA:     "+calculate(table));

			}
		});


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
		
		/**
		 * to calculate required gpa
		 */
		double target=Double.parseDouble(a.getText());
		double required=target-calculate(table);
		//if required is more than 4.0
		if (required>4.0) {
			comment="Try adding more credit hours and recalculating";
			comments.setText("Advice:  "+comment);
		}
		//if require is less than 2 
		if (required<2.0) {
			comment="Take fewer credit hours if they wish.";
			comments.setText("Advice:  "+comment);
		}
		
	}

	/**
	 * add row method
	 */
	public static void addTableRow(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[]{"", "", ""});
	}

	/**
	 * delete row method
	 */
	public void deleterow(JTable table){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int[] rows = table.getSelectedRows();
		for(int i=0;i<rows.length;i++){
			model.removeRow(rows[i]-i);
		}
	}

	/**
	 * quick add method
	 */
	public static void quickadd(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for (int i=0;i<15;i++) {
			model.addRow(new Object[]{"", "", ""});
		}
	}

	/**
	 * reset all method
	 */
	public static void resetall(JTable table) {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		int rowCount = dm.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			dm.removeRow(i);
		}
	}

	/**
	 * method for getting all the data
	 */
	public Object[][] getalldata (JTable table) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
		Object[][] tableData = new Object[nRow][nCol];
		for (int i = 0 ; i < nRow ; i++)
			for (int j = 0 ; j < nCol ; j++)
				tableData[i][j] = dtm.getValueAt(i,j);

		return tableData;
	}


	/**
	 * calculating GPA
	 */
	public double calculate (JTable table) {
		int nRow = table.getModel().getRowCount();
		double totalgrade=0;
		double totalcredit=0;
		double currentGPA=0;
		for (int i=0;i<nRow;i++) {
			/**
			 * if the grade is A+ or A
			 */
			if(table.getModel().getValueAt(i,3).equals("A+")||table.getModel().getValueAt(i,3).equals("A")) {
				int credit=(Integer) table.getModel().getValueAt(i,2);
				totalcredit+=credit;
				totalgrade=credit*4.0;
			}
			
			/**
			 * if the grade is A-
			 */
			if(table.getModel().getValueAt(i,3).equals("A-")){
				int credit=(Integer) table.getModel().getValueAt(i,2);
				totalcredit+=credit;
				totalgrade=credit*3.7;
			}
			/**
			 * if the grade is B+
			 */
			if(table.getModel().getValueAt(i,3).equals("B+")) {
				int credit=(Integer) table.getModel().getValueAt(i,2);
				totalcredit+=credit;
				totalgrade=credit*3.3;
			}
			/**
			 * if the grade is B
			 */
			if(table.getModel().getValueAt(i,3).equals("B")) {
				int credit=(Integer) table.getModel().getValueAt(i,2);
				totalcredit+=credit;
				totalgrade=credit*3.0;
			}
			/**
			 * if the grade is B-
			 */
			if(table.getModel().getValueAt(i,3).equals("B-")) {
				int credit=(Integer) table.getModel().getValueAt(i,2);
				totalcredit+=credit;
				totalgrade=credit*2.7;
			}
			/**
			 * if the grade is C+
			 */
			if(table.getModel().getValueAt(i,3).equals("C+")) {
				int credit=(Integer) table.getModel().getValueAt(i,2);
				totalcredit+=credit;
				totalgrade=credit*2.3;
			}
			/**
			 * if the grade is C
			 */
			if(table.getModel().getValueAt(i,3).equals("C")) {
				int credit=(Integer) table.getModel().getValueAt(i,2);
				totalcredit+=credit;
				totalgrade=credit*2.0;
			}
			/**
			 * if the grade is C-
			 */
			if(table.getModel().getValueAt(i,3).equals("C-")) {
				int credit=(Integer) table.getModel().getValueAt(i,2);
				totalcredit+=credit;
				totalgrade=credit*1.7;
			}
			/**
			 * if the grade is D+
			 */
			if(table.getModel().getValueAt(i,3).equals("D+")) {
				int credit=(Integer) table.getModel().getValueAt(i,2);
				totalcredit+=credit;
				totalgrade=credit*1.3;
			}
			/**
			 * if the grade is D
			 */
			if(table.getModel().getValueAt(i,3).equals("D")) {
				int credit=(Integer) table.getModel().getValueAt(i,2);
				totalcredit+=credit;
				totalgrade=credit*1.0;
			}
			/**
			 * if the grade is D-
			 */
			if(table.getModel().getValueAt(i,3).equals("D-")) {
				int credit=(Integer) table.getModel().getValueAt(i,2);
				totalcredit+=credit;
				totalgrade=credit*0.7;
			}
			/**
			 * if the grade is F
			 */
			if(table.getModel().getValueAt(i,3).equals("F")) {
				int credit=(Integer) table.getModel().getValueAt(i,2);
				totalcredit+=credit;
				totalgrade=credit*0.0;
			}
		}
		currentGPA=totalgrade/totalcredit;
		return currentGPA;
	}


	/**
	 *main method
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GPACalculator();
			}
		});
	}


}
