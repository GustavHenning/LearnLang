package org.dyndns.tdfpro.main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class WordPractice {

	private WordList wordList = new WordList();
	private String[] langs;
	private String path;
	private int currentWord = -1;
	private int currentWordIndex = -1;
	private int currentTip = 0;
	private boolean[] firstTry;
	ArrayList<Integer> nextWord = new ArrayList<Integer>();
	private Output out;
	Random rnd = new Random();

	public WordPractice(CommandParser cp, String path, String[] langs) {
		this.out = cp.out;
		this.path = path;
		this.langs = langs;
	}

	public boolean init() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(path)));
			String line;
			while ((line = br.readLine()) != null) {
				String[] lineWords = line.split("–");
				if (lineWords.length == 1) {
					lineWords = line.split("-");
				}
				if (lineWords.length == langs.length) {
					String[] translation = new String[lineWords.length - 1];
					for (int i = 1; i < lineWords.length; i++) {
						translation[i - 1] = lineWords[i].trim();
					}
					wordList.add(lineWords[0].trim(), translation);
				} else {
					System.err.println("unable to parse: " + line);

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		firstTry = new boolean[wordList.words.size()];
		nextWord();
		return true;
	}

	public void tip() {
		String wordToFind = wordList.get(currentWord);
		String tip = null;

		/* send first letter of each word */
		char[] chars = wordToFind.toCharArray();
		for (int i = 1; i < chars.length; i++) {
			if (chars[i] != ' ' && chars[i - 1] != ' ') {
				chars[i] = '_';
			}
		}
		tip = String.valueOf(chars);
		StringBuilder sb = new StringBuilder();
		sb.append(tip.toString());
		for (int i = 0; i < currentTip; i++) {
			if (i >= wordToFind.length()-1) {
				break;
			}
			boolean replacing = true;
			while (replacing) {
				int at = rnd.nextInt(wordToFind.length());
				if (sb.toString().charAt(at) == '_') {
					sb.setCharAt(at, wordToFind.charAt(at));
					replacing = false;
				}
			}
		}
		tip = sb.toString();

		out.send(tip);
		currentTip++;
	}

	public void nextWord() {
		if(nextWord.size() == 0 || currentWord == nextWord.size()){
			nextWord = new ArrayList<Integer>();
			while(nextWord.size() != wordList.words.size()){
				int rand = rnd.nextInt(wordList.words.size());
				if(!nextWord.contains(rand)){
					nextWord.add(rand);
				}
			}
		}
		if(currentTip == 0 && currentWordIndex >= 0)
			firstTry[currentWordIndex] = true;
		currentWordIndex++;
		while(firstTry[currentWordIndex]){
			currentWordIndex++;
			if(currentWordIndex >= wordList.words.size()){
				currentWordIndex = 0;
			}
		}
		currentWord = nextWord.get(currentWordIndex);
		currentTip = 0;
		StringBuilder sb = new StringBuilder();
		for (String s : wordList.getTranslation(currentWord)) {
			sb.append(s + " ");
		}
		out.send(sb.toString());
	}

	public String currentWord() {
		return wordList.get(currentWord);
	}
	
	public String currentTranslation(){
		StringBuilder sb = new StringBuilder();
		for(String s : wordList.words.get(wordList.get(currentWord))){
			sb.append(s + " ");
		}
		return sb.toString();
	}

}
