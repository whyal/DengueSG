package sg.grp4.DengueSG;

public class Post {

    private String text, imgUrl;

    public Post() {
        //Empty Constructor
    }

    public Post(String text, String imgUrl) {
        if (text.trim().equals("")) {
            text = "No Name";
        }

        this.text = text;
        this.imgUrl = imgUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
