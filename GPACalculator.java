
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GPACalculator {
	JLabel commentLabel= new JLabel("Advice:");
	JLabel comments= new JLabel();
	JTextField goalInput=new JTextField();
	JLabel currentgpa = new JLabel();
	JLabel currentgpaLabel = new JLabel("Current GPA:");
	JLabel targetgpa = new JLabel("Target GPA:");
	JLabel requiredgpa = new JLabel("Required GPA:");
	JButton calculate=new JButton("Calculate");
	JButton reset=new JButton("Reset All");
	JButton quickadd=new JButton("Quick Add");
	JButton addrow=new JButton("Add Row");
	JButton deleterow=new JButton("Delete Row");
	String[] letters = {"A","A-","B+","B","B-","C+","C","C-","D+","D","D-","F"};
	double[] values = {4,3.7,3.3,3,2.7,2.3,2,1.7,1.3,1,0.7,0};
	ArrayList<String> letterGrades = new ArrayList<>(Arrays.asList(letters));
	/**
	 * Calculator constructor
	 */
	public GPACalculator(){
		initialize(); 
	}

	/**
	 * Initialization
	 * 
	 * JFrame and 3 panels are created with corresponding layouts.
	 * JTable is created using a DefaultTableModel
	 */
	public void initialize() {
		//JFrame
		JFrame frame=new JFrame("GPA Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setMinimumSize(new Dimension(600,600));
		frame.setLayout(new BorderLayout());
		//JPanels
		JPanel panel1=new JPanel();
		panel1.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JPanel panel2=new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS ));
		JPanel panel3=new JPanel();
		panel3.setLayout(new GridBagLayout());
		//JTable
		String[] colHeadings = {"Course","Credit","Grade"};
		int numRows = 0 ;
		DefaultTableModel model = new DefaultTableModel(numRows, colHeadings.length) ;
		model.setColumnIdentifiers(colHeadings);
		JTable table = new JTable(model);
		c.gridx = 0;
		c.gridy = 0;
		JScrollPane scroll = new JScrollPane(table);
		panel1.add(scroll);


		/**
		 * Elements are added to Panel3 (bottom aligned)
		 */
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		panel3.add(currentgpaLabel,c);
		c.gridx = 1;
		panel3.add(currentgpa,c);
		c.gridx = 0;
		c.gridy = 1;
		panel3.add(targetgpa,c);
		c.gridx = 1;
		panel3.add(goalInput,c);
		goalInput.setColumns(3);
		c.gridy = 2;
		c.gridx = 0;
		panel3.add(requiredgpa,c);
		c.gridy = 3;
		panel3.add(commentLabel,c);
		c.gridx = 1;
		panel3.add(comments,c);
		
		/**
		 * Elements are added to Panel2 (Side Bar)
		 */
		
		emptyToggle(true);
		
		panel2.add(calculate);
		panel2.add(reset);
		panel2.add(quickadd);
		panel2.add(addrow);
		panel2.add(deleterow);

		/**
		 * Action Listeners for all Buttons in Panel2
		 */
		addrow.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				emptyToggle(false);
				addTableRow(table);

			}
		});

		deleterow.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				deleterow(table);

			}
		});

		quickadd.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				emptyToggle(false);
				quickadd(table);

			}
		});

		reset.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				emptyToggle(true);
				resetall(table);

			}
		});

		calculate.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				TableCellEditor editor = table.getCellEditor();
				if (editor != null) {
				  editor.stopCellEditing();
				}
				double target = 0;
				if (!goalInput.getText().isEmpty()) target=Double.parseDouble(goalInput.getText());
				ArrayList<Object[]> data = getalldata(table);
				if (!checkEmpty(data)) {
					double currentGPA = calculate(data);
					currentgpa.setText(String.format("%.2f",(currentGPA)));
				}

			}
		});

		/**
		 * Add Panel1 to JFrame
		 * Add Panel2 and Panel3 to Panel1
		 */
		frame.add(panel1);
		c.gridx = 2;
		c.gridy = 0;
		panel1.add(panel2,c);
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LAST_LINE_START;
		panel1.add(panel3,c);
		frame.setVisible(true);
	}

	/**
	 * Method to add 1 row to table
	 */
	public static void addTableRow(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[]{"", "", ""});
	}

	/**
	 * Method to deleted selected rows
	 */
	public void deleterow(JTable table){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int[] rows = table.getSelectedRows();
		for(int i=0;i<rows.length;i++){
			model.removeRow(rows[i]-i);
		}
		if (model.getRowCount()==0) emptyToggle(true);
	}

	/**
	 * Method to add 15 rows to table
	 */
	public static void quickadd(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for (int i=0;i<15;i++) {
			model.addRow(new Object[]{"", "", ""});
		}
	}

	/**
	 * Method to clear table
	 */
	public static void resetall(JTable table) {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		int rowCount = dm.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			dm.removeRow(i);
		}
	}
	
	/**
	 * Setter for comments
	 * This method is overloaded so specifying color is not required
	 */
	public void setComment(String commentText) {
		setComment(commentText, Color.black);
	}
	public void setComment(String commentText, Color color) {
		comments.setText(commentText);
		comments.setForeground(color);
	}
	
	/**
	 * Empty List Button Disabler/Enabler
	 * Depending on the status (true for empty list, false otherwise) will set state of buttons.
	 */
	public void emptyToggle(boolean status) {
		calculate.setEnabled(!status);
		deleterow.setEnabled(!status);
		reset.setEnabled(!status);
	}

	public boolean checkEmpty(ArrayList<Object[]> data) {
		for (Object[] row : data)
		{
			if (isMissing(row)) 
			{
				setComment("One or more rows is missing field \"Credit Hours\"",Color.red);
				return true;
			}
		}
		return false;
	}

	/**
	 * method for getting all the data
	 */
	public ArrayList<Object[]> getalldata (JTable table) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		int nRow = dtm.getRowCount();
		ArrayList<Object[]> tableData = new ArrayList<Object[]>();
		for (int i = 0 ; i < nRow ; i++)
		{
			Object[] row = {dtm.getValueAt(i, 0),dtm.getValueAt(i, 1),dtm.getValueAt(i, 2)};
				tableData.add(row);
		}
		return tableData;
	}
	

	/**
	 * calculating GPA
	 */
	public Double calculate (ArrayList<Object[]> data) {
		Double currentGPA=0.0;
		int credithours=0;
		for(int j=0;j<data.size();j++) {
			currentGPA+=Double.parseDouble(data.get(j)[1].toString())*values[letterGrades.indexOf(data.get(j)[2])];
			credithours+=Double.parseDouble(data.get(j)[1].toString());
		}
		return (double) currentGPA/credithours;
	}
    public static boolean isMissing (Object[] row)
    {
    	return (row[1].toString().trim().equals(""));
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
