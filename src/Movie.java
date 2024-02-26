public class Movie {
    String name;
    String cast;
    String director;
    String overview;
    int runTime;
    double userRating;
    public Movie (String str1, String str2, String str3, String str4, int num, double rate) {
        this.name = str1;
        this.cast = str2;
        this.director = str3;
        this.overview = str4;
        this.runTime = num;
        this.userRating = rate;
    }

    public String getName() {
        return name;
    }
    public String getCast() {
        return cast;
    }
    public String getDirector() {
        return director;
    }
    public String getOverview() {
        return overview;
    }
    public int getRunTime() {
        return runTime;
    }
    public double getUserRating() {
        return userRating;
    }
    public String toString() {
        return "\nTitle: " + getName() + "\nRuntime: " + getRunTime() + " minutes\nDirected by: " + getDirector() + "\nCast: " + getCast() + "\nOverview: " + getOverview() + "\nUser rating: " + getUserRating();
    }
}
