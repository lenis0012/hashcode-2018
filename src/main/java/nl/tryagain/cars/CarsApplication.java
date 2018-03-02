package nl.tryagain.cars;

public class CarsApplication {

    public static void main(String[] args) throws Exception {
        solve("a_example.in");
    }

    public static void solve(String file) throws Exception {
        TravelPlan plan = TravelPlan.read(file);

        // SOLVE HERE

        plan.writeToOutput(file + ".output.txt");
    }
}
