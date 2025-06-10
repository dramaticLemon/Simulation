package org.example.objects;

import org.example.Creature;
import org.example.map.GridGraph;

public class Predator extends Creature {
    int speed;
    int HP;
    int powerAttack;

    public void makeMove(GridGraph graph) {
        System.out.println("Predator is move");
    };

    public void attackHerbivore(Herbivore obj) {
        obj.HP -= this.powerAttack;
    }
}
