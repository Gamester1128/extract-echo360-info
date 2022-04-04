package autolecture;

import autolecture.modules.CS131;
import autolecture.testing.Testing;

public class Main {

	public static double BUILD = 0.71;

	public static void main(String[] args) {
		InputProcess.get();
		InputProcess.get().start();
		//Testing testing = new Testing(new CS131());
		//testing.start();
	}

}
