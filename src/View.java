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
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class View extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	private JLabel wordCount, timer, word;

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

		word = new JLabel("Typing Speed");
		word.setHorizontalAlignment(SwingConstants.CENTER);
		word.setFont(new Font("Times New Roman", Font.BOLD, 15));

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Times New Roman", Font.BOLD, 15));
		textField.setColumns(10);

		textField.addKeyListener(controller);

		JLabel lblTime = new JLabel("Time :");

		JLabel lblWord = new JLabel("Word # ");

		timer = new JLabel("0");

		wordCount = new JLabel("0");

		JButton btnNewButton = new JButton("Easy");
		btnNewButton.addActionListener(controller);

		JButton btnMedium = new JButton("Medium");
		btnMedium.addActionListener(controller);

		JButton btnHard = new JButton("Hard");
		btnHard.addActionListener(controller);

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
																Alignment.TRAILING)
														.addGroup(
																Alignment.LEADING,
																gl_contentPane
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				lblWord)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				wordCount)
																		.addGap(43)
																		.addComponent(
																				btnNewButton)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				btnMedium)
																		.addGap(18)
																		.addComponent(
																				btnHard,
																				GroupLayout.PREFERRED_SIZE,
																				55,
																				GroupLayout.PREFERRED_SIZE))
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
																								word,
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
										.addGap(61)
										.addComponent(lblTime)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(timer).addContainerGap()));
		gl_contentPane
				.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGap(59)
										.addComponent(word,
												GroupLayout.PREFERRED_SIZE, 46,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(textField,
												GroupLayout.PREFERRED_SIZE, 38,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED, 67,
												Short.MAX_VALUE)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																gl_contentPane
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				lblWord)
																		.addComponent(
																				wordCount))
														.addGroup(
																gl_contentPane
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				btnHard)
																		.addComponent(
																				btnNewButton)
																		.addComponent(
																				btnMedium))))
						.addGroup(
								Alignment.TRAILING,
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblTime)
														.addComponent(timer))));
		contentPane.setLayout(gl_contentPane);

	}

	public void updateWord(String word) {
		this.word.setText(word);
		textField.setText("");
	}

	public String getCurrentText() {
		return textField.getText();
	}

	public void updateWordNumber(int count) {
		wordCount.setText(String.valueOf(count));
	}

	public void updateTimer(int time) {
		timer.setText(String.valueOf(time));
	}
}
