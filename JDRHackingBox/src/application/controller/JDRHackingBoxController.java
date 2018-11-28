package application.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import application.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class JDRHackingBoxController {

	@FXML
	private TextField communsLetters;
	@FXML
	private TextField numberOfCommunsLetters;
	@FXML
	private TextField compared;
	@FXML
	private TextField comparing;

	@FXML
	public void write(KeyEvent event) {

		TextField compare = (TextField) event.getSource();
		String compareText = compare.getText() + event.getCharacter().charAt(0);

		compareText = compareText.replace("\b", "");
		for (char c : compareText.toCharArray()) {
			if ((!Utils.letters.contains(Character.valueOf(c))) && (c != '\b')) {
				event.consume();
				return;
			}
		}

		if (!sameSize(compare, event.getCharacter().charAt(0) == '\b')) {
			this.communsLetters.setText("");
			this.numberOfCommunsLetters.setText("");
			return;
		}

		Set<Character> comparedUniqueCharacters = new HashSet<>();
		for (char c : this.compared.getText().toCharArray()) {
			if (!comparedUniqueCharacters.contains(c)) comparedUniqueCharacters.add(c);
		}

		List<Character> communChars = new ArrayList<>();
		for (char compared : comparedUniqueCharacters) {
			for (char comparing : this.comparing.getText().toCharArray()) {
				if (compared == comparing) communChars.add(compared);
			}
		}

		if (this.compared == compare) {
			if ((!this.compared.getText().contains(
					event.getCharacter())) && (event.getCharacter().charAt(0) != '\b')) {
				for (char comparingChars : this.comparing.getText().toCharArray()) {
					if (event.getCharacter().charAt(0) == comparingChars) {
						communChars.add(Character.valueOf(comparingChars));
					}
				}

			}
		} else if (this.compared.getText().contains(event.getCharacter())) {
			communChars.add(Character.valueOf(event.getCharacter().charAt(0)));
		}

		Collections.sort(communChars);
		this.communsLetters.setText(Utils.insertBetween(communChars, '-'));
		this.numberOfCommunsLetters.setText(String.valueOf(communChars.size()));
	}

	private boolean sameSize(TextField compare, boolean suppressedButtonKey) {
		int comparedTextSize = this.compared.getText().length();
		int comparingTextSize = this.comparing.getText().length();
		if (!suppressedButtonKey) {
			if (compare == this.compared) {
				comparedTextSize++;
			} else {
				comparingTextSize++;
			}
		}
		return comparedTextSize == comparingTextSize;
	}
}
