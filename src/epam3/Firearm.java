package epam3;

public class Firearm {
    int id;
    String model;
    String handy;
    String origin;
    String range;
    int effectiveRange;
    boolean clip;
    boolean optics;
    String material;

    public Firearm(int id, String model, String handy, String origin, String range, int effectiveRange, boolean clip, boolean optics, String material) {
        this.id = id;
        this.model = model;
        this.handy = handy;
        this.origin = origin;
        this.range = range;
        this.effectiveRange = effectiveRange;
        this.clip = clip;
        this.optics = optics;
        this.material = material;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getHandy() {
        return handy;
    }

    public void setHandy(String handy) {
        this.handy = handy;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public int getEffectiveRange() {
        return effectiveRange;
    }

    public void setEffectiveRange(int effectiveRange) {
        this.effectiveRange = effectiveRange;
    }

    public boolean isClip() {
        return clip;
    }

    public void setClip(boolean clip) {
        this.clip = clip;
    }

    public boolean isOptics() {
        return optics;
    }

    public void setOptics(boolean optics) {
        this.optics = optics;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

}
