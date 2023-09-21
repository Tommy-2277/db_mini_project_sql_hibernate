package extraClassesForEntities;

import java.util.Set;

public class SpecialFeaturesSet {
    private static final Set<String> set = Set.of("Trailers", "Commentaries", "Deleted Scenes", "Behind the Scenes");

    public static Set<String> getSet() {
        return set;
    }
}
