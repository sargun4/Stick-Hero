 package com.application.stickhero;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Shop {
    private MainSceneController mainSceneController;

    @FXML
    private AnchorPane rootPane;
    private int berriesCount = 0;
    @FXML
    private Label moneyLabel;
    @FXML
    private Label berriesCountLabel;
    public int getBerriesCount() {
        return berriesCount;
    }
    private GameProgress gameProgress = new GameProgress();

    @FXML
    private void initialize() {
        updateMoneyAndBerriesLabels();
    }

    public void setMainSceneController(MainSceneController mainSceneController) {
        this.mainSceneController = mainSceneController;
    }

    @FXML
    private void switchToMainScene() throws IOException {
        FXMLLoader mainLoader = new FXMLLoader(App.class.getResource("MainScene.fxml"));
        Scene mainScene = new Scene(mainLoader.load());

        MainSceneController mainController = mainLoader.getController();

        mainController.setShopSceneController(this);
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setScene(mainScene);
    }

    @FXML
    private void purchaseBerries() {
        int berryCost = 5;  // Cost of one berry

        if (gameProgress.getMoney() >= berryCost) {
            gameProgress.setMoney(gameProgress.getMoney() - berryCost);
            gameProgress.setTotalCherries(gameProgress.getTotalCherries() + 1);
            updateMoneyAndBerriesLabels();
        } else {
            System.out.println("Not enough money to purchase berries.");
        }
    }

    private void updateMoneyAndBerriesLabels() {
        moneyLabel.setText("Money: " + gameProgress.getMoney());
        berriesCountLabel.setText("Berries: " + gameProgress.getTotalCherries());
    }

    public void setGameProgress(GameProgress gameProgress) {
        this.gameProgress = gameProgress;
    }
}

