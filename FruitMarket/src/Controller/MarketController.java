package Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import main.Main;
import main.MyListener;
import model.Chokolate;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MarketController implements Initializable {
    private Chokolate selectedChokolade;
    private ArrayList<Chokolate> selectedChokoList = new ArrayList<>();
    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Label fruitNameLable;

    @FXML
    private Label fruitPriceLabel;
    @FXML
    private Label totalprice;

    @FXML
    private ImageView fruitImg;
    @FXML
    private Label market;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    @FXML
    private void addToCard(ActionEvent event) {
        event.consume();
        selectedChokoList.add(selectedChokolade);
        double totalPriceh = 0;
        for (int i = 0; i < selectedChokoList.size(); i++) {
            if(selectedChokoList.get(i)!=null)
            totalPriceh = totalPriceh + selectedChokoList.get(i).getPrice();

        }
        totalprice.setText(String.valueOf(totalPriceh));
        System.out.println("menya tyknuli");
        ;
    }

    @FXML
    private void buy(ActionEvent event) {
        event.consume();
        System.out.println("menya kupili");

    }

    private List<Chokolate> chokolates = new ArrayList<>();
    private Image image;
    private MyListener myListener;

    private List<Chokolate> getData() {
        List<Chokolate> chokolates = new ArrayList<>();
        Chokolate chokolate;

        chokolate = new Chokolate();
        chokolate.setName("Twix");
        chokolate.setPrice(2.99);
        chokolate.setImgSrc("/img/twix.jpg");
        chokolate.setColor("6A7324");
        chokolates.add(chokolate);

        chokolate = new Chokolate();
        chokolate.setName("Snickers");
        chokolate.setPrice(2.50);
        chokolate.setImgSrc("/img/snickers.jpg");
        chokolate.setColor("A7745B");
        chokolates.add(chokolate);

        chokolate = new Chokolate();
        chokolate.setName("Mars");
        chokolate.setPrice(1.50);
        chokolate.setImgSrc("/img/mars.jpg");
        chokolate.setColor("F16C31");
        chokolates.add(chokolate);

        chokolate = new Chokolate();
        chokolate.setName("Bounty");
        chokolate.setPrice(0.99);
        chokolate.setImgSrc("/img/bounty.jpg");
        chokolate.setColor("291D36");
        chokolates.add(chokolate);

        chokolate = new Chokolate();
        chokolate.setName("Albeni");
        chokolate.setPrice(4.99);
        chokolate.setImgSrc("/img/Albeni.jpg");
        chokolate.setColor("22371D");
        chokolates.add(chokolate);

        chokolate = new Chokolate();
        chokolate.setName("Oreo");
        chokolate.setPrice(2.99);
        chokolate.setImgSrc("");
        chokolate.setColor("FB5D03");
        chokolates.add(chokolate);

        chokolate = new Chokolate();
        chokolate.setName("MilkyWay");
        chokolate.setPrice(0.99);
        chokolate.setImgSrc("");
        chokolate.setColor("80080C");
        chokolates.add(chokolate);

        chokolate = new Chokolate();
        chokolate.setName("KitKat");
        chokolate.setPrice(0.99);
        chokolate.setImgSrc("");
        chokolate.setColor("FFB605");
        chokolates.add(chokolate);

        chokolate = new Chokolate();
        chokolate.setName("Skittles");
        chokolate.setPrice(0.99);
        chokolate.setImgSrc("");
        chokolate.setColor("5F060E");
        chokolates.add(chokolate);

        chokolate = new Chokolate();
        chokolate.setName("");
        chokolate.setPrice(1.99);
        chokolate.setImgSrc("");
        chokolate.setColor("E7C00F");
        chokolates.add(chokolate);

        return chokolates;
    }

    private void setChosenChokolate(Chokolate chokolate) {
        fruitNameLable.setText(chokolate.getName());
        fruitPriceLabel.setText(Main.CURRENCY + chokolate.getPrice());
        image = new Image(getClass().getResourceAsStream(chokolate.getImgSrc()));
        fruitImg.setImage(image);
        chosenFruitCard.setStyle("-fx-background-color: #" + chokolate.getColor() + ";\n" +
                "    -fx-background-radius: 30;");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        market.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error Dialog");
//                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
//                alert.setResizable(true);
//                alert.setContentText("Ooops, there was an error!");
//
//                alert.showAndWait();


                MyDialog dialog = new MyDialog(selectedChokoList);
                dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
                dialog.showAndWait();

                event.consume();
            }
        });

        chokolates.addAll(getData());
        if (chokolates.size() > 0) {
            setChosenChokolate(chokolates.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Chokolate chokolate) {
                    setChosenChokolate(chokolate);
                    selectedChokolade = chokolate;
                    System.out.println("kikikik" + chokolate.getName() + chokolate.getPrice());
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < chokolates.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(chokolates.get(i), myListener);

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
