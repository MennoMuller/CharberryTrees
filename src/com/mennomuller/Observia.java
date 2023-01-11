package com.mennomuller;

import java.util.ArrayList;
import java.util.Random;

public class Observia {
    public static void main(String[] args) {
        CharberryTree tree = new CharberryTree();

        Notifier notifier = new Notifier();
        tree.addListener(notifier);

        Harvester harvester = new Harvester();
        tree.addListener(harvester);

        while (true) {
            tree.maybeGrow();
        }
    }
}

class CharberryTree {
    private final Random random = new Random();
    private boolean ripe = false;
    private final ArrayList<TreeListener> listeners = new ArrayList<>();

    public void reset() {
        this.ripe = false;
    }

    public void maybeGrow() {
// Only a tiny chance of ripening each time, but we try a lot!
        if (random.nextDouble() < 0.00000001 && !ripe) {
            ripe = true;
            updateListeners();
        }
    }

    public void addListener(TreeListener listener) {
        listeners.add(listener);
    }

    private void updateListeners() {
        for (TreeListener listener : listeners) {
            listener.handle(this);
        }
    }

}

interface TreeListener {
    void handle(CharberryTree tree);
}


class Notifier implements TreeListener {
    public void handle(CharberryTree tree) {
        System.out.println("A charberry fruit has ripened!");
    }
}

class Harvester implements TreeListener {
    public void harvest(CharberryTree tree) {
        tree.reset();
    }

    public void handle(CharberryTree tree) {
        harvest(tree);
    }
}
