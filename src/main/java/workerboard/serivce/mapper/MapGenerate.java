package workerboard.serivce.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public interface MapGenerate {

    default Map<String,String> toMap(){

        Map<String,String> map = new HashMap<>();
        Class<?> thisClass = null;

        try {
            thisClass = Class.forName(this.getClass().getName());
            Field[] aClassFields = thisClass.getDeclaredFields();

            for(Field f : aClassFields){
                f.setAccessible(true);
                if(f.get(this) != null && (f.getType() == String.class || f.getType() == Long.class )) {
                    map.put(f.getName(), f.get(this).toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }
}
