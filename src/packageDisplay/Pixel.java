package packageDisplay;

import java.awt.*;

public class Pixel {

    private Color pixelColor;

    public Pixel(Color pixelColor){
        this.pixelColor = pixelColor;
    }

    public Color getPixelColor() {
        return pixelColor;
    }

    public void setPixelColor(Color pixelColor) {
        this.pixelColor = pixelColor;
    }
}
