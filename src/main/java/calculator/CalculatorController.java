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
           display.setText(String.format("%.0f", result));
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
    public void processDot(ActionEvent event) {
        /**String dotProcessd = ((Button) event.getSource()).getText();
        System.out.println(dotProcessd);
        number1 = Double.parseDouble(display.getText()) /
                Double.parseDouble(String.valueOf(Math.pow(10, display.getLength())));
        display.setText(String.valueOf(number1));
         */
        String dotProcessd = ((Button) event.getSource()).getText();
        System.out.println(dotProcessd);
        number1 = Double.parseDouble(String.valueOf(display.getText())) +
                Double.parseDouble(String.valueOf(Math.pow(10, -(display.getLength()))));
        display.setText(String.valueOf(number1));
    }

    @FXML
    public void processSign(ActionEvent event){
        String signProcessd = ((Button) event.getSource()).getText();
        System.out.println(signProcessd);

        number1 = Double.parseDouble(display.getText()) * -1;
        display.setText(String.valueOf(number1));
    }

    @FXML
    public void processAC(ActionEvent event){
        String acProcessd = ((Button) event.getSource()).getText();
        System.out.println(acProcessd);
        display.setText("0");
        startNumber = true;
        operator = "";
        number1 = 0;
    }


}
