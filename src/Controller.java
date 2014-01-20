import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller implements KeyListener, ActionListener {

	private View v;
	private WordReader wr;

	private Thread timerThread;
	private int wordCount;
	private int max;

	public Controller(View v) {
		this.v = v;
		try {
			this.wr = new WordReader("List.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.wordCount = 0;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyChar()) {
		case '\n':
			String s;
			try {
				if (wordCount < max && (s = wr.nextWord()) != null) {
					v.updateWord(s);
					v.updateWordNumber(++wordCount);
				}

				break;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

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
		if (timerThread != null)
			timerThread.interrupt();

		timerThread = new Thread(new Runnable() {

			@Override
			public void run() {
				int time = 0;
				while (!timerThread.isInterrupted()) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					v.updateTimer(time);
				}

			}
		});
		timerThread.start();
	}

}
