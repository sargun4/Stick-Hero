package com.application.stickhero;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;
//Observer pattern used
public class MainSceneController {
    @FXML
    private CheckBox musicCheckbox;
    @FXML
    private Button playButton;
    @FXML
    private Label highestPoints;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label berriesCountLabel;
    @FXML
    private Label moneyLabel;
    private Shop shopController;

    private GameProgress gameProgress = new GameProgress(); // Instantiate GameProgress

    @FXML
    private void initialize() {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), playButton);
        translateTransition.setByY(-3);
        translateTransition.setAutoReverse(true);
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), playButton);
        scaleTransition.setToX(1.02);
        scaleTransition.setToY(1.02);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);

        ParallelTransition parallelTransition = new ParallelTransition(translateTransition, scaleTransition);
        parallelTransition.play();

        musicCheckbox.setSelected(App.musicPlaying);
        highestPoints.setText(String.valueOf(gameProgress.getHighestScore()));
        highestPoints.setAlignment(Pos.CENTER_LEFT);

        if (shopController != null) {
            berriesCountLabel.setText("Berries: " + shopController.getBerriesCount());
        }


    }

    public void setShopController(Shop shopController) {
        this.shopController = shopController;
    }

    @FXML
    private void toggleMusic() {
        if (musicCheckbox.isSelected()) {
            App.playMusic();
        } else {
            App.pauseMusic();
        }
    }
    private Scene mainScene;
    @FXML
    private void switchToPlayScene() throws IOException {
        Stage stage = (Stage) musicCheckbox.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("GameScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        mainScene = scene;
        stage.setScene(scene);
    }


    @FXML
    private void switchToHelpScene() throws IOException {
        FXMLLoader helpLoader = new FXMLLoader(App.class.getResource("HelpScene.fxml"));
        Scene helpScene = new Scene(helpLoader.load());

        HelpSceneController helpSceneController = helpLoader.getController();
        helpSceneController.initialize();

        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setScene(helpScene);
    }


    @FXML
    private void switchToShopScene() throws IOException {
        System.out.println("Switching to Shop Scene");
        Stage stage = (Stage) musicCheckbox.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Shop.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Shop shopController = fxmlLoader.getController();
        shopController.setGameProgress(gameProgress);

        stage.setScene(scene);
    }

    @FXML
    private void switchToAvatarSelectionScene() throws IOException {
        FXMLLoader avatarLoader = new FXMLLoader(App.class.getResource("AvatarSelection.fxml"));
        Scene avatarScene = new Scene(avatarLoader.load());

        AvatarSelectionController avatarController = avatarLoader.getController();

        avatarController.setMainSceneController(this);

        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setScene(avatarScene);
    }

    public Scene getMainScene() {
        return mainScene;
    }

    @FXML
    private ImageView avatarImageView;

    @FXML
    public void applyAvatar(String avatarImageName) {
        String imagePath = "/com/application/stickhero/" + avatarImageName;
        Image avatarImage = new Image(Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm());
        avatarImageView.setImage(avatarImage);
    }


    private AvatarSelectionController avatarSelectionController;
    private HelpSceneController helpSceneController;
    private Shop shopSceneController;
    public void setAvatarSelectionController(AvatarSelectionController avatarSelectionController) {
        this.avatarSelectionController = avatarSelectionController;
    }
    public void setHelpSceneController(HelpSceneController helpSceneController) {
        this.helpSceneController = helpSceneController;
    }
    public void setShopSceneController(Shop shopSceneController) {
        this.shopSceneController = shopSceneController;
    }
    @FXML
    private ImageView adImageView;
    @FXML
    private CheckBox noAdsCheckbox;

    @FXML
    private void toggleNoAds() {
        if (noAdsCheckbox.isSelected()) {
            adImageView.setVisible(false);
            System.out.println("Ads disabled");
        } else {
            adImageView.setVisible(true);
            System.out.println("Ads enabled");
        }
    }
    private GameOver mainSceneController;

    public void setMainSceneController(GameOver mainSceneController) {
        this.mainSceneController = mainSceneController;
    }
}
