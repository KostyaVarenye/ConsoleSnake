import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class SnakeGame extends JFrame {
    private static final int BOARD_SIZE = 25;
    private Board board;
    private JTextArea gameView;

    public SnakeGame() {
        Random random = new Random();
        Point snakeStart = new Point(BOARD_SIZE/2, BOARD_SIZE/2);
        Point fruitStart = new Point(random.nextInt(BOARD_SIZE), random.nextInt(BOARD_SIZE));
        Snake snake = new Snake(snakeStart);
        Fruit fruit = new Fruit(fruitStart);
        this.board = new Board(BOARD_SIZE, BOARD_SIZE, snake, fruit);

        // Setup game view
        this.gameView = new JTextArea(BOARD_SIZE, BOARD_SIZE * 2);
        this.gameView.setFont(new Font("monospaced", Font.PLAIN, 12));
        this.gameView.setEditable(false);
        add(gameView);

        // Make the JFrame focusable to receive key events
        setFocusable(true);
        requestFocusInWindow();

        // Setup key listener
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        // Ensure the snake cannot make a 180 degree turn
                        if (snake.getDirection() != 1) {
                            snake.setDirection(3);
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (snake.getDirection() != 3) {
                            snake.setDirection(1);
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if (snake.getDirection() != 0) {
                            snake.setDirection(2);
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (snake.getDirection() != 2) {
                            snake.setDirection(0);
                        }
                        break;
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setTitle("Snake Game");
        setResizable(false);
        setVisible(true);
    }

    public void startGame() {
        while (true) {
            Snake snake = board.getSnake();
            snake.move(board);
            gameView.setText(board.drawBoard());
            // Adjust delay based on direction
            int delay = (snake.getDirection() % 2 == 0) ? 75 : 150; // Less delay for horizontal movement

            // Add delay for visibility
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        SnakeGame game = new SnakeGame();
        game.startGame();
    }
}
