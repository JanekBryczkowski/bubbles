package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    public Stage stage;
    public Timeline timeline;
    List<Circle> circles;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        AnchorPane root = new AnchorPane();
        root.setMaxWidth(1000);
        root.setPrefWidth(1000);
        root.setMinWidth(1000);
        root.setMaxHeight(600);
        root.setMinHeight(600);
        root.setPrefHeight(600);

        circles = new ArrayList<>();
        timeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
            for (int i = 0; i < 100; i++) {
                Circle circle = new Circle(Math.random() * 1000, Math.random() * 600, Math.random() * 50, new Color(0, 0, 0, 0));
                circle.setStroke(new Color(Math.random(), Math.random(), Math.random(), 1));
                circle.setStrokeWidth(4);
                circles.add(circle);
                root.getChildren().add(circle);
            }
            moveCircles();
            makeCirclesBigger();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


        stage.setScene(new Scene(root, 1000, 600));
        stage.show();

        timeline = new Timeline(new KeyFrame(Duration.millis(20), event -> {

            moveCircles();
            makeCirclesBigger();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    private void moveCircles() {
        for (int i = 0; i < circles.size(); i++) {
            double distance = 0.15 * (76 - circles.get(i).getRadius()) + 2;
            circles.get(i).setCenterX(circles.get(i).getCenterX() + distance);
            if (circles.get(i).getCenterX() > 701) {
                circles.get(i).setCenterY(circles.get(i).getCenterY() + 2);
            } else {
                circles.get(i).setCenterY(circles.get(i).getCenterY() - 2);
            }
        }
    }

    private void makeCirclesBigger() {
        for (int i = 0; i < circles.size(); i++) {
            circles.get(i).setRadius(circles.get(i).getRadius() + 0.5);
            if (circles.get(i).getRadius() > 75) {
                for (int j = 0; j < 3; j++) {
                    Circle circle = new Circle(circles.get(i).getCenterX() + Math.random() * 150 - 10,
                            circles.get(i).getCenterY() + Math.random() * 150 - 10, Math.random() * 20,
                            new Color(0, 0, 0, 0));
                    circle.setStroke(new Color(Math.random(), Math.random(), Math.random(), 1));
                    circle.setStrokeWidth(3);
                    circles.add(circle);
                    ((AnchorPane) stage.getScene().getRoot()).getChildren().add(circle);
                }
                ((AnchorPane) stage.getScene().getRoot()).getChildren().remove(circles.get(i));
                circles.remove(circles.get(i));
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
