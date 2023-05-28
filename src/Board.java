class Board {
    private Snake snake;
    private Fruit fruit;
    private int height;
    private int width;

    public Board(int height, int width, Snake snake, Fruit fruit) {
        this.height = height;
        this.width = width;
        this.snake = snake;
        this.fruit = fruit;
    }

    public Snake getSnake() {
        return this.snake;
    }

    public Fruit getFruit() {
        return this.fruit;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public String drawBoard() {
        StringBuilder sb = new StringBuilder();
        // Top border
        for (int i = 0; i < width + 2; i++) {
            sb.append("#");
        }
        sb.append("\n");
        // Board contents
        for (int i = 0; i < height; i++) {
            sb.append("#"); // Left border
            for (int j = 0; j < width; j++) {
                Point point = new Point(j, i);
                if (snake.getHead().x == point.x && snake.getHead().y == point.y) {
                    sb.append("@");
                } else if (snake.getBody().contains(point)) {
                    sb.append("O");
                } else if (fruit.getPosition().x == point.x && fruit.getPosition().y == point.y) {
                    sb.append("$");
                } else {
                    sb.append(" ");
                }
            }
            sb.append("#"); // Right border
            sb.append("\n");
        }
        // Bottom border
        for (int i = 0; i < width + 2; i++) {
            sb.append("#");
        }
        sb.append("\n");
        return sb.toString();
    }

    public boolean checkCollision(Point point) {
        return point.x < 0 || point.x >= width || point.y < 0 || point.y >= height || snake.getBody().contains(point);
    }
}
