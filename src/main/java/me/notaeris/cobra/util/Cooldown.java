package me.notaeris.cobra.util;

import java.util.HashMap;
import java.util.UUID;

public class Cooldown {

    private static final HashMap<String, Cooldown> cooldowns = new HashMap<String, Cooldown>();
    private static long start;
    private final int timeInSeconds;
    private final UUID uuid;
    private final String cooldownName;

    public Cooldown(UUID uuid, String cooldownName, int timeInSeconds) {
        this.uuid = uuid;
        this.cooldownName = cooldownName;
        this.timeInSeconds = timeInSeconds;
    }

    public static boolean hasCooldown(UUID id, String cooldownName) {
        if (getRemaining(id, cooldownName) >= 1) {
            return true;
        }
        stop(id, cooldownName);
        return false;
    }

    private static Cooldown getCooldown(UUID id, String cooldownName) {
        return cooldowns.get(id.toString() + cooldownName);
    }

    public static int getRemaining(UUID id, String cooldownName) {
        Cooldown cooldown = getCooldown(id, cooldownName);
        int f = -1;

        if (cooldown != null) {
            long now = System.currentTimeMillis();
            long cooldownTime = start;
            int totalTime = cooldown.timeInSeconds;
            int r = (int) (now - cooldownTime) / 1000;
            f = (r - totalTime) * -1;
        }
        return f;
    }

    public void start() {
        start = System.currentTimeMillis();
        cooldowns.put(this.uuid.toString() + this.cooldownName, this);
    }

    private static void stop(UUID id, String cooldownName) {
        cooldowns.remove(id + cooldownName);
    }
}
