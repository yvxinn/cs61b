import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    ArrayDequeSolution<Integer> solution=new ArrayDequeSolution<>();
    StudentArrayDeque<Integer> student=new StudentArrayDeque<>();
    String log="\n";
    @Test
    public void testStudentDeque(){
        for(int i=0;i<1000;i++){
            int number=StdRandom.uniform(1000);
            Integer solu=0;
            Integer stud=0;
            if(solution.isEmpty()){
                int isHead=StdRandom.uniform(2);
                if(isHead==1){
                    solution.addFirst(number);
                    student.addFirst(number);
                    log+=("addFirst("+number+")\n");
                }else{
                    solution.addLast(number);
                    student.addLast(number);
                    log+=("addLast("+number+")\n");
                }
            }else{
                int option=StdRandom.uniform(4);
                switch (option){
                    case 0:
                        student.addFirst(number);
                        solution.addFirst(number);
                        log+=("addFirst("+number+")\n");
                        break;
                    case 1:
                        solution.addLast(number);
                        student.addLast(number);
                        log+=("addLast("+number+")\n");
                        break;
                    case 2:
                        solu=solution.removeFirst();
                        stud=student.removeFirst();
                        log+=("removeFirst()\n");
                        break;
                    case 3:
                        solu=solution.removeLast();
                        stud=student.removeLast();
                        log+=("removeLast()\n");
                        break;
                    default:
                }
                assertEquals(log,solu,stud);
            }
        }
    }
}
