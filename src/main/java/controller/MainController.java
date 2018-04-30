package controller;

import entity.Notebook;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.NotebookService;
import service.hibernate.NotebookServiceImpl;
import service.jdbc.NBService;

import java.sql.Date;
import java.time.LocalDate;

public class MainController {

    public enum FindMode{
        Text, Date, Price, TextAndPrice, TextAndDate, DateAndPrice, All
    }

          private NotebookService service;
          private Stage mainStage;
          private Notebook currentNotebook;
          private FindMode mode;

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
        service = new NotebookServiceImpl();
        notebooks = service.getAll();

        fldDate.setValue(LocalDate.now());
        fldDateTo.setValue(LocalDate.now());
        fldDateFrom.setValue(LocalDate.of(2010,1,1));
        fldPriceFrom.setText("0");
        fldPriceTo.setText("100000");


        clnNum.setCellValueFactory( new PropertyValueFactory<Notebook, Long>("id"));
        clnSerial.setCellValueFactory( new PropertyValueFactory<Notebook, String>("serial"));
        clnVendor.setCellValueFactory( new PropertyValueFactory<Notebook, String>("vendor"));
        clnModel.setCellValueFactory( new PropertyValueFactory<Notebook, String>("model"));
        clnDate.setCellValueFactory( new PropertyValueFactory<Notebook, Date>("date"));
        clnPrice.setCellValueFactory( new PropertyValueFactory<Notebook, Double>("price"));
        tblList.setItems(notebooks);
    }

    public void setMainStage(Stage mainStage){
        this.mainStage = mainStage;
    }

    private boolean inputValidation(
            String serial,
            String vendor,
            String model,
            LocalDate date,
            String price
    ){
        String mesage = "Incorect input :\n";

        if (serial.length() == 0)
                mesage += "- serial\n";
        if (vendor.length() == 0)
            mesage += "- vendor\n";
        if (model.length() == 0)
            mesage += "- model\n";
        if (price.length() == 0)
            mesage += "- price\n";
        if (date == null)
            mesage += "- date\n";

        try {
            Double num = Double.parseDouble(price);
            if (num < 0)
                mesage += "- price is below zero \n";
        }catch (NumberFormatException e){
            mesage += "- price is not number \n";
        }



        if (mesage.length()>17){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Incorrect input!");
            alert.initOwner(mainStage);
            alert.setHeaderText(mesage);
            alert.showAndWait();
            return false;
        }

    return true;

    }

    private void refresh(){
        notebooks = service.getAll();
        tblList.setItems(notebooks);
    }

    private void refreshAndClear(){
        refresh();
        fldSerial.clear();
        fldVendor.clear();
        fldModel.clear();
        fldDate.setValue(LocalDate.now());
        fldPrice.clear();
    }

    @FXML
    private void btnNewOnAction(){

        if (inputValidation(
                    fldSerial.getText(),
                    fldVendor.getText(),
                    fldModel.getText(),
                    fldDate.getValue(),
                    fldPrice.getText() )){

            service.save(new Notebook(
                    fldSerial.getText(),
                    fldVendor.getText(),
                    fldModel.getText(),
                    Date.valueOf( fldDate.getValue() ),
                    Double.parseDouble(fldPrice.getText())
            ));
        }
        refresh();

    }

    @FXML
    private void tblListOnMouseClick(){
        currentNotebook = tblList.getSelectionModel().getSelectedItem();
        fldSerial.setText(currentNotebook.getSerial());
        fldVendor.setText(currentNotebook.getVendor());
        fldModel.setText(currentNotebook.getModel());
        fldDate.setValue( currentNotebook.getDate().toLocalDate() );
        fldPrice.setText(currentNotebook.getPrice().toString());
    }

    @FXML
    private void btnUpdateOnAction(){
        if (currentNotebook != null) {
            if (inputValidation(
                    fldSerial.getText(),
                    fldVendor.getText(),
                    fldModel.getText(),
                    fldDate.getValue(),
                    fldPrice.getText())) {

                service.update(new Notebook(
                        currentNotebook.getId(),
                        fldSerial.getText(),
                        fldVendor.getText(),
                        fldModel.getText(),
                        Date.valueOf(fldDate.getValue()),
                        Double.parseDouble(fldPrice.getText())
                ));
            }
            refresh();
        }
    }

    @FXML
    private void btnDeleteOnAction(){
        if(currentNotebook != null){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete notebook");
            alert.initOwner(mainStage);
            alert.setHeaderText("Are you sure delete " + currentNotebook + " ???");
            alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    service.delete(currentNotebook);
                    refreshAndClear();
                }


                if (alert.getResult() == ButtonType.CANCEL) {

                }

        }
    }

    private void defineFindMode(){

        if(fldFind.getText().length() > 0){


            mode = FindMode.All;


        }

    }

    private boolean correctPriceInput(String price){
        try {
            Double res = Double.parseDouble(price);
            if (res < 0)
                return false;
        }catch (NumberFormatException e){
            System.out.println("price not correct");
            return false;
        }
        System.out.println("price correct " + price);
        return true;
    }

    private boolean correctDateInput(Date date){

        if (date==null) {
            System.out.println("date not correct");
            return false;
        }
        System.out.println("date correct " + date);
        return true;
    }

    @FXML
    private void btnFindOnAction(){
//        notebooks = service.findByModel(fldFind.getText());
//        notebooks.addAll(service.findByVendor(fldFind.getText()));
//        if (correctPriceInput(fldPriceFrom.getText()) &
//                correctDateInput( Date.valueOf( fldDateFrom.getValue()) )) {
//            notebooks.addAll(service.findByPriceAndYear(
//                    Double.parseDouble(fldPriceFrom.getText()) ,
//                    Date.valueOf( fldDateFrom.getValue())
//            ));
//        }

//        if (correctPriceInput(fldPriceFrom.getText()) &
//                correctPriceInput(fldPriceTo.getText()) &
//                correctDateInput( Date.valueOf( fldDateTo.getValue())) &
//                !fldFind.getText().equals(""))
//        {
//            notebooks=service.findBetweenPriceLtDateBtVendor(
//                    Double.parseDouble(fldPriceFrom.getText()) ,
//                    Double.parseDouble(fldPriceTo.getText()) ,
//                    Date.valueOf( fldDateTo.getValue()),
//                    fldFind.getText()
//            );
//        }

//        notebooks = service.findBetweenDate(
//                Date.valueOf( fldDateFrom.getValue()),
//                Date.valueOf( fldDateTo.getValue())
//        );

        tblList.setItems(notebooks);
    }


}
