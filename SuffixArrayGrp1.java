import java.io.*;

public class SuffixArrayGrp1{
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
    public static boolean isInputValid(String input){
        for(int i = 0; i < input.length(); i++){
            
            if(input.charAt(i) != 'a' &&  input.charAt(i) != 'c' && input.charAt(i) != 'g' && input.charAt(i) != 't' ) {
                System.out.println("Invalid Input!");
                return false;
            }
        }
        return true;
    }

    public static int[] buildSuffixArray(String input, int len){
        Suffix[] suffix = new Suffix[len];
    
    for (int i = 0; i < len; i++){
        suffix[i] = new Suffix(i);
    }
 
    sort(suffix, suffix.length, input);

    int[] suffixArr = new int[len];
    for (int i = 0; i < len; i++){
        suffixArr[i] = suffix[i].orgIndex;
    }
    
    return suffixArr;
}

    public static void sort(Suffix[] arr, int arrlength, String input){
        int temp;

        for(int j = 0; j < arrlength - 1; j++){
            for(int i = j + 1; i < arrlength; i++){
                if (arr[i].compareSuffix(input,arr[i].orgIndex,arr[j].orgIndex) <= 0){ 
                    temp = arr[j].orgIndex;
                    arr[j].orgIndex = arr[i].orgIndex;
                    arr[i].orgIndex = temp;
                }
            }
        }
    }
    
}