public interface Deque<T> {
    public void addFirst(T x);
    public void addLast(T y);
    public T removeFirst();
    public T removeLast();
    public T get(int i);
    public int size();
    public boolean isEmpty();
    public default void printDeque(){
        for (int i = 0; i < size(); i += 1) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }
    public static void main(String[] args){
        Deque<Integer> L=new SLList<>();
        L.addFirst(0);
        L.addFirst(1);

    }
}
