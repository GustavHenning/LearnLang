package org.dyndns.tdfpro.main;

import java.util.ArrayList;
import java.util.HashMap;

public class WordList {

	HashMap<String, ArrayList<String>> words = new HashMap<String, ArrayList<String>>();

	public WordList() {

	}

	public void add(String word, String[] translation) {
		if (words.get(word) == null) {
			ArrayList<String> ar = new ArrayList<String>();
			for (String s : translation) {
				ar.add(s);
				words.put(word, ar);
			}
		} else {
			for (String s : translation) {
				if (!words.get(word).contains(s)) {
					words.get(word).add(s);
				}
			}
		}
	}

	public String get(int currentWord) {
		int i = 0;
		for(String s : words.keySet()){
			if(i == currentWord % words.size()){
				return s;
			}
			i++;
		}
		return null;
	}
	
	public ArrayList<String> getTranslation(int currentWord){
		int i = 0;
		for(String s : words.keySet()){
			if(i == currentWord % words.size()){
				return words.get(s);
			}
			i++;
		}
		return null;
	}
	
}
