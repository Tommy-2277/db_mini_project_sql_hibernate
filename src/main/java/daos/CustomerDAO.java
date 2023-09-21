package daos;

import org.hibernate.SessionFactory;

public class CustomerDAO {
    private SessionFactory sessionFactory;

    public CustomerDAO(SessionFactory sf) {
        this.sessionFactory = sf;
    }


}
