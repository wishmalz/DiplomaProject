package com.bakalenko.speech.recognition;

/**
 * Created by Wish on 31.08.2015.
 */

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.awt.*;

/**
 * Class with speech recognition thread
 * Created by TwinMo on 28.04.2015.
 */
public class RecognitionThread implements Runnable {
    public Thread tRecog;
    private Label statusLabel;
    private TextArea text;
    private ConfigurationManager cm;
    private Recognizer recognizer;
    private Microphone microphone;
    private Result result;
    private String resultString;

    public RecognitionThread(Label statusText, TextArea mainText) {
        statusLabel = statusText;
        text = mainText;
        tRecog = new Thread(this, "Speech recognition thread");
        System.out.println("Child thread: " + tRecog);
        tRecog.start();
    }

    public void run() {
        cm = new ConfigurationManager(RecognitionThread.class.getResource("pascal.config.xml"));

        // allocate the recognizer
        System.out.println("Loading...");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                statusLabel.setText("Loading...");
            }
        });
        recognizer = (Recognizer) cm.lookup("recognizer");
        recognizer.allocate();

        // start the microphone or exit the program is this is not possible
        microphone = (Microphone) cm.lookup("microphone");
        if (!microphone.startRecording()) {
            System.out.println("Cannot start microphone");
            recognizer.deallocate();
            System.exit(1);
        }
        else {
            System.out.println("Microphone started");
        }

        //statusLabel.setText("Recognition started");
        // loop the recognition until the program exits
        while (true) {
            System.out.println("Recognition started. Say smthng");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    statusLabel.setText("Recognition started");
                }
            });
            result = recognizer.recognize();

            if (result != null) {
                resultString = result.getBestFinalResultNoFiller();//getBestFinalToken().getWordPathNoFiller();
                //getBestFinalResultNoFiller()

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        text.appendText(replaceWords(resultString));
                    }
                });

                System.out.println(resultString);
            }
        }
    }

    public String replaceWords(String inputText) {
        StringBuffer result = new StringBuffer(0);
        String [] words = inputText.split("[\\s]");
        for(String singleUtteredWord:words){
            switch (singleUtteredWord) {
                case "divide":
                case "slash": {
                    singleUtteredWord = "/";
                    result.append(singleUtteredWord);
                    break;
                }
                case "multiply":
                case "asterisk": {
                    singleUtteredWord = "* ";
                    result.append(singleUtteredWord);
                    break;
                }
                case "at": {
                    singleUtteredWord = "@";
                    result.append(singleUtteredWord);
                    break;
                }
                case "backslash": {
                    singleUtteredWord = "\\";
                    result.append(singleUtteredWord);
                    break;
                }
                case "caret": {
                    singleUtteredWord = "^";
                    result.append(singleUtteredWord);
                    break;
                }
                case "colon": {
                    singleUtteredWord = ": ";
                    result.append(singleUtteredWord);
                    break;
                }
                case "comma": {
                    singleUtteredWord = ", ";
                    result.append(singleUtteredWord);
                    break;
                }
                case "full-stop":
                case "dot": {
                    singleUtteredWord = ".";
                    result.append(singleUtteredWord);
                    break;
                }
                case "equals": {
                    singleUtteredWord = "= ";
                    result.append(singleUtteredWord);
                    break;
                }
                case "left-parenthesis": {
                    singleUtteredWord = "(";
                    result.append(singleUtteredWord);
                    break;
                }
                case "left-square-bracket": {
                    singleUtteredWord = "[";
                    result.append(singleUtteredWord);
                    break;
                }
                case "assign": {
                    singleUtteredWord = "= ";
                    result.append(singleUtteredWord);
                    break;
                }
                case "less": {
                    singleUtteredWord = "< ";
                    result.append(singleUtteredWord);
                    break;
                }
                case "minus": {
                    singleUtteredWord = "- ";
                    result.append(singleUtteredWord);
                    break;
                }
                case "more": {
                    singleUtteredWord = "> ";
                    result.append(singleUtteredWord);
                    break;
                }
                case "newline": {
                    singleUtteredWord = "\n";
                    result.append(singleUtteredWord);
                    break;
                }
                case "not-equal": {
                    singleUtteredWord = "<> ";
                    result.append(singleUtteredWord);
                    break;
                }
                case "plus": {
                    singleUtteredWord = "+ ";
                    result.append(singleUtteredWord);
                    break;
                }
                case "procedure": {
                    singleUtteredWord = "Procedure ";
                    result.append(singleUtteredWord);
                    break;
                }
                case "function": {
                    singleUtteredWord = "Function ";
                    result.append(singleUtteredWord);
                    break;
                }
                case "program": {
                    singleUtteredWord = "Program ";
                    result.append(singleUtteredWord);
                    break;
                }
                case "quote": {
                    singleUtteredWord = "'";
                    result.append(singleUtteredWord);
                    break;
                }
                case "right-parenthesis": {
                    singleUtteredWord = ")";
                    result.append(singleUtteredWord);
                    break;
                }
                case "right-square-bracket": {
                    singleUtteredWord = "]";
                    result.append(singleUtteredWord);
                    break;
                }
                case "semicolon": {
                    singleUtteredWord = ";\n";
                    result.append(singleUtteredWord);
                    break;
                }
                case "space": {
                    singleUtteredWord = " ";
                    result.append(singleUtteredWord);
                    break;
                }
                case "square": {
                    singleUtteredWord = "sqr";
                    result.append(singleUtteredWord);
                    break;
                }
                case "square-root": {
                    singleUtteredWord = "sqrt";
                    result.append(singleUtteredWord);
                    break;
                }
                case "tab": {
                    singleUtteredWord = "    ";
                    result.append(singleUtteredWord);
                    break;
                }
                default: {
                    singleUtteredWord = singleUtteredWord + " ";
                    result.append(singleUtteredWord);
                    break;
                }
            }
        }

        return result.toString();
    }

}