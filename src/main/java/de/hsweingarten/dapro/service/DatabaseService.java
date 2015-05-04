package de.hsweingarten.dapro.service;

import de.hsweingarten.dapro.vo.CarModelVO;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DatabaseService implements IDatabaseService {

    @Inject
    IMySQLService mySQLService;

    private final String DISTINCT_DESCRIPTION = "SELECT DISTINCT description AS value FROM car_model";
    private final String DISTINCT_MANUFACTURER = "SELECT DISTINCT manufacturer AS value FROM car_model";
    private final String DISTINCT_CAR_TYPE = "SELECT DISTINCT type AS value FROM car_type";
    private final String DISTINCT_SEATS = "SELECT DISTINCT seats AS value FROM car_model";
    private final String DISTINCT_FUEL = "SELECT DISTINCT fuel AS value FROM car_model";

    private final String CAR_MODELS_WITH_SEATS =
            "SELECT " +
                "m.ID, " +
                "m.description, " +
                "m.manufacturer, " +
                "a.type, " +
                "m.seats, " +
                "m.kw, " +
                "m.fuel, " +
                "m.price_per_day, " +
                "m.price_per_km, " +
                "m.axes, " +
                "m.load_volume, " +
                "m.load_capacity, " +
                "m.driver_license " +
            "FROM car_model m " +
            "INNER JOIN car_type a ON m.car_type_id = a.ID " +
            "WHERE m.description LIKE IFNULL(?, '%') " +
            "AND m.manufacturer LIKE IFNULL(?, '%') " +
            "AND a.type LIKE IFNULL(?, '%') " +
            "AND m.seats = ? " +
            "AND m.fuel LIKE IFNULL(?, '%')";

    private final String CAR_MODELS_WITHOUT_SEATS =
            "SELECT " +
                    "m.ID, " +
                    "m.description, " +
                    "m.manufacturer, " +
                    "a.type, " +
                    "m.seats, " +
                    "m.kw, " +
                    "m.fuel, " +
                    "m.price_per_day, " +
                    "m.price_per_km, " +
                    "m.axes, " +
                    "m.load_volume, " +
                    "m.load_capacity, " +
                    "m.driver_license " +
                    "FROM car_model m " +
                    "INNER JOIN car_type a ON m.car_type_id = a.ID " +
                    "WHERE m.description LIKE IFNULL(?, '%') " +
                    "AND m.manufacturer LIKE IFNULL(?, '%') " +
                    "AND a.type LIKE IFNULL(?, '%') " +
                    "AND m.fuel LIKE IFNULL(?, '%')";

    /**
     * Loads the values for filtering the Car Models
     *
     * @return The loaded values in a HashMap
     */
    @Override
    public HashMap<String, Collection<String>> getColumnValues() {
        HashMap<String, Collection<String>> list = new HashMap<>();
        list.put("description", getResultValuesAsList(DISTINCT_DESCRIPTION));
        list.put("manufacturer", getResultValuesAsList(DISTINCT_MANUFACTURER));
        list.put("carType", getResultValuesAsList(DISTINCT_CAR_TYPE));
        list.put("seats", getResultValuesAsList(DISTINCT_SEATS));
        list.put("fuel", getResultValuesAsList(DISTINCT_FUEL));
        return list;
    }

    /**
     * Loads a filtered List of Car Models
     *
     * @param filters Filters in a HashMap
     * @return filtered List of Car Models
     */
    @Override
    public Collection<CarModelVO> getCarList(HashMap<String, String> filters) {
        try {
            LinkedList<CarModelVO> list = new LinkedList<>();
            Connection connection = mySQLService.getConnection();
            PreparedStatement stmt;
            if (filters.get("seats") == null) {
                stmt = connection.prepareStatement(CAR_MODELS_WITHOUT_SEATS);
                stmt.setString(1, filters.get("description"));
                stmt.setString(2, filters.get("manufacturer"));
                stmt.setString(3, filters.get("carType"));
                stmt.setString(4, filters.get("fuel"));
            }
            else {
                stmt = connection.prepareStatement(CAR_MODELS_WITH_SEATS);
                stmt.setString(1, filters.get("description"));
                stmt.setString(2, filters.get("manufacturer"));
                stmt.setString(3, filters.get("carType"));
                stmt.setInt(4, Integer.parseInt(filters.get("seats")));
                stmt.setString(5, filters.get("fuel"));
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CarModelVO carModel = new CarModelVO();
                carModel.setId(rs.getInt("ID"));
                carModel.setDescription(rs.getString("description"));
                carModel.setManufacturer(rs.getString("manufacturer"));
                carModel.setCarType(rs.getString("type"));
                carModel.setSeats(rs.getInt("seats"));
                carModel.setKW(rs.getInt("kw"));
                carModel.setFuel(rs.getString("fuel"));
                carModel.setPricePerDay(rs.getFloat("price_per_day"));
                carModel.setPricePerKM(rs.getFloat("price_per_km"));
                carModel.setAxes(rs.getInt("axes"));
                carModel.setLoadVolume(rs.getInt("load_volume"));
                carModel.setLoadCapacity(rs.getInt("load_capacity"));
                carModel.setDriverLicense(rs.getString("driver_license"));
                list.add(carModel);
            }
            return list;
        } catch (SQLException e) {
            return new ArrayList<>();
        } catch (ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Executes a prepared Statement and returns a List with the returned values.
     * If an error occurs an empty list is returned.
     *
     * @param sqlStatement prepared SQL-Statement
     * @return list with the returned values
     */
    private LinkedList<String> getResultValuesAsList(String sqlStatement) {
        try {
            LinkedList<String> list = new LinkedList<>();
            Connection connection = mySQLService.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sqlStatement);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("value"));
            }
            return list;
        } catch (SQLException e) {
            return new LinkedList<>();
        } catch (ClassNotFoundException e) {
            return new LinkedList<>();
        }
    }

}
