package de.hsweingarten.dapro.exceptions;

/**
 * Exception if no Row was selected in a Table View
 */
public class ModelSelectionException extends NullPointerException {
    public ModelSelectionException() {
        super("Invalid Model Input");
    }
}
