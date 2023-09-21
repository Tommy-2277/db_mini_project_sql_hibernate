package daos;

import org.hibernate.SessionFactory;


public class PaymentDAO {
    private final SessionFactory sessionFactory;

    public PaymentDAO(SessionFactory sf) {
        this.sessionFactory = sf;
    }

}
