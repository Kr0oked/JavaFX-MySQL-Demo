package de.hsweingarten.dapro.exceptions;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModelSelectionExceptionTest {

    ModelSelectionException modelSelectionException;

    @Before
    public void setUp() throws Exception {
        modelSelectionException = new ModelSelectionException();
    }

    @Test
    public void testGetMessage() throws Exception {
        assertEquals("Invalid Model Input", modelSelectionException.getMessage());
    }
}