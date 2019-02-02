package app.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tubes")
public class Tube {

    @Id
    @GeneratedValue(generator="uuid-string")
    @GenericGenerator(name="uuid-string", strategy = "uuid")
    @Column(name = "uuid", unique = true)
    private String id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "youtube_link", unique = true, nullable = false)
    @NotNull
    private String youtubeLink;

    @Column(name = "uploader", nullable = false, unique = true)
    @NotNull
    private String uploader;

    public Tube() {
    }

    public Tube(String name, @NotNull String description, @NotNull String youtubeLink, @NotNull String uploader) {
        this.name = name;
        this.description = description;
        this.youtubeLink = youtubeLink;
        this.uploader = uploader;
    }

    public static class Builder {
        private Tube tube;

        public Builder() {
            tube = new Tube();
        }

        public Builder name(String name) {
            tube.name = name;
            return this;
        }

        public Builder description(String description) {
            tube.description = description;
            return this;
        }

        public Builder youtubeLink(String youtubeLink) {
            tube.youtubeLink = youtubeLink;
            return this;
        }

        public Builder uploader(String uploader) {
            tube.uploader = uploader;
            return this;
        }

        public Tube build() {
            return tube;
        }
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getUploader() {
        return uploader;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public String getId() {
        return id;
    }
}
