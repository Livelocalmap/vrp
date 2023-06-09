package org.optaweb.vehiclerouting.plugin.planner.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import org.junit.jupiter.api.Test;
import org.optaweb.vehiclerouting.domain.Coordinates;
import org.optaweb.vehiclerouting.domain.Location;

class PlanningLocationFactoryTest {

    @Test
    void planning_location_should_have_same_properties_as_domain_location() {
        long id = 344;
        double latitude = -20.5;
        double longitude = 11.7;
        long distance = 11234;
        Location location = new Location(id, Coordinates.of(latitude, longitude));
        PlanningLocation planningLocation = PlanningLocationFactory.fromDomain(location, otherLocation -> distance);
        assertThat(planningLocation.getId()).isEqualTo(id);

        PlanningLocation other = PlanningLocationFactory.testLocation(id + 1);
        assertThat(planningLocation.distanceTo(other)).isEqualTo(distance);
        assertThat(planningLocation.angleTo(other)).isNotZero();
    }

    @Test
    void test_locations_distance_map_should_work() {
        long distance = 11231;
        PlanningLocation planningLocation = PlanningLocationFactory.testLocation(0, location -> distance);
        assertThat(planningLocation.distanceTo(PlanningLocationFactory.testLocation(1))).isEqualTo(distance);
    }

    @Test
    void test_location_without_distance_map_should_throw_exception() {
        PlanningLocation planningLocation = PlanningLocationFactory.testLocation(0);
        PlanningLocation otherLocation = PlanningLocationFactory.testLocation(1);
        assertThatIllegalStateException().isThrownBy(() -> planningLocation.distanceTo(otherLocation));
    }
}
