package sample;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.lang.String;

public class OrderController implements Initializable {

    private IntegerProperty index = new SimpleIntegerProperty();
    public boolean addCondition = false;

    ObservableList<String> sizeList = FXCollections.observableArrayList("XS","S","M","L","XL","XXL");

    ObservableList<String> typeList = FXCollections.observableArrayList("T-shirt","Trousers","Jacket");

    ObservableList<String> colorList = FXCollections.observableArrayList("Black","Red","Blue","Green","Yellow","White");

    ObservableList<Clothes> clothesList = FXCollections.observableArrayList(new Clothes());

    @FXML
    public TableView tblOrder;
    @FXML
    public ComboBox cbSize;
    @FXML
    public ComboBox cbType;
    @FXML
    public ComboBox cbColor;
    @FXML
    public TextField txtQty;

    @FXML
    public TableColumn typeCol=  new TableColumn("Type");
    @FXML
    public TableColumn sizeCol =  new TableColumn("Size");
    @FXML
    public TableColumn colorCol =  new TableColumn("Color");
    @FXML
    public TableColumn qtyCol =  new TableColumn("Quantity");


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cbType.setItems(typeList);
        cbSize.setItems(sizeList);
        cbColor.setItems(colorList);
        txtQty.setText("");

        typeCol.setCellValueFactory(new PropertyValueFactory<Clothes,String>("type"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<Clothes,String>("size"));
        colorCol.setCellValueFactory(new PropertyValueFactory<Clothes,String>("color"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<Clothes,String>("quantity"));

        //tblOrder.getColumns().addAll(typeCol,sizeCol,colorCol,qtyCol);
        tblOrder.setItems(clothesList);

    }

    public void addItem(Event event) {
        System.out.println(addCondition);

        if(addCondition == true)
        {
            clothesList.add(new Clothes(cbSize.getValue().toString(),cbType.getValue().toString(),cbColor.getValue().toString(),txtQty.getText()));
        }
        else
        {
            clothesList.remove(index.get());
            tblOrder.getSelectionModel().clearSelection();
            clothesList.add(new Clothes(cbSize.getValue().toString(),cbType.getValue().toString(),cbColor.getValue().toString(),txtQty.getText()));
            addCondition = true;
        }
        //tblOrder.getColumns().addAll(cbSize.getValue(),cbType.getValue(),cbColor.getValue(),txtQty.getText());

        //clothesList.add(new Clothes(cbSize.getValue().toString(),cbType.getValue().toString(),cbColor.getValue().toString(),txtQty.getText()));
        //clothesList.add(new Clothes("","","",txtQty.getText()));
    }

    public void delItem(Event event) {
        clothesList.remove(index.get());
        tblOrder.getSelectionModel().clearSelection();
    }
}
