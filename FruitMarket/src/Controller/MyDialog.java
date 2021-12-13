package Controller;

import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Chokolate;

import java.util.List;

public class MyDialog extends Dialog<ButtonType>
{
    public MyDialog(List<Chokolate> selectedChokoList)
    {
        DialogPane dialogPane = new DialogPane();
        dialogPane.resize(600,400);
        VBox outer = new VBox();
        Button button = new Button("List");
        setResult(new ButtonType("NO", ButtonData.LEFT));
        button.setOnMousePressed(e -> close());
        ListView<Text> inner = new ListView<Text>();
        dialogPane.resize(600,400);//

        for (int i = 0; i<selectedChokoList.size(); i++){
            if(selectedChokoList.get(i)!=null){
                System.out.println("list = "+selectedChokoList.get(i).getName());
//                alert.setContentText(selectedChokoList.get(i).getName());
                inner.getItems().add(new Text(selectedChokoList.get(i).getName()));

            }

        }

        inner.setMaxHeight(300);
        outer.getChildren().addAll(button, inner);
        dialogPane.setContent(outer);
        this.setDialogPane(dialogPane);
    }

}
