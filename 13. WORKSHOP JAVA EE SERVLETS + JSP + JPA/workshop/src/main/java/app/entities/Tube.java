package app.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tubes")
public class Tube {

    @Id
    @GeneratedValue(generator = "uuid-string")
    @GenericGenerator(name = "uuid-string", strategy = "uuid")
    @Column(name = "uuid", unique = true)
    private String uuid;

    @Column(name = "title", nullable = false, unique = true)
    @NotNull
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "youtube_id", unique = true, nullable = false)
    @NotNull
    private String youtubeId;

    @Column(name = "views", nullable = false)
    private long views;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "uploader_id")
    private User uploader;

    public Tube() {
    }

    public Tube(@NotNull String title, String description, @NotNull String youtubeId, long views, User uploader) {
        this.title = title;
        this.description = description;
        this.youtubeId = youtubeId;
        this.views = views;
        this.uploader = uploader;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public User getUploader() {
        return uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }
}
