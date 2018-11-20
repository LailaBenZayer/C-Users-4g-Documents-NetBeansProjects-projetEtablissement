/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * FXML Controller class
 *
 * @author 4g
 */
public class ImporterFichierExcelController implements Initializable {

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private ListView listView;

    public void Button1Action(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\4g\\Documents"));
        fc.getExtensionFilters().addAll(new ExtensionFilter("Excel Files", "*.xlsx"));
        File selectFile = fc.showOpenDialog(null);

        if (selectFile != null) {
//            listView.getItems().add(selectFile.getName());
            listView.getItems().add(selectFile.getAbsolutePath());
        } else {
            System.out.println("file is not valid");
        }
    }

    public void Button2Action(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\4g\\Documents"));
        fc.getExtensionFilters().addAll(new ExtensionFilter("Excel Files", "*.xlsx"));
        List<File> selectFiles = fc.showOpenMultipleDialog(null);

        if (selectFiles != null) {
            for (int i = 0; i < selectFiles.size(); i++) {
                listView.getItems().add(selectFiles.get(i).getAbsolutePath());
            }
        } else {
            System.out.println("file is not valid");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
