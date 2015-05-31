package de.hsweingarten.dapro.exceptions;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NumberSelectionExceptionTest {

    NumberSelectionException numberSelectionException;

    @Before
    public void setUp() throws Exception {
        numberSelectionException = new NumberSelectionException();
    }

    @Test
    public void testGetMessage() throws Exception {
        assertEquals("Invalid Number Input", numberSelectionException.getMessage());
    }
}