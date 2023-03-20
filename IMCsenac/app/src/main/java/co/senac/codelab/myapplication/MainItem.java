package co.tiagoaguiar.codelab.myapplication;

public class MainItem {

     private int id;
     private int drawableId;
     private int textStringID;
     private int color;

    public MainItem (int id, int drawableId, int textStringID , int color) {
        this.id = id;
        this.drawableId = drawableId;
        this.textStringID = textStringID;
        this.color = color;
    }

    public void setColor(int color){
         this.color = color;
     }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTextStringID(int textStringID) {
        this.textStringID = textStringID;
    }

    public int getColor() {
        return color;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public int getTextStringID() {
        return textStringID;
    }
}
