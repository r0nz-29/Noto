package com.raunits.noto;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MarkdownEditor extends Application {
    private static final GridPane window = new GridPane();
    private static final TextArea textArea = new TextArea();
    private static final Parser parser = new Parser();
    private static final ToolBar toolBar = new ToolBar();
    private static final VBox textAreaContainer = new VBox(toolBar);
    private final WebView displayArea = new WebView();
    public static final BorderPane displayAreaContainer = new BorderPane();
    private static boolean textareaVisible = true;

    @Override
    public void start(Stage stage) throws IOException {
        String placeholder = "# Start typing...";
        String savedMarkdown = Files.readString(Path.of("/home/raunits/.noto"));

        displayAreaContainer.setCenter(displayArea);

        addDisplayAreaBtn();

        addToolbarButtons();

        textAreaContainer.getChildren().add(textArea);
        textAreaContainer.setFillWidth(true);
        VBox.setVgrow(textArea, Priority.ALWAYS);

        displayArea.setContextMenuEnabled(false);
        textArea.setWrapText(true);
        textArea.setFont(Font.font("JetBrains Mono"));

        responsivize(35, 65);

        // add styles
        displayArea.getEngine().setUserStyleSheetLocation(String.format("data:, %s", CSS.stylesheet));
        // textArea.setStyle("-fx-focus-color: -fx-inner-border ; -fx-faint-focus-color: -fx-inner-border ;");

        if (savedMarkdown != null) {
            textArea.setText(savedMarkdown);
            displayArea.getEngine().loadContent(parser.parse(savedMarkdown));
        } else {
            textArea.setText(placeholder);
            displayArea.getEngine().loadContent("<h1>Start typing...</h1>");
        }

        window.add(textAreaContainer, 0, 0);
        window.add(displayAreaContainer, 1, 0);
        addEventListener();

        // redirectLinks();

        Scene scene = new Scene(window);
        stage.setTitle("Noto");
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void stop() {
        try {
            FileWriter fileWriter = new FileWriter("/home/raunits/.noto");
            String markdown = textArea.getText();
            for(char c : markdown.toCharArray()) {
                fileWriter.write(c);
            }
            fileWriter.close();
            System.out.println("saved successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void responsivize(int textareaWidth, int displayareaWidth) {
        ColumnConstraints textAreaConstraints = new ColumnConstraints();
        textAreaConstraints.setPercentWidth(textareaWidth);
        ColumnConstraints displayAreaConstraints = new ColumnConstraints();
        displayAreaConstraints.setPercentWidth(displayareaWidth);
        window.getColumnConstraints().addAll(textAreaConstraints, displayAreaConstraints);

        // for responsive height
        GridPane.setVgrow(textAreaContainer, Priority.ALWAYS);
    }

    public void addEventListener() {
        textArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String markdown = textArea.getText();
                String html = parser.parse(markdown);
                displayArea.getEngine().loadContent(html);
            }
        });
    }

    public void addDisplayAreaBtn() {
        Button showEditorBtn = new Button("Show editor");
        HBox buttonPane = new HBox(12, showEditorBtn);
        buttonPane.setAlignment(Pos.CENTER_LEFT);
        displayAreaContainer.setBottom(buttonPane);

        showEditorBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                List columnConstraints = window.getColumnConstraints();
                ColumnConstraints textAreaContainerConstraint = (ColumnConstraints) columnConstraints.get(0);
                ColumnConstraints displayAreaConstraint = (ColumnConstraints) columnConstraints.get(1);
                textAreaContainerConstraint.setPercentWidth(35);
                displayAreaConstraint.setPercentWidth(65);
                textareaVisible = true;
            }
        });
    }

    public void addToolbarButtons() {
        Button hideEditor = new Button("Hide Editor");
        Button bold = new Button("Bold");
        Button italic = new Button("Italic");
        Button theme = new Button("Toggle theme");

        hideEditor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                List columnConstraints = window.getColumnConstraints();
                ColumnConstraints textAreaContainerConstraint = (ColumnConstraints) columnConstraints.get(0);
                ColumnConstraints displayAreaConstraint = (ColumnConstraints) columnConstraints.get(1);
                textAreaContainerConstraint.setPercentWidth(0);
                displayAreaConstraint.setPercentWidth(100);
                textareaVisible = false;
            }
        });

        toolBar.getItems().addAll(hideEditor, bold, italic, theme);
    }

//    public void redirectLinks() {
//        displayArea.getEngine().getLoadWorker().stateProperty().addListener((observableValue, oldState, newState) -> {
//            if (newState == Worker.State.SUCCEEDED) {
//                Document DOM = displayArea.getEngine().getDocument();
//                NodeList links = DOM.getElementsByTagName("a");
//                for (int i=0; i<links.getLength(); i++) {
//                    Node link = links.item(i);
//                    EventTarget eventTarget = (EventTarget) link;
//                    eventTarget.addEventListener("click", new EventListener() {
//                        @Override
//                        public void handleEvent(Event event) {
//                            event.preventDefault();
//                            EventTarget target = event.getCurrentTarget();
////                            HTMLAnchorElement anchorElement = (HTMLAnchorElement) target;
////                            String href = anchorElement.getHref();
//                            //handle opening URL outside JavaFX WebView
////                            System.out.println(href);
////                            evt.preventDefault();
//                        }
//                    }, false);
//                }
//            }
//        });
//    }

    public static void main(String[] args) {
        launch();
    }
}