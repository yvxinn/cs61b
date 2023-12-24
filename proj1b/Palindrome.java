public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> target=new SLList<>() ;
        int length=word.length();
        for(int i=0;i<length;i++)
        {
            target.addLast(word.charAt(i));
        }
        return target;
    }
    private boolean helper(Deque<Character> next){
        if(next.size()<=1){
            return true;
        } else if (next.removeFirst()==next.removeLast()) {
            return helper(next);
        }else{
            return false;
        }
    }
    public boolean isPalindrome(String word){
        Deque<Character> target=wordToDeque(word);
        return helper(target);
    }
    private boolean helper1(Deque<Character> next,CharacterComparator cc){
        if(next.size()<=1){
            return true;
        } else if (cc.equalChars(next.removeFirst(),next.removeLast())) {
            return helper1(next,cc);
        }else{
            return false;
        }
    }
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> target=wordToDeque(word);
        return helper1(target,cc);
    }
}
