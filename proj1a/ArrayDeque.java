public class ArrayDeque<T> {
    T[] items;
    int size;
    int nextFirst;
    int nextLast;
    public ArrayDeque(){
        items= (T[]) new Object[8];
        size=0;
        nextFirst=4;
        nextLast=5;
    }
    public void addFirst(T item){
        if(size==items.length){
            enlarge();
        }
        if(nextFirst>0) {
            items[nextFirst] = item;
            nextFirst--;
            size++;
        }else{
            nextFirst=items.length-1;
            items[nextFirst] = item;
            nextFirst--;
            size++;
        }
    }
    public void addLast(T item){
        if(size==items.length){
            enlarge();
        }
        if(nextLast< items.length) {
            items[nextLast] = item;
            nextLast++;
            size++;
        }else{
            nextLast=0;
            items[nextLast] = item;
            nextLast++;
            size++;
        }
    }
    public T removeFirst(){
        if(size==0)
            return null;
        T target = null;
        if(nextFirst+1<=items.length-1) {
            target = items[nextFirst + 1];
            items[nextFirst + 1] = null;
            nextFirst++;
        }else{
            target=items[0];
            items[0]=null;
            nextFirst=0;
        }
        size--;
        if(items.length>=16&&((double) size / items.length<0.25)){
            reduce();
        }
        return target;
    }
    public T removeLast(){
        if(size==0)
            return null;
        T target = null;
        if(nextLast-1>=0) {
            target = items[nextLast - 1];
            items[nextLast - 1] = null;
            nextLast--;
        }else{
            target=items[items.length-1];
            items[items.length-1]=null;
            nextLast=items.length-1;
        }
        size--;
        if(items.length>=16&&((double) size / items.length<0.25)){
            reduce();
        }
        return target;
    }
    private void enlarge(){
        T[] newitems=(T[]) new Object[size*2];
        System.arraycopy(items,0,newitems,
                newitems.length/4,items.length);
        items=newitems;
        nextLast=items.length/4+size;
        nextFirst=items.length/4-1;
    }
    private void reduce(){
        T[] newitems=(T[]) new Object[items.length/2];
        int newindex=0;
        for(int i=nextFirst+1;i!=nextLast;i++){
            if(i>items.length-1){
                i-=items.length;
            }
            newitems[newindex++]=items[i];
        }
        items=newitems;
        nextLast=size;
        nextFirst=items.length-1;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        for(int i=nextFirst+1;i!=nextLast;i++){
            if(i>items.length-1){
                i-=items.length;
            }
            System.out.print(items[i]+" ");
        }
    }
    public T get(int index){
        int realindex=(index+nextFirst+1)% items.length;
        return items[realindex];
    }
    public static void main(String[] args){
        ArrayDeque<Character> L=new ArrayDeque<>();
        L.addLast('a');
        L.addLast('b');
        L.addFirst('c');
        L.addLast('d');
        L.addLast('e');
        L.addFirst('f');
        L.addLast('g');
        L.printDeque();
        L.removeFirst();
        L.removeLast();
        L.addLast('h');
        L.addLast('Z');
        L.addFirst('X');
        L.addLast('Z');
        L.addLast('Z');
        L.addLast('Z');
        L.addLast('Z');
        L.addLast('Z');
        L.addLast('Z');
        L.addLast('Y');
        L.printDeque();
        System.out.println(L.get(2));
    }
}
