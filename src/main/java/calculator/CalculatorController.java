package calculator;

import calculator.model.Calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML
    private TextField display;

    private Calculator calculator;
    private boolean startNumber = true;
    private double number1;
    private String operator = "";

    @FXML
    private void initialize() {
        calculator = new Calculator();
    }

    @FXML
    public void processDigit(ActionEvent event) {
        String digitPressed = ((Button) event.getSource()).getText();
        System.out.println(digitPressed);
        if (startNumber || display.getText().equals("0")) {
            display.setText(digitPressed);
        } else {
            display.setText(display.getText() + digitPressed);
        }
        startNumber = false;
    }

    @FXML
    public void processOperator(ActionEvent event) {
        String operatorPressed = ((Button) event.getSource()).getText();
        System.out.println(operatorPressed);
        if (operatorPressed.equals("=")) {
           if (operator.isEmpty()) {
               return;
           }
           double number2 = Double.parseDouble(display.getText());
           double result = calculator.calculate(number1, number2, operator);
           if (result == (long) result) {
               display.setText(String.format("%d", (long) result));
           } else {
               display.setText(String.format("%s", result));
           }
           operator = "";
        } else {
            if (! operator.isEmpty()) {
                return;
            }
            number1 = Double.parseDouble(display.getText());
            operator = operatorPressed;
            startNumber = true;
        }
    }

    @FXML
    public void processNegative(ActionEvent event) {
        String negativePressed = ((Button) event.getSource()).getText();
        System.out.println(negativePressed);
        if (!display.getText().equals("0")) {
            if (display.getText().charAt(0) != '-') {
                display.setText("-" + display.getText());
            } else {
                display.setText(display.getText().substring(1));
            }
        }
    }

    @FXML
    public void processDot(ActionEvent event) {
        String dotPressed = ((Button) event.getSource()).getText();
        System.out.println(dotPressed);
        if(!startNumber && !display.getText().contains(".")) {
            display.setText(display.getText() + dotPressed);
        }
    }

    @FXML
    public void processClear(ActionEvent event) {
        String clearPressed = ((Button) event.getSource()).getText();
        System.out.println(clearPressed);
        startNumber = true;
        operator = "";
        display.setText("0");
    }

}
