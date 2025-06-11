package org.example;

public enum MapSymbol {
    ROCK("🪨"),
    GRASS("\uD83C\uDF3F"),
    TREE("\uD83C\uDF33"),
    HERBIVORE("\uD83E\uDD92"),
    EMPTY(".");

    private final String symbol;

    MapSymbol (String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol () {
        return symbol;
    }

    public static MapSymbol fromSymbol(String symbol) {
        for (MapSymbol ms: MapSymbol.values()) {
            if (ms.getSymbol().equals(symbol)) {
                return ms;
            }
        }
        throw new IllegalArgumentException("Неизвестный символ карты: " + symbol);
    }

    public boolean isObstacle() {
        return this == TREE || this == ROCK || this == HERBIVORE;
    }

}
