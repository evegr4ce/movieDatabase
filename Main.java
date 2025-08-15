//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;


/**
 * Evelyn Cunneen, March 11th
 *
 * This program creates a database of movies for users to reference.
 */
public class Main {
    public ArrayList<Movie> mymovies;

    public static void main(String[] args) throws FileNotFoundException {
        Main main = new Main();
    }

    public Main() throws FileNotFoundException {
        mymovies = new ArrayList<>();
        String file = "moviesDefault.csv";

        readFile(file);

        boolean cont = true;
        Scanner user = new Scanner(System.in);

        while (cont) {
            System.out.println("Hello! Welcome to my movie database. What would you like to do?");
            System.out.println("""
                    Please enter a number listed below.
                                    
                    1) Print all movie titles.
                    2) Print the highest-grossing movie.
                    3) Print all movies released in a specific year.
                    4) Print the average gross of all movies.
                    5) Add a new movie to the database.
                    6) Remove a movie from the database.
                    7) Print the five highest and lowest grossing movies.
                    8) Print the 5 highest grossing movies from the 30 lowest budget movies.""");

            boolean validInput = false;

            while (!validInput) {
                String ans = user.next();

                // depending on the user input, the requested method will be called.
                validInput = switch (ans) {
                    case "1" -> {
                        printTitles();
                        yield true;
                    }
                    case "2" -> {
                        printHighestGross();
                        yield true;
                    }
                    case "3" -> {
                        System.out.println("What release year would you like printed?");
                        int yr = user.nextInt();

                        printReleaseYr(yr);
                        yield true;
                    }
                    case "4" -> {
                        printAvgGross();
                        yield true;
                    }
                    case "5" -> {
                        Movie movie = new Movie();

                        System.out.println("What is the title of the movie you want to add?");
                        movie.setTitle(user.next());

                        System.out.println("What is the ID number?");
                        movie.setIdNum(user.nextInt());

                        System.out.println("What was the release year?");
                        movie.setReleaseYr(user.nextInt());

                        System.out.println("What was the revenue?");
                        movie.setRevenue(user.nextInt());

                        System.out.println("What was the budget?");
                        movie.setBudget(user.nextInt());

                        System.out.println("What was the runtime?");
                        movie.setIdNum(user.nextInt());

                        System.out.println("What was the popularity rating?");
                        movie.setPopularity(user.nextDouble());

                        System.out.println("What is the vote count?");
                        movie.setVoteCount(user.nextInt());

                        System.out.println("What is the vote average?");
                        movie.setVoteAvg(user.nextDouble());

                        // using the scanner that is already initialized to create the movie and pass that into the addMovie method.
                        addMovie(movie);
                        yield true;
                    }
                    case "6" -> {
                        System.out.println("What is the title of the movie you would like to remove?");
                        String userInput = user.next();

                        removeMovie(userInput);
                        yield true;
                    }
                    case "7" -> {
                        printFiveLowHigh();
                        yield true;
                    }
                    case "8" -> {
                        printHigh30Low();
                        yield true;
                    }
                    default -> {
                        System.out.println("Invalid input. Please try again.");
                        yield false;
                    }
                };
            }

            boolean validCheck = false;

            // continue check loop
            while (!validCheck) {
                System.out.println("\nWould you like to continue? (y/n)");
                String check = user.next();

                if (check.equalsIgnoreCase("n")) {
                    cont = false;
                    validCheck = true;
                    user.close();

                    System.out.println("Thanks for using my database! Goodbye.");
                    System.exit(0);
                } else if (check.equalsIgnoreCase("y")) {
                    validCheck = true;
                } else {
                    System.out.println("Invalid input. Please try again, and enter 'y' or 'n'.");
                }
            }
        }
    }

    /**
     * Prints the titles of all movies in the database.
     */
    public void printTitles() {
        System.out.println("All Movie Titles:");

        for (Movie movie : mymovies) {
            System.out.println(movie.getTitle());
        }
    }

    /**
     * Calculates and prints the highest grossing movie.
     */
    public void printHighestGross() {
        double max = 0;
        Movie highGross = null;

        System.out.print("Highest Grossing Movie: ");

        for (Movie movie : mymovies) {
            double gross = movie.calcGross();

            if (max < gross) {
                highGross = movie;
                max = gross;
            }
        }

        System.out.print(highGross.getTitle() + "\nGross profit: $" + max);
    }

    /**
     * @param yr
     *
     * Prints all the movies released in a given year.
     */
    public void printReleaseYr(int yr) {
        ArrayList<Movie> movies = new ArrayList<>(mymovies);

        // insertion sort
        for (int i = 1; i < movies.size(); i++) {
            Movie next = movies.get(i);

            int j = i;
            while (j > 0 && movies.get(j - 1).getReleaseYr() > next.getReleaseYr()) {
                movies.set(j, movies.get(j - 1));
                j--;
            }

            movies.set(j, next);
        }

        // binary search
        int low = 0;
        int mid = 0;
        int high = movies.size() - 1;

        while (low <= high) {
            mid = (low + high) / 2;
            int diff = movies.get(mid).getReleaseYr() - yr;

            if (diff == 0) {
                break;
            } else if (diff < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // because the search may find the correct year in the middle of the sorted years,
        // back up the index until it has accounted for the entire section
        while (movies.get(mid).getReleaseYr() == yr && mid > 0) {
            mid--;
        }
        mid++;

        while (movies.get(mid).getReleaseYr() == yr) {
            System.out.println(movies.get(mid).getTitle());
            mid++;

            if (mid == movies.size()) {
                break;
            }
        }
    }

    /**
     * Calculates and prints the average gross of every movie in the database.
     */
    public void printAvgGross() {
        double total = 0;
        double count = 0;

        for (Movie movie : mymovies) {
            total += movie.calcGross();
            count++;
        }

        double avgGross = total / count;

        System.out.println("Average Gross Profit: $" + avgGross);
    }

    /**
     * @param movie
     *
     * Adds a movie to the database.
     */
    public void addMovie(Movie movie) {
        mymovies.add(movie);

        System.out.println(movie.getTitle() + " has been added to the database.");
    }

    /**
     * @param title
     *
     * Removes a movie (by title) from the database
     */
    public void removeMovie(String title) {

        for (Movie movie : mymovies) {
            if (title.equalsIgnoreCase(movie.getTitle())) {
                mymovies.remove(movie);
                break;
            }
        }

        System.out.println(title + " has been removed from the database.");
    }

    /**
     * Prints the five highest and lowest grossing movies in the database.
     */
    public void printFiveLowHigh() {
        ArrayList<Movie> movies = new ArrayList<>(mymovies);

        // insertion sort
        for (int i = 1; i < movies.size(); i++) {
            Movie next = movies.get(i);

            int j = i;
            while (j > 0 && movies.get(j - 1).calcGross() > next.calcGross()) {
                movies.set(j, movies.get(j - 1));
                j--;
            }

            movies.set(j, next);
        }

        System.out.println("Lowest Grossing Movies:");
        System.out.println("Title\tGross Profit");
        for (int i = 0; i < 5; i++) {
            System.out.println(movies.get(i).getTitle()+"\t"+movies.get(i).calcGross());
        }

        System.out.println("");
        System.out.println("Highest Grossing Movies:");
        System.out.println("Title\tGross Profit");
        for (int i = movies.size() - 6; i < movies.size(); i++) {
            System.out.println(movies.get(i).getTitle()+"\t"+movies.get(i).calcGross());
        }
    }

    /**
     * Finds the 30 lowest budget movies and prints the 5 highest grossing of those 30.
     */
    public void printHigh30Low() {
        ArrayList<Movie> movies = new ArrayList<>(mymovies);

        for (int i = 1; i < movies.size(); i++) {
            Movie next = movies.get(i);

            int j = i;
            while (j > 0 && movies.get(j - 1).getBudget() > next.getBudget()) {
                movies.set(j, movies.get(j - 1));
                j--;
            }

            movies.set(j, next);
        }

        ArrayList<Movie> lowest30 = new ArrayList<Movie>();

        for (int i = 0; i < 30; i++) {
            lowest30.add(movies.get(i));
        }

        for (int i = 1; i < lowest30.size(); i++) {
            Movie next = lowest30.get(i);

            int j = i;
            while (j > 0 && lowest30.get(j - 1).calcGross() > next.calcGross()) {
                lowest30.set(j, lowest30.get(j - 1));
                j--;
            }

            lowest30.set(j, next);
        }

        System.out.println("Highest Grossing Movies (from the lowest 30 budget movies:");
        System.out.println("Title\tGross Profit");
        int count = 5;
        for (int i = lowest30.size() - 5; i < lowest30.size(); i++) {
            System.out.println(count +") "+lowest30.get(i).getTitle()+"\t"+lowest30.get(i).calcGross());
            count--;
        }
    }

    /**
     * @param fileName
     * @throws FileNotFoundException
     *
     * Reads the given csv file into the database.
     */
    public void readFile(String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName));

        scan.nextLine();
        while (scan.hasNextLine()) {

            String input = scan.nextLine();
            String[] sInput = input.split(",");

            Movie movie = new Movie(parseInt(sInput[0]), parseInt(sInput[1]), parseDouble(sInput[2]), parseInt(sInput[3]), parseDouble(sInput[4]), parseInt(sInput[5]), sInput[6], parseDouble(sInput[7]), parseInt(sInput[8]));

            mymovies.add(movie);
        }

        scan.close();
    }
}