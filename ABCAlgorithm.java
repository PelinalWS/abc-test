import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

public class ABCAlgorithm {
    List<Prey> preyList;
    Group group;
    int foodSourceSize = 10; // Number of food sources (solutions)
    int maxIterations = 1000;

    public ABCAlgorithm(List<Prey> preyList, Group group) {
        this.preyList = preyList;
        this.group = group;
    }

    public Prey runOptimization() {
        List<Prey> foodSources = generateInitialFoodSources();
        Prey bestSolution = null;
        double bestFitness = Double.NEGATIVE_INFINITY;

        for (int iter = 0; iter < maxIterations; iter++) {
            for (Prey foodSource : foodSources) {
                Prey newSolution = explore(foodSource);
                double newFitness = group.calculateFitness(newSolution);

                if (newFitness > bestFitness) {
                    bestFitness = newFitness;
                    bestSolution = newSolution;
                }
            }
        }

        return bestSolution;
    }

    private List<Prey> generateInitialFoodSources() {
        List<Prey> foodSources = new ArrayList<>();
        for (int i = 0; i < foodSourceSize; i++) {
            Collections.shuffle(preyList);
            foodSources.add(preyList.get(0));
        }
        return foodSources;
    }

    private Prey explore(Prey foodSource) {
        Random random = new Random();
        Prey newSolution = preyList.get(random.nextInt(preyList.size()));
        return newSolution;
    }
}



class Predator {
    String name;
    Map<String, Integer> preferredTraits; // Trait name and rating (0-5)

    public Predator(String name, Map<String, Integer> preferredTraits) {
        this.name = name;
        this.preferredTraits = preferredTraits;
    }
}

class Prey {
    String name;
    Map<String, Integer> traits; // Trait name and rating (0-5)

    public Prey(String name, Map<String, Integer> traits) {
        this.name = name;
        this.traits = traits;
    }
}

class Group {
    List<Predator> predators;

    public Group(List<Predator> predators) {
        this.predators = predators;
    }

    // Calculate fitness of a prey for this group of predators
    public double calculateFitness(Prey prey) {
        double fitness = 0.0;
        int totalWeight = 0;

        for (Predator predator : predators) {
            for (Map.Entry<String, Integer> entry : predator.preferredTraits.entrySet()) {
                String trait = entry.getKey();
                int weight = entry.getValue();

                if (prey.traits.containsKey(trait)) {
                    fitness += weight * prey.traits.get(trait);
                    totalWeight += weight;
                }
            }
        }

        return totalWeight > 0 ? fitness / totalWeight : 0;
    }
}
