public class SLList<Type> implements Deque<Type> {
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
    public SLList(){
        Type temp = null;
        sentinel=new node(null,sentinel,sentinel);
        size=0;
    }
    public SLList(Type item){
        Type temp = null;
        sentinel=new node(null,sentinel,sentinel);
        sentinel.next=new node(item,sentinel,sentinel);
        sentinel.pre=sentinel.next;
        size=1;
    }
    @Override
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
    @Override
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
    @Override
    public boolean isEmpty(){
        if(size==0)
            return true;
        return false;
    }
    @Override
    public int size(){
        return size;
    }
    @Override
    public void printDeque(){
        node p=sentinel.next;
        while(p!=sentinel){
            System.out.print(p.item+" ");
            p=p.next;
        }
    }
    @Override
    public Type removeFirst(){
        if(isEmpty()) {
            return null;
        }
        Type target=sentinel.next.item;
        sentinel.next.next.pre=sentinel;
        sentinel.next=sentinel.next.next;
        size--;
        return target;
    }
    @Override
    public Type removeLast(){
        if(isEmpty()) {
            return null;
        }
        Type target=sentinel.pre.item;
        sentinel.pre.pre.next=sentinel;
        sentinel.pre=sentinel.pre.pre;
        size--;
        return target;
    }
    @Override
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
}
