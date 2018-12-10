package com.codecool.coffee.game;

class Action {
    private final String name;
    private final int delay;
    private int n;
    private final int nMax;

    public Action(String name, int delay, int nMax) {
        this.name = name;
        this.delay = delay;
        this.nMax = nMax;
        this.n = 0;
    }

    public void run(Game game, String detail) {
        synchronized (this) {
            assert n <= nMax;
            if (n == nMax) {
                game.fatal(String.format("%s %s: trying to run more than %d at a time", name, detail, nMax));
            }
            n++;
        }
        game.log(String.format("%s %s... (%.2f s)", name, detail, delay / 1000f));
        game.delay(delay);
        game.log(String.format("%s %s: done", name, detail, delay));
        synchronized (this) {
            n--;
            assert n >= 0;
        }
    }
}
