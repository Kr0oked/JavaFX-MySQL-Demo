package de.hsweingarten.dapro.view.overview;

/**
 * Mediator between the CarRentalOverview View and the Business Logic
 */
public interface ICarRentalOverviewMediator {

    /**
     * Initializes values for the View
     */
    void init();

    /**
     * Updates the Car List with filters provided from the View
     */
    void updateCarList();

    /**
     * Reserves a Car with the values provided from the View
     */
    void reserveCar();
}
