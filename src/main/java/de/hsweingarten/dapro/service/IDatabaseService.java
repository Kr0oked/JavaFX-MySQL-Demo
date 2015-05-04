package de.hsweingarten.dapro.service;

import de.hsweingarten.dapro.vo.CarModelVO;

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
}
