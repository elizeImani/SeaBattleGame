public class Cell {
    private CellStatus status;
    private boolean shot;

    public Cell() {
        this.status = CellStatus.EMPTY;
        this.shot = false;
    }

    public CellStatus getStatus() {
        return status;
    }

    public void setStatus(CellStatus status) {
        this.status = status;
    }

    public boolean isShot() {
        return shot;
    }

    public void setShot(boolean shot) {
        this.shot = shot;
    }
}
