package ru.solomka.cross;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.solomka.cross.entity.GameSetting;
import ru.solomka.cross.entity.Player;
import ru.solomka.cross.utils.IntPair;

import java.util.*;

@Getter
@NoArgsConstructor(force = true)
public class GameMap {

    private static final List<Player> PLAYERS = new ArrayList<>();

    private final GameSetting gameSetting;

    public GameMap(GameSetting gameSetting) {
        this.gameSetting = gameSetting;
    }

    public GameMap initMap(Player ...players) {
        System.out.println("[Log] Map is init!");
        PLAYERS.addAll(Arrays.asList(players));
        return this;
    }

    public Player getOfflinePlayer() {
        return PLAYERS.stream().filter(p -> !p.isActiveRole())
                .findAny().orElse(null);
    }

    public Player getActivePlayer() {
        return PLAYERS.stream().filter(Player::isActiveRole)
                .findAny().orElse(null);
    }

    public int getFreePositions() {
        int busyCoordsActive = getActivePlayer().getPositions().size();
        int busyCoordsPassive = getOfflinePlayer().getPositions().size();
        GameSetting setting = Main.ACTIVE_MAP.getGameSetting();
        return (setting.getMapSize()[0] * setting.getMapSize()[1]) - (busyCoordsActive + busyCoordsPassive);
    }

    public Map<Player, List<IntPair<Integer, Integer>>> getBusyPositions() {
        Map<Player, List<IntPair<Integer, Integer>>> positions = new LinkedHashMap<>();
        PLAYERS.forEach(p -> positions.put(p, p.getPositions()));
        return positions;
    }
}