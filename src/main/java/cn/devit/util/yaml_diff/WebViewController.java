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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.web.WebErrorEvent;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class WebViewController implements Initializable {

  @FXML
  Stage stage;

  @FXML
  MenuItem openFileMenuItem;

  @FXML
  WebView webView;

  String url;

  public void onMenuClick() {
    System.out.printf("choose file.");
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select Yaml files");
    fileChooser.setSelectedExtensionFilter(
        new ExtensionFilter("YAML", "*.yaml", "*.yml"));
    List<File> files = fileChooser.showOpenMultipleDialog(stage);
    YamlDiff diff = new YamlDiff();
    DiffData result = diff.diff(files);
    try {
      File report = Files.createTempFile("report", ".html").toFile();
      HtmlReport.htmlTable(result.getTable(), result.getColumns(),
          report);
      setUrl(report.getAbsoluteFile().toURI().toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    webView.getEngine().setOnError(new EventHandler<WebErrorEvent>() {
      @Override
      public void handle(WebErrorEvent arg0) {
        System.out.println(arg0);
      }
    });
    webView.getEngine()
        .setOnStatusChanged(new EventHandler<WebEvent<String>>() {
          @Override
          public void handle(WebEvent<String> arg0) {
            System.out.println(arg0);
          }
        });
  }

  public void setUrl(String string) {
    this.url = string;
    System.out.println("load url:" + url);
    webView.getEngine()
        .load(url);
  }
}
