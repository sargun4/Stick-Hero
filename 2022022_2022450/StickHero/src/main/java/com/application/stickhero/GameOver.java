package com.application.stickhero;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

import static com.application.stickhero.App.getScene;

public class GameOver {

    @FXML
    private Label highScoreLabel;
    @FXML
    private AnchorPane rootPane;

    public void initialize(int berriesCount, int highScore) {
        System.out.println("Initializing GameOver controller");
        highScoreLabel.setText("High Score: " + highScore);
    }

    @FXML
    private void returnToHome() {
        try {
            Stage stage = (Stage) rootPane.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(App.class.getResource("MainScene.fxml"));
            Scene scene = new Scene(loader.load());
            MainSceneController mainSceneController = loader.getController();
            mainSceneController.setMainSceneController(this);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void replay() {
        try {
            Stage stage = (Stage) rootPane.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(App.class.getResource("GameScene.fxml"));
            Scene scene = new Scene(loader.load());
            GameSceneController gameSceneController = loader.getController();
            gameSceneController.setGameOvercontroller(this);

            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private GameOver gameOvercontroller;

    public void setGameOvercontroller(GameOver gameOvercontroller) {
        this.gameOvercontroller = gameOvercontroller;
    }


}
