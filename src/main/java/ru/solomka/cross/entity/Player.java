package ru.solomka.cross.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.solomka.cross.GameMap;
import ru.solomka.cross.utils.IntPair;
import ru.solomka.cross.Main;
import ru.solomka.cross.Render;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.lang.StringTemplate.STR;

@AllArgsConstructor
@Data @FieldDefaults(level = AccessLevel.PRIVATE)
public class Player {

    final String name;
    @Setter List<IntPair<Integer, Integer>> positions;
    @Setter boolean activeRole;

    public void move() {
        System.out.println(STR."Ход игрока - \{name}");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введи координаты хода...(Пример (x/y): 0, 1)");

        String coordinates = scanner.nextLine();

        if(coordinates == null || coordinates.isEmpty() || (coordinates.split(" ").length == 0)) {
            System.out.println("Введены некорректные координаты!");
            return;
        }

        String[] formattedInput = coordinates.split(" ");

        if(formattedInput.length != 2 || formattedInput[0] == null || formattedInput[1] == null) return;

        int x,y;

        try {
            x = Integer.parseInt(formattedInput[0]);
            y = Integer.parseInt(formattedInput[1]);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода координат!");
            return;
        }

        GameMap gameMap = new GameMap();
        if(!validMove(x, y, gameMap)) {
            System.out.println(STR."Введенные координаты вероятно уже использованы или выходят за границу (\{Main.ACTIVE_MAP.getGameSetting().getMapSize()[0]} <= x|y <= \{Main.ACTIVE_MAP.getGameSetting().getMapSize()[1]})!");
            return;
        }

        List<IntPair<Integer, Integer>> array = new ArrayList<>(positions);
        array.add(new IntPair<>(x, y));
        this.setPositions(array);

        gameMap.getOfflinePlayer().setActiveRole(true);
        this.setActiveRole(false);

        Render.renderMap(Main.ACTIVE_MAP);
    }

    private boolean validMove(int x, int y, GameMap map) {
        if(x > Main.ACTIVE_MAP.getGameSetting().getMapSize()[0] || y > Main.ACTIVE_MAP.getGameSetting().getMapSize()[1]) return false;

        for(Map.Entry<Player, List<IntPair<Integer, Integer>>> aMap : map.getBusyPositions().entrySet())
            if(aMap.getValue().contains(new IntPair<>(x, y))) return false;

        return true;
    }
}