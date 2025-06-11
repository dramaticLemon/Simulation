package org.example.static_entity;

import org.example.Entity;
import org.example.MapSymbol;
import org.example.Simulation;
import org.example.map.GridGraph;
import org.example.map.Node;

import java.util.Random;

public class Rock extends Entity {
    private static final MapSymbol icon = MapSymbol.ROCK;
    private final Node place;

    public Rock (Node place) {
        this.place = place;
    }

    public static void initialize(GridGraph map) {
        Random random = new Random();
        Node inittialRockNode = map.getNodeAt(random.nextInt(Simulation.WIDTH), random.nextInt(Simulation.HEIGHT));
        Rock rock = new Rock(inittialRockNode);
        inittialRockNode.setType(rock.getIcon());
    }

    public MapSymbol getIcon () {
        return icon;
    }

    public Node getPlace () {
        return place;
    }
}
