package com.bakalenko.speech;

import java.io.*;
import java.net.URL;
import java.util.*;

import com.bakalenko.speech.recognition.InputHelper;
import com.bakalenko.speech.recognition.RecognitionThread;
import com.bakalenko.speech.recognition.Replacer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.FileChooser;
import javafx.util.Pair;

/**
 * Controller class that handles all actions of the program interface
 * @version 25.08.2015
 * @author Wish
 */
public class MainController implements Initializable, Observer{
    public RecognitionThread recognitionThread;
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
    final FileChooser fileChooser = new FileChooser();
    private boolean isFirstStart = true;

    @Override
    public void initialize(URL location, ResourceBundle bundle) {
        resources = bundle;
        splitPaneTwoTextAreas.setDividerPositions(1);
        inputPlainText.setStyle("-fx-font-size: 18");
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
        ApplicationSettings.applicationLanguage = Locale.forLanguageTag("ru");
        /* TODO добавить флаг, чтобы не перерисовывать зря, если мы уже на том языке, в который переключаемся*/
    }

    /**
     * Changes language of the application to english
     * @throws IOException
     */
    @FXML
    private void changeLangEng()throws IOException {
        updateUI(Locale.ENGLISH);
        ApplicationSettings.applicationLanguage = Locale.ENGLISH;
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
            pane = FXMLLoader.load(getClass().getResource("MainWindow.fxml"), resources);

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
        inputPlainText.setEditable(false);
        if(isFirstStart == false) {
            if(!voiceInputBtn.isSelected()) {
                recognitionThread.SuspendThread();
                System.out.println("Recognition thread suspended");
            }
            if(voiceInputBtn.isSelected()) {
                recognitionThread.ResumeThread();
                System.out.println("Recognition thread resumed");
            }
        }
        if(isFirstStart == true) {
            recognitionThread = new RecognitionThread();
            recognitionThread.addObserver(this);
            isFirstStart = false;
            System.out.println("Recognition thread first launch");
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        Integer forIfInteger = new Integer(1);
        String forIfString = new String("1");

        if(arg.getClass() == forIfInteger.getClass()) {     // if thread changed its state
            switch (recognitionThread.getRecognitionThreadCode()) {
                case 0: {
                    statusLabel.setText(resources.getString("status.loading"));
                    System.out.println("Loading...");
                    break;
                }
                case 1: {
                    statusLabel.setText(resources.getString("status.recognition"));
                    System.out.println("Recognition started");
                    break;
                }
                case 2: {
                    statusLabel.setText(resources.getString("status.suspended"));
                    System.out.println("Recognition suspended");
                    break;
                }
            }

        }
        if(arg.getClass() == forIfString.getClass()) {      // if thread returning result string
            String recognitionResult = recognitionThread.getResultString();
            String code = Replacer.replaceWords(recognitionResult);
            //inputPlainText.appendText(code);
            InputHelper.inputHelp(code, inputPlainText);
        }

    }

    @FXML
    public void openFile() {
        fileChooser.setTitle("Open Pascal File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Pascal files", "*.pas"));
        File openFile = fileChooser.showOpenDialog(Main.stage);
        if (openFile != null) {
            String line = null;
            StringBuffer records = new StringBuffer(0);

            // wrap a BufferedReader around FileReader
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(openFile));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            // use the readLine method of the BufferedReader to read one line at a time.
            // the readLine method returns null when there is nothing else to read.
            try {
                while ((line = bufferedReader.readLine()) != null)
                {
                    records.append(line);
                    records.append('\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // close the BufferedReader when we're done
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            inputPlainText.setText(records.toString());
        }

        /*
        */
    }

    @FXML
    public void saveFile() {
        //TODO добавить флаг проверки сохранен ли файл перед закрытием
        fileChooser.setTitle("Save Pascal File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Pascal files", "*.pas"));
        File saveFile = fileChooser.showSaveDialog(Main.stage);

        if(saveFile != null) {
            FileWriter fw = null;
            try {
                fw = new FileWriter(saveFile);
                fw.write(inputPlainText.getText());
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @FXML
    public void closeFile() {
        //TODO был ли сохранен файл
        inputPlainText.setText("");
    }

    @FXML
    public void exitProgram() {
        Platform.exit();
    }

    @FXML
    public void aboutAuthor() {
        //TODO дописать текст
        // Create the custom dialog.
        Dialog dialog = new Dialog<>();
        dialog.setTitle("About Author");
        // Set the icon (must be included in the project).
        //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

// Set the button types
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new Label("Author Bakalenko V S"), 0, 0);

        dialog.getDialogPane().setContent(grid);

        // Traditional way to get the response value.
        Optional result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Ok author dialog");
        }
    }

    @FXML
    public void aboutProgram() {
        //TODO дописать текст
        // Create the custom dialog.
        Dialog dialog = new Dialog<>();
        dialog.setTitle("About Program");
        // Set the icon (must be included in the project).
        //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

// Set the button types
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new Label("Program AIO Coding"), 0, 0);

        dialog.getDialogPane().setContent(grid);

        // Traditional way to get the response value.
        Optional result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Ok program dialog");
        }
    }
}
