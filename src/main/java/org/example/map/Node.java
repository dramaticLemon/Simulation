package org.example.map;

import org.example.MapSymbol;

public class Node {
    private final int x;
    private final int y;
    private MapSymbol type;



    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = MapSymbol.EMPTY;
    }

    public Node (int y, int x, MapSymbol type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void setType(MapSymbol type) {
        this.type = type;
    }

    public int getX () {
        return x;
    }

    public int getY () {
        return y;
    }

    public MapSymbol getType() {
        return this.type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node node = (Node) obj;
        return  x == node.x && y == node.y && type == node.type;
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
