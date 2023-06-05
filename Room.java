public class Room extends Location {
    private int width; // Room width in feet
    private int height; // Room height in feet

    public Room(int width, int height) {
        super(0, 0);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return width * height;
    }
}
