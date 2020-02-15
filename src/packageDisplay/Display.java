package packageDisplay;

import javax.swing.*;
import java.awt.*;

public class Display {

    private JFrame frame;
    private Canvas canvas;
    private int height;
    private int width;

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Display(int width, int height){
        this.height = height;
        this.width = width;

    }

    public void createDisplay(){
        frame = new JFrame();
        frame.setSize(this.width,this.height);
        frame.setMinimumSize(new Dimension(this.width,this.height));
        frame.setMaximumSize(new Dimension(this.width,this.height));
        frame.setPreferredSize(new Dimension(this.width,this.height));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setSize(this.width,this.height);
        canvas.setMinimumSize(new Dimension(this.width,this.height));
        canvas.setMaximumSize(new Dimension(this.width,this.height));
        canvas.setPreferredSize(new Dimension(this.width,this.height));
        canvas.setFocusable(false);
        canvas.setVisible(true);
        canvas.setBackground(Color.BLACK);

        frame.add(canvas);

        canvas.createBufferStrategy(2);

        frame.pack();
    }

}
