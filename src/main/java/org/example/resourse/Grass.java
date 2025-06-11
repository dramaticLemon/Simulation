package org.example.resourse;

import org.example.Entity;
import org.example.MapSymbol;
import org.example.Simulation;
import org.example.map.GridGraph;
import org.example.map.Node;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Grass extends Entity {


    static Set<Grass> totalObject = new HashSet<>();

    private static final MapSymbol icon = MapSymbol.GRASS;
    private final Node place;

    public Grass (Node place) {
        this.place = place;
    }

    public static void initialize(GridGraph map) {

        Random random = new Random();

        Node inittialGrassNode = null;
        do {
            inittialGrassNode = map.getNodeAt(random.nextInt(Simulation.WIDTH), random.nextInt(Simulation.HEIGHT));
        } while (! inittialGrassNode.getType().equals(MapSymbol.EMPTY));
        Grass grassObject = new Grass(inittialGrassNode);
        totalObject.add(grassObject);
        inittialGrassNode.setType(grassObject.getIcon());
    }

    public static void removeLast() {
        Iterator<Grass> iterator = totalObject.iterator();
        Grass lasElement = null;
        if (iterator.hasNext()) {
            lasElement = iterator.next();
            iterator.remove();
        }
    }

    public static int getSize() {
        return totalObject.size();
    }

    public MapSymbol getIcon () {
        return icon;
    }

    public Node getPlace () {
        return place;
    }
}
