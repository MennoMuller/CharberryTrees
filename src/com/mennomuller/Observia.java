package com.mennomuller;

import java.util.Random;

public class Observia {
    public static void main(String[] args) {
        CharberryTree tree = new CharberryTree();
        Harvester harvester = new Harvester();
        Notifier notifier = new Notifier();
        while (true) {
            tree.maybeGrow();
            if (notifier.handle(tree)) {
                harvester.harvest(tree);
            }
        }
    }
}

class CharberryTree {
    private Random random = new Random();
    private boolean ripe = false;

    public boolean isRipe() {
        return ripe;
    }

    public void reset() {
        this.ripe = false;
    }

    public void maybeGrow() {
// Only a tiny chance of ripening each time, but we try a lot!
        if (random.nextDouble() < 0.00000001 && !ripe)
            ripe = true;
    }
}

class Notifier {
    public boolean handle(CharberryTree tree) {
        if (tree.isRipe()) {
            System.out.println("A charberry fruit has ripened!");
            return true;
        }
        return false;
    }
}

class Harvester {
    public void harvest(CharberryTree tree) {
        tree.reset();
    }
}
