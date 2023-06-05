import java.util.*;

public class PatientZeroFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean exitProgram = false;
        while (!exitProgram) {
            // Get room dimensions from user
            System.out.print("Enter the room width in feet: ");
            int roomWidth = scanner.nextInt();

            System.out.print("Enter the room height in feet: ");
            int roomHeight = scanner.nextInt();

            // Create the room
            Room room = new Room(roomWidth, roomHeight);

            // Get individual details from user
            List<Individual> individuals = new ArrayList<>();
            System.out.print("Enter the number of individuals: ");
            int numIndividuals = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            for (int i = 1; i <= numIndividuals; i++) {
                System.out.print("Enter the name of individual " + i + ": ");
                String name = scanner.nextLine();

                System.out.print("Is " + name + " infected? (true/false): ");
                boolean infected = scanner.nextBoolean();
                scanner.nextLine(); // Consume the newline character

                System.out.print("Enter the x-coordinate of " + name + ": ");
                int x = scanner.nextInt();

                System.out.print("Enter the y-coordinate of " + name + ": ");
                int y = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                individuals.add(new Individual(name, infected, x, y));
            }

            // Get transmission rate from user
            System.out.print("Enter the transmission rate (0-1): ");
            double transmissionRate = scanner.nextDouble();

            // Calculate the maximum distance between individuals
            double maxDistance = Math.sqrt(Math.pow(room.getWidth(), 2) + Math.pow(room.getHeight(), 2));

            // Find all infected individuals
            List<Individual> infectedIndividuals = new ArrayList<>();
            for (Individual individual : individuals) {
                if (individual.isInfected()) {
                    infectedIndividuals.add(individual);
                }
            }

            // Spread the infection to other individuals based on transmission rate and proximity
            while (!infectedIndividuals.isEmpty()) {
                List<Individual> newlyInfected = new ArrayList<>();

                for (Individual infected : infectedIndividuals) {
                    for (Individual susceptible : individuals) {
                        if (!susceptible.isInfected() && susceptible != infected) {
                            double distance = infected.getDistanceTo(susceptible);
                            double areaRatio = room.getArea() / individuals.size();

                            double transmissionProbability = Math.exp(-Math.pow(distance, 2) / (2 * Math.pow(maxDistance * transmissionRate * areaRatio, 2)));

                            if (Math.random() < transmissionProbability) {
                                susceptible.setInfected(true);
                                newlyInfected.add(susceptible);
                            }
                        }
                    }
                }

                infectedIndividuals = newlyInfected;
            }

            // Determine patient zero based on infection timing
            Individual patientZero = null;
            long earliestInfectionTime = Long.MAX_VALUE;
            for (Individual individual : individuals) {
                if (individual.isInfected() && (patientZero == null || individual.getName().compareTo(patientZero.getName()) < 0)) {
                    long infectionTime = System.currentTimeMillis();
                    if (infectionTime < earliestInfectionTime) {
                        earliestInfectionTime = infectionTime;
                        patientZero = individual;
                    }
                }
            }

            // Check if patient zero is infected
            if (patientZero == null) {
                System.out.println("No infected individuals found.");
            } else {
                // Print the result
                System.out.println("Patient zero is: " + patientZero.getName());
            }

            // Ask the user if they want to enter new data or terminate the program
            System.out.print("Do you want to enter new data? (Y/N): ");
            String answer = scanner.next();

            if (!answer.equalsIgnoreCase("Y")) {
                exitProgram = true;
            }

            scanner.nextLine(); // Consume the newline character
        }

        scanner.close();
    }
}
