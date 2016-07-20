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
        classHashMap.put(Utils.REGISTER_URL, RegisterRsp.class);
        classHashMap.put(Utils.VERIFY_URL, BaseResponse.class);


    }

    public static Class<BaseResponse> getModelClass(String url) {
        return classHashMap.get(url);
    }
}