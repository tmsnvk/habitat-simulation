package net.tamasnovak.ui;

public final class UiMessages {
  public static final String SIMULATION_INTRODUCTION = """
  %nWelcome to Habitat Simulation v0.1!
    
  Using the application you may run simulations in various habitat types with a number of animal species and see which animal species are the masters of their environments.
  
  Tips for using the application:
  - Whenever you are asked to enter an input from a list of options, do not feel compelled to enter it in the same letter case, i.e. if the application writes 'SAVANNAH' you are more than welcome to type it out as 'savannah'.
  -
  -
  -
    
  Type 'Start' to start the simulation programme with a number of instructions and setting a few configuration values!
  Type 'Exit' if you ended here by accident and would like to leave!%n
  """;
  public static final String OPTIONS_AT_INTRODUCTION = "The possible options to type: 1. %sStart%s 2. %sExit%s. Enter your choice here: ";

  public static final String OPTIONS_AT_HABITAT_TYPES_SELECTION = "The list of the currently available habitats: ";
  public static final String PROMPT_TO_SELECT_HABITAT = "Enter the name of the habitat you would like to run: ";
  public static final String OPTIONS_AT_VEGETATION_TYPES_SELECTION = "The list of the currently available vegetation types: ";
  public static final String PROMPT_TO_SELECT_VEGETATION_TYPE = "Enter the name of the vegetation type you would like to appear in your simulation run: ";
  public static final String OPTIONS_AT_VEGETATION_SPECIES_SELECTION = "The list of the currently available vegetation species: ";
  public static final String PROMPT_TO_SELECT_VEGETATION_SPECIES = "Enter the name of the vegetation species you would like to appear in your simulation run: ";

  public static final String SELECTION_CONFIRMATION = "Thank you for that! You have chosen: %s%s%s.%n%n";
  public static final String INCORRECT_USER_INPUT_WARNING = "%sEnter a correct input option.%s ";
}
