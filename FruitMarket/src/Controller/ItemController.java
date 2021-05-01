package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.Main;
import main.MyListener;
import model.Chokolate;

public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;
    @FXML
    private Label buys;;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(chokolate);
    }

    private Chokolate chokolate;
    private MyListener myListener;

    public void setData(Chokolate chokolate, MyListener myListener) {
        this.chokolate = chokolate;
        this.myListener = myListener;
        nameLabel.setText(chokolate.getName());
        priceLable.setText(Main.CURRENCY + chokolate.getPrice());
        Image image = new Image(getClass().getResourceAsStream(chokolate.getImgSrc()));
        img.setImage(image);
    }
}
