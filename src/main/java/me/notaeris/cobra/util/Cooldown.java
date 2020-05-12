package me.notaeris.cobra.util;

import java.util.HashMap;
import java.util.UUID;

public class Cooldown {

    private final HashMap<String, Cooldown> cooldowns = new HashMap<>();
    private long start;
    private int seconds;

    public void addCooldown(UUID uuid, String name, int seconds) {
        this.start = System.currentTimeMillis();
        this.cooldowns.put(uuid.toString() + name, this);
        this.seconds = seconds;
    }

    public boolean hasCooldown(UUID uuid, String name) {
        if(getRemaining(uuid, name) >= 1) {
            return true;
        }
        this.cooldowns.remove(uuid + name);
        return false;
    }

    private Cooldown getCooldown(UUID uuid, String name) {
        return cooldowns.get(uuid.toString() + name);
    }

    public int getRemaining(UUID uuid, String name) {
        Cooldown cooldown = getCooldown(uuid, name);
        int f = -1;

        if (cooldown != null) {
            long now = System.currentTimeMillis();
            long cooldownTime = this.start;
            int totalTime = cooldown.seconds;
            int r = (int) (now - cooldownTime) / 1000;
            f = (r - totalTime) * -1;
        }
        return f;
    }
}