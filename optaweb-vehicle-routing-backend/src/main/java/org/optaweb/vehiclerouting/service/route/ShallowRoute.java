package org.optaweb.vehiclerouting.service.route;

import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

// TODO maybe remove this once we fork planning domain from optaplanner-examples
// because then we can hold a reference to the original location

/**
 * Lightweight route description consisting of vehicle and location IDs instead of entities.
 * This makes it easier to quickly construct and share result of route optimization
 * without converting planning domain objects to business domain objects.
 * Specifically, some information may be lost when converting business domain objects to planning domain
 * because it's not needed for optimization (e.g. location address)
 * and so it's impossible to reconstruct the original business object without looking into the repository.
 */
public class ShallowRoute {

    /**
     * Vehicle ID.
     */
    public final long vehicleId;
    /**
     * Depot ID.
     */
    public final long depotId;
    /**
     * Visit IDs (immutable, never {@code null}).
     */
    public final List<Long> visitIds;

    /**
     * Create shallow route.
     *
     * @param vehicleId vehicle ID
     * @param depotId depot ID
     * @param visitIds visit IDs
     */
    public ShallowRoute(long vehicleId, long depotId, List<Long> visitIds) {
        this.vehicleId = vehicleId;
        this.depotId = depotId;
        this.visitIds = Collections.unmodifiableList(new ArrayList<>(Objects.requireNonNull(visitIds)));
    }

    @Override
    public String toString() {
        String route = Stream.concat(Stream.of(depotId), visitIds.stream())
                .map(Object::toString)
                .collect(joining("->", "[", "]"));
        return vehicleId + ": " + route;
    }
}
