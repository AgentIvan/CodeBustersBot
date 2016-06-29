package game;

public class EvaluationState {
    private final boolean iCanBeStunned;
    private final boolean iHaveStun;
    private final boolean isCarryingGhost;
    private final double distToCheckPoint;
    private final double distToBase;
    private final boolean inReleaseRange;
    private final Evaluator.MovesAndDist movesAndDistToBustGhost;
    private final boolean weSeeSomeGhost;
    private final int movesToStunEnemyWithGhost;

    public EvaluationState(
            boolean iCanBeStunned,
            boolean iHaveStun,
            boolean isCarryingGhost,
            double distToCheckPoint,
            double distToBase,
            boolean inReleaseRange,
            Evaluator.MovesAndDist movesAndDistToBustGhost,
            boolean weSeeSomeGhost,
            int movesToStunEnemyWithGhost
    ) {
        this.iCanBeStunned = iCanBeStunned;
        this.iHaveStun = iHaveStun;
        this.isCarryingGhost = isCarryingGhost;
        this.distToCheckPoint = distToCheckPoint;
        this.distToBase = distToBase;
        this.inReleaseRange = inReleaseRange;
        this.movesAndDistToBustGhost = movesAndDistToBustGhost;
        this.weSeeSomeGhost = weSeeSomeGhost;
        this.movesToStunEnemyWithGhost = movesToStunEnemyWithGhost;
    }

    public boolean better(EvaluationState st) {
        if (st == null) {
            return true;
        }
        if (iHaveStun != st.iHaveStun) {
            return iHaveStun;
        }
        if (isCarryingGhost != st.isCarryingGhost) {
            return isCarryingGhost;
        }
        if (isCarryingGhost) {
            if (!iHaveStun) {
                if (iCanBeStunned != st.iCanBeStunned) {
                    return !iCanBeStunned;
                }
            }
            if (inReleaseRange != st.inReleaseRange) {
                return inReleaseRange;
            }
            if (!inReleaseRange) {
                if (distToBase != st.distToBase) {
                    return distToBase < st.distToBase;
                }
            }
        }

        if (movesToStunEnemyWithGhost != st.movesToStunEnemyWithGhost) {
            return movesToStunEnemyWithGhost < st.movesToStunEnemyWithGhost;
        }

        int compareMovesAndDist = movesAndDistToBustGhost.compareTo(st.movesAndDistToBustGhost);
        if (compareMovesAndDist != 0) {
            return compareMovesAndDist < 0;
        }
        if (weSeeSomeGhost && !st.weSeeSomeGhost) {
            throw new RuntimeException();
        }
        if (weSeeSomeGhost) {
            if (distToBase != st.distToBase) {
                return distToBase < st.distToBase;
            }
        }
        if (distToCheckPoint != st.distToCheckPoint) {
            return distToCheckPoint < st.distToCheckPoint;
        }
        return false;
    }

    @Override
    public String toString() {
        return "EvaluationState{" +
                "iCanBeStunned=" + iCanBeStunned +
                ", iHaveStun=" + iHaveStun +
                ", isCarryingGhost=" + isCarryingGhost +
                ", distToCheckPoint=" + distToCheckPoint +
                ", distToBase=" + distToBase +
                ", inReleaseRange=" + inReleaseRange +
                ", movesAndDistToBustGhost=" + movesAndDistToBustGhost +
                ", weSeeSomeGhost=" + weSeeSomeGhost +
                ", movesToStunEnemyWithGhost=" + movesToStunEnemyWithGhost +
                '}';
    }
}
