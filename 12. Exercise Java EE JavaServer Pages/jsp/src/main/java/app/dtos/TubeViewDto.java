package app.dtos;

public class TubeViewDto {

    private String title;
    private String description;
    private String youtubeLink;
    private String uploader;

    public TubeViewDto() {
    }

    public TubeViewDto(String title, String description, String youtubeLink, String uploader) {
        this.title = title;
        this.description = description;
        this.youtubeLink = youtubeLink;
        this.uploader = uploader;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }
}
