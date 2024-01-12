/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        int max_length=0;
        String[] sorted = asciis.clone();
        for(String s:asciis){
            max_length= Math.max(s.length(), max_length);
        }
        for(int d=0;d<max_length;d++){
            sorted=sortHelperLSD(sorted,d,max_length);
        }
        return sorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static String[] sortHelperLSD(String[] asciis, int index,int max_length) {
        // Optional LSD helper method for required LSD radix sort
        int[] counts =new int[256];
        int[] starts =new int[256];
        int end=max_length-index-1;
        for(String s:asciis){
            if(s.length()>=max_length-index){
                counts[s.charAt(end)]++;
            }else {
                counts[0]++;
            }
        }
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }
        String[] sorted = new String[asciis.length];
        for (int i = 0; i < asciis.length; i += 1) {
            String item = asciis[i];
            if(item.length()>=max_length-index){
                int place = starts[item.charAt(end)];
                sorted[place] = item;
                starts[item.charAt(end)]++;
            }else{
                int place = starts[0];
                sorted[place] = item;
                starts[0]++;
            }

        }
        return sorted;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
    public static void main(String[] args){
        String[] origin={"buffer","aoc","b","dog","internet","moon","dream","dad"};
        String[] sorted=RadixSort.sort(origin);
        for(String s:sorted){
            System.out.println(s);
        }
    }
}
