package com.starbattle.client.views.register.validate;

public class NameGenerator {

	public static String generateRandomName() {
		String name = "";

		int anz = 1;
		if ((int) (Math.random() * 5) == 0) {
			anz += (int) (Math.random() * 3 + 1);
		}

		for (int i = 0; i < anz; i++) {
			name += getName();
			if (i < anz - 1) {
				name += " ";
			}
		}

		return name;
	}

	private static String getName() {
		String name = "";
		int length = (int) (Math.random() * 4 + 2) + (int) (Math.random() * 3);
		for (int i = 0; i < length; i++) {
			if (i % 2 == 1) {
				name += getVowel();
			} else {
				name += getConsonant();
			}
		}
		// change first letter to uppercase
		String firstLetter = "" + name.charAt(0);
		name = firstLetter.toUpperCase() + name.substring(1);
		return name;
	}

	private static String getVowel() {
		String vowel = "";
		int r = (int) (Math.random() * 5);
		switch (r) {
		case 0:
			vowel = "a";
			break;
		case 1:
			vowel = "e";
			break;
		case 2:
			vowel = "i";
			break;
		case 3:
			vowel = "o";
			break;
		case 4:
			vowel = "u";
			break;
		}
		if ((int) (Math.random() * 5) == 0) {
			// special
			r = (int) (Math.random() * 5);
			switch (r) {
			case 0:
				vowel = "ai";
				break;
			case 1:
				vowel = "eu";
				break;
			case 2:
				vowel = "oo";
				break;
			case 3:
				vowel = "ou";
				break;
			case 4:
				vowel = "au";
				break;
			}
		}
		return vowel;
	}

	private static String getConsonant() {
		String consonant = "";
		int start = 98;
		int end = 122;
		int r = 0;
		do {
			r = (int) (Math.random() * (end - start + 1)) + start;
			if (r == 101 || r == 105 || r == 111 || r == 117) {
				r = 0;
			}
		} while (r == 0);
		consonant = "" + (char) r;
		if ((int) (Math.random() * 15) == 0) {
			//special
			r = (int) (Math.random() * 5);
			switch (r) {
			case 0:
				consonant = "st";
				break;
			case 1:
				consonant = "pf";
				break;
			case 2:
				consonant = "ch";
				break;
			case 3:
				consonant = "sh";
				break;
			case 4:
				consonant = "th";
				break;
			}
		}
		return consonant;
	}
}
