package group.jsjxh.community.util;

public class AssertUtil {
    public static void Null(Object object){
        if(object==null)
            throw new NullPointerException("is nullPoint");
    }
}
