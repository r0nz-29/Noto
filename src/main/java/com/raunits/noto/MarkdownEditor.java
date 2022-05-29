package com.raunits.noto;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MarkdownEditor extends Application {
    private final TextArea textArea = new TextArea();

    @Override
    public void start(Stage stage) throws IOException {
        final Parser parser = new Parser();

        GridPane window = new GridPane();

        // for responsive column width
        ColumnConstraints textAreaConstraints = new ColumnConstraints();
        textAreaConstraints.setPercentWidth(30);
        ColumnConstraints displayAreaConstraints = new ColumnConstraints();
        displayAreaConstraints.setPercentWidth(70);
        window.getColumnConstraints().addAll(textAreaConstraints, displayAreaConstraints);

        // for responsive height
        GridPane.setVgrow(textArea, Priority.ALWAYS);

        WebView displayArea = new WebView();
        displayArea.getEngine().setUserStyleSheetLocation(String.format("data:, %s", CSS.stylesheet));
        String placeholder = "# Start typing...";
        String savedMarkdown = Files.readString(Path.of("/home/raunits/.noto"));

        if (savedMarkdown != null) {
            textArea.setText(savedMarkdown);
            displayArea.getEngine().loadContent(parser.parse(savedMarkdown));
        } else {
            textArea.setText(placeholder);
            displayArea.getEngine().loadContent("<h1>Start typing...</h1>");
        }

        window.add(textArea, 0, 0);
        window.add(displayArea, 1, 0);

        textArea.setWrapText(true);
        textArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String markdown = textArea.getText();
                String html = parser.parse(markdown);
                displayArea.getEngine().loadContent(html);
            }
        });

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
            e.printStackTrace();;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}