import java.util.ArrayList;

public class MinHeapForBlissfulCategory {
    private ArrayList<Song> array;
    private int size;


    MinHeapForBlissfulCategory(){
        size = 0;
        array = new ArrayList<Song>();
        array.add(null);
    }

//    MinHeapForHearthacheCategory(Song[] items){
//        array = new ArrayList<Song>();
//        array.add(null);
//        for (Song item : items) {
//            array.add(item);
//            size++;
//        }
//        buildHeap();
//    }

    public void insert(Song song) {
        size++;
        array.add(song);
        int hole = size;
        while(hole > 1 && song.blissfulScore < array.get(hole/2).blissfulScore) {
            Song parent = array.get(hole/2);
            array.set(hole / 2, song);
            array.set(hole, parent);
            hole = hole / 2;
        }
        //System.out.println();
        //System.out.println();
        //System.out.println("Added to hearthache: " + song);
        //print();
    }

    public Song peek() {
        return array.get(1);
    }

    public Song pop() {
        Song minItem = peek();
        array.set(1, array.get(size));
        size--;
        percolateDown(1);

        return minItem;
    }

//    private void buildHeap(){
//        for(int i = size / 2; i > 0; i--) {
//            percolateDown(i);
//        }
//    }

    public int size() {
        return size;
    }

    private void percolateDown(int hole) {
        int child;
        Song temp = array.get(hole);

        while(hole * 2 <= size) {
            child = hole * 2;
            if(child != size && array.get(child + 1).blissfulScore < array.get(child).blissfulScore) {
                child++;
            }
            if(array.get(child).blissfulScore < temp.blissfulScore) {
                array.set(hole, array.get(child));
            }else {
                break;
            }

            hole = child;
        }
        array.set(hole, temp);
    }

    void print(){

        System.out.println(size);
        for (int i = 1; i < size + 1 ; i++) {
            float value= (float)(Math.log(i)/Math.log(2));
            if(i==1 || value == (int) value ){
                System.out.println();
            }

            System.out.print(array.get(i) + "  ");
        }
        System.out.println();

    }

}
