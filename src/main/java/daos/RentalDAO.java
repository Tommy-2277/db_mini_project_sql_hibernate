package daos;

import org.hibernate.SessionFactory;

public class RentalDAO {
    private final SessionFactory sessionFactory;

    public RentalDAO(SessionFactory sf) {
        this.sessionFactory = sf;
    }

}
