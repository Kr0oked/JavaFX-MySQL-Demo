package de.hsweingarten.dapro.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class MySQLServiceTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private MySQLService mySQLService;

    @Before
    public void setUp() {
        mySQLService = new MySQLService();
    }

    @Test
    public void testGetConnection() throws Exception {
        exception.expect(ClassNotFoundException.class);
        mySQLService.getConnection();
    }
}