import java.util.ArrayList;

public class BlendCategoriesAndLists {

    //here we construct categories as sorted arraylists  ***
    ArrayList<Song> heartacheCategorySortedArrayList;
    ArrayList<Song> roadtripCategorySortedArrayList;
    ArrayList<Song> blissfulCategorySortedArrayList;

    //here are category constraints
    int maxNumberOfSongsFromListToCategory;
    int maxNumberOfSongsInHeartacheCategory;
    int maxNumberOfSongsInRoadtripCategory;
    int maxNumberOfSongsInBlissfulCategory;

    //number of songs added from lists to categories  ***
    int[] numberOfSongsFromListsToHeartache;
    int[] numberOfSongsFromListsToRoadtrip;
    int[] numberOfSongsFromListsToBlissful;


    //here we construct categoriy waiting lists as sorted array lists (songs in lists but not in categories) ***
    ArrayList<Song> heartacheCategoryWaitingSortedArrayList;
    ArrayList<Song> roadtripCategoryWaitingSortedArrayList;
    ArrayList<Song> blissfulCategoryWaitingSortedArrayList;

    //here are lists which are sorted array lists
    //ArrayList<Song>[] arrayOfLists;



    public BlendCategoriesAndLists() {
        heartacheCategorySortedArrayList = new ArrayList<>();
        roadtripCategorySortedArrayList = new ArrayList<>();
        blissfulCategorySortedArrayList = new ArrayList<>();

        heartacheCategoryWaitingSortedArrayList = new ArrayList<>();
        roadtripCategoryWaitingSortedArrayList = new ArrayList<>();
        blissfulCategoryWaitingSortedArrayList = new ArrayList<>();
    }

    public BlendCategoriesAndLists(int maxNumberOfSongsFromListToCategory, int maxNumberOfSongsInHeartacheCategory, int maxNumberOfSongsInRoadtripCategory, int maxNumberOfSongsInBlissfulCategory) {

        heartacheCategorySortedArrayList = new ArrayList<>();
        roadtripCategorySortedArrayList = new ArrayList<>();
        blissfulCategorySortedArrayList = new ArrayList<>();

        this.maxNumberOfSongsFromListToCategory = maxNumberOfSongsFromListToCategory;
        this.maxNumberOfSongsInHeartacheCategory = maxNumberOfSongsInHeartacheCategory;
        this.maxNumberOfSongsInRoadtripCategory = maxNumberOfSongsInRoadtripCategory;
        this.maxNumberOfSongsInBlissfulCategory = maxNumberOfSongsInBlissfulCategory;

        heartacheCategoryWaitingSortedArrayList = new ArrayList<>();
        roadtripCategoryWaitingSortedArrayList = new ArrayList<>();
        blissfulCategoryWaitingSortedArrayList = new ArrayList<>();

    }

    void takeConstraints(String constraintsLine){
        String[] constraintsParts = constraintsLine.strip().split(" ");

        int constraint0 = Integer.parseInt(constraintsParts[0]);
        int constraint1 = Integer.parseInt(constraintsParts[1]);
        int constraint2 = Integer.parseInt(constraintsParts[2]);
        int constraint3 = Integer.parseInt(constraintsParts[3]);

        maxNumberOfSongsFromListToCategory=constraint0;
        maxNumberOfSongsInHeartacheCategory=constraint1;
        maxNumberOfSongsInRoadtripCategory=constraint2;
        maxNumberOfSongsInBlissfulCategory=constraint3;
    }

    void takeListLength(int numberOfLists){
        numberOfSongsFromListsToHeartache = new int[numberOfLists + 1];
        numberOfSongsFromListsToRoadtrip = new int[numberOfLists + 1];
        numberOfSongsFromListsToBlissful = new int[numberOfLists + 1];
    }

    void addToHeartacheCheckConstraints(Song song){
        boolean canBeAdded = checkHeartacheConstaits(song);
        if(canBeAdded){
            //system.out.println("Added to hearthache: " + song);
            numberOfSongsFromListsToHeartache[song.listId]++;
            heartacheCategorySortedArrayList.add(song);
            //system.out.println(heartacheCategorySortedArrayList);
        }
        else {
            //system.out.println("Added to hearthache waiting: " + song);
            heartacheCategoryWaitingSortedArrayList.add(song);
            //system.out.println(heartacheCategoryWaitingSortedArrayList);
        }

    }

    boolean checkHeartacheConstaits(Song song){
        if(song.listId != 0 ){
            if(heartacheCategorySortedArrayList.size() < maxNumberOfSongsInHeartacheCategory) {
                if (numberOfSongsFromListsToHeartache[song.listId] < maxNumberOfSongsFromListToCategory) {
                    //system.out.println("ÇIKTI");
                    return true;
                }
            }
        }
        return false;
    }


    void addToRoadtripCheckConstraints(Song song){
        if(song.listId - 1 != 0 ){
            if(roadtripCategorySortedArrayList.size()<maxNumberOfSongsInRoadtripCategory) {
                if (numberOfSongsFromListsToRoadtrip[song.listId ] < maxNumberOfSongsFromListToCategory) {
                    //system.out.println("Added to roadtrip: " + song);
                    numberOfSongsFromListsToRoadtrip[song.listId ]++;
                    roadtripCategorySortedArrayList.add(song);
                }
            }
        }
    }

    void addToBlissfulCheckConstraints(Song song){

        if(song.listId - 1 != 0 ){
            if(blissfulCategorySortedArrayList.size()<maxNumberOfSongsInBlissfulCategory) {
                if (numberOfSongsFromListsToBlissful[song.listId ] < maxNumberOfSongsFromListToCategory) {
                    //system.out.println("Added to blissful: " + song);
                    numberOfSongsFromListsToBlissful[song.listId ]++;
                    blissfulCategorySortedArrayList.add(song);
                }
            }
        }
    }



    void addNewSong(Song song){
        int lastIndex = heartacheCategorySortedArrayList.size() - 1;
        if(heartacheCategorySortedArrayList.size() < maxNumberOfSongsInHeartacheCategory){
            if(numberOfSongsFromListsToHeartache[song.listId ] < maxNumberOfSongsFromListToCategory){
                //system.out.println("Added to hearthache: " + song);
                numberOfSongsFromListsToHeartache[song.listId ]++;
                heartacheCategorySortedArrayList.add(song);
                //system.out.println(heartacheCategorySortedArrayList);

                sortThisSingleSongInCategoryArrayList(lastIndex + 1, song);

            }

            else{
                replaceSongsIfTheMinimumSongOfTheListIsSmaller(song);
            }

        }
        else {
            if(numberOfSongsFromListsToHeartache[song.listId ] == maxNumberOfSongsFromListToCategory){
                replaceSongsIfTheMinimumSongOfTheListIsSmaller(song);
            }

            else {

                Song minimumSongInCategory = heartacheCategorySortedArrayList.get(lastIndex);
                boolean replaced = false;

                if (minimumSongInCategory.heartacheScore < song.heartacheScore) {
                    //system.out.println("Replaced to hearthache: BURA " + song);
                    numberOfSongsFromListsToHeartache[song.listId ]++;

                    Song songToGo = heartacheCategorySortedArrayList.get(lastIndex);
                    numberOfSongsFromListsToHeartache[songToGo.listId ]--;
                    //system.out.println("Added to hearthache waiting: " + songToGo);
                    heartacheCategoryWaitingSortedArrayList.add(songToGo);
                    //system.out.println(heartacheCategoryWaitingSortedArrayList);
                    int waitingArrayListIndex = heartacheCategoryWaitingSortedArrayList.size() - 1;
                    sortThisSingleSongInCategoryWaitingArrayList(waitingArrayListIndex, songToGo);



                    heartacheCategorySortedArrayList.set(lastIndex, song);
                    //system.out.println(heartacheCategorySortedArrayList);

                    sortThisSingleSongInCategoryArrayList(lastIndex, song);
                    replaced = true;
                }

                if(!replaced){
                    //system.out.println("Added to hearthache waiting: " + song);
                    heartacheCategoryWaitingSortedArrayList.add(song);
                    //system.out.println(heartacheCategoryWaitingSortedArrayList);
                    int waitingArrayListIndex = heartacheCategoryWaitingSortedArrayList.size() - 1;
                    sortThisSingleSongInCategoryWaitingArrayList(waitingArrayListIndex, song);
                }

            }

        }



    }


    void replaceSongsIfTheMinimumSongOfTheListIsSmaller(Song song){
        boolean replaced = false;
        for (int i = heartacheCategorySortedArrayList.size() - 1; i > -1 ; i--) {
            Song songInCategory = heartacheCategorySortedArrayList.get(i);
            if(songInCategory.listId == song.listId) {
                //Song minimumSongInCategoryFromList = songInCategory;
                if (songInCategory.heartacheScore < song.heartacheScore) {

                    Song songToGo = heartacheCategorySortedArrayList.get(i);
                    //system.out.println("Added to hearthache waiting: " + songToGo);
                    heartacheCategoryWaitingSortedArrayList.add(songToGo);
                    //system.out.println(heartacheCategoryWaitingSortedArrayList);
                    int waitingArrayListIndex = heartacheCategoryWaitingSortedArrayList.size() - 1;
                    sortThisSingleSongInCategoryWaitingArrayList(waitingArrayListIndex, songToGo);

                    heartacheCategorySortedArrayList.set(i, song);
                    sortThisSingleSongInCategoryArrayList(i, song);
                    //system.out.println("Replaced to hearthache: " + song);

                    replaced = true;
                }
                break;
            }
        }

        if(!replaced){
            //system.out.println("Added to hearthache waiting: " + song);
            heartacheCategoryWaitingSortedArrayList.add(song);
            //system.out.println(heartacheCategoryWaitingSortedArrayList);
            int waitingArrayListIndex = heartacheCategoryWaitingSortedArrayList.size() - 1;
            sortThisSingleSongInCategoryWaitingArrayList(waitingArrayListIndex, song);
        }

    }

    void sortThisSingleSongInCategoryArrayList(int index, Song song){
        int minIndex = 0;
        int maxIndex = heartacheCategorySortedArrayList.size()-1;
        if(index != minIndex) {
            while (song.heartacheScore > heartacheCategorySortedArrayList.get(index - 1).heartacheScore) {
                heartacheCategorySortedArrayList.set(index, heartacheCategorySortedArrayList.get(index-1));
                heartacheCategorySortedArrayList.set(index - 1, song );
                index--;
                //system.out.println(heartacheCategorySortedArrayList);
                if(index==minIndex){break;}
            }
        }
        if(index != maxIndex){
            while (song.heartacheScore < heartacheCategorySortedArrayList.get(index + 1).heartacheScore) {
                heartacheCategorySortedArrayList.set(index, heartacheCategorySortedArrayList.get(index+1));
                heartacheCategorySortedArrayList.set(index + 1, song );
                index++;
                //system.out.println(heartacheCategorySortedArrayList);
                if(index==maxIndex){break;}
            }
        }
    }

    void sortThisSingleSongInCategoryWaitingArrayList(int index, Song song){
        int minIndex = 0;
        int maxIndex = heartacheCategoryWaitingSortedArrayList.size()-1;
        if(index != minIndex) {
            while (song.heartacheScore > heartacheCategoryWaitingSortedArrayList.get(index - 1).heartacheScore) {
                heartacheCategoryWaitingSortedArrayList.set(index, heartacheCategoryWaitingSortedArrayList.get(index-1));
                heartacheCategoryWaitingSortedArrayList.set(index - 1, song );
                index--;
                //system.out.println(heartacheCategoryWaitingSortedArrayList);
                if(index==minIndex){break;}
            }
        }
        if(index != maxIndex){
            while (song.heartacheScore < heartacheCategoryWaitingSortedArrayList.get(index + 1).heartacheScore) {
                heartacheCategoryWaitingSortedArrayList.set(index, heartacheCategoryWaitingSortedArrayList.get(index+1));
                heartacheCategoryWaitingSortedArrayList.set(index + 1, song );
                index++;
                //system.out.println(heartacheCategoryWaitingSortedArrayList);
                if(index==maxIndex){break;}
            }
        }
    }


    void addNewSongHearthache(){

    }








    void removeSong(Song song, int listId){
        song.listId = 0;

    }




}





//
//
//
//        if(numberOfSongsFromListsToHeartache[song.listId - 1] == maxNumberOfSongsFromListToCategory){
//                replaceSongsIfTheMinimumSongOfTheListIsSmaller(song);
//                }
//                else {
//                if(heartacheCategorySortedArrayList.size() < maxNumberOfSongsInHeartacheCategory){
//        //system.out.println("Added to hearthache: " + song);
//        numberOfSongsFromListsToHeartache[song.listId - 1]++;
//        heartacheCategorySortedArrayList.add(song);
//        //system.out.println(heartacheCategorySortedArrayList);
//        }
//        else {
//        int lastIndex = heartacheCategorySortedArrayList.size() - 1;
//        Song minimumSongInCategory = heartacheCategorySortedArrayList.get(lastIndex);
//
//        if (minimumSongInCategory.heartacheScore < song.heartacheScore) {
//        //system.out.println("Added to hearthache: " + song);
//        numberOfSongsFromListsToHeartache[song.listId - 1]++;
//        heartacheCategorySortedArrayList.add(song);
//        //system.out.println(heartacheCategorySortedArrayList);
//
//
//        heartacheCategorySortedArrayList.set(lastIndex, song);
//        sortThisSingleSongInCategoryArrayList(lastIndex, song);
//        //system.out.println("Replaced to hearthache: BURA " + song);
//        }
//        }
//        }