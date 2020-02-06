package sg.grp4.DengueSG;

public class Post {

    private String text;
    private String imgUrl;
    private String user;
    private String likes;

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public Post() {
        //Empty Constructor
    }

    public Post(String text, String imgUrl, String user) {
        if (text.trim().equals("")) {
            text = "No Name";
        }

        this.text = text;
        this.imgUrl = imgUrl;
        this.user = user;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
