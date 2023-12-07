package Notepad;

import javax.swing.*;
import javax.swing.text.DefaultHighlighter;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Function_Edit{
    GUI gui;

    //JTextArea textArea;

    public Function_Edit(GUI gui) {
        this.gui=gui;

    }

    public void undo() {
        gui.um.undo();
    }

    public void redo() {
        gui.um.redo();
    }

    public int findText(String textToFind) {
        String text = gui.textArea.getText();
        int count = 0;

        // Remove previous highlights
        gui.removeHighlights();

        int index = text.indexOf(textToFind);
        DefaultHighlighter.DefaultHighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);

        while (index != -1) {
            count++;
            gui.highlightText(index, index + textToFind.length());
            index = text.indexOf(textToFind, index + textToFind.length());
        }

        return count;
    }

    public void replace() {
        String wordToReplace = JOptionPane.showInputDialog(gui.window, "Enter word to replace:");
        String replacementWord = JOptionPane.showInputDialog(gui.window, "Enter replacement word:");

        if (wordToReplace != null && replacementWord != null && !wordToReplace.isEmpty()) {
            String text = gui.textArea.getText();
            String newText = text.replaceAll(Pattern.quote(wordToReplace), Matcher.quoteReplacement(replacementWord));

            if (!text.equals(newText)) {
                gui.textArea.setText(newText);
                JOptionPane.showMessageDialog(gui.window, "Replacement complete.", "Replace Results", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(gui.window, "Word not found.", "Replace Results", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

   /* public void  undo() {
        String currentText = textArea.getText();
        int caretPosition = textArea.getCaretPosition();
        int lastSpaceIndex = currentText.lastIndexOf(' ', caretPosition - 1);

        if (lastSpaceIndex == -1) {
            // If no space is found, clear the text
            textArea.setText("");
        } else {
            textArea.setText(currentText.substring(0, lastSpaceIndex));
        }

        redoStack.push(new String(currentText));
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            textArea.setText(redoStack.pop());
        }
    }*/

}

