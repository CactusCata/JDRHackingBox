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

/**
 * Controller for FX gui
 * 
 * @author adamc
 *
 */
public class JDRHackingBoxController {

	@FXML
	private TextField commonsLetters, numberOfCommonsLetters;
	@FXML
	private TextField compared, comparing;

	@FXML
	public void write(KeyEvent event) {

		TextField compare = (TextField) event.getSource();
		// Get text of text field plus the added character
		String compareText = compare.getText() + event.getCharacter().charAt(0);

		// Delete del key
		compareText = compareText.replace("\b", "");
		// Check if user write alphabetic character, otherwise, the event is
		// canceled
		for (char c : compareText.toCharArray()) {
			if ((!Utils.letters.contains(Character.valueOf(c))) && (c != '\b')) {
				event.consume();
				return;
			}
		}

		// Check if compared and comparing have the same lenght
		if (!sameSize(compare, event.getCharacter().charAt(0) == '\b')) {
			// Reset text fields
			this.commonsLetters.setText("");
			this.numberOfCommonsLetters.setText("");
			return;
		}

		// Eliminate compared character in several copies
		Set<Character> comparedUniqueCharacters = new HashSet<>();
		for (char c : this.compared.getText().toCharArray()) {
			if (!comparedUniqueCharacters.contains(c)) comparedUniqueCharacters.add(c);
		}

		// Each all compared character for match all comparing character
		List<Character> commonsChars = new ArrayList<>();
		for (char compared : comparedUniqueCharacters) {
			for (char comparing : this.comparing.getText().toCharArray()) {
				if (compared == comparing) commonsChars.add(compared);
			}
		}

		// Mysterious method c:
		if (this.compared == compare) {
			if ((!this.compared.getText().contains(
					event.getCharacter())) && (event.getCharacter().charAt(0) != '\b')) {
				for (char comparingChars : this.comparing.getText().toCharArray()) {
					if (event.getCharacter().charAt(0) == comparingChars) {
						commonsChars.add(Character.valueOf(comparingChars));
					}
				}

			}
		} else if (this.compared.getText().contains(event.getCharacter())) {
			commonsChars.add(Character.valueOf(event.getCharacter().charAt(0)));
		}

		// Sort commons characters in alphabetic order
		Collections.sort(commonsChars);
		// Fill commons fields
		this.commonsLetters.setText(Utils.insertBetween(commonsChars, '-'));
		this.numberOfCommonsLetters.setText(String.valueOf(commonsChars.size()));
	}

	/**
	 * Check if compared and comparing have the same size. This method was
	 * called when user write in compared or comparing text field. But the
	 * problem is: compared text field and comparing text field aren't updated
	 * (like their size).
	 * 
	 * @param compare
	 *            comared or comparing text field who called in write method
	 * @param suppressedButtonKey
	 *            the delete key is already correcly counted in textField, works
	 *            on this is useless
	 * @return if compared and comparing have the same size to compare
	 */
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
