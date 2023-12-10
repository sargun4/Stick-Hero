package com.application.stickhero;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;
//Template pattern is used
public class GameSceneController {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Rectangle block1;
    @FXML
    private Rectangle block2;
    @FXML
    private Rectangle block3;
    @FXML
    private Rectangle stick;
    @FXML
    private ImageView hero;
    @FXML
    private Button exit;
    private Timeline growingTimeline;
    private Rotate stickRotation;
    private static int score;
    private String gameState;
    private static final int BLOCK_HEIGHT = 113;
    @FXML
    private Label scoreLabel;

    @FXML
    private ImageView avatarImageView;
    public void applyAvatar(String avatarImageName) {
        String imagePath = "/com/application/stickhero/" + avatarImageName;
        Image avatarImage = new Image(Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm());
        avatarImageView.setImage(avatarImage);
    }


    @FXML
    void initialize() {

        stickRotation = new Rotate();
        stickRotation.setPivotX(stick.getX() + stick.getWidth() / 2.0);
        stickRotation.setPivotY(stick.getY() + stick.getHeight());
        stick.getTransforms().add(stickRotation);
        gameState = "waiting";
        score = 0;
        scoreLabel.setText(String.valueOf(score));
    }

    @FXML
    void startGrowing() {
        if (gameState.compareTo("waiting") == 0) {
            growingTimeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
                updateStickLength();
            }));
            growingTimeline.setCycleCount(Timeline.INDEFINITE);
            growingTimeline.play();
            gameState = "growing";
        }
    }

    private void updateStickLength() {
        stick.setY(stick.getY() - 1);
        stick.setHeight(stick.getHeight()+1);
    }

    @FXML
    private void stopGrowing() {
        if (gameState.compareTo("growing") == 0) {
            growingTimeline.stop();
            gameState = "rotating";
            rotateStick();
        }
    }

    private void flipCharacterAboutLegs() {
        double pX = hero.getX() + hero.getFitWidth() / 2.0;
        double pY = hero.getY() + hero.getFitHeight();

        Rotate rotate = new Rotate(180, pX, pY);
        hero.getTransforms().add(rotate);
    }

    private void rotateStick() {
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(30),
                        event -> {
                            stickRotation.setAngle(stickRotation.getAngle() +  3);
                        }
                )
        );
        timeline.setCycleCount(30);
        timeline.setOnFinished(event -> {
            moveHero();
        });
        timeline.play();
    }

    private void moveHero() {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(hero);
        translateTransition.setDuration(Duration.seconds(2));
        if (block1.getX() + block1.getWidth() + stick.getHeight() > block2.getLayoutX() && block1.getX() + block1.getWidth() + stick.getHeight() < block2.getLayoutX() + block2.getWidth()) {
            score++;
            scoreLabel.setText(String.valueOf(score));
            translateTransition.setOnFinished(actionEvent -> {resetGame();});
        }
        else {
            translateTransition.setOnFinished(actionEvent -> {fall();});
        }
        translateTransition.setByX(stick.getHeight() + hero.getFitWidth()/2);
        translateTransition.setInterpolator(Interpolator.LINEAR);
        gameState = "movingHero";
        translateTransition.play();
    }

    private void resetGame() {
        stick.setX(0);
        stick.setY(0);
        stick.setLayoutX(76);
        stick.setLayoutY(488);
        stick.setHeight(4);
        stickRotation.setAngle(0);
        hero.setX(0);
        hero.setY(0);
        hero.setLayoutX(37);
        hero.setLayoutY(454);
        hero.setTranslateX(0);
        randomizeWidth();
        gameState = "waiting";
    }

    private void randomizeWidth() {
        Random random = new Random();
        double minWidth = 10;
        double maxWidth = 180;
        double randomWidth = minWidth + (maxWidth - minWidth) * random.nextDouble();
        block2.setWidth(randomWidth);
        double minX = block1.getX() + block1.getWidth()+20;
        double maxX = Math.min(500 - randomWidth, minX + 250);
        double randomPositionX = minX + (maxX - minX) * random.nextDouble();
        block2.setLayoutX(randomPositionX);
    }
    private void fall() {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(hero);
        translateTransition.setDuration(Duration.seconds(1));
        translateTransition.setByY(100);
        translateTransition.setOnFinished(actionEvent -> {
            try {
                switchToMainScene();
//                switchToGameOverScene();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        translateTransition.play();
    }
    private void recordScore() {
        GameProgress.setLastScore(score);
        int h = GameProgress.getHighestScore();
        if (score > h) {
            GameProgress.setHighestScore(GameProgress.getLastScore());
        }
    }

    @FXML
    private void switchToMainScene() throws IOException {
        recordScore();
        Stage stage = (Stage) stick.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    @FXML
    private void switchToGameOverScene() throws IOException {
        recordScore();
        Stage stage = (Stage) stick.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("GameOver.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }


    private GameOver gameOvercontroller;

    public void setGameOvercontroller(GameOver gameOvercontroller) {
        this.gameOvercontroller = gameOvercontroller;
    }

    public String getGameState() {
        return gameState;
    }
}

