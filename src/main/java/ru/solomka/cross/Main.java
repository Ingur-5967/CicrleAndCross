package ru.solomka.cross;

import ru.solomka.cross.entity.GameSetting;
import ru.solomka.cross.entity.Player;

import java.util.Collections;

public class Main {

    public static GameMap ACTIVE_MAP =
            new GameMap(new GameSetting(new int[]{3, 3}, 3))
                    .initMap(new Player("X", Collections.emptyList(), false), new Player("0", Collections.emptyList(), true));

    public static void main(String[] args) {
        System.out.println("Игра запущена!");

        Render.renderMap(Main.ACTIVE_MAP);

        while (true) {
            GameMap gameMap = Main.ACTIVE_MAP;
            gameMap.getActivePlayer().move();

            if(gameMap.getFreePositions() == 0) {
                System.out.println("Игра окончена!");
                break;
            }
        }
    }
}