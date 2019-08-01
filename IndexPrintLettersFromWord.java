import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class IndexPrintLettersFromWord {

    private static final String EMPTY_STRING = " ";

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String entryText = scan.nextLine().replaceAll("[^A-Za-z]", EMPTY_STRING).toLowerCase();

        Map<Character, TreeSet<String>> wordsAndLettersMap = new TreeMap<>();
        Set<Character> uniqueLetterSet = entryText.chars().mapToObj(chr -> (char) chr)
                .filter(notSpace()).collect(Collectors.toCollection(HashSet::new));
        Set<String> uniqueWordSet = new HashSet<>(Arrays.asList(entryText.split(EMPTY_STRING)));

        for (Character uniqueLetter : uniqueLetterSet) {
            TreeSet<String> wordsContainingSpecificLetter = new TreeSet<>(getWordsContainingSpecificLetter(uniqueWordSet, uniqueLetter));
            wordsAndLettersMap.put(uniqueLetter, wordsContainingSpecificLetter);
        }
        printResults(wordsAndLettersMap);
    }

    private static Predicate<Character> notSpace() {
        return character -> character != ' ';
    }

    private static Set<String> getWordsContainingSpecificLetter(Set<String> words, Character letter) {
        return words.stream().filter(word -> isWordContainsCharacter(word, letter)).collect(Collectors.toSet());
    }

    private static boolean isWordContainsCharacter(String word, Character letter) {
        return word.contains(letter.toString());
    }

    private static void printResults(Map<Character, TreeSet<String>> mapCharacterAndTreeSet) {
        mapCharacterAndTreeSet.forEach((key, value) -> {
            System.out.printf("\n %s:", key);
            value.forEach(setValue -> System.out.printf(" %s", setValue));
        });
    }
}