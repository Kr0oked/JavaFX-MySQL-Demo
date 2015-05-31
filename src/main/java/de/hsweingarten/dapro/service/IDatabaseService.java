package de.hsweingarten.dapro.service;

import de.hsweingarten.dapro.vo.CarModelVO;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;

/**
 * Interface of DatabaseService for Dependency Injection
 */
public interface IDatabaseService {

    /**
     * Loads the values for filtering the Car Models
     *
     * @return The loaded values in a HashMap
     */
    HashMap<String, Collection<String>> getColumnValues();

    /**
     * Loads a filtered List of Car Models
     *
     * @param filters Filters in a HashMap
     * @return filtered List of Car Models
     */
    Collection<CarModelVO> getCarList(HashMap<String, String> filters);

    /**
     * Reserves a Car and returns a Status Message
     *
     * @param customerId
     * @param carModelId
     * @param startDate
     * @param endDate
     * @return Status Message
     */
    String reserveCar(int customerId, int carModelId, LocalDate startDate, LocalDate endDate);
}
