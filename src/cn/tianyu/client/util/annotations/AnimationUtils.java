/*
 * Decompiled with CFR 0.152.
 */
package cn.tianyu.client.util.annotations;

public class AnimationUtils {
    private static int delta;

    public static float clamp(float number, float min, float max) {
        return number < min ? min : Math.min(number, max);
    }

    public static float animateIDK(double target, double current, double speed) {
        boolean larger;
        boolean bl = larger = target > current;
        if (speed < 0.0) {
            speed = 0.0;
        } else if (speed > 1.0) {
            speed = 1.0;
        }
        double dif = Math.abs(current - target);
        double factor = dif * speed;
        current = larger ? (current += factor) : (current -= factor);
        return (float)current;
    }

    public static double animate(double target, double current, double speed) {
        boolean larger;
        if (current == target) {
            return current;
        }
        boolean bl = larger = target > current;
        if (speed < 0.0) {
            speed = 0.0;
        } else if (speed > 1.0) {
            speed = 1.0;
        }
        double dif = Math.max(target, current) - Math.min(target, current);
        double factor = dif * speed;
        if (factor < 0.1) {
            factor = 0.1;
        }
        if (larger) {
            if ((current += factor) >= target) {
                current = target;
            }
        } else if ((current -= factor) <= target) {
            current = target;
        }
        return current;
    }

    public static float animateSmooth(float current, float target, float speed) {
        return AnimationUtils.purse(target, current, AnimationUtils.getDelta(), Math.abs(target - current) * speed);
    }

    public static float purse(float target, float current, long delta, float speed) {
        if (delta < 1L) {
            delta = 1L;
        }
        float difference = current - target;
        float smoothing = Math.max(speed * ((float)delta / 16.0f), 0.15f);
        current = difference > speed ? Math.max(current - smoothing, target) : (difference < -speed ? Math.min(current + smoothing, target) : target);
        return current;
    }

    public static float animate(float target, float current, float speed) {
        boolean larger;
        if (current == target) {
            return current;
        }
        boolean bl = larger = target > current;
        if (speed < 0.0f) {
            speed = 0.0f;
        } else if (speed > 1.0f) {
            speed = 1.0f;
        }
        double dif = Math.max(target, current) - Math.min(target, current);
        double factor = dif * (double)speed;
        if (factor < 0.1) {
            factor = 0.1;
        }
        if (larger) {
            if ((current = (float)((double)current + factor)) >= target) {
                current = target;
            }
        } else if ((current = (float)((double)current - factor)) <= target) {
            current = target;
        }
        return current;
    }

    public static int getDelta() {
        return delta;
    }

    public static void setDelta(int delta) {
        AnimationUtils.delta = delta;
    }
}

