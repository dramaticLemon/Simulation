package org.example.map;


// самостоятельна единица карты

// TODO сделать так что можно была определить строго ограниченные типы объектов на карте
// камень дерево скала травоядное хищник, возможно это сделать через ENUM


public class Node {
    private final int x;
    private final int y;
    private char type;


    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = '.'; // default type
    }

    public Node (int y, int x, char type) {
        this.y = y;
        this.x = x;
        this.type = type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public int getX () {
        return x;
    }

    public char getType () {
        return type;
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
