import java.util.*;

public class TowerOfHanoi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tower Of Hanoi");
        System.out.print("Recursive / Iterative? (r/i): ");

        String input = scanner.nextLine();
        int disks;

        switch (input){
            case "r":
                System.out.print("Number of disks: ");
                disks = scanner.nextInt();
                recursionHanoi(disks);
                break;
            case "i":
                System.out.print("Number of disks: ");
                disks = scanner.nextInt();
                iterativeHanoi(disks);
                break;
            default:
                System.out.println("Please choose either (r/i) only!");
                break;
        }
    }

    // Recursion - Tower Of Hanoi - Main
    public static void recursionHanoi(int disks){
        ArrayList<Character>[] towers = new ArrayList[3];
        for (int i = 0; i < 3; i++) {
            towers[i] = new ArrayList<>();
        }

        for (int i = disks; i > 0; i--) {
            towers[0].add((char)('A' + i - 1));
        }

        movesRecursion(disks, 'A', 'C', 'B', towers, disks);
    }

    // Recursion - Tower Of Hanoi - Moving the stacks
    public static void movesRecursion(int disksNum, char initialTower, char finalTower, char extraTower, ArrayList<Character>[] towers, int maxHeight){
        if (disksNum == 1){
            char disk = towers[initialTower- 'A'].removeLast();
            towers[finalTower - 'A'].add(disk);
            printRecursion(towers, maxHeight);
            return;
        }

        movesRecursion(disksNum - 1, initialTower, extraTower, finalTower, towers, maxHeight);

        char disk = towers[initialTower- 'A'].removeLast();
        towers[finalTower - 'A'].add(disk);
        printRecursion(towers, maxHeight);

        movesRecursion(disksNum - 1, extraTower, finalTower, initialTower, towers, maxHeight);
    }

    // Recursion - Tower Of Hanoi - Drawing the towers
    public static void printRecursion(ArrayList<Character>[] towers, int maxHeight){
        for (int level = maxHeight; level > 0; level--) {
            for (int tower = 0; tower < 3; tower++) {
                if (towers[tower].size() >= level) {
                    System.out.print(towers[tower].get(level - 1) + "\t");
                } else {
                    System.out.print("|\t");
                }
            }
            System.out.println();
        }
        System.out.println("----------");
    }

    // Iterative - Tower Of Hanoi - Main
    public static void iterativeHanoi(int disks){
        Stack<Character> initialTower = new Stack<>();
        Stack<Character> finalTower = new Stack<>();
        Stack<Character> extraTower = new Stack<>();

        for (int i = disks; i > 0; i--) {
            initialTower.push((char)('A' + i - 1));
        }

        printIterative(initialTower, finalTower, extraTower);

        int totalMoves = (int) Math.pow(2, disks) - 1;

        for (int i = 1; i <= totalMoves; i++) {
            if (i % 3 == 1) {
                movesIterative(initialTower, finalTower);
            } else if (i % 3 == 2) {
                movesIterative(initialTower, extraTower);
            } else if (i % 3 == 0) {
                movesIterative(extraTower, finalTower);
            }

            printIterative(initialTower, finalTower, extraTower);
        }
    }

    // Iterative - Tower Of Hanoi - Moving the stacks
    public static void movesIterative(Stack<Character> initialTower, Stack<Character> finalTower){
        if (!initialTower.isEmpty() && (finalTower.isEmpty() || initialTower.peek() < finalTower.peek())) {
            finalTower.push(initialTower.pop());
        } else {
            initialTower.push(finalTower.pop());
        }
    }

    // Iterative - Tower Of Hanoi - Drawing the towers
    public static void printIterative(Stack<Character> initialTower, Stack<Character> finalTower, Stack<Character> extraTower) {
        for (int i = 3 - 1; i >= 0; i--) {
            String firstTower = (initialTower.size() > i) ? String.valueOf(initialTower.get(i)) : "|";
            String secondTower = (extraTower.size() > i) ? String.valueOf(extraTower.get(i)) : "|";
            String thirdTower = (finalTower.size() > i) ? String.valueOf(finalTower.get(i)) : "|";

            System.out.println(firstTower + "\t" + secondTower + "\t" + thirdTower);
        }

        System.out.println("----------");
    }
}