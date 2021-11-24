import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import javax.swing.*;

public class Controller {

    @FXML
    private Text txt;

    @FXML
    private HBox hbox0;

    @FXML
    private HBox hbox1;

    @FXML
    private HBox hbox2;

    @FXML
    private HBox hbox3;

    @FXML
    private HBox hbox4;


    private Button[] btns;

    @FXML
    public void initialize() {
        txt.setText("0");
        btns = new Button[18];
        btns[0] = new Button ("CE");
        btns[1] = new Button("⁺∕₋");
        btns[2] = new Button("+");
        btns[3] = new Button("7");
        btns[4] = new Button("8");
        btns[5] = new Button("9");
        btns[6] = new Button("-");
        btns[7] = new Button("4");
        btns[8] = new Button("5");
        btns[9] = new Button("6");
        btns[10] = new Button("*");
        btns[11] = new Button("1");
        btns[12] = new Button("2");
        btns[13] = new Button("3");
        btns[14] = new Button("/");
        btns[15] = new Button("0");
        btns[16] = new Button(".");
        btns[17] = new Button("=");
        btns[0].setPrefSize(hbox0.getPrefWidth() / 2, hbox0.getPrefHeight());
        btns[15].setPrefSize(hbox4.getPrefWidth() / 2, hbox4.getPrefHeight());
        btns[16].setPrefSize(hbox4.getPrefWidth() / 4, hbox0.getPrefHeight());
        btns[17].setPrefSize(hbox4.getPrefWidth() / 4, hbox0.getPrefHeight());

        for (int i = 1; i < 15; i++) {
            btns[i].setPrefSize(hbox0.getPrefWidth() / 4, hbox0.getPrefHeight());
        }
        for (int i = 0; i < 3; i++) {
            hbox0.getChildren().add(i % 3, btns[i]);
        }
        for (int i = 3; i < 7; i++) {
            hbox1.getChildren().add((i + 1) % 4, btns[i]);
        }
        for (int i = 7; i < 11; i++) {
            hbox2.getChildren().add((i + 1) % 4, btns[i]);
        }
        for (int i = 11; i < 15; i++) {
            hbox3.getChildren().add((i + 1) % 4, btns[i]);
        }
        for (int i = 15; i < 18; i++) {
            hbox4.getChildren().add((i + 1) % 4, btns[i]);
        }
        for (int i = 0; i < btns.length; i++) {
            btns[i].setOnAction(this::handleButton);
        }
    }
    private void handleButton(ActionEvent event) {

    }
}
