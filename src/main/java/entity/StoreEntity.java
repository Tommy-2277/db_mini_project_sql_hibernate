package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;


@Entity
@Table(name = "store")
@Getter
@Setter
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Byte storeId;
    @OneToOne
    @JoinColumn(name = "manager_staff_id")
    private StaffEntity managerStaffId;
    @OneToOne
    @JoinColumn(name = "address_id")
    private AddressEntity addressId;
    @Column(name = "last_update")
    @UpdateTimestamp
    private Timestamp lastUpdate;
}
