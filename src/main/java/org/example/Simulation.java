package org.example;

import org.example.map.GridGraph;
import org.example.map.Node;
import org.example.objects.Herbivore;
import org.example.resourse.Grass;
import org.example.static_entity.Rock;
import org.example.static_entity.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation {
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;

    GridGraph map = new GridGraph(WIDTH, HEIGHT);
    int moveCount = 0;
    static boolean runFlag = true;


    private static final int MAX_COUNT_HERBIVORE = 3;
    List<Herbivore> herbivoreList = new ArrayList<>();

    private static final int MAX_COUNT_ROCK = 15;
    private static final int MAX_COUNT_TREE = 15;
    private static final int MAX_COUNT_GRASS = 10;

    public static void pauseSimulation() {
        runFlag = !runFlag;
    }

    public void startSimulation() {
        initActions();
        while (runFlag) {
            System.out.println("Вечная симуляция");
            nextTurn();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void nextTurn() {
        turnActions();
        renderMap(map);
    }

    private void renderMap(GridGraph map) {
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                Node node = map.getNodeAt(x, y);
                if (node != null) {
                    System.out.printf("%-3s", node.getType().getSymbol());
                } else {
                    System.out.print("? ");
                }
            }
            System.out.println();
        }
        System.out.println("-----------");
    }

    private void turnActions() {

        // движение всех травоядных за один ход
        for (Herbivore herbivore: herbivoreList) {
            Node oldHerbivoreNode = herbivore.getCurrentNode();
            herbivore.makeMove(map);
            oldHerbivoreNode.setType(MapSymbol.EMPTY);
        }

        // передвижение всех хищников за один ход
        // проверить количество травоядных добавить если мало

        if (Grass.getSize() < MAX_COUNT_GRASS) {
            for (int i = 0; i < MAX_COUNT_GRASS - Grass.getSize(); i++) {
                Grass.initialize(map);
            }
        }

    }


    private void initActions() {
        placeObjectsOnTheMap();
        placeCreatureOnTheMap();
    }


    /**
     * Инициализировать на карте траву, скалы и траву
     */
    private void placeObjectsOnTheMap() {

        for (int i = 0; i < MAX_COUNT_TREE; i++) {
            Tree.initialize(map);
        }

        for (int i = 0; i < MAX_COUNT_ROCK; i++) {
            Rock.initialize(map);
        }

        for (int i = 0; i < MAX_COUNT_GRASS; i++) {
            Grass.initialize(map);
        }
    }

    private void placeCreatureOnTheMap() {
        Random random = new Random();

        for (int i = 1; i < MAX_COUNT_HERBIVORE; i++) {
            Node inittialHerbivireNode = map.getNodeAt(random.nextInt(Simulation.WIDTH), random.nextInt(Simulation.HEIGHT));

            if (inittialHerbivireNode == null) {
                System.err.println("Ошибка: Начальная позиция для травоядного невалидна!");
                return;
            }

            Herbivore herbivore = new Herbivore(2, 10, inittialHerbivireNode);
            inittialHerbivireNode.setType(herbivore.getIcon());
            herbivoreList.add(herbivore);
            }
        }

        // расставить хищников
}

