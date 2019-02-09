package app.model.views;

import app.enums.TubeStatus;

public class TubeViewModel {

    private String uuid;

    private String youtubeId;

    private String title;

    private String author;

    private long views;

    private String description;

    private TubeStatus tubeStatus;

    public TubeViewModel() {
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TubeStatus getTubeStatus() {
        return tubeStatus;
    }

    public void setTubeStatus(TubeStatus tubeStatus) {
        this.tubeStatus = tubeStatus;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
