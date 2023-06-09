package org.optaweb.vehiclerouting.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Definition of the vehicle routing problem instance.
 */
public class RoutingProblem {

    private final String name;
    private final List<VehicleData> vehicles;
    private final LocationData depot;
    private final List<LocationData> visits;

    /**
     * Create routing problem instance.
     *
     * @param name the instance name
     * @param vehicles list of vehicles (not {@code null})
     * @param depot the depot (may be {@code null} if there is no depot)
     * @param visits list of visits (not {@code null})
     */
    public RoutingProblem(
            String name,
            List<? extends VehicleData> vehicles,
            LocationData depot,
            List<? extends LocationData> visits) {
        this.name = Objects.requireNonNull(name);
        this.vehicles = new ArrayList<>(Objects.requireNonNull(vehicles));
        this.depot = depot;
        this.visits = new ArrayList<>(Objects.requireNonNull(visits));
    }

    /**
     * Get routing problem instance name.
     *
     * @return routing problem instance name
     */
    public String name() {
        return name;
    }

    /**
     * Get the depot.
     *
     * @return depot (never {@code null})
     */
    public Optional<LocationData> depot() {
        return Optional.ofNullable(depot);
    }

    /**
     * Get locations that should be visited.
     *
     * @return visits
     */
    public List<LocationData> visits() {
        return visits;
    }

    /**
     * Vehicles that are part of the problem definition.
     *
     * @return vehicles
     */
    public List<VehicleData> vehicles() {
        return vehicles;
    }

    @Override
    public String toString() {
        return name;
    }
}
