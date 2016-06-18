package lemonlabs.in.savelife;

import java.util.HashMap;

/**
 * Created by Santhosh.Joseph on 17-06-2016.
 */
public class ModelClassMapper {
    private static HashMap<String, Class> classHashMap;
    static {
        classHashMap = new HashMap<>();
        classHashMap.put(Utils.LOGIN_URL, LoginRsp.class);
        //classHashMap.put(Utils.NOTIFICATION_LIST_URL, NotificationRsp.class);
    }

    public static Class<ModelClassMapper> getModelClass(String url) {
        return classHashMap.get(url);
    }
}