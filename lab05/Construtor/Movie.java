package Construtor;

import java.util.List;

public class Movie {
    private final String title;
    private final int year;
    private final Person director;
    private final Person writer;
    private final String series;
    private final List<Person> cast;
    private final List<Place> locations;
    private final List<String> languages;
    private final List<String> genres;
    private final boolean isTelevision;
    private final boolean isNetflix;
    private final boolean isIndependent;

    private Movie(Builder builder) {
        this.title = builder.title;
        this.year = builder.year;
        this.director = builder.director;
        this.writer = builder.writer;
        this.series = builder.series;
        this.cast = builder.cast;
        this.locations = builder.locations;
        this.languages = builder.languages;
        this.genres = builder.genres;
        this.isTelevision = builder.isTelevision;
        this.isNetflix = builder.isNetflix;
        this.isIndependent = builder.isIndependent;
    }

    public static class Builder {
        // mandatory attributes
        private final String title;
        private final int year;

        // optional attributes
        private Person director;
        private Person writer;
        private String series;
        private List<Person> cast;
        private List<Place> locations;
        private List<String> languages;
        private List<String> genres;
        private boolean isTelevision;
        private boolean isNetflix;
        private boolean isIndependent;

        // main constructor
        public Builder(String title, int year) {
            this.title = title;
            this.year = year;
        }

        // assign director
        public Builder director(Person director) {
            this.director = director;
            return this;
        }

        // assign writer
        public Builder writer(Person writer) {
            this.writer = writer;
            return this;
        }

        // assign series
        public Builder series(String series) {
            this.series = series;
            return this;
        }

        // assign cast
        public Builder cast(List<Person> cast) {
            this.cast = cast;
            return this;
        }

        // assign locations
        public Builder locations(List<Place> locations) {
            this.locations = locations;
            return this;
        }

        // assign languages
        public Builder languages(List<String> languages) {
            this.languages = languages;
            return this;
        }

        // assign genres
        public Builder genres(List<String> genres) {
            this.genres = genres;
            return this;
        }

        // assign isTelevision
        public Builder isTelevision(boolean isTelevision) {
            this.isTelevision = isTelevision;
            return this;
        }

        // assign isNetflix
        public Builder isNetflix(boolean isNetflix) {
            this.isNetflix = isNetflix;
            return this;
        }

        // assign isIndependent
        public Builder isIndependent(boolean isIndependent) {
            this.isIndependent = isIndependent;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public Person getDirector() {
        return director;
    }

    public Person getWriter() {
        return writer;
    }

    public String getSeries() {
        return series;
    }

    public List<Person> getCast() {
        return cast;
    }

    public List<Place> getLocations() {
        return locations;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public List<String> getGenres() {
        return genres;
    }

    public boolean isTelevision() {
        return isTelevision;
    }

    public boolean isNetflix() {
        return isNetflix;
    }

    public boolean isIndependent() {
        return isIndependent;
    }

    @Override
    public String toString() {
        return String.format("""
                 _____
                |Movie|
                 ‾‾‾‾‾
                Title: %s
                Year: %d
                Director: %s
                Writer: %s
                Series: %s
                Cast: %s
                Locations: %s
                Languages: %s
                Genres: %s
                isTelevision: %s
                isNetflix: %s
                isIndependent: %s
                """,
                this.getTitle(),
                this.getYear(),
                this.getDirector(),
                this.getWriter(),
                this.getSeries(),
                this.getCast(),
                this.getLocations(),
                this.getLanguages(),
                this.getGenres(),
                this.isTelevision(),
                this.isNetflix(),
                this.isIndependent()
        );
    }
}
