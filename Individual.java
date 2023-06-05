public class Individual extends Location {
    private String name;
    private boolean infected;

    public Individual(String name, boolean infected, int x, int y) {
        super(x, y);
        this.name = name;
        this.infected = infected;
    }

    public String getName() {
        return name;
    }

    public boolean isInfected() {
        return infected;
    }

    public void setInfected(boolean infected) {
        this.infected = infected;
    }

    @Override
    public double getArea() {
        return 0; // Individual does not have an area
    }
}
