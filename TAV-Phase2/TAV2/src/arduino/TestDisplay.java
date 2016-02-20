package arduino;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class TestDisplay {

	
	ReadUserInput read;
	
	private double torque, ultra, ir;
	
	public JFrame frame;
	public JTextField TSent;
	public JTextField USent;
	public JTextField ISent;
	public JTextField TRec;
	public JTextField SRec;
	private JTextField InputT;
	private JTextField InputU;
	private JTextField InputI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

	}

	/**
	 * Create the application
	 */
	public TestDisplay() {
		torque = 0.0;
		ultra = 0.0;
		ir = 0.0;
		read = new ReadUserInput();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.select"));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblUserInput = new JLabel("Input values(editable)");
		lblUserInput.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 16));
		lblUserInput.setBounds(30, 20, 250, 15);
		panel.add(lblUserInput);
		
		JLabel lblInputT = new JLabel("Torque:");
		lblInputT.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 14));
		lblInputT.setBounds(30, 49, 86, 15);
		panel.add(lblInputT);
		
		JLabel lblInputU = new JLabel("Ultra distance:");
		lblInputU.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 14));
		lblInputU.setBounds(30, 69, 133, 15);
		panel.add(lblInputU);
		
		JLabel lblInputI = new JLabel("IR distance");
		lblInputI.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 14));
		lblInputI.setBounds(30, 89, 133, 15);
		panel.add(lblInputI);
		
		JLabel lblSent = new JLabel("Sent values");
		lblSent.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 16));
		lblSent.setBounds(30, 147, 105, 15);
		panel.add(lblSent);
		
		JLabel lblTSent = new JLabel("Torque:");
		lblTSent.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 14));
		lblTSent.setBounds(30, 177, 86, 15);
		panel.add(lblTSent);
		
		JLabel lblUSent = new JLabel("Ultra distance:");
		lblUSent.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 14));
		lblUSent.setBounds(30, 197, 133, 15);
		panel.add(lblUSent);
		
		JLabel lblISent = new JLabel("IR distance");
		lblISent.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 14));
		lblISent.setBounds(30, 217, 133, 15);
		panel.add(lblISent);
		
		JLabel lblReceived = new JLabel("Received values");
		lblReceived.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 16));
		lblReceived.setBounds(240, 147, 147, 15);
		panel.add(lblReceived);
		
		JLabel lblRec = new JLabel("Torque:");
		lblRec.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 14));
		lblRec.setBounds(240, 177, 86, 15);
		panel.add(lblRec);
		
		JLabel lblSRec = new JLabel("Speed:");
		lblSRec.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 14));
		lblSRec.setBounds(240, 197, 86, 15);
		panel.add(lblSRec);
		
				
		InputT = new JTextField();
		InputT.setText("1");
		InputT.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 15));
		InputT.setColumns(10);
		InputT.setBackground(Color.WHITE);
		InputT.setBounds(166, 49, 50, 19);
		panel.add(InputT);
		
		/**
		 * Variables for torque, ultra and ir.
		 * And listeners for their respective text fields.
		 */
		
		InputT.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				double torque = read.getUserInputTorque(InputT.getText());
				System.out.println(torque);
				TSent.setText(String.valueOf(torque));
				
				setTorque(torque);
				
			}
			
		});
		
		InputU = new JTextField();
		InputU.setText("1");
		InputU.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 15));
		InputU.setColumns(10);
		InputU.setBackground(Color.WHITE);
		InputU.setBounds(166, 69, 50, 19);
		panel.add(InputU);
		
		InputU.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				double ultra = read.getUserInputUltra(InputU.getText());
				
				USent.setText(String.valueOf(ultra));
				
				setUltra(ultra);
				
			}
			
		});
		
		InputI = new JTextField();
		InputI.setText("1");
		InputI.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 15));
		InputI.setColumns(10);
		InputI.setBackground(Color.WHITE);
		InputI.setBounds(166, 89, 50, 19);
		panel.add(InputI);
		
		InputI.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				double ir = read.getUserInputIr(InputI.getText());
				
				ISent.setText(String.valueOf(ir));
				
				setIr(ir);
				
			}
			
		});
		
		TSent = new JTextField();
		TSent.setEditable(false);
		TSent.setBackground(UIManager.getColor("Button.shadow"));
		TSent.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 15));
		TSent.setBounds(166, 177, 50, 19);
		panel.add(TSent);
		TSent.setColumns(10);
		TSent.setText("1");

		
		USent = new JTextField();
		USent.setEditable(false);
		USent.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 15));
		USent.setColumns(10);
		USent.setBackground(UIManager.getColor("Button.select"));
		USent.setBounds(166, 197, 50, 19);
		panel.add(USent);
		USent.setText("1");

		
		ISent = new JTextField();
		ISent.setEditable(false);
		ISent.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 15));
		ISent.setColumns(10);
		ISent.setBackground(UIManager.getColor("Button.select"));
		ISent.setBounds(166, 217, 50, 19);
		panel.add(ISent);
		ISent.setText("1");
		
		TRec = new JTextField();
		TRec.setEditable(false);
		TRec.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 15));
		TRec.setColumns(10);
		TRec.setBackground(UIManager.getColor("Button.select"));
		TRec.setBounds(376, 177, 50, 19);
		panel.add(TRec);
		TRec.setText("1");

		
		SRec = new JTextField();
		SRec.setEditable(false);
		SRec.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 15));
		SRec.setColumns(10);
		SRec.setBackground(UIManager.getColor("Button.select"));
		SRec.setBounds(376, 197, 50, 19);
		panel.add(SRec);
		SRec.setText("1");
		
		

	}
	
	/**
	 * Getter and setter methods for torque, ultra and ir.
	 */
	
	private void setTorque(double torque) {
		this.torque = torque;
	}
	
	private void setUltra(double ultra) {
		this.ultra = ultra;
	}
	
	private void setIr(double ir) {
		this.ir = ir;
	}
	
	public double getTorque() {
		return torque;
	}
	
	public double getUltra() {
		return ultra;
	}
	
	public double getIr() {
		return ir;
	}
}
