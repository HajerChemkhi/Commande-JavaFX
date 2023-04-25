/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.project.commande;

import DBCnx.MyConnection;

import static DBCnx.MyConnection.MyConnection;

import com.edu.project.entities.commande;
import com.edu.project.entities.commande;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.WriterException;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author islem
 */
public class commandeController implements Initializable {

    private Connection cnx;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    @FXML
    private Button updatebutton;  
    
    @FXML
    private Button addbutton;


    @FXML
    private Button updatebutton1;
    @FXML
    private ListView<commande> listfourni;
    @FXML
    private TextField date_commandetf;
    @FXML
    private TextField date_livraisontf;
    @FXML
    private TextField totaltf;
    @FXML
    private TextField prix_livraisontf;
    @FXML
    private Button btnbrowse;
    @FXML
    private Button btnstat;

    
private static final String EXPECTED_STRING_DATE = "Aug 1, 2018 12:00 PM";
private static final String DATE_FORMAT = "MMM d, yyyy HH:mm a";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
            showcommandess();
            //showRec();
            //searchRec();
    }

    public ObservableList<commande> getcommandeList() {
        ObservableList<commande> compteList = FXCollections.observableArrayList();
        Connection conn = MyConnection();
        String query = "SELECT * FROM commande";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            commande commande;
            String dateString = "2023-04-25"; // example string date
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // create a date format
            Date date = null;

            while (rs.next()) {
           
           try {
              date = (Date) dateFormat.parse(dateString); // parse the string date to a Date type
               } catch (ParseException e) {
                 e.printStackTrace();
                }

            System.out.println(date);
       
    }}  catch (SQLException ex) {
            Logger.getLogger(commandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
   return compteList;
 }
    /*/////////////////////////////////////////////////Controle de saisie/////////////////////////////////////////////////////////////////////*/
    private boolean validateFields() {
        if (date_commandetf.getText().isEmpty()) {
            showAlert("Error", "Commande Date field is required.");
            return false;
        }

        if (date_livraisontf.getText().isEmpty()) {
            showAlert("Error", "Delevery Date field is required.");
            return false;
        }
        if (totaltf.getText().isEmpty()) {
            showAlert("Error", "Total field is required.");
            return false;
        }
        if (prix_livraisontf.getText().isEmpty()) {
            showAlert("Error", "Price field is required.");
            return false;
        }
        return true;
    }

    public void showcommandess(){
        listfourni.setStyle("-fx-background-color: transparent;");
        try{
            listfourni.getItems().clear();
            Connection conn = MyConnection();
            ResultSet rs = conn.createStatement().executeQuery("select * from commande");

            while (rs.next()) {
            listfourni.getItems().add(new commande(rs.getInt("id"), rs.getDate("date_commande"), rs.getDate("date_livraison"), rs.getInt("total"), rs.getInt("prix_livraison")));
            }
       
            listfourni.setCellFactory(new Callback<ListView<commande>, ListCell<commande>>() {
                @Override
                public ListCell<commande> call(ListView<commande> param) {
                    return new ListCell<commande>() {
                        @Override
                        protected void updateItem(commande item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item != null) {
                                // Create a VBox to hold the content of the card
                                VBox card = new VBox();
                                card.setAlignment(Pos.CENTER);
                                card.setPadding(new Insets(10));
                                card.setSpacing(10);

                                // Create labels to display the cours properties
                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                String dateCommandeStr = formatter.format(item.getDateCommande());
                                Label societeLabel = new Label("date_commande: " + dateCommandeStr);

                                Label telLabel = new Label("Date_livraison: " + item.getDateLivraison());
                                Label respoLabel = new Label("Total: " + item.getTotal());
                                Label i = new Label(" prix_livraison: " + item.getPrixLivraison());
                                // Add the labels to the card
                                card.getChildren().addAll(societeLabel, telLabel, respoLabel, i );

                                // Create a HBox to hold the edit and delete buttons
                                HBox buttonsBox = new HBox();
                                buttonsBox.setAlignment(Pos.CENTER);
                                buttonsBox.setSpacing(10);

// Create the edit button
Button editButton = new Button("Edit");

editButton.setOnAction(event -> {
    commande selectedCommande = listfourni.getSelectionModel().getSelectedItem();
    if (selectedCommande != null) {
       
        String selectedDateCommandeStr = formatter.format(selectedCommande.getDateCommande());
        date_commandetf.setText(selectedDateCommandeStr);

        String selectedDateLivraisonStr = formatter.format(selectedCommande.getDateLivraison());
        date_livraisontf.setText(selectedDateLivraisonStr);

        totaltf.setText(String.valueOf(selectedCommande.getTotal()));
        prix_livraisontf.setText(String.valueOf(selectedCommande.getPrixLivraison()));

     
        listfourni.scrollTo(selectedCommande);
        listfourni.getSelectionModel().select(selectedCommande);
    }
});

                                // Create the delete button
                                Button deleteButton = new Button("Delete");
                                deleteButton.setOnAction(event -> {
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Confirmation");
                                    alert.setContentText("Are you sure you want to delete this cours?");
                                    Optional<ButtonType> result = alert.showAndWait();
                                    if (result.isPresent() && result.get() == ButtonType.OK) {
                                        try {
                                            PreparedStatement ps = null;
                                            String query = "DELETE FROM `commande` WHERE id =" + item.getId();
                                            Connection conn = MyConnection();
                                            ps = conn.prepareStatement(query);
                                            ps.execute();
                                            listfourni.getItems().remove(item);
                                        } catch (SQLException ex) {
                                            Logger.getLogger(commandeController.class.getName()).log(Level.SEVERE, null, ex);
                               }
                                    }
                                });
                                

                                // Add the HBox to the card
                                card.getChildren().add(buttonsBox);

                                // Set the cell content to the card
                                setGraphic(card);
                            } else {
                                setGraphic(null);
                            }
                        }
                    };
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
                    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void onAdd(ActionEvent event) {
        if (event.getSource() == addbutton)  {
            if (validateFields()) {

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date_commande = null ;
                Date date_livraison = null ;
                try {
                    //Date  date_commande= new Date(date_commandetf.getText());
                    date_commande = new java.sql.Date(format.parse(date_commandetf.getText()).getTime());
                    date_livraison = new java.sql.Date(format.parse(date_livraisontf.getText()).getTime());
                    System.out.println(date_commande);
                    
                } catch (ParseException e) {
                    System.out.println("Invalid date format: " );
                }

                Integer total = Integer.valueOf(totaltf.getText());
                Integer prix_livraison = Integer.valueOf(prix_livraisontf.getText());
                commande commande = listfourni.getSelectionModel().getSelectedItem();
                //String query = "update commande SET date_commande = '" + date_commande + "' ,   = '" + date_livraison + "' , total = '" +total + "', prix_livraison = '" + prix_livraison + "'  WHERE id='" + commande.getId() + "' ";
                String query = "INSERT INTO `commande` (`date_commande`, `date_livraison`, `total`, `prix_livraison`) VALUES ('" + date_commande + "', '" + date_livraison + "', " + total + ", " + prix_livraison + ")";

                executeQuery(query);
                showcommandess();
            }
        }
    }

    private void executeQuery(String query) {
        Connection conn = MyConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        }                   catch (SQLException ex) {
                                Logger.getLogger(commandeController.class.getName()).log(Level.SEVERE, null, ex);
                            }
//catch
    //            (SQLException ex) {
      //      ex.printStackTrace();

        //}//

    
    }
    @FXML
    private void handle(ActionEvent event) {
        Document document = new Document();
        try {
            // Create a temporary file with a unique name to store the PDF
            File tempFile = File.createTempFile("table", ".pdf");

            // Set the file to be deleted on exit
            tempFile.deleteOnExit();

            // Write the PDF to the temporary file
            PdfWriter.getInstance(document, new FileOutputStream(tempFile));
            document.open();

            // Add a title to the PDF
            Paragraph title = new Paragraph("commande Data");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            PdfPTable pdfTable = new PdfPTable(5);
            addTableHeader(pdfTable);
            addRows(pdfTable, listfourni);
            document.add(pdfTable);
            document.close();

            // Create a new FileChooser to allow the user to choose where to save the file
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialFileName("table.pdf");

            // Set the initial directory for the FileChooser to the user's home directory
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

            // Show the FileChooser and wait for the user to select a file
            File file = fileChooser.showSaveDialog(listfourni.getScene().getWindow());

            // If the user selected a file, copy the contents of the temporary file to the selected file
            if (file != null) {
                Files.copy(tempFile.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }

    private void addTableHeader(PdfPTable pdfTable) {
        pdfTable.addCell("ID");
        pdfTable.addCell("date_commande");
        pdfTable.addCell("date_livraison");
        pdfTable.addCell("total");
        pdfTable.addCell("prix_livraison");
    }

    private void addRows(PdfPTable pdfTable, ListView<commande> listView) {
        ObservableList<commande> items = listView.getItems();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        for (commande item : items) {
            pdfTable.addCell(String.valueOf(item.getId()));
            pdfTable.addCell(formatter.format(item.getDateCommande()));
            pdfTable.addCell(formatter.format(item.getDateLivraison()));
            pdfTable.addCell(String.valueOf(item.getTotal()));
            pdfTable.addCell(String.valueOf(item.getPrixLivraison()));

        }
    }

   

    public ObservableList<PieChart.Data> getPieChartData() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        Connection conn = MyConnection();
        String query = "SELECT date_commande, AVG(total) AS average FROM commande GROUP BY date_commande";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                String date_commande = rs.getString("date_commande");
                double average = rs.getDouble("average");
                pieChartData.add(new PieChart.Data(date_commande + " (" + average + ")", average));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pieChartData;
    }

    @FXML
    private void stat(ActionEvent event) {
        ObservableList<PieChart.Data> pieChartData = getPieChartData();
        PieChart chart = new PieChart(pieChartData);
        chart.setTitle("total by commande");
        chart.setLabelLineLength(10);
        chart.setLegendSide(Side.LEFT);
        chart.setLabelsVisible(false);
        chart.setStartAngle(180);
        Group root = new Group(chart);
        for (final PieChart.Data data : chart.getData()) {
            Label label = new Label();
            label.setText(String.format("%s (%.1f)", data.getName(), data.getPieValue()));
            label.setTranslateX(data.getNode().getBoundsInParent().getMinX());
            label.setTranslateY(data.getNode().getBoundsInParent().getMinY() - 25);
            root.getChildren().add(label);
        }
        Scene scene = new Scene(root, 500, 400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
            }
                    
    
         