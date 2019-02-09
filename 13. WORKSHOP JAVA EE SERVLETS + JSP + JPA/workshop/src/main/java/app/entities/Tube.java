package app.entities;

import app.enums.TubeStatus;
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

    @Column(name = "title", nullable = false)
    @NotNull
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "youtube_id", nullable = false)
    @NotNull
    private String youtubeId;

    @Column(name = "author", nullable = false)
    @NotNull
    private String author;

    @Column(name = "views", nullable = false)
    private long views;

    @Column(name = "tube_status", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private TubeStatus tubeStatus;

    @ManyToOne
    @JoinColumn(name = "uploader_id", referencedColumnName = "uuid")
    private User uploader;

    public Tube() {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public TubeStatus getTubeStatus() {
        return tubeStatus;
    }

    public void setTubeStatus(TubeStatus tubeStatus) {
        this.tubeStatus = tubeStatus;
    }
}
