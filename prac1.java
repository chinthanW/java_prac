public class prac1 {

    public static void main(String[] args) {
        //Valid if the string has exactly 4 or 6 digits (0-9 only).

        String s = "owjgn038u021";
        int count =0;
        for(char c : s.toCharArray()){
            if(Character.isDigit(c)){
                count++;
            }
        }

        if(count==4 || count==6){
            System.out.println("Yes");
        }
        else{
            System.out.println("No");
        }

    }
}