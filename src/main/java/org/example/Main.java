package org.example;

import entity.*;
import extraClassesForEntities.Feature;
import extraClassesForEntities.RatingConverter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


public class Main {

    private static SessionFactory sessionFactory;


    static {
        sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(ActorEntity.class)
                .addAnnotatedClass(AddressEntity.class)
                .addAnnotatedClass(CategoryEntity.class)
                .addAnnotatedClass(CityEntity.class)
                .addAnnotatedClass(CountryEntity.class)
                .addAnnotatedClass(CustomerEntity.class)
                .addAnnotatedClass(FilmEntity.class)
                .addAnnotatedClass(InventoryEntity.class)
                .addAnnotatedClass(LanguageEntity.class)
                .addAnnotatedClass(PaymentEntity.class)
                .addAnnotatedClass(RentalEntity.class)
                .addAnnotatedClass(StaffEntity.class)
                .addAnnotatedClass(StoreEntity.class)
                .buildSessionFactory();
    }

    public static void main(String[] args) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            newFilm();
            transaction.commit();

        }
    }

    public void createCustomer() {
        AddressEntity ae = new AddressEntity();
        ae.setAddress("564 Vosem'desyat-vos'maya");
        ae.setDistrict("Moscow");
        ae.setPostalCode("46456");
        ae.setPhone("46457456");

        CustomerEntity ce = new CustomerEntity();
        ce.setStoreId(sessionFactory.getCurrentSession().get(StoreEntity.class, (byte) 2));
        ce.setFirstName("FGhfgh");
        ce.setLastName("Bfhfhgfgh");
        ce.setEmail("dhfjksdhfk@email.com");
        ce.setAddressId(sessionFactory.getCurrentSession().get(AddressEntity.class, (short) 343));
        ce.setActive(true);
        sessionFactory.getCurrentSession().saveOrUpdate(ce);
    }

    public static void rentFilm() {
        CustomerEntity ce = sessionFactory.getCurrentSession().get(CustomerEntity.class, (short) 88);
        StoreEntity se = ce.getStoreId();
        StaffEntity ste = se.getManagerStaffId();

        RentalEntity re = new RentalEntity();
        re.setInventoryId(sessionFactory.getCurrentSession().get(InventoryEntity.class, 90));
        re.setCustomerId(ce);
        re.setStaffId(ste);
        re.setRentalDate(LocalDateTime.now());
        sessionFactory.getCurrentSession().saveOrUpdate(re);

        PaymentEntity pe = new PaymentEntity();
        pe.setCustomerId(ce);
        pe.setStaffId(ste);
        pe.setRentalId(re);
        pe.setAmount(BigDecimal.valueOf(22.77));
        pe.setPaymentDate(LocalDateTime.now());
        sessionFactory.getCurrentSession().saveOrUpdate(pe);
    }

    public static void returnFilm() {
        CustomerEntity ce = sessionFactory.getCurrentSession().get(CustomerEntity.class, (short) 88);
        InventoryEntity ie = sessionFactory.getCurrentSession().get(InventoryEntity.class, 90);
        String s = "select r.rentalId from RentalEntity r where r.customerId = :customerID and r.inventoryId = :inventoryID and r.returnDate is null";
        Query<RentalEntity> query = sessionFactory.getCurrentSession().createQuery(s);
        query.setParameter("customerID", ce);
        query.setParameter("inventoryID", ie);
        List results = query.getResultList();

        Integer rentalId = (Integer) results.get(0);
        RentalEntity re = sessionFactory.getCurrentSession().get(RentalEntity.class, rentalId);
        re.setReturnDate(LocalDateTime.now());
        sessionFactory.getCurrentSession().saveOrUpdate(re);
    }

    public static void newFilm() {
        FilmEntity fe = new FilmEntity();
        RatingConverter rc = new RatingConverter();
        Set<Feature> set = Set.of(Feature.Trailers, Feature.BehindTheScenes);
        fe.setTitle("Kakaya-to rozovaya huinya dlya podrostkov");
        fe.setDescription("A lot of korean boys and american villagers fights with each other");
        fe.setReleaseYear((short) 2023);
        fe.setLanguageId(sessionFactory.getCurrentSession().get(LanguageEntity.class, (byte) 1));
        fe.setRentalDuration((byte) 55);
        fe.setRentalRate(BigDecimal.valueOf(9.99));
        fe.setLength((short) 101);
        fe.setReplacementCost(BigDecimal.valueOf(77.88));
        fe.setRating(rc.convertToEntityAttribute("PG-13"));
        fe.setSpecialFeatures(set);
        sessionFactory.getCurrentSession().saveOrUpdate(fe);

        for (int i = 0; i < 2; i++) {
            InventoryEntity ie = new InventoryEntity();
            ie.setFilmId(fe);
            ie.setStoreId(sessionFactory.getCurrentSession().get(StoreEntity.class, (byte) 1));
            sessionFactory.getCurrentSession().saveOrUpdate(ie);
        }
        for (int i = 0; i < 2; i++) {
            InventoryEntity ie = new InventoryEntity();
            ie.setFilmId(fe);
            ie.setStoreId(sessionFactory.getCurrentSession().get(StoreEntity.class, (byte) 2));
            sessionFactory.getCurrentSession().saveOrUpdate(ie);
        }
    }
}
