package sample;


public class Clothes {

    String size;
    String type;
    String color;
    String quantity;

    public Clothes() //constructor
    {
        size = null;
        type = null;
        color = null;
        quantity = null;
    }

    public Clothes(String size,String type, String color, String quantity)
    {
        this.size = size;
        this.type = type;
        this.color = color;
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}
