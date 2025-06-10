package org.example.static_entity;

import org.example.Entity;
import org.example.map.GridGraph;
import org.example.map.Node;

import java.util.Random;

public class Rock extends Entity {
    private static final char icon = 'R';
    private final Node place;

    public Rock (Node place) {
        this.place = place;
    }

    public static void initialize(GridGraph map) {
        Random random = new Random();
        Node inittialRockNode = map.getNodeAt(random.nextInt(10), random.nextInt(10));
        Rock rock = new Rock(inittialRockNode);
        inittialRockNode.setType(rock.getIcon());
    }

    public char getIcon () {
        return icon;
    }

    public Node getPlace () {
        return place;
    }
}
