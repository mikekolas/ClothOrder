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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.lang.String;

public class OrderController implements Initializable {

    private IntegerProperty index = new SimpleIntegerProperty();

    ObservableList<String> sizeList = FXCollections.observableArrayList("XS","S","M","L","XL","XXL");

    ObservableList<String> typeList = FXCollections.observableArrayList("T-shirt","Trousers","Jacket");

    ObservableList<String> colorList = FXCollections.observableArrayList("Black","Red","Blue","Green","Yellow","White");

    ObservableList<Clothes> clothesList;

    @FXML
    public TableView<Clothes> tblOrder;
    @FXML
    public ComboBox cbSize;
    @FXML
    public ComboBox cbType;
    @FXML
    public ComboBox cbColor;
    @FXML
    public TextField txtQty;

    @FXML
    public TableColumn<Clothes, String> typeCol=  new TableColumn("Type");
    @FXML
    public TableColumn<Clothes, String> sizeCol =  new TableColumn("Size");
    @FXML
    public TableColumn<Clothes, String> colorCol =  new TableColumn("Color");
    @FXML
    public TableColumn<Clothes, String> qtyCol =  new TableColumn("Quantity");

    Connection DBobj;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cbType.setItems(typeList);
        cbSize.setItems(sizeList);
        cbColor.setItems(colorList);
        txtQty.setText("");

        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        colorCol.setCellValueFactory(new PropertyValueFactory<>("color"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        clothesList = FXCollections.observableArrayList();

        DBobj = SqliteConnection.Connector();
        buildData();

        tblOrder.setItems(clothesList);
        //tblOrder.getColumns().addAll(typeCol,sizeCol,colorCol,qtyCol);
        //tblOrder.setItems(clothesList);

    }

    public void buildData()
    {
        try {
            String theQuery = "Select * from t_shirt Order By id";
            ResultSet rs = DBobj.createStatement().executeQuery(theQuery);
            while(rs.next())
            {
                Clothes cl = new Clothes();
                cl.setId(rs.getInt("id"));
                cl.setType(rs.getString("type"));
                cl.setSize(rs.getString("size"));
                cl.setColor(rs.getString("color"));
                cl.setQuantity(rs.getString("quantity"));
                clothesList.add(cl);
            }
            tblOrder.setItems(clothesList);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    public void addItem(Event event)
    {
        Clothes cl = new Clothes();
        cl.setType(cbType.getValue().toString());
        cl.setSize(cbSize.getValue().toString());
        cl.setColor(cbColor.getValue().toString());
        cl.setQuantity(txtQty.getText());

        String theQuery = "Insert into t_shirt(type,size,color,quantity) values (?,?,?,?)";

        try{
            PreparedStatement pst = DBobj.prepareStatement(theQuery);
            //int id = cl.getId();
            String size = cl.getSize();
            String type = cl.getType();
            String color = cl.getColor();
            String quantity = cl.getQuantity();

            pst.setString(1, type);
            pst.setString(2, size);
            pst.setString(3, color);
            pst.setString(4, quantity);
            pst.executeUpdate();
            clothesList.add(cl);
            //tblOrder.setItems(clothesList);
        }
        catch(Exception e){
           e.printStackTrace();
           System.out.println("Error on Adding Data");
        }
    }

    public void delItem(Event event) {
        Clothes cl = tblOrder.getSelectionModel().getSelectedItem();

        String theQuery = "Delete from t_shirt where id = ?";
        try{
            PreparedStatement pst = DBobj.prepareStatement(theQuery);

            int _id = cl.getId();

            pst.setInt(1,_id);
            pst.executeUpdate();
            clothesList.remove(cl);
            //tblOrder.setItems(clothesList);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Deleting Data");
        }
        tblOrder.getSelectionModel().clearSelection(); //deselection
    }
}
