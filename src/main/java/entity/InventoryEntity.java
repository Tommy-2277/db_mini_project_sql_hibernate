package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "inventory")
@Getter
@Setter
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Integer inventoryId;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private FilmEntity filmId;
    @ManyToOne
    @JoinColumn(name = "store_id")
    private StoreEntity storeId;
    @Column(name = "last_update")
    @UpdateTimestamp
    private Timestamp lastUpdate;
}
