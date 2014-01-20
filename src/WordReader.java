import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class WordReader {

	private BufferedReader br;

	public WordReader(String path) throws FileNotFoundException {
		br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
	}

	public String nextWord() throws IOException {
		String s = "";
		if ((s = br.readLine()) == null)
			return null;

		return s;
	}
}
