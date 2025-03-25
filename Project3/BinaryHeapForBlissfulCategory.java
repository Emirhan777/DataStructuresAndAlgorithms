import java.util.ArrayList;

public class BinaryHeapForBlissfulCategory {
    private ArrayList<Song> array;
    private int size;


    BinaryHeapForBlissfulCategory(){
        size = 0;
        array = new ArrayList<Song>();
        array.add(null);
    }

    BinaryHeapForBlissfulCategory(Song[] items){
        array = new ArrayList<Song>();
        array.add(null);
        for (Song item : items) {
            array.add(item);
            size++;
        }
        buildHeap();
    }

    public void insert(Song value) {
        size++;
        array.add(value);
        int hole = size;
        while(hole > 1 && value.heartacheScore > array.get(hole/2).heartacheScore) {
            Song parent = array.get(hole/2);
            array.set(hole / 2, value);
            array.set(hole, parent);
            hole = hole / 2;
        }
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

    private void buildHeap(){
        for(int i = size / 2; i > 0; i--) {
            percolateDown(i);
        }
    }

    public int size() {
        return size;
    }

    private void percolateDown(int hole) {
        int child;
        Song temp = array.get(hole);

        while(hole * 2 <= size) {
            child = hole * 2;
            if(child != size && array.get(child + 1).heartacheScore > array.get(child).heartacheScore) {
                child++;
            }
            if(array.get(child).heartacheScore > temp.heartacheScore) {
                array.set(hole, array.get(child));
            }else {
                break;
            }

            hole = child;
        }
        array.set(hole, temp);
    }


}
