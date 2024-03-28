package system;
import java.awt.Point;
import java.util.ArrayList;
import elements.barriers.*;

public class LevelRandomizer {
    public ArrayList<Barrier> barriers;
    public int barrierCount;

    public LevelRandomizer(int count){
        barrierCount = count;
        barriers = new ArrayList<>(barrierCount);
    }

    public void createBarriers(){
        Point currentPoint = null;
        for (int i = 0; i < barrierCount; i++){
            currentPoint = new Point();
            currentPoint.x = (i % 12) * 30;
            currentPoint.y = (i / 12) * 60;
            Barrier barrier = new SimpleBarrier(currentPoint);
            barriers.add(barrier);
        }
    }
}
