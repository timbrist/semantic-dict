package fi.coin.semanticdict.dto;



public class ObjectCoorDto {
    Double score;
    String label;
    Box box;

    public ObjectCoorDto() {
        this.score = 0.0;
        this.label = "";
        box = new Box();
        box.xmax = 0;
        box.xmin = 0;
        box.ymax = 0;
        box.ymin =0;
    }

    public Double getScore() {
        return score;
    }

    public String getLabel() {
        return label;
    }

    public Box getBox() {
        return box;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setBox(Box b) {
        this.box = b;
    }
}
