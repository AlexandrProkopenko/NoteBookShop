package controller;

import entity.Notebook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;

public class mainController {

    @FXML private TableView<Notebook> tblList;
          private ObservableList<Notebook> notebooks;
    @FXML private TableColumn<Notebook, Long> clnNum;
    @FXML private TableColumn<Notebook, String> clnSerial;
    @FXML private TableColumn<Notebook, String> clnVendor;
    @FXML private TableColumn<Notebook, String> clnModel;
    @FXML private TableColumn<Notebook, Date> clnDate;
    @FXML private TableColumn<Notebook, Double> clnPrice;

    @FXML private TextField fldSerial;
    @FXML private TextField fldVendor;
    @FXML private TextField fldModel;
    @FXML private TextField fldPrice;
    @FXML private DatePicker fldDate;


    @FXML private TextField fldFind;
    @FXML private TextField fldPriceFrom;
    @FXML private TextField fldPriceTo;
    @FXML private DatePicker fldDateFrom;
    @FXML private DatePicker fldDateTo;

    @FXML
    private void initialize(){
        notebooks = FXCollections.observableArrayList();

        clnNum.setCellValueFactory( new PropertyValueFactory<Notebook, Long>("id"));
        clnSerial.setCellValueFactory( new PropertyValueFactory<Notebook, String>("serial"));
        clnVendor.setCellValueFactory( new PropertyValueFactory<Notebook, String>("vendor"));
        clnModel.setCellValueFactory( new PropertyValueFactory<Notebook, String>("model"));
        clnDate.setCellValueFactory( new PropertyValueFactory<Notebook, Date>("date"));
        clnPrice.setCellValueFactory( new PropertyValueFactory<Notebook, Double>("price"));
        tblList.setItems(notebooks);
    }

    @FXML
    private void btnNewOnAction(){

    }

    @FXML
    private void btnUpdateOnAction(){

    }

    @FXML
    private void btnDeleteOnAction(){

    }

    @FXML
    private void btnFindOnAction(){

    }


}
