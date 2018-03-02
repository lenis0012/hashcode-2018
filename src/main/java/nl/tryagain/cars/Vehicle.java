package nl.tryagain.cars;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class Vehicle {
    @Getter
    private List<Ride> rides = new ArrayList<>();

    @Getter @Setter
    private boolean finished;

    @Getter @Setter private int row;
    @Getter @Setter private int col;
    @Getter @Setter private int totalSteps;

    public void addRide(Ride ride) {
        rides.add(ride);

        int distanceToRide;
        totalSteps += (distanceToRide = Lennart.calculateDistance(this, ride));
        totalSteps += Lennart.calculateWaitTime(this, ride, distanceToRide);
        totalSteps += Lennart.calculateDistance(ride);
    }
}
