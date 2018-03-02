package nl.tryagain.cars;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    @Getter
    private List<Ride> rides = new ArrayList<>();

    public void addRide(Ride ride) {
        rides.add(ride);
    }
}
