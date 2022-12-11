package unisa.diem.seproject.model.extensions;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TextField;

/**
 * Class that extends the JavaFX TextField class to allow only numbers to be entered
 */
public class NumberTextField extends TextField {

    private final NumberFormat nf;
    private final ObjectProperty<BigDecimal> number = new SimpleObjectProperty<>();

    public final void setNumber(BigDecimal value) {
        number.set(value);
    }

    public ObjectProperty<BigDecimal> numberProperty() {
        return number;
    }

    public NumberTextField() {
        this(BigDecimal.ZERO);
    }

    public NumberTextField(BigDecimal value) {
        this(value, NumberFormat.getInstance());
        initHandlers();
    }

    public NumberTextField(BigDecimal value, NumberFormat nf) {
        super();
        this.nf = nf;
        initHandlers();
        setNumber(value);
    }

    /**
     * Initialize listener for properties changes
     */
    private void initHandlers() {
        setOnAction(arg0 -> parseAndFormatInput());
        focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                parseAndFormatInput();
            }
        });
        numberProperty().addListener((observable, oldValue, newValue) -> setText(nf.format(newValue)));
    }

    /**
     * Parses input checking that only numbers are entered
     */
    private void parseAndFormatInput() {
        try {
            String input = getText();
            if (input == null || input.length() == 0) {
                return;
            }
            Number parsedNumber = nf.parse(input);
            BigDecimal newValue = new BigDecimal(parsedNumber.toString());
            setNumber(newValue);
            selectAll();
        } catch (ParseException ex) {
            setText(nf.format(number.get()));
        }
    }
}
