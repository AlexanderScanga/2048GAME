package game2048;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class GameController {
    private GUIDriver board;
    private final GridPane grid;
    private final Label scoreLabel;
    private final Runnable onRestart;

    public GameController(GridPane grid, Label scoreLabel, Runnable onRestart) {
        this.grid = grid;
        this.scoreLabel = scoreLabel;
        this.onRestart = onRestart;
        this.board = new GUIDriver();
        render();
    }

    public void handleKey(KeyCode key) {
        int direction = -1;

        if (key == KeyCode.UP) direction = 0;
        else if (key == KeyCode.RIGHT) direction = 1;
        else if (key == KeyCode.DOWN) direction = 2;
        else if (key == KeyCode.LEFT) direction = 3;

        if (direction != -1) {
            boolean moved = board.move(direction);
            render();

            if (board.isGameOver()) {
                System.out.println("Game Over!");
                // Removed restart dialog and restart call
            }
        }
    }

    private void render() {
        scoreLabel.setText("Score: " + board.getScore());
        grid.getChildren().clear();

        for (int i = 0; i < GUIDriver.SIZE; i++) {
            for (int j = 0; j < GUIDriver.SIZE; j++) {
                Driver tile = board.getDriver(i, j);
                Label label = new Label(tile.toString());

                label.setMinSize(150, 150);
                label.setPrefSize(150, 150);
                label.setMaxSize(150, 150);

                label.setAlignment(Pos.CENTER);
                label.setFont(new Font("Arial", 40));
                label.setStyle("-fx-border-color: black; -fx-background-color: " + getTileColor(tile.getValue()));

                grid.add(label, j, i);
            }
        }
    }

    private String getTileColor(int value) {
        switch (value) {
            case 2: return "#eee4da";
            case 4: return "#ede0c8";
            case 8: return "#f2b179";
            case 16: return "#f59563";
            case 32: return "#f67c5f";
            case 64: return "#f65e3b";
            case 128: return "#edcf72";
            case 256: return "#edcc61";
            case 512: return "#edc850";
            case 1024: return "#edc53f";
            case 2048: return "#edc22e";
            default: return "#cdc1b4";
        }
    }
}

