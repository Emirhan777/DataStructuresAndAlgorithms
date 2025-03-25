import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Project3 {

    public static void main(String[] args) throws FileNotFoundException {

        BlendCategoriesAndLists blend = new BlendCategoriesAndLists();

        String songsTextFilePath = "/Users/emirhan/IdeaProjects/Assignment3Cmpe250/minheapmacheap/src/songs.txt";//args[0];
        String listsTextFilePath = "/Users/emirhan/IdeaProjects/Assignment3Cmpe250/minheapmacheap/src/general_small.txt";//args[1];


////////////////////////////////    Songs File /////////////////////////////////////////////////////////////////////
        //Read songs text file
        File songsTextFile = new File(songsTextFilePath);
        Scanner songsTextFile_scanner = new Scanner(songsTextFile);


        ArrayList<String> songsTextFileLinesArrayList = new ArrayList<>();
        while (songsTextFile_scanner.hasNextLine()) {
            String line = songsTextFile_scanner.nextLine();
            songsTextFileLinesArrayList.add(line);
        }
        songsTextFile_scanner.close();


        int numberOfSongs = Integer.parseInt(songsTextFileLinesArrayList.get(0));
        Song[] songsArray =new Song[numberOfSongs + 1];;

        for (int j = 1; j < songsTextFileLinesArrayList.size(); j++) {
            String[] parts = songsTextFileLinesArrayList.get(j).strip().split(" ");

            int songId = Integer.parseInt(parts[0].strip());
            String songName = parts[1].strip();
            int playCount = Integer.parseInt(parts[2].strip());
            int heartacheScore = Integer.parseInt(parts[3].strip());
            int roadtripScore = Integer.parseInt(parts[4].strip());
            int blissfulScore = Integer.parseInt(parts[5].strip());

            Song song=new Song(songId, songName, playCount, heartacheScore, roadtripScore, blissfulScore);
            songsArray[j]=song;
        }

        //for (Song song : songsArray) {System.out.println(song);}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Binary Heap
        MaxHeapForHearthacheCategory binaryHeapHearthache = new MaxHeapForHearthacheCategory();
        MaxHeapForRoadtripCategory binaryHeapRoadtrip = new MaxHeapForRoadtripCategory();
        MaxHeapForBlissfulCategory binaryHeapBlissful = new MaxHeapForBlissfulCategory();
        //for (Song song : songsArray) {binaryHeapHearthache.insert(song);}
        //for (Song song : songsArray) {binaryHeapRoadtrip.insert(song);}
        //for (Song song : songsArray) {binaryHeapBlissful.insert(song);}




///////////////////////////////////////////  Lists File //////////////////////////////////////////////////////////////////////////////////////////////
        //Read lists and commands text file
        File listsTextFile = new File(listsTextFilePath);
        Scanner listsTextFile_scanner = new Scanner(listsTextFile);


        ArrayList<String> listsTextFileLinesArrayList = new ArrayList<>();
        while (listsTextFile_scanner.hasNextLine()) {
            String line = listsTextFile_scanner.nextLine();
            listsTextFileLinesArrayList.add(line);
        }
        songsTextFile_scanner.close();


        blend.takeConstraints(listsTextFileLinesArrayList.get(0));
        int numberOfLists = Integer.parseInt(listsTextFileLinesArrayList.get(1));
        blend.takeListLength(numberOfLists);

        for (int i = 2; i < 2*numberOfLists + 2 ; i++) {
            String[] parts = listsTextFileLinesArrayList.get(i).strip().split(" ");
            int theListNumber_1_2_3 =  i/2;

            if(i%2==0){if(Integer.parseInt(parts[1])==0){i++;}}
            else{
                for (String songIdString: parts) {
                    int songId = Integer.parseInt(songIdString);
                    songsArray[songId].listId = theListNumber_1_2_3;
                    binaryHeapHearthache.insert(songsArray[songId]);
                    binaryHeapRoadtrip.insert(songsArray[songId]);
                    binaryHeapBlissful.insert(songsArray[songId]);
                }
            }
        }

        //for (Song song : songsArray) {System.out.println(song);}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////  Binary Heaps to Blend Categories  //////////////////////////////////////////////////////////////////////////////////////////////




        while(binaryHeapHearthache.size() > 0) {
            Song song = binaryHeapHearthache.pop();
            blend.addToHeartacheCheckConstraints(song);
            }




        while(binaryHeapRoadtrip.size() > 0) {
            Song song = binaryHeapRoadtrip.pop();
            blend.addToRoadtripCheckConstraints(song);
            }




        while(binaryHeapBlissful.size() > 0) {
            Song song = binaryHeapBlissful.pop();
            blend.addToBlissfulCheckConstraints(song);
            }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////  Commands Addition-Removal-Ask  //////////////////////////////////////////////////////////////////////////////////////////////////

        for (int i =  2*numberOfLists + 2; i < listsTextFileLinesArrayList.size() ; i++) {
            String[] parts = listsTextFileLinesArrayList.get(i).strip().split(" ");

            if(parts[0].equals("ADD")) {
                int songId= Integer.parseInt(parts[1]);
                Song song = songsArray[songId];
                song.listId = Integer.parseInt(parts[2]);
                blend.addNewSong(song);
                blend.addNewSongRoadtrip(song);
                blend.addNewSongBlissful(song);

                //blend.blissfulCategoryBinaryHeap.print();
                System.out.println(blend.addedToHearthache + " " + blend.addedToRoadtrip + " " + blend.addedToBlissful);
                System.out.println(blend.addedToHearthacheWaiting + " " + blend.addedToRoadtripWaiting + " " + blend.addedToBlissfulWaiting);
                blend.addedToHearthache=0;
                blend.addedToRoadtrip=0;
                blend.addedToBlissful=0;
                blend.addedToHearthacheWaiting=0;
                blend.addedToRoadtripWaiting=0;
                blend.addedToBlissfulWaiting=0;
            }

            if(parts[0].equals("REM")){
                int songId= Integer.parseInt(parts[1]);
                Song song = songsArray[songId];
                int listId = Integer.parseInt(parts[2]);
                blend.removeSong(song, listId);
                System.out.println(blend.addedToHearthache + " " + blend.addedToRoadtrip + " " + blend.addedToBlissful);
                System.out.println(blend.addedToHearthacheWaiting + " " + blend.addedToRoadtripWaiting + " " + blend.addedToBlissfulWaiting);
                blend.addedToHearthache=0;
                blend.addedToRoadtrip=0;
                blend.addedToBlissful=0;
                blend.addedToHearthacheWaiting=0;
                blend.addedToRoadtripWaiting=0;
                blend.addedToBlissfulWaiting=0;
            }

            if(parts[0].equals("ASK")){
                blend.askCommand();
            }

        }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    }
}















//String outputTextFile = //args[2];

//        try {
//            PrintStream fileOut = new PrintStream(outputTextFile);
//            System.setOut(fileOut);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }