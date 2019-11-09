package group.jsjxh.community.util;

import java.util.UUID;

public abstract class UuidUtil {
    public static String uuid(){
        return UUID.randomUUID().toString();
    }
}
