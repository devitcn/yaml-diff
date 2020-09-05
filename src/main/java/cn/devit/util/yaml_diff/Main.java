/*
 * Copyright © 2020 lxb (lxbzmy@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.devit.util.yaml_diff;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    try {
      System.out.println("application started.");
      URL uri = WebViewController.class
          .getResource("BasicApplication_css.fxml");
      FXMLLoader fxmlLoader = new FXMLLoader(uri);
      Scene scene = new Scene(fxmlLoader.load(), 800, 600);
      scene.getStylesheets().add(
          getClass().getResource("BasicApplication.css").toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.show();
      WebViewController controller = fxmlLoader.getController();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
