import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Read predators and prey from CSV files
            List<Predator> predators = CSVReader.readPredators("predators.csv");
            List<Prey> preyList = CSVReader.readPrey("prey.csv");

            // Create a group of predators (you can modify this as needed)
            Group group = new Group(predators);

            // Run the ABC optimization
            ABCAlgorithm abc = new ABCAlgorithm(preyList, group);
            Prey bestPrey = abc.runOptimization();

            System.out.println("The most suitable prey for the group is: " + bestPrey.name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
