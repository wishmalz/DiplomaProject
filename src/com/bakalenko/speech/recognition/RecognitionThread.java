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
public class RecognitionThread extends Thread {
    public Thread tRecog;
    private Label statusLabel;
    private TextArea text;
    private ConfigurationManager cm;
    private Recognizer recognizer;
    private Microphone microphone;
    private Result result;
    private String resultString;
    private boolean isSuspended;
    private static boolean recognitionFlag;

    public RecognitionThread(Label statusText, TextArea mainText) {
        statusLabel = statusText;
        text = mainText;
        tRecog = new Thread(this, "Speech recognition thread");
        System.out.println("Child thread: " + tRecog);
        isSuspended = false;
        recognitionFlag = true;
        tRecog.start();
    }

    public void run() {
        cm = new ConfigurationManager(RecognitionThread.class.getResource("pascal.config.xml"));

        System.out.println("Loading...");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                statusLabel.setText("Loading...");
            }
        });

        // allocate the recognizer
        recognizer = (Recognizer) cm.lookup("recognizer");
        recognizer.allocate();

        // start the microphone or exit the program is this is not possible
        microphone = (Microphone) cm.lookup("microphone");
        if (!microphone.startRecording()) {
            System.out.println("Cannot start microphone");
            recognizer.deallocate();
            System.exit(1);
        } else {
            System.out.println("Microphone started");
        }

        // loop the recognition until the program exits
        while (recognitionFlag) {
            synchronized (this) {
                while (isSuspended) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            System.out.println("Recognition started. Say smthng");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    statusLabel.setText("Recognition started");
                }
            });
            result = recognizer.recognize();

            if (result != null) {
                resultString = result.getBestFinalResultNoFiller();

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
        String[] words = inputText.split("[\\s]");
        for (String singleUtteredWord : words) {
            switch (singleUtteredWord) {
                case "divide": {
                    result.append("/ ");
                    break;
                }
                case "slash": {
                    result.append("/");
                    break;
                }
                case "multiply":
                case "asterisk": {
                    result.append("* ");
                    break;
                }
                case "at": {
                    singleUtteredWord = "@";
                    result.append(singleUtteredWord);
                    break;
                }
                case "backslash": {
                    result.append("\\ ");
                    break;
                }
                case "caret": {
                    result.append("^ ");
                    break;
                }
                case "colon": {
                    result.append(": ");
                    break;
                }
                case "comma": {
                    result.append(", ");
                    break;
                }
                case "full-stop":
                case "dot": {
                    result.append(".");
                    break;
                }
                case "equals": {
                    result.append("= ");
                    break;
                }
                case "left-parenthesis": {
                    result.append("(");
                    break;
                }
                case "left-square-bracket": {
                    result.append("[");
                    break;
                }
                case "assign": {
                    result.append(":= ");
                    break;
                }
                case "less": {
                    result.append("< ");
                    break;
                }
                case "minus": {
                    result.append("- ");
                    break;
                }
                case "more": {
                    result.append("> ");
                    break;
                }
                case "newline": {
                    result.append("\n");
                    break;
                }
                case "var": {
                    result.append("var\n");
                    break;
                }
                case "begin": {
                    result.append("begin\n");
                    break;
                }
                case "not-equal": {
                    result.append("<> ");
                    break;
                }
                case "plus": {
                    result.append("+ ");
                    break;
                }
                case "procedure": {
                    result.append("Procedure ");
                    break;
                }
                case "function": {
                    result.append("Function ");
                    break;
                }
                case "program": {
                    result.append("Program ");
                    break;
                }
                case "quote": {
                    result.append("'");
                    break;
                }
                case "right-parenthesis": {
                    result.append(")");
                    break;
                }
                case "right-square-bracket": {
                    result.append("]");
                    break;
                }
                case "semicolon": {
                    result.append(";\n");
                    break;
                }
                case "space": {
                    result.append(" ");
                    break;
                }
                case "square": {
                    result.append("sqr");
                    break;
                }
                case "square-root": {
                    result.append("sqrt");
                    break;
                }
                case "tab": {
                    result.append("    ");
                    break;
                }
                default: {
                    result.append(singleUtteredWord + " ");
                    break;
                }
            }
        }

        return result.toString();
    }

    public synchronized void SuspendThread() {
        isSuspended = true;
        microphone.stopRecording();
    }

    public synchronized void ResumeThread() {
        isSuspended = false;
        microphone.startRecording();
        notify();
    }

    public static synchronized void stopRecognitionThread() {
        recognitionFlag = false;
        System.exit(-1);
    }

}