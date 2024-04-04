package domain.barriers;

public interface BarrierInterface {

    public static void hit(Barrier barrier) {
        barrier.hitpoints--;
    }

    public static boolean isDestroyed(Barrier barrier) {
        return barrier.hitpoints <= 0;
    }
}
