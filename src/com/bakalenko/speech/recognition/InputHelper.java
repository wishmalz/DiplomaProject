package com.bakalenko.speech.recognition;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

/**
 * Created by Wish on 13.10.2015.
 */
public class InputHelper {
    private static int currentReplacement = -1; // 0 - name 1 - variable 2 - value
    private static int currentReplacementIndex = 999999;

    public static void inputHelp(String recognitionResultText, TextArea resultText) {
        StringBuffer resultCode = new StringBuffer(recognitionResultText);
        int nameIndex = resultCode.indexOf("name");
        int variableIndex = resultCode.indexOf("variable");
        int valueIndex = resultCode.indexOf("value");
        int appendTextStartIndex = 0;
        int appendTextEndIndex = 0;

        if(nameIndex != -1 && variableIndex != -1 && valueIndex != -1)
            resultText.appendText(resultCode.toString());

        while(nameIndex != -1 && variableIndex != -1 && valueIndex != -1) {
            setReplacementIndex(nameIndex, variableIndex, valueIndex);
            appendTextEndIndex = currentReplacementIndex;
            if(appendTextEndIndex == 999999)
                appendTextEndIndex = resultCode.length();

            resultText.appendText(resultCode.substring(appendTextStartIndex, appendTextEndIndex));

            switch (currentReplacement) {
                case 0: {


                    break;
                }
                case 1: {
                    break;
                }
                case 2: {
                    TextInputDialog dialog = new TextInputDialog("value");
                    dialog.setTitle("Value Input Dialog");
                    dialog.setHeaderText(null);
                    dialog.setContentText("Value:");

                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()){
                        System.out.println("Value: " + result.get());
                    }

                    resultText.deleteText(currentReplacementIndex, currentReplacement + "value".length());
                    resultText.appendText(result.get().toString());

                    appendTextStartIndex = appendTextEndIndex + result.get().toString().length();
                    currentReplacementIndex = 999999;

                    break;
                }
            }
        }
    }

    private static void setReplacementIndex(int nameIndex, int variableIndex, int valueIndex) {
        if(currentReplacementIndex >= nameIndex) {
            currentReplacementIndex = nameIndex;
            currentReplacement = 0;
        }

        if(currentReplacementIndex >= variableIndex) {
            currentReplacementIndex = variableIndex;
            currentReplacement = 1;
        }

        if(currentReplacementIndex >= valueIndex) {
            currentReplacementIndex = valueIndex;
            currentReplacement = 2;
        }
    }

}
