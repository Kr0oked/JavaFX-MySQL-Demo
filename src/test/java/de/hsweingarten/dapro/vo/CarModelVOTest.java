package de.hsweingarten.dapro.vo;

import org.junit.Before;
import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import static org.junit.Assert.*;

public class CarModelVOTest {

    CarModelVO carModel;
    int testInt = 0;
    String testString = "Test";
    float testFloat = 0.0f;

    @Before
    public void setUp() throws Exception {
        carModel = new CarModelVO();
    }

    @Test
    public void testGetterAndSetter() {
        carModel.setId(testInt);
        carModel.setDescription(testString);
        carModel.setManufacturer(testString);
        carModel.setCarType(testString);
        carModel.setSeats(testInt);
        carModel.setKW(testInt);
        carModel.setFuel(testString);
        carModel.setPricePerDay(testFloat);
        carModel.setPricePerKM(testFloat);
        carModel.setAxes(testInt);
        carModel.setLoadVolume(testInt);
        carModel.setLoadCapacity(testInt);
        carModel.setDriverLicense(testString);

        assertEquals(testInt, carModel.getId());
        assertEquals(testString, carModel.getDescription());
        assertEquals(testString, carModel.getManufacturer());
        assertEquals(testString, carModel.getCarType());
        assertEquals(testInt, carModel.getSeats());
        assertEquals(testInt, carModel.getKW());
        assertEquals(testString, carModel.getFuel());
        assertEquals(testFloat, carModel.getPricePerDay(), 0.0f);
        assertEquals(testFloat, carModel.getPricePerKM(), 0.0f);
        assertEquals(testInt, carModel.getAxes());
        assertEquals(testInt, carModel.getLoadVolume());
        assertEquals(testInt, carModel.getLoadCapacity());
        assertEquals(testString, carModel.getDriverLicense());

        assertEquals(testInt, carModel.idProperty().get());
        assertEquals(testString, carModel.descriptionProperty().get());
        assertEquals(testString, carModel.manufacturerProperty().get());
        assertEquals(testString, carModel.carTypeProperty().get());
        assertEquals(testInt, carModel.seatsProperty().get());
        assertEquals(testInt, carModel.kWProperty().get());
        assertEquals(testString, carModel.fuelProperty().get());
        assertEquals(testFloat, carModel.pricePerDayProperty().get(), 0.0f);
        assertEquals(testFloat, carModel.pricePerKMProperty().get(), 0.0f);
        assertEquals(testInt, carModel.axesProperty().get());
        assertEquals(testInt, carModel.loadVolumeProperty().get());
        assertEquals(testInt, carModel.loadCapacityProperty().get());
        assertEquals(testString, carModel.driverLicenseProperty().get());
    }

    @Test
    public void testBeanProperties() {
        try {
            BeanInfo info = Introspector.getBeanInfo(CarModelVO.class);
            PropertyDescriptor[] pds = info.getPropertyDescriptors();
            assertEquals(13 + 1, pds.length);
        } catch (IntrospectionException e) {
            fail();
        }
    }
}