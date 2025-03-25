public class Song {

    //<song-id><song-name><play-count><heartache-score><roadtrip-score><blissful-score>
    int songId;
    String songName;
    int playCount;
    int heartacheScore;
    int roadtripScore;
    int blissfulScore;
    int listId;

    public Song(int songId, String songName, int playCount, int heartacheScore, int roadtripScore, int blissfulScore) {
        this.songId = songId;
        this.songName = songName;
        this.playCount = playCount;
        this.heartacheScore = heartacheScore;
        this.roadtripScore = roadtripScore;
        this.blissfulScore = blissfulScore;
    }


    public String toString() {
        return songId + " " + songName + " " + playCount + " " + heartacheScore + " " + roadtripScore + " " + blissfulScore + " " + listId;
    }




}
