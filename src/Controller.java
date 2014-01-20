import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Controller implements KeyListener, ActionListener {

	private View v;
	private WordReader wr;

	private Thread timerThread;
	private int wordCount;
	private int max;
	private int time;

	private int correctChars, correctWords;

	public Controller(View v) {
		this.v = v;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyChar()) {
		case '\n':
			if (timerThread == null)
				return;

			System.out.println("Enter");
			String s;
			try {
				if (wordCount < max && (s = wr.nextWord()) != null) {
					v.updateWord(s);
					v.updateWordNumber(++wordCount);
					checkInputWord(v.getCurrentInput(), v.getCurrentWord());
				} else
					finishProgram();

				break;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	private void finishProgram() {
		// TODO Auto-generated method stub

	}

	private void checkInputWord(final String currentText, final String word) {
		Thread checkThread = new Thread(new Runnable() {

			@Override
			public void run() {
				char[] myWord = word.toCharArray();
				char[] input = currentText.toCharArray();
				int i;
				for (i = 0; i < (input.length - 1) && i < myWord.length; i++) {
					if (myWord[i] == input[i])
						correctChars++;
				}
				if (i == myWord.length)
					correctWords++;
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
			max = 30;
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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.wordCount = 0;
		this.correctChars = 0;
		this.correctWords = 0;

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
