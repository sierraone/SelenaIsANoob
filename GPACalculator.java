import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GPACalculator {
	/**
	 * Create all elements in the frame, including an arrayList with the letter grades.
	 */
	JLabel commentLabel= new JLabel("Tips:");
	JLabel commentText= new JLabel();
	JTextField targetInput=new JTextField();
	JLabel currentGPAText = new JLabel();
	JLabel currentGPALabel = new JLabel("Current GPA:");
	JLabel targetGPALabel = new JLabel("Target GPA:");
	JLabel requiredGPALabel = new JLabel("Required GPA:");
	JLabel requiredGPAText = new JLabel();
	JButton calculateButton=new JButton("Calculate");
	JButton resetButton=new JButton("Reset All");
	JButton quickAddButton=new JButton("Quick Add");
	JButton addRowButton=new JButton("Add Row");
	JButton deleteRowButton=new JButton("Delete Row");
	String[] letters = {"A","A-","B+","B","B-","C+","C","C-","D+","D","D-","F"};
	double[] values = {4,3.7,3.3,3,2.7,2.3,2,1.7,1.3,1,0.7,0};
	ArrayList<String> letterGrades = new ArrayList<>(Arrays.asList(letters));
	int futureCredits = 0;
	int currentCredits = 0;
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
	 * JTable is created using a DefaultTableModel.
	 * Minimum size set to 600x600 to meet low-resolution requirements.
	 */
	public void initialize() {
		//JFrame
		JFrame calcFrame=new JFrame("GPA Calculator");
		calcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		calcFrame.setSize(600, 600);
		calcFrame.setMinimumSize(new Dimension(600,600));
		calcFrame.setLayout(new BorderLayout());
		//JPanels
		JPanel panel1=new JPanel();
		panel1.setLayout(new GridBagLayout());
		JPanel panel2=new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS ));
		JPanel panel3=new JPanel();
		panel3.setLayout(new GridBagLayout());
		//JTable
		String[] colHeadings = {"Course","Credit","Grade"};
		DefaultTableModel model = new DefaultTableModel(0, colHeadings.length) ;
		model.setColumnIdentifiers(colHeadings);
		JTable table = new JTable(model);
		GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 0;
		JScrollPane scroll = new JScrollPane(table);
		panel1.add(scroll);


		/**
		 * Elements are added to Panel3, with left alignment.
		 */
		gridConstraints.ipadx = 10;
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 0;
		gridConstraints.anchor = GridBagConstraints.LINE_START;
		panel3.add(currentGPALabel,gridConstraints);
		gridConstraints.gridx = 1;
		panel3.add(currentGPAText,gridConstraints);
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 1;
		panel3.add(targetGPALabel,gridConstraints);
		gridConstraints.gridx = 1;
		panel3.add(targetInput,gridConstraints);
		targetInput.setColumns(3);
		gridConstraints.gridy = 2;
		gridConstraints.gridx = 0;
		panel3.add(requiredGPALabel,gridConstraints);
		gridConstraints.gridx = 1;
		panel3.add(requiredGPAText,gridConstraints);
		gridConstraints.gridy = 3;
		gridConstraints.gridx = 0;
		panel3.add(commentLabel,gridConstraints);
		gridConstraints.gridx = 1;
		panel3.add(commentText,gridConstraints);
		
		/**
		 * Elements are added to Panel2 (Side Bar)
		 */
			
		panel2.add(calculateButton);
		panel2.add(resetButton);
		panel2.add(quickAddButton);
		panel2.add(addRowButton);
		panel2.add(deleteRowButton);

		//disable some buttons on startup
		emptyToggle(true);
		
		/**
		 * Action Listeners for all Buttons in Panel2
		 */
		addRowButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				emptyToggle(false);
				addTableRow(table);

			}
		});

		deleteRowButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				deleterow(table);

			}
		});

		quickAddButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				emptyToggle(false);
				quickadd(table);

			}
		});

		resetButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				emptyToggle(true);
				resetall(table);

			}
		});

		calculateButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				TableCellEditor editor = table.getCellEditor();
				if (editor != null) {
				  editor.stopCellEditing();
				}
				ArrayList<Object[]> data = getalldata(table);
				if (!checkEmpty(data)) {
					double currentGPA = calculate(data);
					currentGPAText.setText(String.format("%.2f",(currentGPA)));
					if (!targetInput.getText().isEmpty()) {
					double targetGPA = Double.parseDouble(targetInput.getText().toString());
					if (currentGPA>=targetGPA) {
						commentText.setText("Congratulations! You've reached your GPA goal!");
					}
					else
					{
						double requiredGPA = (targetGPA*(currentCredits+futureCredits)-currentGPA*(currentCredits))/futureCredits; 
						requiredGPAText.setText(String.format("%.2f",requiredGPA));
						if (requiredGPA<=2.0) {
							commentText.setText("You can maybe take fewer credits if you wish.");
						}
						else if(requiredGPA>4.0) {
							commentText.setText("Required GPA is above 4.0, add more credits and recalculate.");
						}
						else {
							commentText.setText("You need a perfect 4.0 to reach your goal!");
						}
					}
				}
				}

			}
		});

		/**
		 * Add Panel1 to JFrame
		 * Add Panel2 and Panel3 to Panel1
		 */
		calcFrame.add(panel1);
		gridConstraints.gridx = 2;
		gridConstraints.gridy = 0;
		panel1.add(panel2,gridConstraints);
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 2;
		gridConstraints.anchor = GridBagConstraints.LAST_LINE_START;
		panel1.add(panel3,gridConstraints);
		calcFrame.setVisible(true);
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
	public void setComment(String comment, Color color) {
		commentText.setText(comment);
		commentText.setForeground(color);
	}
	
	/**
	 * Empty List Button Disabler/Enabler
	 * Depending on the status (true for empty list, false otherwise) will set state of buttons.
	 */
	public void emptyToggle(boolean status) {
		calculateButton.setEnabled(!status);
		deleteRowButton.setEnabled(!status);
		resetButton.setEnabled(!status);
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
		currentCredits = 0;
		futureCredits = 0;
		for(int j=0;j<data.size();j++) {
			if (!data.get(j)[2].toString().isEmpty()) {
			currentGPA+=Double.parseDouble(data.get(j)[1].toString())*values[letterGrades.indexOf(data.get(j)[2])];
			currentCredits+=Double.parseDouble(data.get(j)[1].toString());
			}
			else {
				futureCredits+=Double.parseDouble(data.get(j)[1].toString());
			}
			
			
		}
		return (double) currentGPA/currentCredits;
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
