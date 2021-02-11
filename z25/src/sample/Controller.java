package sample;

import java.net.URL;
import java.util.ResourceBundle;
import java.lang.Math;

import static java.lang.Float.NaN;
import static java.lang.Float.isNaN;
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnFill;

    @FXML
    private Button btnDoTask;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnExit;

    @FXML
    private ImageView img3;

    @FXML
    private ImageView img2;

    @FXML
    private TextField TextField1;

    @FXML
    private TextField TextFiled2;

    @FXML
    private TableView<Robot> TableView1;
    @FXML
    private TableColumn<Robot, String> K;
    @FXML
    private TableColumn<Robot, String> Y;
    ObservableList<Robot> items = FXCollections.observableArrayList();
    @FXML
    void initialize() {
        TableView1.setEditable(true);
        TableView1.itemsProperty().setValue(items);
        float[] arr = new float[10];
        float[] Sum = new float[10];
        img3.setVisible(false);
        img2.setVisible(false);
        btnFill.setOnMouseClicked(mouseEvent -> {
            // Если в таблица уже заполнена, то очищаем ее.
            if (items.size() != 0) {
                for (int i = 0; i < 10; i++) {
                    items.remove(0);
                }
            }
            // Заполняем таблицу случайными числами и находим сумму К
            for (int i = 0; i < 10; i++) {
                arr[i] = ((float) (Math.random() * 25) - 15);
                items.add(new Robot(String.valueOf(arr[i]), null));
                K.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNum1()));
                K.setCellFactory(TextFieldTableCell.forTableColumn());
            }
        });
        btnDoTask.setOnMouseClicked(mouseEvent -> {
            // Если в таблица уже заполнена, то очищаем ее.
            if (items.size() != 0) {
                for (int i = 0; i < 10; i++) {
                    items.remove(0);
                }
            }
            String A = String.valueOf(TextField1.getText());
            String B = String.valueOf(TextField1.getText());
            try {
                int a = Integer.parseInt(A);
                int b = Integer.parseInt(B);
                if ((a == 0) && (b == 0)) {
                    img2.setVisible(false);
                    TextField1.clear();
                    TextFiled2.clear();
                }

                img3.setVisible(false);
                for (int i = 1; i < 10; i++) {
                    float summ = 0;
                    float pr=1;
                    for (int j = 0; j <= i - 1; j++) {
                        pr *= arr[j];
                    }
                    summ+=i;
                    Sum[i] = (float)Math.pow((Math.pow((a*a+b*b),3)*Math.pow(Math.cos(arr[i]),2))/(summ*pr),1./3);
                    if (isNaN(Sum[i])) {
                        Sum[i] = 0;
                    }
                    items.add(new Robot(String.valueOf(arr[i-1]), String.valueOf(Sum[i])));
                    Y.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNum2()));
                    Y.setCellFactory(TextFieldTableCell.forTableColumn());
                }
            } catch (NumberFormatException nfe) {
                img3.setVisible(true);
                TextField1.clear();
                TextFiled2.clear();
            }
        });
        btnClear.setOnMouseClicked(mouseEvent -> {
            if (items.size() != 0) {
                for (int i = 0; i < 10; i++) {
                    items.remove(0);
                }
            }
            img3.setVisible(false);
            img2.setVisible(false);
            TextField1.clear();
            TextFiled2.clear();
        });
        btnExit.setOnMouseClicked(mouseEvent -> {
            System.exit(0);
        });
    }
}
