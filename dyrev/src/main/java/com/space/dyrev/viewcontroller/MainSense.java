package com.space.dyrev.viewcontroller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainSense extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            System.out.println(getClass().getResource("/"));
            Parent root = FXMLLoader.load(getClass().getResource("/view/registersense/RegisterSense.fxml"));

//            Scene scene = new Scene(root,500,522);//修改了
//            primaryStage.setScene(scene);
//            primaryStage.setResizable(false);//设置不能窗口改变大小
//            primaryStage.setTitle("一个简单的JavaFX");//设置标题
//            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
