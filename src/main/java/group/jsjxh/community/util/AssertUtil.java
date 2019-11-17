package group.jsjxh.community.util;

import group.jsjxh.community.exception.ParamNoFoundException;

public class AssertUtil {
    public static void Null(Object object,String msg) throws ParamNoFoundException {
        if(object==null)
            throw new ParamNoFoundException(msg);
    }
}
