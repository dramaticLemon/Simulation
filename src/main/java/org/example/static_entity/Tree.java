package org.example.static_entity;

import org.example.Entity;
import org.example.map.GridGraph;
import org.example.map.Node;

import java.util.Random;

public class Tree extends Entity {
    private static final char icon = 'T';
    private final Node place;

    public Tree (Node place) {
        this.place = place;
    }

    public static void initialize(GridGraph map) {
        Random random = new Random();
        Node inittialTreeNode = map.getNodeAt(random.nextInt(10), random.nextInt(10));
        Tree tree = new Tree(inittialTreeNode);
        inittialTreeNode.setType(tree.getIcon());
    }

    public char getIcon () {
        return icon;
    }

    public Node getPlace () {
        return place;
    }
}
