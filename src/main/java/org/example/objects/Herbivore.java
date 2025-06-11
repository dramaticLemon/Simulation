package org.example.objects;

import org.example.Creature;
import org.example.MapSymbol;
import org.example.map.GridGraph;
import org.example.map.Node;
import org.example.resourse.Grass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Herbivore extends Creature {
    int speed;
    int HP;
    private final MapSymbol icon = MapSymbol.HERBIVORE;
    private Node currentNode;

    public MapSymbol getIcon () {
        return icon;
    }

    public Herbivore(int speed, int hp, Node initialNode) {
        this.speed = speed;
        this.HP = hp;
        this.currentNode = initialNode;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Node newNode) {
        this.currentNode.setType(MapSymbol.EMPTY);
        this.currentNode = newNode;
        this.currentNode.setType(MapSymbol.HERBIVORE);
    }


    // высший приоритет идти к ближней траве (ближняя клетка)
    // высокий (если травы рядом нет найти ее на карте и двигаться к ней)
    // средний (избегать хищников)
    // низкий (случайное блуждание)
    // избегание препятствий


    public void makeMove(GridGraph graph) {
        List<Node> possibleMoves = graph.getNeighbors(this.currentNode);

        // ближняя клетка травы
        for (Node neighbor: possibleMoves) {
            if (neighbor.getType().equals(MapSymbol.GRASS)) {
                setCurrentNode(neighbor);
                Grass.removeLast();
                return;
            }
        }
        // поиск ближайшего пути к траве
        Optional<List<Node>> nearestGrassOption = graph.findPath(MapSymbol.GRASS, currentNode, graph);
        if (nearestGrassOption.isPresent()) {
            List<Node> nearestGrassPath = nearestGrassOption.get();
            Node pathToGrass = nearestGrassPath.getFirst();
            if (pathToGrass.getType().equals(MapSymbol.EMPTY)) {
                setCurrentNode(pathToGrass);
                System.out.println("Травоядное на " + currentNode + " движется к ближайшей траве на " + pathToGrass);
                return;
            }
        }

        // посмотреть какие клетки пустые
        List<Node> validMoves = new ArrayList<>();
        for (Node neighbor: possibleMoves) {
            if (neighbor.getType().equals(MapSymbol.EMPTY) || neighbor.getType().equals(MapSymbol.GRASS)) {
                validMoves.add(neighbor);
            }
        }
        // блуждание
        if (!validMoves.isEmpty()) {

            Random random = new Random();
            Node nextNode = validMoves.get(random.nextInt(validMoves.size()));
            System.out.println("Травоядное на " + currentNode + " случайным образом движется на " + nextNode);
            setCurrentNode(nextNode);
        } else {
            System.out.println("Травоядное на " + currentNode + " не может двигаться (нет доступных ходов).");
        }

    };
}
