/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import classe.Employe;
import classe.Etablissement;
import classe.Etudiant;
import classe.Profil;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.EtablissementService;
import service.EtudiantService;

/**
 * FXML Controller class
 *
 * @author 4g
 */
public class EtudiantController implements Initializable {

    EtudiantService es=new EtudiantService();
    ObservableList<Etudiant> etudiantList = FXCollections.observableArrayList();
    ObservableList<Etablissement> etabList = FXCollections.observableArrayList();
    EtablissementService ets=new EtablissementService();
    private static int index;
    Date dt = new Date();
    
    @FXML
    private TextField nom;
     @FXML
    private TextField prenom;
    @FXML
    private TextField codeNational;
    @FXML
    private TextField lieuNaissance;
    @FXML
    private TextField niveauEtud;
    @FXML
    private DatePicker dateNais;
    @FXML
    private ComboBox<Etablissement> cmbEtab;
    
    @FXML
    private TableView<Etudiant> etudiants;
    @FXML
    private TableColumn<Etudiant, String> cNom;
    @FXML
    private TableColumn<Etudiant, String> cPrenom;
    @FXML
    private TableColumn<Etudiant, LocalDate> cDateNaissance;
    @FXML
    private TableColumn<Etudiant, String> cLieuNaissnace;
    @FXML
    private TableColumn<Etudiant, String> cCodeNational;
    @FXML
    private TableColumn<Etudiant, String> cNiveauEtude;
    @FXML
    private TableColumn<Etudiant, String> cEtab;
    
    
     @FXML
    private void saveAction(ActionEvent event) {
        String n = nom.getText().toString();
        String p = prenom.getText().toString();
        LocalDate d = dateNais.getValue();
        String lieu=lieuNaissance.getText().toString();
        String code=codeNational.getText().toString();
        String niveau=niveauEtud.getText().toString();
        int et=cmbEtab.getValue().getId();
        Instant instant = Instant.from(d.atStartOfDay(ZoneId.systemDefault()));
        dt = Date.from(instant);

        es.create(new Etudiant(n, p,dt,lieu,code,niveau,ets.findById(et)));
        load();
        clean();
    }
    
    @FXML
    private void delete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de suppression");
        alert.setContentText("Vous vous vraiment supprimer ce Etudiant?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            es.delete(es.findById(index));
            etudiantList.clear();
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
        alert.setContentText("Vous vous vraiment modifier ce Etudiant?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Etudiant e2 = es.findById(index);

            e2.setDtaeNaissnace(dt);
            e2.setNom(nom.getText());
            e2.setPrenom(prenom.getText());
            e2.setLieuNaissnace(lieuNaissance.getText());
            e2.setCodeNational(codeNational.getText());
            e2.setNiveauEtude(niveauEtud.getText());
            e2.setEtablissement(cmbEtab.getValue());
            Instant instant = Instant.from(dateNais.getValue().atStartOfDay(ZoneId.systemDefault()));
            dt = Date.from(instant);
            e2.setDtaeNaissnace(dt);
            
            es.update(e2);
            etudiantList.clear();
            load();
            clean();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         etabList.addAll(ets.findAll());
         cmbEtab.setItems(etabList);
         
         load();
        etudiants.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TablePosition pos = (TablePosition) etudiants.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                Etudiant item = etudiants.getItems().get(row);
                nom.setText(item.getNom());
                prenom.setText(item.getPrenom());
                lieuNaissance.setText(item.getLieuNaissnace());
                codeNational.setText(item.getCodeNational());
                niveauEtud.setText(item.getNiveauEtude());
                index = item.getId();
                //la convertion de la date a LocalDate
                Date date = item.getDtaeNaissnace();
                
//              System.out.println("date = "+date);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                SimpleDateFormat sdf = new  SimpleDateFormat("dd-MM-yyyy");
                LocalDate localDate = LocalDate.parse(sdf.format(date), formatter);
                dateNais.setValue(localDate);
                cmbEtab.setValue(item.getEtablissement());

                load();
            }
        });
        
    }    
    
    public void clean()
    {
        nom.setText("");
        prenom.setText("");
        dateNais.setValue(null);
        lieuNaissance.setText("");
        codeNational.setText("");
        niveauEtud.setText("");
        cmbEtab.setValue(null);
    }
    
     public void load() {
        etudiantList.clear();
        cNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cCodeNational.setCellValueFactory(new PropertyValueFactory<>("codeNational"));
        cNiveauEtude.setCellValueFactory(new PropertyValueFactory<>("niveauEtude"));
        cLieuNaissnace.setCellValueFactory(new PropertyValueFactory<>("lieuNaissnace"));
        cDateNaissance.setCellValueFactory(new PropertyValueFactory<>("dtaeNaissnace"));
        cEtab.setCellValueFactory(new PropertyValueFactory<>("etablissement"));
        for (Etudiant e : es.findAll()) {
            etudiantList.add(new Etudiant(e.getId(), e.getNom(), e.getPrenom(), e.getDtaeNaissnace(), e.getLieuNaissnace(),e.getCodeNational(),e.getNiveauEtude(),e.getEtablissement()));
        }

        etudiants.setItems(etudiantList);
    }
    
}
