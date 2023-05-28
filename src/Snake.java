import java.awt.*;
import java.util.LinkedList;

public class Snake {
    private LinkedList<Point> body;
    private int direction; // 0: right, 1: down, 2: left, 3: up

    public Snake(Point initialPosition) {
        this.body = new LinkedList<>();
        this.body.add(initialPosition);
        this.direction = 0;
    }

    public LinkedList<Point> getBody() {
        return this.body;
    }

    public Point getHead() {
        return new Point(this.body.getFirst()); // Returns a copy of the head point
    }

    public int getDirection() {
        return this.direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void move(Board board) {
        Point newHead = new Point(getHead());
        switch (this.direction) {
            case 0:
                newHead.x++;
                break;
            case 1:
                newHead.y++;
                break;
            case 2:
                newHead.x--;
                break;
            case 3:
                newHead.y--;
                break;
        }

        // If new head point is a collision point, it's game over
        if (board.checkCollision(newHead)) {
            System.out.println("Game Over");
            System.exit(0);
        }

        // Add new head to body
        this.body.addFirst(newHead);

        // If fruit is eaten, respawn fruit. Snake body grows by not removing the tail.
        if (newHead.equals(board.getFruit().getPosition())) {
            board.getFruit().respawn(board);
        } else {
            // If fruit not eaten, move without growing by removing the tail
            this.body.removeLast();
        }
    }
}
