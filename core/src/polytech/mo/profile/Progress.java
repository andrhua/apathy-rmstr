package polytech.mo.profile;

import com.badlogic.gdx.utils.Array;

import polytech.mo.game.levels.LevelBuilder;

public class Progress extends BasePlayerData {
    private Array<Level> levels;

    public Progress(){
        levels= new Array<>(6);
        for (int i=0; i<6; i++){
            levels.add(new Level(LevelBuilder.Level.values()[i]));
        }
    }

    @Override
    public void read() {

    }

    @Override
    public void write() {

    }

    @Override
    public void reset() {
        for (int i=0; i<6; i++) levels.get(i).reset();
    }

    public Level getLevel(LevelBuilder.Level level){
        return levels.get(level.ordinal());
    }

    public Array<Level> getLevels(){
        return levels;
    }

    public static class Level implements Resetable {
        private LevelBuilder.Level level;
        private boolean isUnlocked;
        private int deaths;

        public Level(LevelBuilder.Level level){
            this.level=level;
            isUnlocked=level==LevelBuilder.Level.NEGATION;
            deaths=0;
        }

        public void addDeaths(int number){
            deaths+=number;
        }

        public void checkCompleted(){
            isUnlocked =true;
        }

        public LevelBuilder.Level getLevel() {
            return level;
        }

        public boolean isUnlocked() {
            return isUnlocked;
        }

        @Override
        public void reset() {
            isUnlocked=level==LevelBuilder.Level.NEGATION;
            deaths=0;
        }

        public int getDeaths() {
            return deaths;
        }
    }
}
