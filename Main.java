import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static void main() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        StringBuffer alltext = new StringBuffer();

        System.out.println("Введите текст");

        try {
            while (true) {
                String line = br.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                }

                alltext.append(line).append(" ");
            }
        } catch (IOException e) {
            System.out.println("Ошибка ");
        } finally {
            try {
                br.close();
                isr.close();
            } catch (IOException e) {
                System.out.println("Ошибка");
            }
        }

        String text = alltext.toString();
        String delimiters = " \t\n\r,.:;-?!()\"'";
        StringTokenizer Token = new StringTokenizer(text, delimiters);
        int Count = Token.countTokens();

        String[] words = new String[Count];
        int wordsSize = 0;

        while (Token.hasMoreTokens()) {
            String temp = Token.nextToken().toLowerCase();

            if (temp.length() > 1) {
                boolean saved = false;
                for (int i = 0; i < wordsSize; i++) {
                    if (words[i].equals(temp)) {
                        saved = true;
                        break;
                    }
                }
                if (!saved) {
                    words[wordsSize] = temp;
                    wordsSize++;
                }
            }
        }

        System.out.println("\nОбращения:");
        boolean found = false;

        for (int i = 0; i < wordsSize; i++) {
            String word1 = words[i];

            StringBuffer sb = new StringBuffer(word1);
            sb.reverse();
            String reversedWord1 = sb.toString();

            for (int j = i + 1; j < wordsSize; j++) {
                String word2 = words[j];

                if (word2.equals(reversedWord1)) {
                    System.out.println( word1 + " <-> " + word2 );
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("Пары слов не найдены.");
        }
    }
}
