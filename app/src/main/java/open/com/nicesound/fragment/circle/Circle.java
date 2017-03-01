package open.com.nicesound.fragment.circle;



/**
 * Created by fight on 2017/2/16.
 */

public class Circle {
    private String name;
    private String img;
    private String postNum;
    private String attentionNum;

    public Circle(String name, String img, String postNum, String attentionNum) {
        this.name = name;
        this.img = img;
        this.postNum = postNum;
        this.attentionNum = attentionNum;
    }

    public String getPostNum() {
        return postNum;
    }

    public void setPostNum(String postNum) {
        this.postNum = postNum;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAttentionNum() {
        return attentionNum;
    }

    public void setAttentionNum(String attentionNum) {
        this.attentionNum = attentionNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
