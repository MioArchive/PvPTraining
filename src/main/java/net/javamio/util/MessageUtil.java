package net.javamio.util;

import lombok.experimental.UtilityClass;
import net.javamio.PvPTraining;

import java.util.Objects;

@UtilityClass
public class MessageUtil {

    public final String PREFIX = PvPTraining.getInstance().getConfig().getString("messages.prefix");

    public void getString(String key) {
        Objects.requireNonNull(PvPTraining.getInstance().getConfig().getString(key)).replace("%prefix%", PREFIX);
    }

}
