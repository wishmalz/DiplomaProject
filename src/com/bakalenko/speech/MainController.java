/**
 * Created by Wish on 25.08.2015.
 */
package com.bakalenko.speech;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.HTMLEditor;

public class MainController implements Initializable{
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
    @FXML private HTMLEditor mainText;
    @FXML private ToggleButton voiceInputBtn;
    @FXML private ToggleButton voiceOutputBtn;

    @Override
    public void initialize(URL location, ResourceBundle bundle) {
        resources = bundle;

        mainText.lookup(".top-toolbar").setManaged(false);
        mainText.lookup(".top-toolbar").setVisible(false);
        mainText.lookup(".bottom-toolbar").setManaged(false);
        mainText.lookup(".bottom-toolbar").setVisible(false);
    }

    @FXML
    private void changeLangRus() throws IOException {
        updateUI(Locale.forLanguageTag("ru"));
    }

    @FXML
    private void changeLangEng()throws IOException {
        updateUI(Locale.ENGLISH);
    }

    @FXML
    private void changeThemeLight(){

    }
    @FXML
    private void changeThemeDark(){

    }

    private void updateUI(Locale locale){
        try {
            resources = ResourceBundle.getBundle("com.bakalenko.resources.TextBundle", locale);
            pane = FXMLLoader.load(getClass().getResource("mainWindow.fxml"), resources);

            Main.stage.setTitle(resources.getString("appName"));
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
}
