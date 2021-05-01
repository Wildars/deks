package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import main.Main;
import main.MyListener;
import model.Fruit;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MarketController implements Initializable {
    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Label fruitNameLable;

    @FXML
    private Label fruitPriceLabel;

    @FXML
    private ImageView fruitImg;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private List<Fruit> fruits = new ArrayList<>();
    private Image image;
    private MyListener myListener;

    private List<Fruit> getData() {
        List<Fruit> fruits = new ArrayList<>();
        Fruit fruit;

        fruit = new Fruit();
        fruit.setName("Twix");
        fruit.setPrice(2.99);
        fruit.setImgSrc("/img/twix.jpg");
        fruit.setColor("6A7324");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Snickers");
        fruit.setPrice(3.99);
        fruit.setImgSrc("/img/snickers.jpg");
        fruit.setColor("A7745B");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Mars");
        fruit.setPrice(1.50);
        fruit.setImgSrc("");
        fruit.setColor("F16C31");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Bounty");
        fruit.setPrice(0.99);
        fruit.setImgSrc("");
        fruit.setColor("291D36");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Albeni");
        fruit.setPrice(4.99);
        fruit.setImgSrc("");
        fruit.setColor("22371D");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Oreo");
        fruit.setPrice(2.99);
        fruit.setImgSrc("");
        fruit.setColor("FB5D03");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("MilkyWay");
        fruit.setPrice(0.99);
        fruit.setImgSrc("");
        fruit.setColor("80080C");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("KitKat");
        fruit.setPrice(0.99);
        fruit.setImgSrc("");
        fruit.setColor("FFB605");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("Skittles");
        fruit.setPrice(0.99);
        fruit.setImgSrc("");
        fruit.setColor("5F060E");
        fruits.add(fruit);

        fruit = new Fruit();
        fruit.setName("M&M");
        fruit.setPrice(1.99);
        fruit.setImgSrc("");
        fruit.setColor("E7C00F");
        fruits.add(fruit);

        return fruits;
    }

    private void setChosenFruit(Fruit fruit) {
        fruitNameLable.setText(fruit.getName());
        fruitPriceLabel.setText(Main.CURRENCY + fruit.getPrice());
        image = new Image(getClass().getResourceAsStream(fruit.getImgSrc()));
        fruitImg.setImage(image);
        chosenFruitCard.setStyle("-fx-background-color: #" + fruit.getColor() + ";\n" +
                "    -fx-background-radius: 30;");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fruits.addAll(getData());
        if (fruits.size() > 0) {
            setChosenFruit(fruits.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Fruit fruit) {
                    setChosenFruit(fruit);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < fruits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(fruits.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}