package de.hsweingarten.dapro.view.overview;

import de.hsweingarten.dapro.vo.CarModelVO;

import java.util.Collection;
import java.util.HashMap;

public interface ICarRentalOverviewView {

    /**
     * Updates the CarList
     *
     * @param cars Car Models which will replace the existing ones in the list
     */
    void updateCarList(Collection<CarModelVO> cars);

    /**
     * Updates the DescriptionValuesList
     *
     * @param descriptions Strings which will replace the existing ones in the list
     */
    void updateDescriptionValuesList(Collection<String> descriptions);

    /**
     * Updates the ManufacturerValuesList
     *
     * @param manufacturers Strings which will replace the existing ones in the list
     */
    void updateManufacturerValuesList(Collection<String> manufacturers);

    /**
     * Updates the CarTypeValuesList
     *
     * @param carTypes Strings which will replace the existing ones in the list
     */
    void updateCarTypeValuesList(Collection<String> carTypes);

    /**
     * Updates the SeatsValuesList
     *
     * @param seats Strings which will replace the existing ones in the list
     */
    void updateSeatsValuesList(Collection<String> seats);

    /**
     * Updates the FuelValuesList
     *
     * @param fuels Strings which will replace the existing ones in the list
     */
    void updateFuelValuesList(Collection<String> fuels);

    /**
     * Returns a HashMap with Filters for filtering the Car Models
     *
     * @return HashMap with Filters
     */
    HashMap<String, String> getFilters();
}
