package com.application.stickhero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.util.Objects;

public class App extends Application
{
    private static Scene scene;
    private static MediaPlayer mediaPlayer;
    public static boolean musicPlaying;
    private static void musicSetup() {
        String musicFilePath = App.class.getResource("music.mp3").toString();;
        Media media = new Media(musicFilePath);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        playMusic();
    }
    public static void playMusic() {
        musicPlaying = true;
        mediaPlayer.play();
    }
    public static void pauseMusic() {
        musicPlaying = false;
        mediaPlayer.pause();
    }
    public static Scene getScene() {
        return scene;
    }
    @Override
    public void start(Stage stage) throws IOException {
        musicSetup();

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainScene.fxml"));
        scene = new Scene(fxmlLoader.load());

        stage.setTitle("StickHero");

        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("hero.png")));
        stage.getIcons().add(icon);

        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}