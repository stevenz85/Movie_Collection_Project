import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection {
    private Scanner scan;
    private ArrayList<Movie> movieList;
    public MovieCollection () {
        scan = new Scanner(System.in);
        movieList = new ArrayList<Movie>();
        start();
    }
    public void start() {
        readData();
        menu();
    }
    public void menu() {
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";
        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }
    public void readData() {
        try {
            File myFile = new File("src//movies_data.csv");
            Scanner fileScanner = new Scanner(myFile);
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                String str1 = splitData[0];
                String str2 = splitData[1];
                String str3 = splitData[2];
                String str4 = splitData[3];
                int num = Integer.parseInt(splitData[4]);
                double rate = Double.parseDouble(splitData[5]);
                Movie movie = new Movie(str1, str2, str3, str4, num, rate);
                movieList.add(movie);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
