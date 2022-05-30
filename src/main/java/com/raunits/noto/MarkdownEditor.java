package com.raunits.noto;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class MarkdownEditor extends Application {
    private static final GridPane window = new GridPane();
    private static final TextArea textArea = new TextArea();
    private static final Parser parser = new Parser();
    private final WebView displayArea = new WebView();
    private static final ToolBar toolBar = new ToolBar();
    private static final VBox textAreaContainer = new VBox(toolBar);


    @Override
    public void start(Stage stage) throws IOException {
        String placeholder = "# Start typing...";
        String savedMarkdown = Files.readString(Path.of("/home/raunits/.noto"));

        addToolbarButtons();

        textAreaContainer.getChildren().add(textArea);
        textAreaContainer.setFillWidth(true);
        VBox.setVgrow(textArea, Priority.ALWAYS);

        displayArea.setContextMenuEnabled(false);
        textArea.setWrapText(true);
        textArea.setFont(Font.font("JetBrains Mono"));

        responsivize();

        // add styles
        displayArea.getEngine().setUserStyleSheetLocation(String.format("data:, %s", CSS.stylesheet));
//        textArea.setStyle("-fx-focus-color: -fx-inner-border ; -fx-faint-focus-color: -fx-inner-border ;");

        if (savedMarkdown != null) {
            textArea.setText(savedMarkdown);
            displayArea.getEngine().loadContent(parser.parse(savedMarkdown));
        } else {
            textArea.setText(placeholder);
            displayArea.getEngine().loadContent("<h1>Start typing...</h1>");
        }

        window.add(textAreaContainer, 0, 0);
        window.add(displayArea, 1, 0);
        addEventListener();

//        redirectLinks();

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

    public void responsivize() {
        ColumnConstraints textAreaConstraints = new ColumnConstraints();
        textAreaConstraints.setPercentWidth(35);
        ColumnConstraints displayAreaConstraints = new ColumnConstraints();
        displayAreaConstraints.setPercentWidth(65);
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

    public void addToolbarButtons() {
        Button hideEditor = new Button("Hide Editor");
        Button bold = new Button("Bold");
        Button italic = new Button("Italic");
        Button theme = new Button("Toggle theme");
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