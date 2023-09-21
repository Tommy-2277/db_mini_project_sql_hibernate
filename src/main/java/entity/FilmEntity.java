package entity;

import extraClassesForEntities.Feature;
import extraClassesForEntities.Rating;
import extraClassesForEntities.RatingConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Entity
@Table(name = "film")
@Getter
@Setter
@ToString
public class FilmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Short filmId;

    @Column(name = "title")
    private String title;
    @Column(name = "description")
    @Type(type="text")
    private String description;
    @Column(name = "release_year", columnDefinition = "year")
    private Short releaseYear;
    @ManyToOne
    @JoinColumn(name = "language_id")
    private LanguageEntity languageId;
    @ManyToOne
    @JoinColumn(name = "original_language_id")
    private LanguageEntity originalLanguageId;
    @Column(name = "rental_duration")
    private Byte rentalDuration;
    @Column(name = "rental_rate")
    private BigDecimal rentalRate;
    @Column(name = "length")
    private Short length;
    @Column(name = "replacement_cost")
    private BigDecimal replacementCost;
    @Column(name = "rating" , columnDefinition="ENUM('G','PG','PG-13','NC-17',R)" ,nullable = false )
    @Convert(converter = RatingConverter.class)
    private Rating rating;
    @Column(name = "special_features", columnDefinition = "set('Trailers,'Commentaries',''Deleted Scenes','Behind the Scenes')")
    private String specialFeatures;
    @Column(name = "last_update")
    @UpdateTimestamp
    private Timestamp lastUpdate;

    public Set<Feature> getSpecialFeatures() {
        if(isNull(specialFeatures) || specialFeatures.isEmpty()){
            return null;
        }
        Set<Feature> features = new HashSet<>();
        Arrays.stream(specialFeatures.split(",")).forEach(e -> features.add(Feature.getFeatureByValue(e)));
        features.remove(null);
        return features;
    }

    public void setSpecialFeatures(Set<Feature> specialFeatures) {
        if(specialFeatures == null){
            this.specialFeatures = null;
        }
        else {
            this.specialFeatures = specialFeatures.stream().map(Feature::getValue).collect(Collectors.joining(","));
        }
    }
}
