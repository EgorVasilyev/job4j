package thread;

import javafx.scene.shape.Rectangle;

import java.util.Random;

public class RectangleMove implements Runnable {
    private final Rectangle rect;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        Random randomX = new Random();
        Random randomY = new Random();
        int deltaX = 1;
        int deltaY = 1;
        while (!Thread.currentThread().isInterrupted()) {
            if (this.rect.getX() + deltaX > 100) {
                deltaX = -(randomX.nextInt(3) + 1);
            }
            if (this.rect.getY() + deltaY > 100) {
                deltaY = -(randomX.nextInt(3) + 1);
            }
            if (this.rect.getX() + deltaX < 0) {
                deltaX = randomX.nextInt(3) + 1;
            }
            if (this.rect.getY() + deltaY < 0) {
                deltaY = randomY.nextInt(3) + 1;
            }
            this.rect.setX(this.rect.getX() + deltaX);
            this.rect.setY(this.rect.getY() + deltaY);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}