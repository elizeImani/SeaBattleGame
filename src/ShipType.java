public enum ShipType {
    ONE_BY_ONE(1),
    TWO_BY_ONE(2),
    THREE_BY_ONE(3),
    FOUR_BY_ONE(4);

    private final int size;

    ShipType(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
