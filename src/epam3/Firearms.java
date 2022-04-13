package epam3;

import java.util.List;

public class Firearms {
    private List<Firearm> firearm;

    public List<Firearm> getFirearm() {
        return firearm;
    }

    public void setFirearm(List<Firearm> firearm) {
        this.firearm = firearm;
    }

    @Override
    public String toString() {
        return "Firearms{" +
                "firearm=" + firearm +
                '}';
    }
}
