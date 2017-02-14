package open.com.nicesound.constant;

/**
 * Created by Crcker on 14/02/2017.
 * 邮箱：635281462@qq.com
 */

public class Constants {

    public static final int RESULT_NETERROR = 001;
    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_FAIL = 003;
    public static final int RESULT_CODE_ERROR = 004;

    public static final String RULE = "[a-zA-Z0-9_]{6,16}";
    public static final String PHONE_RULE = "(^(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7})$";

    //服务器地址
    public static final String SERVER_ADRESS = "http://api1.peiyinxiu.com/";
    //主页 热门
    public static final String HOME_HOT = "Api/Film/HomeData?appkey=8b232b5997100d5a&area=110100&pg=1&t=9686&token=&userId=0&sign=a1acd41d5dfbbd410ada28df79f48694";

}
