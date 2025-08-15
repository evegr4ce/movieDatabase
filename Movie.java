/**
 * Evelyn Cunneen, March 11th
 *
 * This class creates a movie object.
 */

public class Movie {
    private int idNum;
    private int releaseYr;
    private double revenue;
    private int budget;
    private int runtime;
    private String title;
    private double popularity;
    private int voteCount;
    private double voteAvg;

    public Movie() {
        idNum = 0;
        releaseYr = 2000;
        revenue = 0;
        budget = 0;
        runtime = 0;
        title = "Default";
        voteCount = 0;
        voteAvg = 0;
    }

    public Movie(int budget, int idNum, double popularity, int releaseYr, double revenue, int runtime, String title, double voteAvg, int voteCount) {
        this.idNum = idNum;
        this.releaseYr = releaseYr;
        this.revenue = revenue;
        this.budget = budget;
        this.runtime = runtime;
        this.title = title;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.voteAvg = voteAvg;
    }

    // METHODS

    /**
     * @return Gross Profit
     *
     * Calculates the gross profit of a movie.
     */
    public double calcGross() {
        return (revenue - budget);
    }

    // GETTERS & SETTERS
    public int getIdNum() {
        return idNum;
    }

    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }

    public int getReleaseYr() {
        return releaseYr;
    }

    public void setReleaseYr(int releaseYr) {
        this.releaseYr = releaseYr;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public double getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public double getVoteAvg() {
        return voteAvg;
    }

    public void setVoteAvg(double voteAvg) {
        this.voteAvg = voteAvg;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
}
