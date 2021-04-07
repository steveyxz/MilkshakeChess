package com.milkshakeChess.inputs;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyInput extends KeyAdapter {

    public static ArrayList<Integer> keyPressedCodes = new ArrayList<>();

    @Override
    public void keyPressed(KeyEvent e) {
        if (!keyPressedCodes.contains(e.getKeyCode())) {
            keyPressedCodes.add(e.getKeyCode());
        }
        testKeyBoardShortcuts();
    }

    private void testKeyBoardShortcuts() {
        if (keyPressedCodes.contains(KeyEvent.VK_1) && keyPressedCodes.contains(KeyEvent.VK_ESCAPE) && keyPressedCodes.size() == 2) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (keyPressedCodes.contains(e.getKeyCode())) {
            keyPressedCodes.remove((Integer) e.getKeyCode());
        }
    }
}
