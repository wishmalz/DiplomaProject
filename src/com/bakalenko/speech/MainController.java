package com.bakalenko.speech;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import com.bakalenko.speech.recognition.RecognitionThread;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

/**
 * Controller class that handles all actions of the program interface
 * @version 25.08.2015
 * @author Wish
 */
public class MainController implements Initializable{
    private RecognitionThread recognitionThread;
    private ResourceBundle resources;
    @FXML private BorderPane pane;
    @FXML private Menu fileMenu;
    @FXML private MenuItem open;
    @FXML private MenuItem save;
    @FXML private MenuItem close;
    @FXML private MenuItem exit;
    @FXML private Menu voiceInput;
    @FXML private MenuItem setParams;
    @FXML private MenuItem speakerAdjustment;
    @FXML private MenuItem programmingLanguage;
    @FXML private MenuItem tokensReplacement;
    @FXML private MenuItem wordsHighlighting;
    @FXML private MenuItem phrasesHighlighting;
    @FXML private MenuItem debugging;
    @FXML private Menu voiceOutput;
    @FXML private MenuItem computerVoiceParams;
    @FXML private MenuItem backlitScoringTokens;
    @FXML private MenuItem typeOfScoringTokens;
    @FXML private MenuItem stopInWords;
    @FXML private Menu compilation;
    @FXML private Menu settingsOfEditor;
    @FXML private Menu changeLanguage;
    @FXML private RadioMenuItem changeToRus;
    @FXML private RadioMenuItem changeToEng;
    @FXML private Menu changeTheme;
    @FXML private RadioMenuItem changeToLight;
    @FXML private RadioMenuItem changeToDark;
    @FXML private Menu help;
    @FXML private MenuItem helpCHM;
    @FXML private MenuItem aboutProgram;
    @FXML private MenuItem aboutAuthor;
    @FXML private ToggleButton voiceInputBtn;
    @FXML private ToggleButton voiceOutputBtn;
    @FXML private TextFlow mainText;
    @FXML private SplitPane splitPaneTwoTextAreas;
    @FXML private Label statusLabel;
    @FXML private TextArea inputPlainText;
    @FXML private TextFlow highlightedText;

    @Override
    public void initialize(URL location, ResourceBundle bundle) {
        resources = bundle;
        splitPaneTwoTextAreas.setDividerPositions(1);
        inputPlainText.setStyle("-fx-font-size: 16");
        Text test = new Text("TEst");
        test.setFill(Color.LIME);
        test.setUnderline(false);
        test.setFont(new Font("Times New Roman", 20));
        Text test1 = new Text("TEst");
        test1.setFill(Color.CRIMSON);
        test1.setUnderline(true);
        test1.setFont(new Font("Times New Roman", 18));
        highlightedText.getChildren().addAll(test, test1);
        highlightedText.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     * Changes language of the application to russian
     * @throws IOException
     */
    @FXML
    private void changeLangRus() throws IOException {
        updateUI(Locale.forLanguageTag("ru"));
        /* TODO добавить флаг, чтобы не перерисовывать зря, если мы уже на том языке, в который переключаемся*/
    }

    /**
     * Changes language of the application to english
     * @throws IOException
     */
    @FXML
    private void changeLangEng()throws IOException {
        updateUI(Locale.ENGLISH);
    }

    @FXML
    private void changeThemeLight(){
        /* TODO добавить флаг, чтобы не перерисовывать зря, если мы уже на той теме, в которую переключаемся*/
    }

    @FXML
    private void changeThemeDark(){
        voiceInputBtn.setStyle("-fx-background-color: lightgreen;");
    }

    /**
     * Updates text of all UI controls of the application
     * @param locale - language of the app
     */
    private void updateUI(Locale locale){
        try {
            resources = ResourceBundle.getBundle("com.bakalenko.resources.TextBundle", locale);
            pane = FXMLLoader.load(getClass().getResource("mainWindow.fxml"), resources);

            fileMenu.setText(resources.getString("fileMenu"));
            open.setText(resources.getString("fileMenu.open"));
            save.setText(resources.getString("fileMenu.save"));
            close.setText(resources.getString("fileMenu.close"));
            exit.setText(resources.getString("fileMenu.exit"));
            voiceInput.setText(resources.getString("voiceInput"));
            setParams.setText(resources.getString("voiceInput.setParams"));
            speakerAdjustment.setText(resources.getString("voiceInput.speakerAdjustment"));
            programmingLanguage.setText(resources.getString("voiceInput.programmingLanguage"));
            tokensReplacement.setText(resources.getString("voiceInput.tokensReplacement"));
            wordsHighlighting.setText(resources.getString("voiceInput.wordsHighlighting"));
            phrasesHighlighting.setText(resources.getString("voiceInput.phrasesHighlighting"));
            debugging.setText(resources.getString("voiceInput.debugging"));
            voiceOutput.setText(resources.getString("voiceOutput"));
            computerVoiceParams.setText(resources.getString("voiceOutput.computerVoiceParams"));
            backlitScoringTokens.setText(resources.getString("voiceOutput.backlitScoringTokens"));
            typeOfScoringTokens.setText(resources.getString("voiceOutput.typeOfScoringTokens"));
            stopInWords.setText(resources.getString("voiceOutput.stopInWords"));
            compilation.setText(resources.getString("compilation"));
            settingsOfEditor.setText(resources.getString("settingsOfEditor"));
            changeLanguage.setText(resources.getString("settingsOfEditor.changeLanguage"));
            changeToRus.setText(resources.getString("settingsOfEditor.changeLanguage.russianLang"));
            changeToEng.setText(resources.getString("settingsOfEditor.changeLanguage.englishLang"));
            changeTheme.setText(resources.getString("settingsOfEditor.changeTheme"));
            changeToLight.setText(resources.getString("settingsOfEditor.changeTheme.lightTheme"));
            changeToDark.setText(resources.getString("settingsOfEditor.changeTheme.darkTheme"));
            help.setText(resources.getString("help"));
            helpCHM.setText(resources.getString("help.helpCHM"));
            aboutProgram.setText(resources.getString("help.aboutProgram"));
            aboutAuthor.setText(resources.getString("help.aboutAuthor"));
            voiceInputBtn.setText(resources.getString("voiceInputB"));
            voiceOutputBtn.setText(resources.getString("voiceOutputB"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void startRecognition() {
        if(recognitionThread == null) {
            recognitionThread = new RecognitionThread(statusLabel, inputPlainText);
        }
        //splitPaneTwoTextAreas.setDividerPositions(0);
    }

    public void addText(String text) {

    }

    public void setStatus(String statusText) {
        statusLabel.setText(statusText);
    }


}
