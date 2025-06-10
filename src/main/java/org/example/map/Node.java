package org.example.map;

// TODO сделать так что можно была определить строго ограниченные типы объектов на карте
// камень дерево скала травоядное хищник, возможно это сделать через ENUM


import org.example.MapSymbol;

public class Node {
    private final int x;
    private final int y;
    private MapSymbol type;
//    private Set<Node> neighbors;


    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = MapSymbol.EMPTY; // default type
//        this.neighbors = new HashSet<>();
    }

    public Node (int y, int x, MapSymbol type) {
        this.x = x;
        this.y = y;
        this.type = type;
//        this.neighbors = new HashSet<>();
    }

//    public void connect(Node node) {
//        if (this == node) throw new IllegalArgumentException("Can't connect node ti itself");
//        this.neighbors.add(node);
//        node.neighbors.add(this);
//    }

//    public Optional<Node> search (Character value, Node start) {
//        Set<Node> alreadyVisited = new HashSet<>();
//        Queue<Node> queue = new ArrayDeque<>();
//        queue.add(start);
//        Node currentNode;
//        while (!queue.isEmpty()) {
//            currentNode = queue.remove();
//
//            if (currentNode.getType().equals(value)) {
//                return Optional.of(currentNode);
//            } else {
//                alreadyVisited.add(currentNode);
//                queue.addAll(currentNode.getNeighbors());
//                queue.removeAll(alreadyVisited);
//            }
//        }
//
//        return Optional.empty();
//    }

    public void setType(MapSymbol type) {
        this.type = type;
    }

    public int getX () {
        return x;
    }

    public MapSymbol getType() {
        return this.type;
    }

    public int getY () {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node node = (Node) obj;
        return  x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
