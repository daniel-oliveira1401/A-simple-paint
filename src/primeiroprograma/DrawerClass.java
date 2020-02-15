package primeiroprograma;

import packageColors.ColorFrame;
import packageDisplay.Display;
import packageDisplay.Pixel;
import packageKeyManager.KeyManager;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class DrawerClass extends Thread{

    public static final int SPOT_SIZE = 30;
    public static final int SPACE_BETWEEN_SPOTS = 10;

    private Display display;
    private KeyManager keyManager;
    private Pixel[][] pixels;

    private int eraserSize = 10;

    private ColorFrame[] colors;

    private Color gColor = Color.WHITE;

    private int dotSize;

    private boolean recording;

    private boolean erasing = false;

    private BufferStrategy bs;

    private Graphics g;

    public DrawerClass(Display display){

        this.display = display;
    }

    public void run(){
        init();
        int fps = 120;
        double tickTime = 1e9/fps;
        long now;
        long lastTime = System.nanoTime();
        long timePassed;
        while(true){
            now = System.nanoTime();
            timePassed = now - lastTime;
            if(timePassed >=tickTime){
                update();
                lastTime = now;
            }

        }
    }

    public void init(){
        this.initDisplay();
        this.initPixels();
        this.initKeyManager();
        this.initColorPallet();
        bs = display.getCanvas().getBufferStrategy();
        g = bs.getDrawGraphics();
        this.cleanMemory();
        dotSize = 3;
    }

    public void initColorPallet(){

        colors = new ColorFrame[5];
        colors[0] = new ColorFrame(SPOT_SIZE*3+5*SPACE_BETWEEN_SPOTS,SPACE_BETWEEN_SPOTS,Color.CYAN);
        colors[1] = new ColorFrame(SPOT_SIZE*4+6*SPACE_BETWEEN_SPOTS,SPACE_BETWEEN_SPOTS,Color.YELLOW);
        colors[2] = new ColorFrame(SPOT_SIZE*5+7*SPACE_BETWEEN_SPOTS,SPACE_BETWEEN_SPOTS,Color.WHITE);
        colors[3] = new ColorFrame(SPOT_SIZE*6+8*SPACE_BETWEEN_SPOTS,SPACE_BETWEEN_SPOTS,Color.GREEN);
        colors[4] = new ColorFrame(SPOT_SIZE*7+9*SPACE_BETWEEN_SPOTS,SPACE_BETWEEN_SPOTS,Color.RED);
    }

    public void initPixels(){
        pixels = new Pixel[display.getHeight()][display.getWidth()];
        for(int l = 0;l<pixels.length;l++){
            for(int c= 0;c<pixels.length;c++){
                pixels[l][c] = new Pixel(Color.BLACK);
            }
        }
    }

    public void initDisplay(){

        display.createDisplay();
    }

    public void initKeyManager(){
        keyManager = new KeyManager();
        display.getFrame().addKeyListener(keyManager);
        display.getCanvas().addMouseMotionListener(keyManager);
        display.getCanvas().addMouseListener(keyManager);
    }

    public void update(){
        keyManager.tick();
        if(keyManager.cleanMemory){
            this.cleanMemory();
        }
        if(keyManager.startRecording){

            erasing = false;
            recording = true;
        }
        if(keyManager.stopRecording){

            recording = false;
        }
        if(keyManager.eraserOn){
            erasing = true;

        }
        if(keyManager.eraserOff){
            erasing = false;
        }

        if(recording){


            if(display.getCanvas().getMousePosition() != null){
                int mouseX = display.getCanvas().getMousePosition().x;
                if(display.getCanvas().getMousePosition()!=null){
                    int mouseY = display.getCanvas().getMousePosition().y;

                    pixels[mouseY][mouseX].setPixelColor(gColor);
                }

            }

        }
        if(erasing){
            if(!recording){
                if(display.getCanvas().getMousePosition()!=null){
                    Point mouse = new Point(display.getCanvas().getMousePosition().x,display.getCanvas().getMousePosition().y);

                    for(int l = mouse.y-eraserSize/2;l<mouse.y+dotSize+eraserSize/2;l++){
                        for(int c= mouse.x-eraserSize/2;c<mouse.x+dotSize+eraserSize/2;c++){
                            this.pixels[l][c].setPixelColor(Color.BLACK);

                        }
                    }

                }
            }else{
                recording = false;
            }


        }
        if(keyManager.cleanCanvas){
            this.cleanCanvas();
        }
        if(keyManager.showDrawing){
            if(recording){
                recording = false;
            }
            g.setColor(gColor);
            showDrawing();
        }

        if(keyManager.clicked){

            Point mouse = new Point(display.getCanvas().getMousePosition().x,display.getCanvas().getMousePosition().y);

            for(ColorFrame color : colors){
                for(int l = color.fromY;l<color.toY+DrawerClass.SPOT_SIZE;l++){
                    for(int c = color.fromX;c<color.toX;c++){
                        if((mouse.getX() == c)&&(mouse.getY() == l)){
                            gColor = color.getFrameColor();
                        }
                    }
                }
            }



            keyManager.clicked = false;
        }

        this.showRecording();
        this.showErasing();
        for(ColorFrame color:colors){
            color.renderFrame(g);
        }
        this.showCurrentColor();
        g.setColor(gColor);
        bs.show();

    }

    private void showErasing() {
        if(erasing){
            g.setColor(Color.RED);
        }else{
            g.setColor(Color.GREEN);
        }

        g.fillRect(SPOT_SIZE+2*SPACE_BETWEEN_SPOTS,SPACE_BETWEEN_SPOTS,SPOT_SIZE*2,SPOT_SIZE);

    }

    private void showDrawing() {

        for(int l = 0;l<display.getHeight();l++){
            for(int c= 0;c<display.getWidth();c++){

                    g.setColor(pixels[l][c].getPixelColor());
                    g.fillRect(c,l,dotSize,dotSize);

            }
        }


    }

    public void showCurrentColor(){
        g.setColor(gColor);
        g.fillOval(display.getWidth()-SPOT_SIZE-SPACE_BETWEEN_SPOTS,SPACE_BETWEEN_SPOTS,SPOT_SIZE,SPOT_SIZE);
    }

    public void showRecording(){
        if(recording){
            g.setColor(Color.RED);
        }else{
            g.setColor(Color.GREEN);
        }

        g.fillOval(SPACE_BETWEEN_SPOTS,SPACE_BETWEEN_SPOTS,SPOT_SIZE,SPOT_SIZE);

    }

    public void cleanMemory(){
        for(int l = 0;l<display.getHeight();l++){
            for(int c= 0;c<display.getWidth();c++){

                pixels[l][c].setPixelColor(Color.BLACK);

            }
        }

    }

    public void cleanCanvas(){
        g.clearRect(0,0,display.getWidth(),display.getHeight());


    }


}
// file >> project structure>> artifacts>new JAR