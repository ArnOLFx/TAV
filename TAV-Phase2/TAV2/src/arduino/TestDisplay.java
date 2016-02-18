package arduino;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JTextField;

public class TestDisplay {

	private JFrame frame;
	public JTextField TSent;
	public JTextField USent;
	public JTextField ISent;
	public JTextField TRec;
	public JTextField SRec;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestDisplay window = new TestDisplay();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestDisplay() {
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
		
		JLabel lblSent = new JLabel("Sent values");
		lblSent.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 16));
		lblSent.setBounds(35, 30, 105, 15);
		panel.add(lblSent);
		
		JLabel lblTSent = new JLabel("Torque:");
		lblTSent.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 14));
		lblTSent.setBounds(35, 60, 86, 15);
		panel.add(lblTSent);
		
		JLabel lblUSent = new JLabel("Ultra distance:");
		lblUSent.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 14));
		lblUSent.setBounds(35, 80, 133, 15);
		panel.add(lblUSent);
		
		JLabel lblISent = new JLabel("IR distance");
		lblISent.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 14));
		lblISent.setBounds(35, 100, 133, 15);
		panel.add(lblISent);
		
		JLabel lblReceived = new JLabel("Received values");
		lblReceived.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 16));
		lblReceived.setBounds(35, 151, 164, 15);
		panel.add(lblReceived);
		
		JLabel lblRec = new JLabel("Torque:");
		lblRec.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 14));
		lblRec.setBounds(35, 180, 86, 15);
		panel.add(lblRec);
		
		JLabel lblSRec = new JLabel("Speed:");
		lblSRec.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 14));
		lblSRec.setBounds(35, 200, 86, 15);
		panel.add(lblSRec);
		
		TSent = new JTextField();
		TSent.setEditable(false);
		TSent.setBackground(UIManager.getColor("Button.shadow"));
		TSent.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 15));
		TSent.setBounds(171, 60, 114, 19);
		panel.add(TSent);
		TSent.setColumns(10);
		TSent.setText("0.42");

		
		USent = new JTextField();
		USent.setEditable(false);
		USent.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 15));
		USent.setColumns(10);
		USent.setBackground(UIManager.getColor("Button.select"));
		USent.setBounds(171, 80, 114, 19);
		panel.add(USent);
		USent.setText("8.17");

		
		ISent = new JTextField();
		ISent.setEditable(false);
		ISent.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 15));
		ISent.setColumns(10);
		ISent.setBackground(UIManager.getColor("Button.select"));
		ISent.setBounds(171, 100, 114, 19);
		panel.add(ISent);
		ISent.setText("4.56");
		
		TRec = new JTextField();
		TRec.setEditable(false);
		TRec.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 15));
		TRec.setColumns(10);
		TRec.setBackground(UIManager.getColor("Button.select"));
		TRec.setBounds(171, 180, 114, 19);
		panel.add(TRec);
		TRec.setText("0.56");

		
		SRec = new JTextField();
		SRec.setEditable(false);
		SRec.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 15));
		SRec.setColumns(10);
		SRec.setBackground(UIManager.getColor("Button.select"));
		SRec.setBounds(171, 200, 114, 19);
		panel.add(SRec);
		SRec.setText("14.89");

	}
}
