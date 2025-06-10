package org.example.objects;

import org.example.Creature;
import org.example.MapSymbol;
import org.example.map.GridGraph;
import org.example.map.Node;
import org.example.resourse.Grass;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


// стремиться найти траву
// может потратить свой ход на движение в сторону травы
// может потратить свой ход на поглощение травы
// если значение hp равно 0 объект должен удалится

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
        this.currentNode = newNode;
    }

    public void makeMove(GridGraph graph) {

        Node grassNode = null;

        // получить список всех возможных узлов
        List<Node> possibleMoves = graph.getNeighbors(this.currentNode);

        // получить список узлов которые свободны это для передвижения
        List<Node> validMoves = new ArrayList<>();

        for (Node neighbor: possibleMoves) {
            if (neighbor.getType() == MapSymbol.GRASS) {
                grassNode = neighbor;
                break;
            }
            if (neighbor.getType() == MapSymbol.EMPTY) {
                validMoves.add(neighbor);
            }
        }

        if (grassNode != null) {
            this.currentNode = grassNode;
            grassNode.setType(MapSymbol.EMPTY);
            this.currentNode.setType(this.icon);
            Grass.removeLast();
            return;
        }

        // проверка есть ли куда перемещаться
        if (possibleMoves.isEmpty()) {
            System.out.println("Herbivore at " + currentNode + " has no possible moves.");
            return;
        }

        Random random = new Random();
        Node nextNode = validMoves.get(random.nextInt(validMoves.size()));

        // TODO сделать более сложную логику выбора движения
        // - избегать препятствий
        // - двигаться к еде
        // - избегать хищников
        // Очищаем старую позицию на карте (возвращаем к "земле")

        // Обновляем текущий узел травоядного
        setCurrentNode(nextNode);
        // Помечаем новую позицию значком
        currentNode.setType(this.icon);


    };
}
