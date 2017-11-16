package pl.sdacademy;

public enum Genre {

    AFRICAN_HEAVY_METAL("African heavy metal"),
    EXPERIMENTAL_MUSIC("Experimental music"),
    ROCK("rock"),
    POP("pop"),
    CLASSIC("classic"),
    DRUM_AND_BASS("drum n bass");

    private String name;

    private Genre(String name) {
        this.name = name;
    }
}
