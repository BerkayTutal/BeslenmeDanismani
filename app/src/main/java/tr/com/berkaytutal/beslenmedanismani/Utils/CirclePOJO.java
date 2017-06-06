package tr.com.berkaytutal.beslenmedanismani.Utils;

import java.util.ArrayList;

/**
 * Created by berka on 6.06.2017.
 */

public class CirclePOJO extends CircleTekrarAbsPOJO {
    private ArrayList<CircleTekrarAbsPOJO> arraylist;
    private int circleCount;

    public CirclePOJO(ArrayList<CircleTekrarAbsPOJO> arraylist, int circleCount) {
        this.arraylist = arraylist;
        this.circleCount = circleCount;
    }

    public ArrayList<CircleTekrarAbsPOJO> getArraylist() {
        return arraylist;
    }

    public void setArraylist(ArrayList<CircleTekrarAbsPOJO> arraylist) {
        this.arraylist = arraylist;
    }

    public int getCircleCount() {
        return circleCount;
    }

    public void setCircleCount(int circleCount) {
        this.circleCount = circleCount;
    }

    @Override
    public boolean isCircle() {
        return true;
    }

    @Override
    public boolean isTekrar() {
        return false;
    }
}
