import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {

    public static List<Predator> readPredators(String filePath) throws IOException {
        List<Predator> predators = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Read header line
            String[] fields = line.split(";"); // Trait names
            
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                String name = values[0];
                Map<String, Integer> preferredTraits = new HashMap<>();
                
                for (int i = 1; i < values.length; i++) {
                    preferredTraits.put(fields[i], Integer.parseInt(values[i]));
                }
                predators.add(new Predator(name, preferredTraits));
            }
        }
        return predators;
    }

    public static List<Prey> readPrey(String filePath) throws IOException {
        List<Prey> preyList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Read header line
            String[] fields = line.split(";"); // Trait names
            
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                String name = values[0];
                Map<String, Integer> traits = new HashMap<>();
                
                for (int i = 1; i < values.length; i++) {
                    traits.put(fields[i], Integer.parseInt(values[i]));
                }
                preyList.add(new Prey(name, traits));
            }
        }
        return preyList;
    }
}
