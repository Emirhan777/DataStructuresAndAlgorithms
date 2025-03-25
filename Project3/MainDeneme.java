//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class MainDeneme {
//
//    public static void main(String[] args) throws FileNotFoundException {
//
//        String songsTextFilePath = "/Users/emirhan/IdeaProjects/Assignment3Cmpe250/src/songs.txt";//args[0];
//        String inputTextFilePath = "/Users/emirhan/IdeaProjects/Assignment3Cmpe250/src/sample_0.txt";//args[1];
//
//        //Read songs text file
//        File songsTextFile = new File(songsTextFilePath);
//        Scanner songsTextFile_scanner = new Scanner(songsTextFile);
//
//        Song[] songsArray;
//        int numberOfSongs;
//
//        int i = 0;
//
//        while (songsTextFile_scanner.hasNextLine()) {
//
//            String line = songsTextFile_scanner.nextLine();
//            String[] parts = line.strip().split(" ");
//
//            numberOfSongs = Integer.parseInt(parts[0]);
//            songsArray = new Song[numberOfSongs];
//            i++;
//            break;
//
//
//        }
//        System.out.println(numberOfSongs);
//
//
//
//
//
//
//        while (songsTextFile_scanner.hasNextLine()) {
//
//            String line = songsTextFile_scanner.nextLine();
//            String[] parts = line.strip().split(" ");
//
//            if (i == 0) {
//                numberOfSongs = Integer.parseInt(parts[0]);
//                songsArray = new Song[numberOfSongs];
//                i++;
//                continue;
//            }
//
//            int songId = Integer.parseInt(parts[0].strip());
//            String songName = parts[1].strip();
//            int playCount = Integer.parseInt(parts[2].strip());
//            int heartacheScore = Integer.parseInt(parts[3].strip());
//            int roadtripScore = Integer.parseInt(parts[4].strip());
//            int blissfulScore = Integer.parseInt(parts[5].strip());
//
//            Song song = new Song(songId, songName, playCount, heartacheScore, roadtripScore, blissfulScore);
//            songsArray[i] = song;
//
//            i++;
//        }
//        songsTextFile_scanner.close();
//
//
//        for (int j = 0; j < songsArray.length; j++) {
//            System.out.println(songsArray[j]);
//        }
//
//    }
//}