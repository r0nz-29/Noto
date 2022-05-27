package com.raunits.noto;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;

public class MarkdownEditor extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        final Parser parser = new Parser();
        GridPane window = new GridPane();
        TextArea textArea = new TextArea();
        WebView displayArea = new WebView();
        String placeholder = "# Start typing...";

        textArea.setText(placeholder);
        window.add(textArea, 0, 0);

        displayArea.getEngine().loadContent("<h1>Start typing...</h1>");
        window.add(displayArea, 1, 0);

        textArea.setWrapText(true);
        textArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String markdown = textArea.getText();
                System.out.println(markdown);
                String html = parser.parse(markdown);
                displayArea.getEngine().loadContent(html);
                System.out.println(html);
            }
        });

        Scene scene = new Scene(window);
        stage.setTitle("Noto");
        stage.setScene(scene);
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}