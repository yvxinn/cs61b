public class LinkedListDeque<Type> {
    private node sentinel;
    private int size;
    public class node{
        public Type item;
        public node pre;
        public node next;
        public node(Type x,node p,node n){
            this.item=x;
            this.pre=p;
            this.next=n;
        }
    }
    public LinkedListDeque(){
        Type temp = null;
        sentinel=new node(null,sentinel,sentinel);
        size=0;
    }
    public LinkedListDeque(Type item){
        Type temp = null;
        sentinel=new node(null,sentinel,sentinel);
        sentinel.next=new node(item,sentinel,sentinel);
        sentinel.pre=sentinel.next;
        size=1;
    }
    public void addFirst(Type item){
        if(size==0){
            sentinel.next=new node(item,sentinel,sentinel);
            sentinel.pre=sentinel.next;
        }
        else{
            sentinel.next=new node(item,sentinel,sentinel.next);
            sentinel.next.next.pre=sentinel.next;
        }
        size++;
    }
    public void addLast(Type item){
        if(size==0){
            sentinel.next=new node(item,sentinel,sentinel);
            sentinel.pre=sentinel.next;
        }
        else{
            sentinel.pre=new node(item,sentinel.pre,sentinel);
            sentinel.pre.pre.next=sentinel.pre;
        }
        size++;
    }
    public boolean isEmpty(){
        if(size==0)
            return true;
        return false;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        node p=sentinel.next;
        while(p!=sentinel){
            System.out.print(p.item+" ");
            p=p.next;
        }
    }
    public Type removeFirst(){
        Type target=sentinel.next.item;
        sentinel.next.next.pre=sentinel;
        sentinel.next=sentinel.next.next;
        size--;
        return target;
    }
    public Type removeLast(){
        Type target=sentinel.pre.item;
        sentinel.pre.pre.next=sentinel;
        sentinel.pre=sentinel.pre.pre;
        size--;
        return target;
    }
    public Type get(int index){
        if (index>size)
            return null;
        else if(index<=size-index){
            node p=sentinel.next;
            for(int i=0;i<index;i++){
                p=p.next;
            }
            return p.item;
        }else{
            node p=sentinel.pre;
            for(int i=0;i<size-index-1;i++){
                p=p.pre;
            }
            return p.item;
        }
    }
    private Type getRecursive(int index,node p){
        if(index==0){
            return p.item;
        }else{
            p=p.next;
            index--;
            return getRecursive(index, p);
        }
    }
    public Type getRecursive(int index){
        if(index>size){
            return null;
        }
        return getRecursive(index,sentinel.next);
    }
    public static void main(String[] args){
        LinkedListDeque<Double> L=new LinkedListDeque<>();
        L.addFirst(0.0);
        L.addFirst(1.0);
        L.addFirst(2.0);
        L.addLast(-1.0);
        L.addLast(-2.0);
        System.out.println(L.get(6));
        System.out.println(L.getRecursive(6));
        System.out.println(L.get(3));
        System.out.println(L.getRecursive(3));

    }
}
