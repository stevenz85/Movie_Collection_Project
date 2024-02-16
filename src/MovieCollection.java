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
            menuOption = scan.nextLine();

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
    public void searchTitles() {
        System.out.println("Enter a title search term: ");
        String str = scan.nextLine();
        ArrayList<Movie> searchList = new ArrayList<Movie>();
        str = str.substring(0, 1).toUpperCase() + str.substring(1);
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getName().contains(str)) {
                searchList.add(movieList.get(i));
            }
        }
        if (searchList.size() == 0) {
            System.out.println("No movie titles match that search term!");
        } else {
            for (int i = 0; i < searchList.size(); i++) {
                System.out.println((i + 1) + ". " + searchList.get(i).getName());
            }
            System.out.println("Which movie would you like to learn more about?\nEnter number: ");
            int learnNum = scan.nextInt();
            System.out.println(searchList.get(learnNum).toString());
        }
    }
    public void searchCast() {
        System.out.println("Enter a title search term: ");
        String str = scan.nextLine();
        ArrayList<Movie> searchList = new ArrayList<Movie>();
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getCast().contains(str)) {
                searchList.add(movieList.get(i));
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
            sortData();
            for (int i = 0; i < movieList.size(); i++) {
                System.out.println((i + 1) + ". " + movieList.get(i).getName());
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
    public void sortData() {
        for (int i = 1; i < movieList.size(); i++) {
            int position = i - 1;
            Movie movie = movieList.get(i);
            while (position >= 0 && movie.getName().toLowerCase().compareTo(movieList.get(position).getName().toLowerCase()) < 0) {
                movieList.set(position + 1, movieList.get(position));
                position--;
            }
            movieList.set(position + 1, movie);
        }
    }
}
