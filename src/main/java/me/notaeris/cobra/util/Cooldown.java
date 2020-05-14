package me.notaeris.cobra.util;

import java.util.HashMap;
import java.util.UUID;

public class Cooldown {

    private final HashMap<String, Cooldown> cooldowns = new HashMap<>();
    private long start;
    private int seconds;

    /**
     * add a cooldown
     *
     * @param uuid uuid of a cooldown
     * @param name name of a cooldown
     * @param seconds time of a cooldown
     */
    public void addCooldown(UUID uuid, String name, int seconds) {
        this.start = System.currentTimeMillis();
        this.cooldowns.put(uuid.toString() + name, this);
        this.seconds = seconds;
    }

    /**
     * check if the cooldown is active
     *
     * @param uuid uuid of a cooldown
     * @param name name of a cooldown
     * @return the cooldown
     */
    public boolean hasCooldown(UUID uuid, String name) {
        if(getRemaining(uuid, name) >= 1) {
            return true;
        }
        this.cooldowns.remove(uuid + name);
        return false;
    }

    /**
     * get an existing cooldown
     *
     * @param uuid uuid of the cooldown
     * @param name name of the cooldown
     * @return the cooldown
     */
    private Cooldown getCooldown(UUID uuid, String name) {
        return cooldowns.get(uuid.toString() + name);
    }

    /**
     * get the remaining time of a cooldown
     *
     * @param uuid uuid of a cooldown
     * @param name name of a cooldown
     * @return the remaining time
     */
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