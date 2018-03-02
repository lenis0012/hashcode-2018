package nl.tryagain.cars;

import lombok.SneakyThrows;
import lombok.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Value
public class TravelPlan {
    private int rows;
    private int cols;
    //private int vehicleCount;
    //private int rides;
    private int bonus;
    private int steps;

    private List<Ride> rides;

    private List<Vehicle> vehicles;

    @SneakyThrows // Yes i know, boo hoo
    public static TravelPlan read(String file) {
        List<String> lines = Files.readAllLines(Paths.get(CarsApplication.class.getResource(file).toURI()));

        // Read header
        String[] header = lines.get(0).split(Pattern.quote(" "));
        int rows = Integer.parseInt(header[0]);
        int cols = Integer.parseInt(header[1]);
        int vehicleCount = Integer.parseInt(header[2]);
        int rideCount = Integer.parseInt(header[3]);
        int bonus = Integer.parseInt(header[4]);
        int steps = Integer.parseInt(header[5]);

        // Read vehicles
        List<String> rideData = lines.subList(1, lines.size());
        List<Ride> rides = IntStream.range(0, rideCount).mapToObj(line -> {
            List<Integer> data = Arrays.stream(rideData.get(line).split(Pattern.quote(" ")))
                    .map(Integer::parseInt).collect(Collectors.toList());
            return new Ride(
                    line,
                    data.get(0),
                    data.get(1),
                    data.get(2),
                    data.get(3),
                    data.get(4),
                    data.get(5)
            );
        }).collect(Collectors.toList());

        // Prepare some vehicles
        List<Vehicle> vehicles = IntStream.range(0, vehicleCount).mapToObj(i -> new Vehicle()).collect(Collectors.toList());

        return new TravelPlan(rows, cols, bonus, steps, rides, vehicles);
    }

    public void writeToOutput(String file) throws IOException {
        System.out.println(vehicles);

        List<String> output = vehicles.stream().map(vehicle -> vehicle.getRides().stream()
                .map(r -> Integer.toString(r.getId())).collect(Collectors.joining(" ")))
                .collect(Collectors.toList());

        Files.write(Paths.get(Paths.get(file).getFileName().toString()), output);
    }
}
