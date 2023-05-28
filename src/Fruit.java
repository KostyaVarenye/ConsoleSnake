import java.util.Random;

public class Fruit {
    private Point position;

    public Fruit(Point initialPosition) {
        this.position = initialPosition;
    }

    public Point getPosition() {
        return this.position;
    }

    public void respawn(Board board) {
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(board.getWidth());
            y = random.nextInt(board.getHeight());
        } while (board.getSnake().getBody().contains(new Point(x, y))); // Ensure not spawning on the snake
        this.position = new Point(x, y);
    }

}