public abstract class Location {
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getDistanceTo(Location other) {
        int dx = x - other.getX();
        int dy = y - other.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public abstract double getArea();
}
