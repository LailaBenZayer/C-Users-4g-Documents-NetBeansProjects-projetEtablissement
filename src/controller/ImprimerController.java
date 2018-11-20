/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classe.Etudiant;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import service.EtudiantService;

/**
 * FXML Controller class
 *
 * @author 4g
 */
public class ImprimerController implements Initializable {

    EtudiantService es = new EtudiantService();
    ObservableList<Etudiant> etudiantList = FXCollections.observableArrayList();
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TableView<Etudiant> etudiants;
    @FXML
    private TableColumn<Etudiant, String> cNom;
    @FXML
    private TableColumn<Etudiant, String> cPrenom;
    @FXML
    private TableColumn<Etudiant, LocalDate> cDateNaissance;

    @FXML 
    private void findEtudiant(KeyEvent event) {
        String n = nom.getText();
        String p = prenom.getText();
        Etudiant e = es.findByNomAndPrenom(n, p);
        if (e != null) {
            etudiantList.clear();
            cNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            cPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            cDateNaissance.setCellValueFactory(new PropertyValueFactory<>("dtaeNaissnace"));
            for (Etudiant e2 : es.findAll()) {
                etudiantList.add(new Etudiant(e.getId(), e.getNom(), e.getPrenom(), e.getDtaeNaissnace()));
            }
        }
        
         etudiants.setItems(etudiantList);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //findEtudiant(event);
    }

}
