package com.bakalenko.speech.recognition;

/**
 * Created by Wish on 31.08.2015.
 */

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Class with speech recognition thread
 * Created by TwinMo on 28.04.2015.
 */
public class RecognitionThread extends Observable implements Runnable{
    public Thread tRecog;
    private ConfigurationManager cm;
    private Recognizer recognizer;
    private Microphone microphone;
    private Result result;
    private String resultString;
    private int recognitionThreadCode;
    private boolean isSuspended;
    private static boolean recognitionFlag;

    public RecognitionThread() {
        tRecog = new Thread(this, "Speech recognition thread");
        System.out.println("Child thread: " + tRecog);
        isSuspended = false;
        recognitionFlag = true;
        tRecog.start();
    }

    public void run() {
        cm = new ConfigurationManager(RecognitionThread.class.getResource("pascal.config.xml"));

        setRecognitionThreadCode(0); // Loading

        // allocate the recognizer
        recognizer = (Recognizer) cm.lookup("recognizer");
        recognizer.allocate();

        // start the microphone or exit the program if this is not possible
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

            setRecognitionThreadCode(1); // recognition started

            result = recognizer.recognize();
            if (result != null) {
                resultString = result.getBestFinalResultNoFiller();

                setChanged();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        notifyObservers(resultString);
                    }
                });

                System.out.println(resultString);
            }
        }
    }

    public synchronized void SuspendThread() {
        isSuspended = true;
        microphone.stopRecording();
        setRecognitionThreadCode(2); // recognition suspended
    }

    public synchronized void ResumeThread() {
        isSuspended = false;
        microphone.startRecording();
        notify();
    }

    public void setResultString(String resultString) {
        this.resultString = resultString;
    }

    public String getResultString() {
        return resultString;
    }

    public void setRecognitionThreadCode(int recognitionThreadCode) {
        this.recognitionThreadCode = recognitionThreadCode;

        setChanged();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                notifyObservers(recognitionThreadCode);
            }
        });
    }

    public int getRecognitionThreadCode() {
        return recognitionThreadCode;
    }

}