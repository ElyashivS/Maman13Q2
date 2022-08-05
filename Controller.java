import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * This class represents the controller of the calculator.
 */
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

    private boolean isFirstNum = true;
    private String btnTxt = "";
    private double firstNum;
    private double secondNum;
    private double result;
    private boolean isDPPressed = false;
    private boolean isPMPressed = false;
    private String operator = "";
    private Button[] btns;

    @FXML
    public void initialize() {
        txt.setText("0");
        btns = new Button[18];
        String[] buttonsNames = new String[]{"CE", "+/-", "+", "7", "8", "9", "-", "4", "5", "6",
                "*","1","2","3","/","0",".","="};
        for (int i = 0; i < 18; i++) {
            btns[i] = new Button(buttonsNames[i]); // Name the buttons
        }

        // Initialize the size of the buttons
        btns[0].setPrefSize(hbox0.getPrefWidth() / 2, hbox0.getPrefHeight());
        btns[15].setPrefSize(hbox4.getPrefWidth() / 2, hbox4.getPrefHeight());
        btns[16].setPrefSize(hbox4.getPrefWidth() / 4, hbox4.getPrefHeight());
        btns[17].setPrefSize(hbox4.getPrefWidth() / 4, hbox4.getPrefHeight());

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
        for (Button btn : btns) {
            btn.setOnAction(this::handleButton);
        }
    }

    // The function handleButton handle the event that happened when a button is pressed.
        private void handleButton(ActionEvent event) {

        if (((Button) event.getSource()).getText().matches("[0-9]+")) { // Checks if pressed button is a number
            if (isPMPressed) {
                btnTxt = btnTxt.replace("-", "");
                btnTxt += ((Button) event.getSource()).getText();
                btnTxt = "-" + btnTxt;
                txt.setText(btnTxt);
            } else if (operator.equals("")) {
                btnTxt += ((Button) event.getSource()).getText();
                txt.setText(btnTxt);
            } else {
                if (isFirstNum) {
                    btnTxt = "";
                    isDPPressed = false;
                    isFirstNum = false;
                }
                btnTxt += ((Button) event.getSource()).getText();
                txt.setText(btnTxt);
            }
        }

        if ((((Button) event.getSource()).getText().equals("+") ||
                ((Button) event.getSource()).getText().equals("/") ||
                ((Button) event.getSource()).getText().equals("*") ||
                ((Button) event.getSource()).getText().equals("-")) && (!operator.equals(""))) {
            equalPressed();
        }

        switch (((Button) event.getSource()).getText()) {
            case "+" -> plusPressed();
            case "-" -> minusPressed();
            case "*" -> multPressed();
            case "/" -> dividePressed();
            case "=" -> equalPressed();
            case "." -> decimalPointPressed();
            case "+/-" -> plusMinusPressed();
            case "CE" -> CEPressed();
        }
    }

    // The 4 functions below, handle the 5 arithmetic operations; +, -, *, /, =.
    private void plusPressed() {
        operator = "+";
        isPMPressed();
        isPMPressed = false;
    }

    private void minusPressed() {
        operator = "-";
        isPMPressed();
        isPMPressed = false;
    }

    private void multPressed() {
        operator = "*";
        isPMPressed();
        isPMPressed = false;
    }

    private void dividePressed() {
        operator = "/";
        isPMPressed();
        isPMPressed = false;
    }

    private void equalPressed() {
        if (btnTxt.contains("-")) {
            secondNum = Double.parseDouble(btnTxt.replace("-", ""));
            secondNum = secondNum * (-1);
        } else
            secondNum = Double.parseDouble(btnTxt);
        switch (operator) {
            case "+" -> result = firstNum + secondNum;
            case "-" -> result = firstNum - secondNum;
            case "*" -> result = firstNum * secondNum;
            case "/" -> result = firstNum / secondNum;
        }

        // Checks if ".0" should be removed
        if (result % 1 == 0) {
            btnTxt = String.valueOf(result).substring(0, String.valueOf(result).length() - 2);
        } else {
            btnTxt = String.valueOf(result);
        }
        txt.setText(btnTxt);

        // Max numbers that can appear on screen as result is 12
        if (String.valueOf(result).length() > 12) {
            btnTxt = String.valueOf(result).substring(0, 12);
            txt.setText(btnTxt);
        }

        if (txt.getText().contains("-")) {
            btnTxt = String.valueOf(result);
            txt.setText(String.valueOf(result));
        }

        // Save the current number that appears on screen
        double temp = Double.parseDouble(btnTxt);
        CEPressed();
        if (temp % 1 == 0) {
            btnTxt = String.valueOf(temp).substring(0, String.valueOf(temp).length() - 2);
        } else {
            btnTxt = String.valueOf(temp);
        }
        if (temp < 0)
            isPMPressed = true;
        txt.setText(btnTxt);
    }

    // This function handle decimal point function
    private void decimalPointPressed() {
        if (!isDPPressed) {
            btnTxt += ".";
            txt.setText(btnTxt);
            isDPPressed = true;
        }
    }

    // This function handle plus minus function
    private void plusMinusPressed() {
        if (!isPMPressed) {
            btnTxt = "-" + btnTxt;
            isPMPressed = true;
        } else {
            btnTxt = btnTxt.replace("-", "");
            isPMPressed = false;
        }
        txt.setText(btnTxt);
    }

    // This function checks if plus minus pressed, and handle it if so.
    private void isPMPressed() {
        if (isPMPressed) {
            firstNum = Double.parseDouble(btnTxt.replace("-", ""));
            firstNum = firstNum * (-1);
        } else {
            firstNum = Double.parseDouble(btnTxt);
        }
    }

    // Reset all
    private void CEPressed() {
        isFirstNum = true;
        btnTxt = "";
        firstNum = 0;
        secondNum = 0;
        result = 0;
        isDPPressed = false;
        isPMPressed = false;
        operator = "";
        txt.setText("0");
    }
}
