package com.starbattle.server.console.output;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class TextFileReader {

	public static String readTextFile(String name) {
//		URL url = TextFileReader.class.getResource("/resource/" + name);
		String text = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("resource/"+name));

			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			text = sb.toString();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return text;
	}

}
