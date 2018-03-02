package nl.tryagain.cars;

import lombok.experimental.UtilityClass;

import java.util.Set;

@UtilityClass
public class Lennart {
    Ride findBestRide(Vehicle vehicle, TravelPlan plan, Set<Ride> ridesLeft) {
        int shortest = Integer.MAX_VALUE;
        Ride best = null;
        for(Ride ride : ridesLeft) {
            int stepsToStart = calculateSteps(vehicle, ride);
            if(stepsToStart < shortest) {
                int rideDistance = calculateDistance(ride);
                if(vehicle.getTotalSteps() + stepsToStart + rideDistance > plan.getSteps())
                    continue;

                shortest = stepsToStart;
                best = ride;
            }
        }

        return best;
    }

    /**
     * Calculate steps that would be requires before person pickup
     *
     * @param vehicle
     * @param ride
     * @return
     */
    int calculateSteps(Vehicle vehicle, Ride ride) {
       int distance = calculateDistance(vehicle, ride);
       return distance + calculateWaitTime(vehicle, ride, distance);
    }

    /**
     * Calculate distance between vehicle and ride
     * @param vehicle
     * @param ride
     * @return
     */
    public static int calculateDistance(Vehicle vehicle, Ride ride) {
        return Math.abs(ride.getStartRow() - vehicle.getRow())
                + Math.abs(ride.getStartCol() - vehicle.getCol());
    }

    /**
     * Calculate distance between ride start and fiish
     *
     * @param ride
     * @return
     */
    public static int calculateDistance(Ride ride) {
        return Math.abs(ride.getFinishRow() - ride.getStartRow())
                + Math.abs(ride.getFinishCol() - ride.getStartCol());
    }

    public static int calculateWaitTime(Vehicle vehicle, Ride ride, int distance) {
        return Math.max(0, ride.getEarliestStart() - vehicle.getTotalSteps() - distance);
    }
}
