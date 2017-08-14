package polytech.mo.game.table;

import polytech.mo.game.objects.GameObject;

public class Table {
    private int width, height;
    private GameObject matrix[][];

    public Table(int width, int height, GameObject... objects){
        this.width=width;
        this.height=height;
        matrix=new GameObject[width][height];
        for (GameObject object:objects) {

        }
    }
}
