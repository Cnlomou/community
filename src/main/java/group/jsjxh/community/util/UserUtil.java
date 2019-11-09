package group.jsjxh.community.util;

public abstract class UserUtil {
    private static final String DEAULT_NAME="小白";

    public static boolean isEmpty(String name){
        if(name==null||name.equals(""))
            return true;
        return false;
    }
    public static String userName(String name){
        if(isEmpty(name))
            return DEAULT_NAME;
        return name;
    }
}
