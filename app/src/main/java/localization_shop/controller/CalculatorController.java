package localization_shop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import localization_shop.model.Calculator;
import localization_shop.model.Language;

public class CalculatorController {

    public HBox langButtonBox;
    public Label lblResult;
    public Label lblPrice;
    public Label lblQuantity;
    public Button btnCalc;
    public Button btnAdd;
    public Label lblResultNum;
    public TextField textFieldQuantity;
    public TextField textFieldPrice;

    private Language currentLanguage = new Language();
    Calculator calculator = new Calculator();

    @FXML
    public void initialize() {
        setLanguage("en");

        Button engButton = createLanguageButton("English", "en");
        Button sweButton = createLanguageButton("Swedish", "sv");
        Button jpaButton = createLanguageButton("日本語", "ja");
        Button araButton = createLanguageButton("العربية", "ar");

        langButtonBox.getChildren().addAll(engButton, sweButton, jpaButton, araButton);

        lblResultNum.setText("");
    }

    private Button createLanguageButton(String text, String locale) {
        Button button = new Button(text);
        button.setOnAction(e -> setLanguage(locale));
        HBox.setMargin(button, new javafx.geometry.Insets(5, 10, 5, 10));
        return button;
    }

    private void setLanguage(String locale) {
        currentLanguage.setLanguage(locale);
        lblResult.setText(""); // Clear previous result

        // Update all UI text
        lblQuantity.setText(currentLanguage.getString("itemNumberPrompt"));
        lblPrice.setText(currentLanguage.getString("itemPricePrompt"));
        lblResult.setText(currentLanguage.getString("totalCostMessage"));
        btnAdd.setText(currentLanguage.getString("addItemPrompt"));
        btnCalc.setText(currentLanguage.getString("calcItemPrompt"));
    }

    public void calculateTotal(ActionEvent actionEvent) {
        lblResultNum.setText(Double.toString(calculator.getTotal()));
        System.out.println("Total cost: " + calculator.getTotal());
    }

    public void addToTotal(ActionEvent actionEvent) {
        calculator.getCurrentItemPrice(textFieldPrice.getText());
        calculator.getCurrentItemQuantity(textFieldQuantity.getText());
        textFieldPrice.setText("");
        textFieldQuantity.setText("");
    }
}
