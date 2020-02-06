package sg.grp4.DengueSG;

public class ShopItem {
    private String Title;
    private String Description ;
    private String Price ;
    private int Thumbnail ;

    public ShopItem() {
    }

    public ShopItem(String title, String description, String price, int thumbnail) {
        Title = title;
        Description = description;
        Price = price;
        Thumbnail = thumbnail;
    }


    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getPrice() { return Price; }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}

