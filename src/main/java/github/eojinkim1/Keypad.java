package github.eojinkim1;

import java.util.HashMap;
import java.util.Map;

public class Keypad {

    public static void main(String[] args) {
        Keypad keypad = new Keypad();
        int[] number1 = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String hand = "right";
        System.out.println(keypad.solution(number1, hand));

        int[] number2 = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
        String hand2 = "left";
        System.out.println(keypad.solution(number2, hand2));

        int[] number3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        String hand3 = "right";
        System.out.println(keypad.solution(number3, hand3));

    }

    public String solution(int[] numbers, String hand) {
        Map<Integer, int[]> keypad = new HashMap<>();

        keypad.put(1, new int[]{0, 0});
        keypad.put(2, new int[]{0, 1});
        keypad.put(3, new int[]{0, 2});
        keypad.put(4, new int[]{1, 0});
        keypad.put(5, new int[]{1, 1});
        keypad.put(6, new int[]{1, 2});
        keypad.put(7, new int[]{2, 0});
        keypad.put(8, new int[]{2, 1});
        keypad.put(9, new int[]{2, 2});
        keypad.put(0, new int[]{3, 1});
        keypad.put(-1, new int[]{3, 0}); // * 키
        keypad.put(-2, new int[]{3, 2}); // # 키

        int[] left = keypad.get(-1);
        int[] right = keypad.get(-2);

        StringBuilder answer = new StringBuilder();

        for (int number : numbers) {
            if (number == 1 || number == 4 || number == 7) {
                answer.append('L');
                left = keypad.get(number);
            } else if (number == 3 || number == 6 || number == 9) {
                answer.append('R');
                right = keypad.get(number);
            } else {
                int[] targetPos = keypad.get(number);
                int leftPos = distance(left, targetPos);
                int rightPos = distance(right, targetPos);

                if (leftPos < rightPos || leftPos == rightPos && hand.equals("left")) {
                    answer.append('L');
                    left = targetPos;
                } else {
                    answer.append('R');
                    right = targetPos;
                }
            }
        }
        return answer.toString();
    }

    private int distance(int[] position1, int[] position2) {
        return Math.abs(position1[0] - position2[0]) + Math.abs(position1[1] - position2[1]);
    }
}
