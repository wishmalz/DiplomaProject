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
    private static int startIndex = 0;
    private static boolean isNotLastReplacement = true;
    private static int nameIndex = -1;
    private static int valueIndex = -1;
    private static int variableIndex = -1;
    private static StringBuffer temp = new StringBuffer(0);
    private static StringBuffer resultTextAfterReplacement = new StringBuffer(0);
    private static int startIndexForRes = 0;
    private static String stringForDynamicAppend = "";

    public static void inputHelp(String recognitionResultText, TextArea resultText) {
        temp = new StringBuffer(0);
        resultTextAfterReplacement = new StringBuffer(0);
        currentReplacement = -1; // 0 - name 1 - variable 2 - value
        currentReplacementIndex = 999999;
        startIndex = 0;
        isNotLastReplacement = true;
        nameIndex = -1;
        valueIndex = -1;
        variableIndex = -1;
        startIndexForRes = 0;
        stringForDynamicAppend = "";

        temp.append(recognitionResultText);

        nameIndex = temp.indexOf("name");
        valueIndex = temp.indexOf("value");
        variableIndex = temp.indexOf("variable");

        if ((nameIndex == -1) && (valueIndex == -1) && (variableIndex == -1)) {
            isNotLastReplacement = false;
            resultTextAfterReplacement.append(recognitionResultText);
            resultText.insertText(resultText.getCaretPosition(), resultTextAfterReplacement.toString());
        }

        while (isNotLastReplacement) {
            replacementIndexes();

            stringForDynamicAppend = temp.substring(startIndex, currentReplacementIndex);
            resultTextAfterReplacement.append(stringForDynamicAppend);
            resultText.insertText(resultText.getCaretPosition(), stringForDynamicAppend);

            if(!isNotLastReplacement)
                break;

            switch (currentReplacement) {
                case 0: {   //name
                    showDialog("name", resultText);
                    break;
                }
                case 1: {   //variable
                    showDialog("variable", resultText);
                    break;
                }
                case 2: {   //value
                    showDialog("value", resultText);
                    break;
                }
            }

        }
    }

    private static void showDialog(String replacement, TextArea resultText) {
        TextInputDialog dialog = new TextInputDialog(replacement);
        dialog.setTitle("Input Dialog");
        dialog.setHeaderText(null);
        dialog.setContentText("Please enter " + replacement + ":");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your " + replacement + ": " + result.get());
            stringForDynamicAppend = result.get().toString();
        }
        else stringForDynamicAppend = replacement;

        resultTextAfterReplacement.append(stringForDynamicAppend);
        resultText.insertText(resultText.getCaretPosition(), stringForDynamicAppend);

        temp.replace(startIndex, currentReplacementIndex + replacement.length(), resultTextAfterReplacement
                .substring(startIndexForRes, resultTextAfterReplacement.length()));
        startIndex = currentReplacementIndex + stringForDynamicAppend.length();
        startIndexForRes = resultTextAfterReplacement.length();
    }

    private static void replacementIndexes() {
        String tempStringForReplacementIndexes = temp.substring(startIndex, temp.length());
        nameIndex = tempStringForReplacementIndexes.indexOf("name");
        valueIndex = tempStringForReplacementIndexes.indexOf("value");
        variableIndex = tempStringForReplacementIndexes.indexOf("variable");
        currentReplacementIndex = 999999;
        currentReplacement = -1;

        // if replacement exists
        if ((nameIndex != -1) || (valueIndex != -1) || (variableIndex != -1)) {
            if ((currentReplacementIndex > nameIndex) && (nameIndex >= 0)) {
                currentReplacementIndex = nameIndex;
                currentReplacement = 0;
            }
            if ((currentReplacementIndex > variableIndex) && (variableIndex >= 0)) {
                currentReplacementIndex = variableIndex;
                currentReplacement = 1;
            }
            if ((currentReplacementIndex > valueIndex) && (valueIndex >= 0)) {
                currentReplacementIndex = valueIndex;
                currentReplacement = 2;
            }

            isNotLastReplacement = true;
            currentReplacementIndex += startIndex;
        }

        // if there are no replacements in string
        if ((nameIndex == -1) && (valueIndex == -1) && (variableIndex == -1)) {
            isNotLastReplacement = false;
            currentReplacementIndex = temp.length();
        }
    }

}
