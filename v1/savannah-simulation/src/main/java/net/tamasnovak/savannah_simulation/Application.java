package net.tamasnovak.savannah_simulation;

import net.tamasnovak.savannah_simulation.model.animals.Animal;
import net.tamasnovak.savannah_simulation.model.animals.Herbivore;
import net.tamasnovak.savannah_simulation.model.animals.Predator;
import net.tamasnovak.savannah_simulation.model.area.Cell;
import net.tamasnovak.savannah_simulation.model.area.Savannah;

import java.util.ArrayList;
import java.util.Random;

public class Application {
  private static final Random RANDOM = new Random();
  private static int numberOfAnimalsPlaced = 0;
  public static ArrayList<Animal> animals = new ArrayList<>();

  public static void main(String[] args) {
    int xCoordinate = 20;
    int yCoordinate = 20;
    int numberOfAnimals = (xCoordinate * yCoordinate) / 2;
    int simulatedYears = 100;

    Savannah savannah = new Savannah(generateSavannahWithAnimals(xCoordinate, yCoordinate, numberOfAnimals));

    for (int i = 1; i <= simulatedYears; i++) {
      savannah.performAnnualRoutine();

      if (i % 25 == 0) {
        printSavannah(savannah.getArea());
      }
    }
  }

  private static ArrayList<ArrayList<Cell>> generateSavannahWithAnimals(int xCoordinate, int yCoordinate, int numberOfAnimals) {
    ArrayList<ArrayList<Cell>> grid = new ArrayList<>();
    
    for (int i = 0; i < xCoordinate; i++) {
      for (int j = 0; j < yCoordinate; j++) {
        ArrayList<Cell> row = new ArrayList<>();

        if (isThereAnimalAtThisLocation(numberOfAnimals) && numberOfAnimalsPlaced < numberOfAnimals) {
          Cell cell = new Cell(i, j);
          generatePredatorOrHerbivore(cell);
          row.add(cell);

          numberOfAnimalsPlaced++;
        } else {
          Cell cell = new Cell(i, j);
          row.add(cell);
        }

        grid.add(row);
      }
    }

    System.out.println(numberOfAnimalsPlaced);
    return grid;
  }

  private static void generatePredatorOrHerbivore(Cell cell) {
    float randomNumber = (float) RANDOM.nextDouble(0, 10);

    if (randomNumber < 5.5) {
      Animal animal = new Predator(cell);
      animals.add(animal);
    }

    Animal animal = new Herbivore(cell);
    animals.add(animal);
  }

  private static boolean isThereAnimalAtThisLocation(int numberOfAnimals) {
    int randomNumber = RANDOM.nextInt(0, 10);

    if (numberOfAnimalsPlaced < numberOfAnimals) {
      return randomNumber > 3.5;
    }

    return randomNumber > 5;
  }

  private static void printSavannah(ArrayList<ArrayList<Cell>> savannah) {
    for (ArrayList<Cell> row : savannah) {
      for (Cell cell : row) {
        System.out.print(cell);
      }
    }
  }
}
