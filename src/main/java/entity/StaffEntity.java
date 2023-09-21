package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Timestamp;

@Entity
@Table(name = "staff")
@Getter
@Setter
public class StaffEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Byte staffId;

    @OneToOne
    @JoinColumn(name = "store_id")
    private StoreEntity storeId;
    @OneToOne
    @JoinColumn(name = "address_id")
    private AddressEntity addressId;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Lob
    @Type(type="org.hibernate.type.ImageType")
    private byte[] picture;
    @Column(name = "email")
    private String email;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "last_update")
    @UpdateTimestamp
    private Timestamp lastUpdate;
}
