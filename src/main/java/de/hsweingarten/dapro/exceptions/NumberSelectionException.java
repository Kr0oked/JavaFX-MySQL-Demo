package de.hsweingarten.dapro.exceptions;

/**
 * Exception if a invalid Number was entered
 */
public class NumberSelectionException extends NullPointerException {
    public NumberSelectionException() {
        super("Invalid Number Input");
    }
}
