package org.example;

public enum MapSymbol {
    ROCK('R'),
    GRASS('#'),
    TREE('T'),
    HERBIVORE('@'),
    EMPTY('.');

    private final char symbol;

    MapSymbol (char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol () {
        return symbol;
    }

    public static MapSymbol fromSymbol(char symbol) {
        for (MapSymbol ms: MapSymbol.values()) {
            if (ms.getSymbol() == symbol) {
                return ms;
            }
        }
        throw new IllegalArgumentException("Неизвестный символ карты: " + symbol);

    }
}
