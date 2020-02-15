package primeiroprograma;

import packageDisplay.Display;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


        Display display = new Display(1000,1000);
        DrawerClass drawer = new DrawerClass(display);

        drawer.start();

    }
}
/*
array2D[linha][coluna];

o comando array2D.length vai retornar o numero de linhas que o array possui
 */