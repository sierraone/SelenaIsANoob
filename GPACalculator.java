import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

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
		DefaultTableModel model = new DefaultTableModel(0, colHeadings.length) ;
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
		  
		  calculate.addActionListener(new ActionListener() 
		  {
			 @Override
			 public void actionPerformed(ActionEvent event)
			 {
				 System.out.println(Arrays.deepToString(getData(table)));
			 }
			 
		  });
		  addrow.addActionListener(new ActionListener()
				  		{
				  			@Override
				  			public void actionPerformed(ActionEvent event)
				  			{
				  				addTableRow(table);
				  
				  			}
				  		});
		  quickadd.addActionListener(new ActionListener()
	  		{
	  			@Override
	  			public void actionPerformed(ActionEvent event)
	  			{
	  				for (int j=0;j<15;j++) {
	  				addTableRow(table);
	  				}
	  			}
	  		});
		  reset.addActionListener(new ActionListener()
	  		{
	  			@Override
	  			public void actionPerformed(ActionEvent event)
	  			{
	  				resetTable(table);
	  			}
	  		});
		  
		 deleterow.addActionListener(new ActionListener()
	  		{
	  			@Override
	  			public void actionPerformed(ActionEvent event)
	  			{
	  				removeSelectedRows(table);
	  
	  			}
	  		});
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
		 * text field for panel3
		 */
		JTextField targettextfield=new JTextField(5);

		/**
		 * adding labels and comments to panel3
		 */
		panel3.add(currentgpa);
		panel3.add(targetgpa);
		panel3.add(targettextfield);
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
	
	public Object[][] getData (JTable table) {
		    DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
		    Object[][] tableData = new Object[nRow][nCol];
		    for (int i = 0 ; i < nRow ; i++)
		        for (int j = 0 ; j < nCol ; j++)
		            tableData[i][j] = dtm.getValueAt(i,j);
		    return tableData;
	}
	public static void addTableRow(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[]{"", "", ""});
	}
	
	public static void removeSelectedRows(JTable table){
		   DefaultTableModel model = (DefaultTableModel) table.getModel();
		   int[] rows = table.getSelectedRows();
		   for(int i=0;i<rows.length;i++){
		     model.removeRow(rows[i]-i);
		   }
		}
	public static void resetTable(JTable table) {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		  int rowCount = dm.getRowCount();
		  //Remove rows one by one from the end of the table
		  for (int i = rowCount - 1; i >= 0; i--) {
		      dm.removeRow(i);
		  }
	}
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GPACalculator();
			}
		});
	}
	


}
