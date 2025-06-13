package game2048;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Game2048App extends Application {

    private GameController controller;

    @Override
    public void start(Stage primaryStage) {
        // Starting screen
        StackPane startRoot = new StackPane();
        Button startButton = new Button("Start Game");
        startButton.setFont(new Font("Arial", 24));
        startRoot.getChildren().add(startButton);
        Scene startScene = new Scene(startRoot, 600, 650);

        // Game screen
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        Label scoreLabel = new Label("Score: 0");
        scoreLabel.setFont(new Font("Arial", 24));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setPrefSize(600, 600);
        grid.setMinSize(600, 600);
        grid.setMaxSize(600, 600);

        // Create the controller without restart handler
        controller = new GameController(grid, scoreLabel, null);

        root.getChildren().addAll(scoreLabel, grid);
        Scene gameScene = new Scene(root, 600, 700);

        startButton.setOnAction(e -> {
            primaryStage.setScene(gameScene);
            root.requestFocus();
        });

        gameScene.setOnKeyPressed((KeyEvent event) -> {
            controller.handleKey(event.getCode());
        });

        primaryStage.setResizable(false);
        primaryStage.setTitle("2048 Game");
        primaryStage.setScene(startScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
