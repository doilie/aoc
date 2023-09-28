package impl.treehouse;

public class TreeVisibility {
    private boolean fromLeft;
    private boolean fromRight;
    private boolean fromTop;
    private boolean fromBottom;

    public void setFromLeft(boolean fromLeft) {
        this.fromLeft = fromLeft;
    }

    public void setFromRight(boolean fromRight) {
        this.fromRight = fromRight;
    }

    public void setFromTop(boolean fromTop) {
        this.fromTop = fromTop;
    }

    public void setFromBottom(boolean fromBottom) {
        this.fromBottom = fromBottom;
    }

    public void setVisible() {
        this.fromLeft = true;
        this.fromRight = true;
        this.fromTop = true;
        this.fromBottom = true;
    }

    public boolean isVisible() {
        return fromLeft || fromRight || fromTop || fromBottom;
    }
}
