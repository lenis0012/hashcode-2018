package nl.tryagain.cars;

import java.util.HashSet;
import java.util.Set;

public class CarsApplication {

    public static void main(String[] args) throws Exception {
        solve("/a_example.in");
    }

    public static void solve(String file) throws Exception {
        TravelPlan plan = TravelPlan.read(file);

        Set<Ride> ridesLeft = new HashSet<>(plan.getRides());
        while(!plan.getVehicles().stream().allMatch(Vehicle::isFinished)) {

            plan.getVehicles().stream().filter(v -> !v.isFinished()).forEach(vehicle -> {
                Ride bestRide = Lennart.findBestRide(vehicle, plan, ridesLeft);
                if(bestRide == null) {
                    vehicle.setFinished(true);
                } else {
                    ridesLeft.remove(bestRide);
                    vehicle.addRide(bestRide);
                }
            });
        }

        plan.writeToOutput(file + ".output.txt");
    }
}
