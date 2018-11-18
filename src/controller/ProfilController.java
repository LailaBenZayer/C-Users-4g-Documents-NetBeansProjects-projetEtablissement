/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classe.Profil;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.ProfilService;

/**
 * FXML Controller class
 *
 * @author 4g
 */
public class ProfilController implements Initializable {

    ProfilService ps=new  ProfilService();
    ObservableList<Profil> profilList = FXCollections.observableArrayList();
    private static int index;
    
    @FXML
    private TextField code;
    @FXML
    private TextField libelle;
    @FXML
    private TableView<Profil> profils;
    @FXML
    private TableColumn<Profil, String> cCode;
    @FXML
    private TableColumn<Profil, String> cLibelle;
    
     @FXML
     private void save(ActionEvent event)
     {
         String c=code.getText().toString();
         String l=libelle.getText().toString();
         
         ps.create(new Profil(c, l));
         load();
         clean();
     }
     
     @FXML
    private void update() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de modification");
        alert.setContentText("Vous vous vraiment modifier ce profil?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Profil p2 = ps.findById(index);
            
            p2.setCode(code.getText());
            p2.setLibelle(libelle.getText());
            ps.update(p2);
            profilList.clear();
            load();
            clean();
        }
    }
     
      @FXML
    private void delete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de suppression");
        alert.setContentText("Vous vous vraiment supprimer ce profil?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ps.delete(ps.findById(index));
            profilList.clear();
            load();
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        load();
        
        profils.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TablePosition pos = (TablePosition) profils.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                Profil item = profils.getItems().get(row);
                code.setText(item.getCode());
                libelle.setText(item.getLibelle());
                index = item.getId();
                load();
            }
        });
        
    }    
    
    
    public void clean()
    {
        code.setText("");
        libelle.setText("");
    }
    public void load() {
        profilList.clear();
        cCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        cLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));

        for (Profil p : ps.findAll()) {
            profilList.add(new Profil(p.getId(), p.getCode(), p.getLibelle()));
        }

        profils.setItems(profilList);
    }   
    

}
