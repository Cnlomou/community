package group.jsjxh.community.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class FormateUtil {
    private static ObjectMapper objectMapper=new ObjectMapper();
    static{
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES,true);
    }
    public static String toJSON(Object object){
        if(object==null)
            throw new NullPointerException();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static ObjectMapper getMapper(){
        return objectMapper;
    }

    public static <T> T toObject(String json,Class<T> c) throws JsonProcessingException {
        return objectMapper.readValue(json,c);
    }

}
