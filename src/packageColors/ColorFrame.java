package packageColors;

import primeiroprograma.DrawerClass;

import java.awt.*;

public class ColorFrame {


    private Color frameColor;
    public int fromX,toX,fromY,toY;

    private int posX,posY;



    public ColorFrame(int posX,int posY,Color frameColor){
        this.posX = posX;
        this.posY = posY;
        this.frameColor = frameColor;
        this.updateCoords();
    }


    public void renderFrame(Graphics g){
        g.setColor(frameColor);
        g.fillRoundRect(posX,posY, DrawerClass.SPOT_SIZE,DrawerClass.SPOT_SIZE,10,10);
    }

    void updateCoords(){
        fromX = this.posX;
        toX = fromX + DrawerClass.SPOT_SIZE;

        fromY = this.posY;
        toY = fromY + DrawerClass.SPOT_SIZE;
    }


    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Color getFrameColor() {
        return frameColor;
    }

    public void setFrameColor(Color frameColor) {
        this.frameColor = frameColor;
    }
}
