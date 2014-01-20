import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Controller implements KeyListener, ActionListener {

	private View v;
	private WordReader wr;

	private Thread timerThread;
	private int wordCount;
	private int max;
	private int time;

	private int correctChars, correctCharsForCorrectWords, correctWords;

	public Controller(View v) {
		this.v = v;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyChar()) {
		case '\n':
			if (timerThread == null)
				return;

			String s;
			try {
				if (wordCount < max && (s = wr.nextWord()) != null) {
					checkInputWord(v.getCurrentInput(), v.getCurrentWord());
					v.updateWord(s);
					v.updateWordNumber(++wordCount);
				} else
					finishProgram();

				break;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	private void finishProgram() {
		timerThread = null;
		JOptionPane.showMessageDialog(v, "Result:\n- Correct Words = "
				+ correctWords + "\n- Total Correct Characters = "
				+ correctChars + "\n- Correct Characters in CW = "
				+ correctCharsForCorrectWords + "\n- Time = " + time
				+ "sec.\n- Average Time for each character = "
				+ ((double) (time*1.0 / correctChars*1.0)) + "sec.");

	}

	private void checkInputWord(final String currentText, final String word) {
		Thread checkThread = new Thread(new Runnable() {

			@Override
			public void run() {
				char[] myWord = word.trim().toCharArray();
				char[] input = currentText.toCharArray();
				int i;
				int count = 0;
				for (i = 0; i < (input.length - 1) && i < myWord.length; i++) {
					if (myWord[i] == input[i])
						count++;
				}
				if (i == myWord.length - 1) {
					correctWords++;
					correctCharsForCorrectWords += count;
				}
				correctChars += count;
			}
		});
		checkThread.start();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		switch (a.getActionCommand()) {
		case "Easy":
			max = 5;
			break;
		case "Medium":
			max = 150;
			break;
		case "Hard":
			max = Integer.MAX_VALUE;
			break;
		}
		start();
	}

	private void start() {
		try {
			this.wr = new WordReader("List.txt");
			v.updateWord(wr.nextWord());
			v.updateWordNumber(++wordCount);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.wordCount = 0;
		this.correctChars = 0;
		this.correctWords = 0;
		this.correctCharsForCorrectWords = 0;

		timerThread = new Thread(new Runnable() {

			@Override
			public void run() {
				time = 0;
				while (!timerThread.isInterrupted()) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					v.updateTimer(time++);
				}

			}
		});
		timerThread.start();
	}

}
