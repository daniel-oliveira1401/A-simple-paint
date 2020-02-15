package packageKeyManager;

import javax.swing.event.MouseInputListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class KeyManager implements KeyListener, MouseInputListener {

    private boolean[] keys;
    public boolean startRecording,stopRecording,showDrawing,cleanMemory,cleanCanvas, eraserOn,eraserOff,
    clicked;


    public KeyManager(){
        init();
    }

    public void tick(){
        startRecording = keys[KeyEvent.VK_R];
        stopRecording = keys[KeyEvent.VK_S];
        showDrawing = keys[KeyEvent.VK_SPACE];
        cleanMemory = keys[KeyEvent.VK_C];
        cleanCanvas = keys[KeyEvent.VK_K];
        eraserOn = keys[KeyEvent.VK_E];
        eraserOff = keys[KeyEvent.VK_F];
    }

    public void init(){
        keys = new boolean[300];
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        clicked = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
