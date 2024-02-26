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
        str = str.toLowerCase();
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getName().toLowerCase().contains(str)) {
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
            int learnNum = scan.nextInt() - 1;
            if (learnNum > 0 && learnNum < searchList.size()) {
                System.out.println(searchList.get(learnNum).toString());
            }
        }
    }
    public void searchCast() {
        System.out.println("Enter a person to search for (first or last name): ");
        String str = scan.nextLine();
        ArrayList<String> searchList = new ArrayList<String>();
        str = str.toLowerCase();
        for (int i = 0; i < movieList.size(); i++) {
            String castNames = movieList.get(i).getCast();
            String[] castList = castNames.split("\\|");
            for (int j = 0; j < castList.length; j++) {
                if (castList[j].toLowerCase().contains(str) && !searchList.contains(castList[j])) {
                    searchList.add(castList[j]);
                }
            }
        }
        if (searchList.size() == 0) {
            System.out.println("No results match that search!");
        } else {
            sortList(searchList);
            for (int i = 0; i < searchList.size(); i++) {
                System.out.println((i + 1) + ". " + searchList.get(i));
            }
            System.out.println("Which person would you like to see more of?\nEnter number: ");
            int learnNum = scan.nextInt() - 1;
            if (learnNum > 0 && learnNum < searchList.size()) {
                searchAllMoviesOfCast(searchList.get(learnNum));
            }
        }
    }
    public void searchAllMoviesOfCast(String cast) {
        String castName = cast.toLowerCase();
        ArrayList<Movie> searchList = new ArrayList<Movie>();
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getCast().toLowerCase().contains(castName)) {
                searchList.add(movieList.get(i));
            }
        }
        for (int i = 0; i < searchList.size(); i++) {
            System.out.println((i + 1) + ". " + searchList.get(i).getName());
        }
        System.out.println("Which movie would you like to learn more about?\nEnter number: ");
        int learnNum = scan.nextInt() - 1;
        System.out.println(searchList.get(learnNum).toString());
    }
    public void sortList(ArrayList<String> list){
        for (int i = 1; i < list.size(); i++) {
            String str = list.get(i);
            int idx = i - 1;
            while (idx >= 0 && str.compareTo(list.get(idx)) <= 0) {
                list.set(idx + 1, list.get(idx));
                idx--;
            }
            list.set(idx + 1, str);
        }
    }
    public void readData() {
        try {
            File myFile = new File("src//movies_data.csv");
            Scanner fileScanner = new Scanner(myFile);
            fileScanner.nextLine();
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
