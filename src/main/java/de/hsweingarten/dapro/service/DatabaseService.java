package de.hsweingarten.dapro.service;

import de.hsweingarten.dapro.entities.CarEntity;
import de.hsweingarten.dapro.entities.CarModelEntity;
import de.hsweingarten.dapro.entities.CustomerEntity;
import de.hsweingarten.dapro.entities.ReservationEntity;
import de.hsweingarten.dapro.vo.CarModelVO;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Performs Query to communicate with a Database
 */
public class DatabaseService implements IDatabaseService {

    @Inject
    IHibernateService hibernateService;

    private final String DISTINCT_DESCRIPTION = "select distinct car.description from CarModelEntity car";
    private final String DISTINCT_MANUFACTURER = "select distinct car.manufacturer from CarModelEntity car";
    private final String DISTINCT_CAR_TYPE = "select distinct car.type from CarTypeEntity car";
    private final String DISTINCT_SEATS = "select distinct car.seats from CarModelEntity car";
    private final String DISTINCT_FUEL = "select distinct car.fuel from CarModelEntity car";

    /**
     * Loads the values for filtering the Car Models
     *
     * @return The loaded values in a HashMap
     */
    @Override
    public HashMap<String, Collection<String>> getColumnValues() {
        Session session = hibernateService.getSessionFactory().openSession();

        Query descriptionQuery = session.createQuery(DISTINCT_DESCRIPTION);
        Query manufacturerQuery = session.createQuery(DISTINCT_MANUFACTURER);
        Query carTypeQuery = session.createQuery(DISTINCT_CAR_TYPE);
        Query seatsQuery = session.createQuery(DISTINCT_SEATS);
        Query fuelQuery = session.createQuery(DISTINCT_FUEL);

        HashMap<String, Collection<String>> list = new HashMap<>();
        list.put("description", descriptionQuery.list());
        list.put("manufacturer", manufacturerQuery.list());
        list.put("carType", carTypeQuery.list());
        list.put("seats", seatsQuery.list());
        list.put("fuel", fuelQuery.list());

        session.close();

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
        Session session = hibernateService.getSessionFactory().openSession();
        Criteria criteria = buildCarListCriteria(session, filters);
        List entityList = criteria.list();
        session.close();

        ArrayList<CarModelVO> modelList = new ArrayList<>();

        for (int i = 0; i < entityList.size(); i++) {
            CarModelEntity entity = (CarModelEntity) entityList.get(i);

            CarModelVO carModel = new CarModelVO();
            carModel.setId(entity.getId());
            carModel.setDescription(entity.getDescription());
            carModel.setManufacturer(entity.getManufacturer());
            carModel.setCarType(entity.getCarType().getType());
            carModel.setSeats(entity.getSeats());
            carModel.setKW(entity.getKw());
            carModel.setFuel(entity.getFuel());
            carModel.setPricePerDay(entity.getPricePerDay());
            carModel.setPricePerKM(entity.getPricePerKm());
            carModel.setAxes(entity.getAxes());
            carModel.setLoadVolume(entity.getLoadVolume());
            carModel.setLoadCapacity(entity.getLoadCapacity());
            carModel.setDriverLicense(entity.getDriverLicense());

            modelList.add(carModel);
        }

        return modelList;
    }

    /**
     * Reserves a Car and returns a Status Message
     *
     * @param customerId The ID of the Customer in the Database
     * @param carModelId The ID of the Car Model in the Database
     * @param startDate Date where the Reservation starts
     * @param endDate Date where the Reservation ends
     * @return Status Message
     */
    @Override
    public String reserveCar(int customerId, int carModelId, LocalDate startDate, LocalDate endDate) {
        Session session = hibernateService.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        tx.setTimeout(5);

        try {
            Timestamp startTimestamp = localDateToTimestamp(startDate);
            Timestamp endTimestamp = localDateToTimestamp(endDate);

            CustomerEntity customer = (CustomerEntity) session.get(CustomerEntity.class, customerId);
            if (customer == null) {
                session.close();
                return "Invalid Customer ID";
            }

            CarModelEntity carModel = (CarModelEntity) session.get(CarModelEntity.class, carModelId);
            if (carModel == null) {
                session.close();
                return "Invalid Car Model ID";
            }

            if (!isCarModelReservable(session, carModel, startTimestamp, endTimestamp)) {
                session.close();
                return "No free Car in this period";
            }

            createReservation(session, customer, carModel, startTimestamp, endTimestamp);

            session.flush();
            tx.commit();

            return "Successfully created new Reservation";
        }
        catch (RuntimeException exception) {
            if (tx.isActive()) {
                tx.rollback();
            }
        }
        finally {
            if (session.isOpen()) {
                session.clear();
                session.close();
            }
        }

        return "An Error occurred while creating new Reservation";
    }

    /**
     * Builds the Criteria for loading a filtered List of Car Models
     *
     * @param session Hibernate Session
     * @param filters Filters in a HashMap
     * @return Criteria for loading a filtered List of Car Models
     */
    private Criteria buildCarListCriteria(Session session, HashMap<String, String> filters) {
        Criteria criteria = session.createCriteria(CarModelEntity.class, "carModel");
        criteria.createAlias("carModel.carType", "carType");

        Iterator it = filters.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> pair = (Map.Entry)it.next();
            if (pair.getValue() != null) {
                criteria.add(Restrictions.like(pair.getKey(), pair.getValue()));
            }
        }

        String criteriaString = filters.get("description");
        if (criteriaString != null) {
            criteria.add(Restrictions.like("description", filters.get("description")));
        }

        return criteria;
    }

    /**
     * Creates a new Reservation Entity and saves it
     *
     * @param session Hibernate Session
     * @param customer Customer Entity
     * @param carModel Car Model Entity
     * @param start Date where the Reservation starts
     * @param end Date where the Reservation ends
     */
    private void createReservation(Session session, CustomerEntity customer, CarModelEntity carModel,
                                   Timestamp start, Timestamp end) {
        ReservationEntity reservation = new ReservationEntity();
        reservation.setCustomer(customer);
        reservation.setCarModel(carModel);
        reservation.setStart(start);
        reservation.setEnd(end);

        session.save(reservation);
    }

    /**
     * Converts a LocalDate to a Timestamp
     *
     * @param date Date to convert
     * @return Converted Timestamp
     */
    private Timestamp localDateToTimestamp(LocalDate date) {
        return Timestamp.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Checks if at least one Car of the specified Car Model can be reserved in a specified period
     *
     * @param session Hibernate Session
     * @param carModel Model of the Car which has to be reserved
     * @param start Timestamp where the period starts
     * @param end Timestamp where the period ends
     * @return
     */
    private boolean isCarModelReservable(Session session, CarModelEntity carModel, Timestamp start, Timestamp end) {
        long carCount = (long) session.createCriteria(CarEntity.class).add(Restrictions.eq("carModel", carModel)).
                setProjection(Projections.rowCount()).uniqueResult();

        List<ReservationEntity> reservationList = session.createCriteria(ReservationEntity.class).
                add(Restrictions.eq("carModel", carModel)).list();

        long reservationCount = 0;
        for (ReservationEntity reservation : reservationList) {
            Timestamp startRes = reservation.getStart();
            Timestamp endRes = reservation.getEnd();

            if (startRes.equals(start) ||
                    (startRes.after(start) && startRes.before(end)) ||
                    (endRes.after(start) && endRes.before(end)) ||
                    (startRes.before(start) && endRes.after(end))) {
                reservationCount++;
            }
        }

        return (reservationCount > carCount);
    }
}
