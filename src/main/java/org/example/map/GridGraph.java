package org.example.map;

import org.example.MapSymbol;

import java.util.*;

public class GridGraph {
    private final int width;
    private final int height;
    private final Map<Node, List<Node>> adjList;
    private final Node[][] nodes;


    public GridGraph (int width, int height) {
        this.width = width;
        this.height = height;
        this.adjList = new HashMap<>();
        this.nodes = new Node[height][width];
        initializeGraph();
    }

    private void initializeGraph () {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < width; j++) {
                Node node = new Node(i, j);
                nodes[i][j] = node;
                adjList.put(node, new ArrayList<>());
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Node currentNode = nodes[i][j];

                // вверх, вниз, влево, вправо, диагональное
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

    /**
     * Метод для получения всех возможных соседей узла
     * @param node текущая нода
     * @return список все соседних нод
     */
    public List<Node> getNeighbors(Node node) {
        return adjList.getOrDefault(node, new ArrayList<>());
    }

    /**
     * Получить узел по координатам
     * @param x Координата Х
     * @param y Координата У
     * @return объект ноды
     */
    public Node getNodeAt(int x, int y) {
        if (isValidCoordinate(x, y)) {
            return nodes[y][x];
        }

        return null;
    }

    /**
     * Поиск в ширину с возвратом пути узлов
     * @param value Значение узла для поиска
     * @param startNode Текущий узел с которого будет стартовать поиск
     * @param map собственно карта
     * @return списков кратчайшего пути узлов
     */
    public Optional<List<Node>> findPath(MapSymbol value, Node startNode, GridGraph map) {

        if (startNode == null || value == null) {
            return Optional.empty();
        }

        Queue<Node> queue = new ArrayDeque<>();
        Set<Node> alreadyVisited = new HashSet<>();// локальный сет для отслеживания посещаемых узлов
        Map<Node, Node> parentMap = new HashMap<>(); // хранение родительских узлов: откуда пришли к текущему

        // добавить стартовый узел в очередь и пометить как посещенный
        queue.add(startNode);
        alreadyVisited.add(startNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.remove();

            // проверка, является ли текущий узел искомым
            if (currentNode.getType().equals(value)) {
                return Optional.of(reconstructionPath(parentMap, startNode, currentNode));
            }

            for (Node neighbor : map.getNeighbors(currentNode)) {
                if (!alreadyVisited.contains(neighbor) &&
                        neighbor.getType() != MapSymbol.HERBIVORE &&
                        neighbor.getType() != MapSymbol.TREE &&
                        neighbor.getType() != MapSymbol.ROCK) {
                    alreadyVisited.add(neighbor);
                    queue.add(neighbor);
                    parentMap.put(neighbor, currentNode);
                }
            }
        }

        return Optional.empty();
    }

    /**
     * Вспомогательный метод для восстановления пути от целевого узла к стартовому
     * используя карту родителей
     *
     * @param parentMap Карта, где ключ - узел, значение - его родитель в BFS
     * @param startNode Начальный узел пути
     * @param endNode Конечный (целевой) узел пути
     * @return Список узлов, предоставляющий путь от startNode до EndNode
     */
    private List<Node> reconstructionPath(Map<Node, Node> parentMap, Node startNode, Node endNode) {
        List<Node> path = new ArrayList<>();
        Node current = endNode;

        // идем от конечного узла назад к стартовому
        while (current != null && ! current.equals(startNode)) {
            path.add(current);
            current = parentMap.get(current);
        }

        if (current != null && current.equals(startNode)) {
            path.add(startNode);
        }
        Collections.reverse(path);

        if (path.size() > 1) {
            path.removeFirst();
        }
        return path;
    }

    public int getWidth () {
        return width;
    }

    public int getHeight () {
        return height;
    }
}
