package com.milkshakeChess.settings;

import com.milkshakeChess.enums.gameChoice.Difficulty;
import com.milkshakeChess.enums.gameChoice.GameType;
import com.milkshakeChess.enums.gameChoice.Style;

public class GameChoiceStorage {

    public static GameType gameType = GameType.NoneChosen;
    public static Difficulty difficulty = Difficulty.NoneChosen;
    public static Style style = Style.NoneChosen;

    public static int convertDifficultyToRating() {
        return switch (difficulty) {
            case CompletelyStupid -> 50;
            case Trash -> 200;
            case PrettyBadStill -> 400;
            case OK -> 600;
            case Average -> 800;
            case RegularPlayer -> 1000;
            case IntermediatePlayer -> 1200;
            case SortaSweatyPlayer -> 1400;
            case SweatyPlayer -> 1600;
            case VerySweatyPlayer -> 1800;
            case NoneChosen -> 0;
        };
    }

}
