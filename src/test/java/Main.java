import com.codingame.astarcraft.JVPlayer;
import com.codingame.gameengine.runner.SoloGameRunner;

public class Main {
    public static void main(String[] args) {
        SoloGameRunner gameRunner = new SoloGameRunner();

        gameRunner.setAgent(JVPlayer.class);
        gameRunner.setTestCase("test19.json");

        gameRunner.start(9999);
    }
}
