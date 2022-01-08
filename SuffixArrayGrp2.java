import java.io.*;

public class SuffixArrayGrp2 {
    public static void main(String[] args){
        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
            String input;
            do{
                System.out.print("Enter String[a,c,g,t]: ");
                input = bReader.readLine();
            } while(!isInputValid(input));

            int[] suffixArr = buildSuffixArray(input, input.length());

            System.out.print("Suffix Array: ");
            for(int i = 0; i < input.length(); i++){
               System.out.print("["+suffixArr[i]+"]");
            }
        } catch (Exception e) {
            e.printStackTrace();    
        }
    }

    public static boolean isInputValid(String input){ // Utility function: O(n)
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) != 'a' &&  input.charAt(i) != 'c' && input.charAt(i) != 'g' && input.charAt(i) != 't' ) {
                System.out.println("Invalid Input!");
                return false;
            }
        }
        return true;
    }

    public static int[] buildSuffixArray(String input, int len){ // O(n)
    Suffix[] suffix = new Suffix[len];
    
    for (int i = 0; i < len; i++){
        suffix[i] = new Suffix(i);
    }
 
    sort(suffix, 0, suffix.length - 1, input);

    int[] suffixArr = new int[len];
    for (int i = 0; i < len; i++){
        suffixArr[i] = suffix[i].orgIndex;
    }
    
    return suffixArr;
}
    
    public static void sort(Suffix[] input, int left, int right, String suffixString){ // O(logn)
        if (left < right) {
            int midpoint =  left + (right-left)/2;
            sort(input, left, midpoint, suffixString);
            sort(input, midpoint + 1, right, suffixString);
            merge(input, left, midpoint, right, suffixString); 
        }
    }

    public static void merge(Suffix[] input, int left, int midpoint, int right, String suffixString){ // O(n)
        int arr1Size = midpoint - left + 1;
        int arr2Size = right - midpoint;

        int[] tempL = new int[arr1Size];
        int[] tempR = new int[arr2Size];

        /*Copy data to temp arrays */
        for (int i = 0; i < arr1Size; ++i){
            tempL[i] = input[left + i].orgIndex;
        }
        for (int j = 0; j < arr2Size; ++j){
            tempR[j] = input[midpoint + 1 + j].orgIndex;
        }
  
        // Merge the temp arrays
        int i = 0; 
        int j = 0;

        // Initial index of merged subarray array
        int k = left;
        while (i < arr1Size && j < arr2Size) {
            if (input[i].compareSuffix(suffixString,tempL[i],tempR[j]) <= 0) {
                input[k].orgIndex = tempL[i];
                i++;
            }
            else {
                input[k].orgIndex = tempR[j];
                j++;
            }
            k++;
        }

       while (i < arr1Size) {
            input[k].orgIndex = tempL[i];
            i++;
            k++;
        }

       while (j < arr2Size) {
            input[k].orgIndex = tempR[j];
            j++;
            k++;
        }
    }
}
