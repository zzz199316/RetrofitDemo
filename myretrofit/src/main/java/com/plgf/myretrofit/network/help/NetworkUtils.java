package com.plgf.myretrofit.network.help;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class NetworkUtils {

    /**
     * Post用map
     */
    public static Map<String, Object> getPostMap(Object object) {
        Map<String, Object> map = new HashMap<>();
        List<Field> fields = getFields(object.getClass(), Object.class);
        for (Field temp : fields) {
            temp.setAccessible(true);
            try {
                if (temp.get(object) != null && !"serialVersionUID".equals(temp.getName())) {
                    map.put(temp.getName(), String.valueOf(temp.get(object)));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    private static List<Field> getFields(Class<?> cls, Class<?> end) {
        ArrayList list = new ArrayList();
        if (!cls.equals(end)) {
            Field[] fields = cls.getDeclaredFields();
            Field[] var7 = fields;
            int var6 = fields.length;
            for (int var5 = 0; var5 < var6; ++var5) {
                Field superClass = var7[var5];
                list.add(superClass);
            }
            Class var8 = (Class) cls.getGenericSuperclass();
            list.addAll(getFields(var8, end));
        }
        return list;
    }

}
