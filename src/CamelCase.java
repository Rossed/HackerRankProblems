import java.util.ArrayList;

/**
 * Created by Edwards on 24/12/2016.
 */
public class CamelCase {
    public static void main(String[] args) {
        String s = "save";

        ArrayList<String> seperatedWords = new ArrayList<String>();
        String word = "";
        boolean moreThanOneWord = false;
        //For all the letters in the String
        for (int i=0; i<s.length(); i++) {
            int ascii = (int) s.charAt(i);

            //IF its a capital letter
            if (ascii>=64 && ascii<=90) {
                moreThanOneWord=true;
                seperatedWords.add(word);
                word = "" + s.charAt(i);
            //ELSE its a lower case letter
            } else {
                word = word + s.charAt(i);
            }
        }
        seperatedWords.add(word);
        System.out.println(seperatedWords.size());
    }
}
