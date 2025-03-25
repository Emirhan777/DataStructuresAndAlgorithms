import java.util.ArrayList;

public class BlendCategoriesAndLists {

    //categories as heaps  ***
    MinHeapForHearthacheCategory heartacheCategoryBinaryHeap;
    MinHeapForRoadtripCategory roadtripCategoryBinaryHeap;
    MinHeapForBlissfulCategory blissfulCategoryBinaryHeap;

    //category constraints
    int maxNumberOfSongsFromListToCategory;
    int maxNumberOfSongsInHeartacheCategory;
    int maxNumberOfSongsInRoadtripCategory;
    int maxNumberOfSongsInBlissfulCategory;

    //number of songs added from lists to categories  ***
    int[] numberOfSongsFromListsToHeartache;
    int[] numberOfSongsFromListsToRoadtrip;
    int[] numberOfSongsFromListsToBlissful;


    //category waiting lists as heaps (songs in lists but not in categories) ***
    MaxHeapForHearthacheCategory heartacheCategoryWaitingBinaryHeap;
    MaxHeapForRoadtripCategory roadtripCategoryWaitingBinaryHeap;
    MaxHeapForBlissfulCategory blissfulCategoryWaitingBinaryHeap;

    //here are lists which are sorted array lists
    //ArrayList<Song>[] arrayOfLists;


    int addedToHearthache;
    int addedToRoadtrip;
    int addedToBlissful;

    int addedToHearthacheWaiting;
    int addedToRoadtripWaiting;
    int addedToBlissfulWaiting;


    public BlendCategoriesAndLists() {

        heartacheCategoryBinaryHeap = new MinHeapForHearthacheCategory();
        roadtripCategoryBinaryHeap = new MinHeapForRoadtripCategory();
        blissfulCategoryBinaryHeap = new MinHeapForBlissfulCategory();

        heartacheCategoryWaitingBinaryHeap = new MaxHeapForHearthacheCategory();
        roadtripCategoryWaitingBinaryHeap = new MaxHeapForRoadtripCategory();
        blissfulCategoryWaitingBinaryHeap = new MaxHeapForBlissfulCategory();



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
            numberOfSongsFromListsToHeartache[song.listId]++;
            heartacheCategoryBinaryHeap.insert(song);
            
        }
        else {
            heartacheCategoryWaitingBinaryHeap.insert(song);
            
        }

    }

    boolean checkHeartacheConstaits(Song song){
        if(song.listId != 0 ){
            if(heartacheCategoryBinaryHeap.size() < maxNumberOfSongsInHeartacheCategory) {
                if (numberOfSongsFromListsToHeartache[song.listId] < maxNumberOfSongsFromListToCategory) {
                    ////System.out.println("ÇIKTI");
                    return true;
                }
            }
        }
        return false;
    }

    void addToRoadtripCheckConstraints(Song song){
        boolean canBeAdded = checkRoadtripConstaits(song);
        if(canBeAdded){
            numberOfSongsFromListsToRoadtrip[song.listId]++;
            roadtripCategoryBinaryHeap.insert(song);

        }
        else {
            roadtripCategoryWaitingBinaryHeap.insert(song);

        }

    }

    boolean checkRoadtripConstaits(Song song){
        if(song.listId != 0 ){
            if(roadtripCategoryBinaryHeap.size() < maxNumberOfSongsInRoadtripCategory) {
                if (numberOfSongsFromListsToRoadtrip[song.listId] < maxNumberOfSongsFromListToCategory) {
                    ////System.out.println("ÇIKTI");
                    return true;
                }
            }
        }
        return false;
    }

    void addToBlissfulCheckConstraints(Song song){
        boolean canBeAdded = checkBlissfulConstaits(song);
        if(canBeAdded){
            numberOfSongsFromListsToBlissful[song.listId]++;
            blissfulCategoryBinaryHeap.insert(song);

        }
        else {
            blissfulCategoryWaitingBinaryHeap.insert(song);

        }

    }

    boolean checkBlissfulConstaits(Song song){
        if(song.listId != 0 ){
            if(blissfulCategoryBinaryHeap.size() < maxNumberOfSongsInBlissfulCategory) {
                if (numberOfSongsFromListsToBlissful[song.listId] < maxNumberOfSongsFromListToCategory) {
                    ////System.out.println("ÇIKTI");
                    return true;
                }
            }
        }
        return false;
    }




//    void addToRoadtripCheckConstraints(Song song){
//        if(song.listId - 1 != 0 ){
//            if(roadtripCategorySortedArrayList.size()<maxNumberOfSongsInRoadtripCategory) {
//                if (numberOfSongsFromListsToRoadtrip[song.listId ] < maxNumberOfSongsFromListToCategory) {
//                    ////System.out.println("Added to roadtrip: " + song);
//                    numberOfSongsFromListsToRoadtrip[song.listId ]++;
//                    roadtripCategorySortedArrayList.add(song);
//                }
//            }
//        }
//    }
//
//    void addToBlissfulCheckConstraints(Song song){
//
//        if(song.listId - 1 != 0 ){
//            if(blissfulCategorySortedArrayList.size()<maxNumberOfSongsInBlissfulCategory) {
//                if (numberOfSongsFromListsToBlissful[song.listId ] < maxNumberOfSongsFromListToCategory) {
//                    ////System.out.println("Added to blissful: " + song);
//                    numberOfSongsFromListsToBlissful[song.listId ]++;
//                    blissfulCategorySortedArrayList.add(song);
//                }
//            }
//        }
//    }



    void addNewSong(Song song){
        //System.out.println("ADD NEW SONG BAŞLADI "+ song );
        if(heartacheCategoryBinaryHeap.size() < maxNumberOfSongsInHeartacheCategory){
            if(numberOfSongsFromListsToHeartache[song.listId ] < maxNumberOfSongsFromListToCategory){
                numberOfSongsFromListsToHeartache[song.listId ]++;
                //System.out.println("addNewSong a");
                heartacheCategoryBinaryHeap.insert(song);
                addedToHearthache=song.songId;
            }

            else if(numberOfSongsFromListsToHeartache[song.listId ] == maxNumberOfSongsFromListToCategory){
                replaceSongsIfTheMinimumSongOfTheListIsSmaller(song);
            }
            else{
                //System.out.println("BİR YERDE HATA VAR");
            }

        }
        else if(heartacheCategoryBinaryHeap.size() == maxNumberOfSongsInHeartacheCategory) {
            if(numberOfSongsFromListsToHeartache[song.listId ] == maxNumberOfSongsFromListToCategory){
                replaceSongsIfTheMinimumSongOfTheListIsSmaller(song);
            }

            else if(numberOfSongsFromListsToHeartache[song.listId ] < maxNumberOfSongsFromListToCategory) {

                boolean replacedByHeap = false;
                MinHeapForHearthacheCategory temp = new MinHeapForHearthacheCategory();

                //direk en tepe root ile replace edip percolate down demeyi de dene
                int heapSize = heartacheCategoryBinaryHeap.size();
                if(heapSize>0) {
                    for (int i = 0; i < heapSize; i++) {
                        Song songToReplace = heartacheCategoryBinaryHeap.pop();

                        if (songToReplace.heartacheScore < song.heartacheScore && !replacedByHeap) {
                            //System.out.println("Replaced to hearthache: BURA " + song);
                            numberOfSongsFromListsToHeartache[song.listId]++;
                            numberOfSongsFromListsToHeartache[songToReplace.listId]--;
                            heartacheCategoryWaitingBinaryHeap.insert(songToReplace);
                            addedToHearthacheWaiting = songToReplace.songId;
                            temp.insert(song);
                            addedToHearthache = song.songId;

                            replacedByHeap = true;
                        } else {
                            temp.insert(songToReplace);
                        }
                    }
                    heartacheCategoryBinaryHeap = temp;
                }

                if(!replacedByHeap){
                    heartacheCategoryWaitingBinaryHeap.insert(song);
                }
            }
            else{
                //System.out.println("BİR YERDE HATA VAR");
            }
        }
        else{
            //System.out.println("BİR YERDE HATA VAR");
        }
    }
    void addNewSongRoadtrip(Song song){
        //System.out.println("ADD NEW SONG BAŞLADI "+ song );
        if(roadtripCategoryBinaryHeap.size() < maxNumberOfSongsInRoadtripCategory){
            if(numberOfSongsFromListsToRoadtrip[song.listId ] < maxNumberOfSongsFromListToCategory){
                numberOfSongsFromListsToRoadtrip[song.listId ]++;
                //System.out.println("addNewSong a");
                roadtripCategoryBinaryHeap.insert(song);
                addedToRoadtrip=song.songId;
            }

            else if(numberOfSongsFromListsToRoadtrip[song.listId ] == maxNumberOfSongsFromListToCategory){
                replaceSongsIfTheMinimumSongOfTheListIsSmallerRoadtrip(song);
            }
            else{
                //System.out.println("BİR YERDE HATA VAR");
            }

        }
        else if(roadtripCategoryBinaryHeap.size() == maxNumberOfSongsInRoadtripCategory) {
            if(numberOfSongsFromListsToRoadtrip[song.listId ] == maxNumberOfSongsFromListToCategory){
                replaceSongsIfTheMinimumSongOfTheListIsSmallerRoadtrip(song);
            }

            else if(numberOfSongsFromListsToRoadtrip[song.listId ] < maxNumberOfSongsFromListToCategory) {

                boolean replacedByHeap = false;
                MinHeapForRoadtripCategory temp = new MinHeapForRoadtripCategory();

                //direk en tepe root ile replace edip percolate down demeyi de dene
                int heapSize = roadtripCategoryBinaryHeap.size();
                if(heapSize>0) {
                    for (int i = 0; i < heapSize; i++) {
                        Song songToReplace = roadtripCategoryBinaryHeap.pop();

                        if (songToReplace.roadtripScore < song.roadtripScore && !replacedByHeap) {
                            //System.out.println("Replaced to Roadtrip: BURA " + song);
                            numberOfSongsFromListsToRoadtrip[song.listId]++;
                            numberOfSongsFromListsToRoadtrip[songToReplace.listId]--;
                            roadtripCategoryWaitingBinaryHeap.insert(songToReplace);
                            addedToRoadtripWaiting = songToReplace.songId;
                            temp.insert(song);
                            addedToRoadtrip = song.songId;

                            replacedByHeap = true;
                        } else {
                            temp.insert(songToReplace);
                        }
                    }
                    roadtripCategoryBinaryHeap = temp;
                }

                if(!replacedByHeap){
                    roadtripCategoryWaitingBinaryHeap.insert(song);
                }
            }
            else{
                //System.out.println("BİR YERDE HATA VAR");
            }
        }
        else{
            //System.out.println("BİR YERDE HATA VAR");
        }
    }
    void addNewSongBlissful(Song song){
        //System.out.println("ADD NEW SONG BAŞLADI "+ song );
        if(blissfulCategoryBinaryHeap.size() < maxNumberOfSongsInBlissfulCategory){
            if(numberOfSongsFromListsToBlissful[song.listId ] < maxNumberOfSongsFromListToCategory){
                numberOfSongsFromListsToBlissful[song.listId ]++;
                //System.out.println("addNewSong a");
                blissfulCategoryBinaryHeap.insert(song);
                addedToBlissful=song.songId;

            }

            else if(numberOfSongsFromListsToBlissful[song.listId ] == maxNumberOfSongsFromListToCategory){
                replaceSongsIfTheMinimumSongOfTheListIsSmallerBlissful(song);
            }
            else{
                //System.out.println("BİR YERDE HATA VAR");
            }

        }
        else if(blissfulCategoryBinaryHeap.size() == maxNumberOfSongsInBlissfulCategory) {
            if(numberOfSongsFromListsToBlissful[song.listId ] == maxNumberOfSongsFromListToCategory){
                replaceSongsIfTheMinimumSongOfTheListIsSmallerBlissful(song);
            }

            else if(numberOfSongsFromListsToBlissful[song.listId ] < maxNumberOfSongsFromListToCategory) {

                boolean replacedByHeap = false;
                MinHeapForBlissfulCategory temp = new MinHeapForBlissfulCategory();

                //direk en tepe root ile replace edip percolate down demeyi de dene
                int heapSize = blissfulCategoryBinaryHeap.size();
                if(heapSize>0) {
                    for (int i = 0; i < heapSize; i++) {
                        Song songToReplace = blissfulCategoryBinaryHeap.pop();

                        if (songToReplace.blissfulScore < song.blissfulScore && !replacedByHeap) {
                            //System.out.println("Replaced to Blissful: BURA " + song);
                            numberOfSongsFromListsToBlissful[song.listId]++;
                            numberOfSongsFromListsToBlissful[songToReplace.listId]--;
                            blissfulCategoryWaitingBinaryHeap.insert(songToReplace);
                            addedToBlissfulWaiting = songToReplace.songId;
                            temp.insert(song);
                            addedToBlissful = song.songId;


                            replacedByHeap = true;
                        } else {
                            temp.insert(songToReplace);
                        }
                    }
                    blissfulCategoryBinaryHeap = temp;
                }

                if(!replacedByHeap){
                    blissfulCategoryWaitingBinaryHeap.insert(song);
                }
            }
            else{
                //System.out.println("BİR YERDE HATA VAR");
            }
        }
        else{
            //System.out.println("BİR YERDE HATA VAR");
        }
    }
    
    
    
    


    void replaceSongsIfTheMinimumSongOfTheListIsSmaller(Song song){

        boolean replacedByHeap = false;
        MinHeapForHearthacheCategory temp = new MinHeapForHearthacheCategory();

        int heapSize = heartacheCategoryBinaryHeap.size();
        if(heapSize>0) {
            for (int i = 0; i < heapSize; i++) {
                Song songInCategory = heartacheCategoryBinaryHeap.pop();

                if (songInCategory.listId == song.listId && !replacedByHeap) {
                    if (songInCategory.heartacheScore < song.heartacheScore) {
                        //System.out.println("Replaced to hearthache: BURaaaA " + song);
                        numberOfSongsFromListsToHeartache[song.listId]++;
                        numberOfSongsFromListsToHeartache[songInCategory.listId]--;

                        heartacheCategoryWaitingBinaryHeap.insert(songInCategory);
                        addedToHearthacheWaiting = songInCategory.songId;
                        temp.insert(song);
                        addedToHearthache = song.songId;

                        replacedByHeap = true;
                    }
                } else {
                    temp.insert(songInCategory);

                }
            }
            heartacheCategoryBinaryHeap = temp;
        }

        if(!replacedByHeap){
            heartacheCategoryWaitingBinaryHeap.insert(song);
        }



    }
    void replaceSongsIfTheMinimumSongOfTheListIsSmallerRoadtrip(Song song){

        boolean replacedByHeap = false;
        MinHeapForRoadtripCategory temp = new MinHeapForRoadtripCategory();

        int heapSize = roadtripCategoryBinaryHeap.size();
        if(heapSize>0) {
            for (int i = 0; i < heapSize; i++) {
                Song songInCategory = roadtripCategoryBinaryHeap.pop();

                if (songInCategory.listId == song.listId && !replacedByHeap) {
                    if (songInCategory.roadtripScore < song.roadtripScore) {
                        //System.out.println("Replaced to Roadtrip: BURaaaA " + song);
                        numberOfSongsFromListsToRoadtrip[song.listId]++;
                        numberOfSongsFromListsToRoadtrip[songInCategory.listId]--;

                        roadtripCategoryWaitingBinaryHeap.insert(songInCategory);
                        addedToRoadtripWaiting = songInCategory.songId;
                        temp.insert(song);
                        addedToRoadtrip = song.songId;

                        replacedByHeap = true;
                    }
                    else {
                        temp.insert(songInCategory);
                    }
                }
                else {
                    temp.insert(songInCategory);
                }
            }
            roadtripCategoryBinaryHeap = temp;
        }

        if(!replacedByHeap){
            roadtripCategoryWaitingBinaryHeap.insert(song);
        }
    }

    void replaceSongsIfTheMinimumSongOfTheListIsSmallerBlissful(Song song){

        boolean replacedByHeap = false;
        MinHeapForBlissfulCategory temp = new MinHeapForBlissfulCategory();

        int heapSize = blissfulCategoryBinaryHeap.size();

        //if(heapSize>0) {
            for (int i = 0; i < heapSize; i++) {
                Song songInCategory = blissfulCategoryBinaryHeap.pop();


                if (songInCategory.listId == song.listId && !replacedByHeap) {
                    if (songInCategory.blissfulScore < song.blissfulScore) {
                        //System.out.println("Replaced to Blissful: BURaaaA " + song);
                        numberOfSongsFromListsToBlissful[song.listId]++;
                        numberOfSongsFromListsToBlissful[songInCategory.listId]--;
                        blissfulCategoryWaitingBinaryHeap.insert(songInCategory);
                        addedToBlissfulWaiting = songInCategory.songId;
                        temp.insert(song);
                        addedToBlissful = song.songId;
                        replacedByHeap = true;
                    }
                    else {
                        temp.insert(songInCategory);
                    }
                } else {
                    temp.insert(songInCategory);

                }
            }
            blissfulCategoryBinaryHeap = temp;
        //}

        if(!replacedByHeap){
            blissfulCategoryWaitingBinaryHeap.insert(song);
            //System.out.println("Nooo");
            //blissfulCategoryBinaryHeap.print();
        }
    }







    void removeSong(Song song, int listId){
        song.listId = 0;

        MinHeapForHearthacheCategory temp1 = new MinHeapForHearthacheCategory();
        MinHeapForRoadtripCategory temp2 = new MinHeapForRoadtripCategory();
        MinHeapForBlissfulCategory temp3 = new MinHeapForBlissfulCategory();

        int heapSize1 = heartacheCategoryBinaryHeap.size();
        int heapSize2 = roadtripCategoryBinaryHeap.size();
        int heapSize3 = blissfulCategoryBinaryHeap.size();

        for (int i = 0; i < heapSize1; i++) {
            Song song1 = heartacheCategoryBinaryHeap.pop();
            if(song1!=song){
                temp1.insert(song1);
            }
            else {
                addedToHearthacheWaiting = song.songId;
                if(heartacheCategoryWaitingBinaryHeap.size()>0) {
                    addedToHearthache = heartacheCategoryWaitingBinaryHeap.pop().songId;
                }
            }
        }
        heartacheCategoryBinaryHeap=temp1;


        for (int i = 0; i < heapSize2; i++) {
            Song song1 = roadtripCategoryBinaryHeap.pop();
            if(song1!=song) {
                temp2.insert(song1);
            }
            else {
                addedToRoadtripWaiting = song.songId;

                if(roadtripCategoryWaitingBinaryHeap.size()>0) {
                    addedToRoadtrip = roadtripCategoryWaitingBinaryHeap.pop().songId;
                }
            }
        }
        roadtripCategoryBinaryHeap=temp2;


        for (int i = 0; i < heapSize3; i++) {
            Song song1 = blissfulCategoryBinaryHeap.pop();
            if(song1!=song) {
                temp3.insert(song1);
            }
            else {
                addedToBlissfulWaiting = song.songId;
                if(blissfulCategoryWaitingBinaryHeap.size()>0) {
                    addedToBlissful = blissfulCategoryWaitingBinaryHeap.pop().songId;
                }
            }
        }
        blissfulCategoryBinaryHeap=temp3;


    }

    void askCommand(){
        MaxHeapForPlayCount heap = new MaxHeapForPlayCount();

        MinHeapForHearthacheCategory temp1 = new MinHeapForHearthacheCategory();
        MinHeapForRoadtripCategory temp2 = new MinHeapForRoadtripCategory();
        MinHeapForBlissfulCategory temp3 = new MinHeapForBlissfulCategory();

        int heapSize1 = heartacheCategoryBinaryHeap.size();
        int heapSize2 = roadtripCategoryBinaryHeap.size();
        int heapSize3 = blissfulCategoryBinaryHeap.size();

        for (int i = 0; i < heapSize1; i++) {
            Song song = heartacheCategoryBinaryHeap.pop();
            heap.insert(song);
            temp1.insert(song);
        }
        heartacheCategoryBinaryHeap=temp1;

        for (int i = 0; i < heapSize2; i++) {
            Song song = roadtripCategoryBinaryHeap.pop();
            heap.insert(song);
            temp2.insert(song);
        }
        roadtripCategoryBinaryHeap=temp2;

        for (int i = 0; i < heapSize3; i++) {
            Song song = blissfulCategoryBinaryHeap.pop();
            heap.insert(song);
            temp3.insert(song);
        }
        blissfulCategoryBinaryHeap=temp3;

        int heapSize4 = heap.size();
        for (int i = 0; i < heapSize4; i++) {
            Song song = heap.pop();
            System.out.print(song.playCount + " ");
        }



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
//        ////System.out.println("Added to hearthache: " + song);
//        numberOfSongsFromListsToHeartache[song.listId - 1]++;
//        heartacheCategorySortedArrayList.add(song);
//        ////System.out.println(heartacheCategorySortedArrayList);
//        }
//        else {
//        int lastIndex = heartacheCategorySortedArrayList.size() - 1;
//        Song minimumSongInCategory = heartacheCategorySortedArrayList.get(lastIndex);
//
//        if (minimumSongInCategory.heartacheScore < song.heartacheScore) {
//        ////System.out.println("Added to hearthache: " + song);
//        numberOfSongsFromListsToHeartache[song.listId - 1]++;
//        heartacheCategorySortedArrayList.add(song);
//        ////System.out.println(heartacheCategorySortedArrayList);
//
//
//        heartacheCategorySortedArrayList.set(lastIndex, song);
//        sortThisSingleSongInCategoryArrayList(lastIndex, song);
//        ////System.out.println("Replaced to hearthache: BURA " + song);
//        }
//        }
//        }