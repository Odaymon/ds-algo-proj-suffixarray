public class Suffix {
    int orgIndex;
    //String suffixString;

    public Suffix(int orgIndex) {
        this.orgIndex = orgIndex;
        //this.suffixString = suffixString;
    }

    public int compareSuffix(String input, int index1, int index2){
        int len = input.length();
        System.out.println(input.substring(index1,len)+" compare to "+input.substring(index2,len));
        return input.substring(index1,len).compareToIgnoreCase(input.substring(index2,len));
    }
}