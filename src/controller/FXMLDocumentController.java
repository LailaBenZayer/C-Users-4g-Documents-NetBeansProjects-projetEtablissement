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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.EmployeService;
import service.EtablissementService;
import service.ProfilService;

/**
 *
 * @author 4g
 */
public class FXMLDocumentController implements Initializable {
    
    EmployeService es = new EmployeService();
    ObservableList<Employe> employeList = FXCollections.observableArrayList();
    ObservableList<Profil> profilList = FXCollections.observableArrayList();
    ObservableList<Etablissement> etabList = FXCollections.observableArrayList();
    ProfilService ps=new ProfilService();
    EtablissementService ets=new EtablissementService();
    private static int index;
    Date dt = new Date();
    Date dt2=new Date();
    
    @FXML
    private Label label;
    @FXML
    private TextField nom;
     @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField pass;
    @FXML
    private TextField profil;
    @FXML
    private DatePicker dateNais;
    @FXML
    private DatePicker dateEmb;
    @FXML
    private ComboBox<Profil> cmbProfil;
    @FXML
    private ComboBox<Etablissement> cmbEtab;
    
    @FXML
    private TableView<Employe> employes;
    @FXML
    private TableColumn<Employe, String> cNom;
    @FXML
    private TableColumn<Employe, String> cPrenom;
    @FXML
    private TableColumn<Employe, String> cEmail;
    @FXML
    private TableColumn<Employe, String> cPass;
    @FXML
    private TableColumn<Employe, LocalDate> cDateNaissance;
    @FXML
    private TableColumn<Employe, LocalDate> cDateEmb;
    @FXML
    private TableColumn<Employe, String> cProfil;
    @FXML
    private TableColumn<Employe, String> cEtablissement;
    
    
    @FXML
    private void saveAction(ActionEvent event) {
        try {
        String n = nom.getText().toString();
        String p = prenom.getText().toString();
        String e=email.getText().toString();
        LocalDate d = dateNais.getValue();
        LocalDate de = dateEmb.getValue();
        String pas = pass.getText().toString();
        //System.out.println(cmbProfil.getValue().getId());
        //System.out.println(cmbEtab.getValue().getId());
        int  prof=cmbProfil.getValue().getId();
        int etab=cmbEtab.getValue().getId();
        Instant instant = Instant.from(d.atStartOfDay(ZoneId.systemDefault()));
        Instant instant2 = Instant.from(de.atStartOfDay(ZoneId.systemDefault()));
        dt = Date.from(instant);
        dt2 = Date.from(instant2);
        
        //ProfilService ps=new ProfilService();
        //EtablissementService es=new EtablissementService();
       
        es.create(new Employe(n, p,e,pas,dt,dt2,ps.findById(prof),ets.findById(etab)));
       
        load();
        clean();
            
        } catch (Exception e) {
        }
        
    }
    
    @FXML
    private void delete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de suppression");
        alert.setContentText("Vous vous vraiment supprimer ce employe?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            es.delete(es.findById(index));
            employeList.clear();
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
        alert.setContentText("Vous vous vraiment modifier ce employe?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Employe e2 = es.findById(index);
            //MachinList.set(index, m2);
            
            e2.setNom(nom.getText());
            e2.setPrenom(prenom.getText());
            e2.setEmail(email.getText());
            e2.setPassword(pass.getText());
            e2.setProfil(cmbProfil.getValue());
            e2.setEtablissement(cmbEtab.getValue());
            Instant instant = Instant.from(dateNais.getValue().atStartOfDay(ZoneId.systemDefault()));
            dt = Date.from(instant);
            e2.setDateNaissance(dt);
            
            Instant instant2 = Instant.from(dateEmb.getValue().atStartOfDay(ZoneId.systemDefault()));
            dt2 = Date.from(instant2);
            e2.setDateEmbauche(dt2);
            
            es.update(e2);
            employeList.clear();
            load();
            clean();
        }
    }
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        load();
         profilList.addAll(ps.findAll());
         cmbProfil.setItems(profilList);
         
         etabList.addAll(ets.findAll());
         cmbEtab.setItems(etabList);
         
         load();
        employes.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TablePosition pos = (TablePosition) employes.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                Employe item = employes.getItems().get(row);
                nom.setText(item.getNom());
                prenom.setText(item.getPrenom());
                email.setText(item.getPrenom());
                pass.setText(item.getPrenom());
                index = item.getId();
                //la convertion de la date a LocalDate
                Date date = item.getDateNaissance();
                Date date2 = item.getDateEmbauche();
//                System.out.println("date = "+date);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                SimpleDateFormat sdf = new  SimpleDateFormat("dd-MM-yyyy");
                LocalDate localDate = LocalDate.parse(sdf.format(date), formatter);
                LocalDate localDate2 = LocalDate.parse(sdf.format(date2), formatter);
                dateNais.setValue(localDate);
                dateEmb.setValue(localDate2);
                cmbProfil.setValue(item.getProfil());
                cmbEtab.setValue(item.getEtablissement());
//                System.out.println(localDate.MIN);
                load();
            }
        });
         
         
    }    
    
     public void clean() {
        nom.setText("");
        prenom.setText("");
        dateNais.setValue(null);
        dateEmb.setValue(null);
        pass.setText("");
        email.setText("");
        cmbProfil.setValue(null);
        cmbEtab.setValue(null);
    }
     
      public void load() {
        employeList.clear();
        cNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cDateNaissance.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        cDateEmb.setCellValueFactory(new PropertyValueFactory<>("dateEmbauche"));
        cEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        cPass.setCellValueFactory(new PropertyValueFactory<>("password"));
        cProfil.setCellValueFactory(new PropertyValueFactory<>("profil"));
        cEtablissement.setCellValueFactory(new PropertyValueFactory<>("etablissement"));
        for (Employe e : es.findAll()) {
            employeList.add(new Employe(e.getId(), e.getNom(), e.getPrenom(),e.getEmail(),e.getPassword(), e.getDateNaissance(),e.getDateEmbauche(), e.getProfil(),e.getEtablissement()));
        }

        employes.setItems(employeList);
    }
    
}
