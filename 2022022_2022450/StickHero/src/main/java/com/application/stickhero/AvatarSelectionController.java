package com.application.stickhero;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
//Strategy Pattern
public class AvatarSelectionController {

    public VBox avatarSelectionRoot;
    @FXML
    private ListView<ImageView> avatarsListView;

    @FXML
    private Button selectButton;

    private MainSceneController mainSceneController;

    public void setMainSceneController(MainSceneController mainSceneController) {
        this.mainSceneController = mainSceneController;
    }
    private String selectedAvatar;

    public String getSelectedAvatar() {
        return selectedAvatar;
    }

    @FXML
    private void initialize() {
        avatarsListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(ImageView item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    setGraphic(item);
                }
            }
        });

        avatarsListView.getItems().clear();

        avatarsListView.getItems().addAll(
                createAvatarImageView("avatar1.png"),
                createAvatarImageView("avatar2.png"),
                createAvatarImageView("avatar3.png")
        );
    }

    private ImageView createAvatarImageView(String imageName) {
        String imagePath = "/com/application/stickhero/" + imageName;
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm()));
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        imageView.setOnMouseClicked(event -> handleAvatarSelection(imageName));

        return imageView;
    }

    private void handleAvatarSelection(String selectedAvatar) {
        mainSceneController.applyAvatar(selectedAvatar);
    }

    @FXML
    private void switchToMainScene() throws IOException {
        FXMLLoader mainLoader = new FXMLLoader(App.class.getResource("MainScene.fxml"));
        Scene mainScene = new Scene(mainLoader.load());

        MainSceneController mainController = mainLoader.getController();
        mainController.setAvatarSelectionController(this);

        Stage stage = (Stage) avatarsListView.getScene().getWindow();
        stage.setScene(mainScene);
    }
}
