package org.example;

import org.example.map.GridGraph;

public abstract class Creature extends Entity {
    int speed; // существо имеет скорость (сколько клеток может пройти за 1 ход)
    int HP;

    protected abstract void makeMove (GridGraph graph);

}
