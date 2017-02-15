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
    //主页 直播
    public static final String HOME_LIVE= "Api/live/LiveList?appkey=8b232b5997100d5a&pg=2&token=&userId=0&sign=9a27bafcdcae8ed66aa79d140e69f2df";
    //主页 直播下拉刷新
    public static final String HOME_LIVE_REFRESH= "Api/live/LiveList?appkey=8b232b5997100d5a&pg=1&token=&userId=0&sign=3acd5155480082729eec9c94ccb20049";
}
