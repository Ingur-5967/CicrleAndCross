package ru.solomka.cross;

import ru.solomka.cross.entity.GameSetting;
import ru.solomka.cross.entity.Player;
import ru.solomka.cross.game.GameMap;
import ru.solomka.cross.game.Render;
import ru.solomka.cross.game.enums.GameState;

import java.util.Collections;

public class Main {

    public static GameMap ACTIVE_MAP =
            new GameMap(GameState.IDLE, new GameSetting(new int[]{3, 3}, 3))
                    .initMap(new Player("X", Collections.emptyList(), false), new Player("0", Collections.emptyList(), true));

    public static void main(String[] args) {
        System.out.println("Игра запущена!");
     
        Render.renderMap(Main.ACTIVE_MAP);

        while (Main.ACTIVE_MAP.getState() == GameState.IDLE) {
            GameMap gameMap = Main.ACTIVE_MAP;

            gameMap.getActivePlayer().move();

            if(gameMap.getFreePositions() == 0) {
                gameMap.setState(GameState.END);
                System.out.println("Игра окончена!");
            }
        }
    }
}