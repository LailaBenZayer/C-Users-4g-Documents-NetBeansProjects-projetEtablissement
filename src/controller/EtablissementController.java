/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classe.Etablissement;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.EtablissementService;
import service.ProfilService;

/**
 * FXML Controller class
 *
 * @author 4g
 */
public class EtablissementController implements Initializable {

    EtablissementService es = new EtablissementService();
    ObservableList<Etablissement> etablissemntList = FXCollections.observableArrayList();
    private static int index;

    @FXML
    private TextField nom;
    @FXML
    private TextField type;
    @FXML
    private TextField region;
    @FXML
    private TextField poursuit;
    @FXML
    private TableView<Etablissement> etablissements;
    @FXML
    private TableColumn<Etablissement, String> cNom;
    @FXML
    private TableColumn<Etablissement, String> cType;
    @FXML
    private TableColumn<Etablissement, String> cRegion;
    @FXML
    private TableColumn<Etablissement, String> cPoursuit;

    @FXML
    private void saveAction(ActionEvent event) {
        String n = nom.getText();
        String t = type.getText();
        String r = region.getText();
        String p = poursuit.getText();

        es.create(new Etablissement(n, t, r, p));
        load();
        clean();
    }

    @FXML
    private void delete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de suppression");
        alert.setContentText("Vous vous vraiment supprimer cette etablissement?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            es.delete(es.findById(index));
            load();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
        clean();
    }

    @FXML
    private void update() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de modification");
        alert.setContentText("Vous vous vraiment modifier cette etablissement?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Etablissement e2 = es.findById(index);
            e2.setNom(nom.getText());
            e2.setType(type.getText());
            e2.setRegion(region.getText());
            e2.setPoursuit(poursuit.getText());
            es.update(e2);
            load();
            clean();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
        etablissements.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TablePosition pos = (TablePosition) etablissements.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                Etablissement item = etablissements.getItems().get(row);
                nom.setText(item.getNom());
                type.setText(item.getType());
                region.setText(item.getRegion());
                poursuit.setText(item.getPoursuit());
                index = item.getId();
                load();
            }
        });
    }

    public void clean() {
        nom.setText("");
        type.setText("");
        region.setText("");
        poursuit.setText("");

    }

    public void load() {
        etablissemntList.clear();
        cNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cType.setCellValueFactory(new PropertyValueFactory<>("type"));
        cRegion.setCellValueFactory(new PropertyValueFactory<>("region"));
        cPoursuit.setCellValueFactory(new PropertyValueFactory<>("poursuit"));
        for (Etablissement e : es.findAll()) {
            etablissemntList.add(new Etablissement(e.getId(), e.getNom(), e.getType(), e.getRegion(), e.getPoursuit()));
        }

        etablissements.setItems(etablissemntList);
    }

}
