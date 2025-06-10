package org.example.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridGraph {
    private final int width;
    private final int height;
    private Map<Node, List<Node>> adjList;
    private Node[][] nodes;


    public GridGraph (int width, int height) {
        this.width = width;
        this.height = height;
        this.adjList = new HashMap<>();
        this.nodes = new Node[height][width];
        initializeGraph();
    }

    private void initializeGraph () {
        // создать все узлы(отдельные куски карты) и добавить их в мапу
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < width; j++) {
                Node node = new Node(i, j);
                nodes[i][j] = node;
                adjList.put(node, new ArrayList<>());
            }
        }

        // добавляем ребра
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Node currentNode = nodes[i][j];

                // движения (вверх, вниз, влево, вправо, диагональное)
                int[][] directions = {
                        {0, 1}, {0, - 1}, {1, 0}, {- 1, 0},
                        {1, 1}, {1, - 1}, {- 1, 1}, {- 1, - 1}
                };

                for (int[] dir : directions) {
                    int neighborX = j + dir[0];
                    int neighborY = i + dir[1];

                    if (isValidCoordinate(neighborX, neighborY)) {
                        Node neighborNode = nodes[neighborY][neighborX];
                        adjList.get(currentNode).add(neighborNode);
                    }
                }
            }
        }
    }

    public boolean isValidCoordinate (int x, int y) {
        return x >= 0 && x < this.width && y >= 0 && y < height;
    }

    // Метод для получения соседей узла
    public List<Node> getNeighbors(Node node) {
        return adjList.getOrDefault(node, new ArrayList<>());
    }

    public Node getNodeAt(int x, int y) {
        if (isValidCoordinate(x, y)) {
            return nodes[y][x];
        }
        return null;
    }

    public int getWidth () {
        return width;
    }

    public int getHeight () {
        return height;
    }
}
