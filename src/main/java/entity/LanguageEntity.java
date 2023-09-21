package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "language")
@Getter
@Setter
@ToString
public class LanguageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Byte id;
    @Column(name = "name", columnDefinition = "char")
    private String name;
    @Column(name = "last_update")
    @UpdateTimestamp
    private Timestamp lastUpdate;
}
