import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

public class View extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	private Controller controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public View() {

		controller = new Controller(this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Word");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));

		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.BOLD, 15));
		textField.setColumns(10);

		textField.addKeyListener(controller);

		JLabel lblTime = new JLabel("Time :");

		JLabel lblWord = new JLabel("Word # ");

		JLabel label = new JLabel("0");

		JLabel label_1 = new JLabel("0");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																Alignment.TRAILING,
																gl_contentPane
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				lblWord)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				label_1)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				271,
																				Short.MAX_VALUE)
																		.addComponent(
																				lblTime)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				label))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(100)
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.TRAILING,
																								false)
																						.addComponent(
																								lblNewLabel,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								textField,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								210,
																								Short.MAX_VALUE))))
										.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING)
				.addGroup(
						gl_contentPane
								.createSequentialGroup()
								.addGap(59)
								.addComponent(lblNewLabel,
										GroupLayout.PREFERRED_SIZE, 46,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(textField,
										GroupLayout.PREFERRED_SIZE, 38,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED,
										76, Short.MAX_VALUE)
								.addGroup(
										gl_contentPane
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(lblTime)
												.addComponent(lblWord)
												.addComponent(label)
												.addComponent(label_1))));
		contentPane.setLayout(gl_contentPane);
	}
}
