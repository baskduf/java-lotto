package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.Comparator;
import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
        this.numbers.sort(Comparator.naturalOrder());
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    // TODO: 추가 기능 구현
    public void showNumbers(){
        StringBuilder str = new StringBuilder();
        str.append("[");
        for(int element : numbers){
            str.append(element + ", ");
        }
        str.append("]");
        str.deleteCharAt(str.length() -2);
        str.deleteCharAt(str.length() -2);
        System.out.println(str);
    }

    public int[] checkNumbers(int[] winNumbers, int bonusNum){
        int[] result = new int[2];
        for(int i = 0; i < 6; i++){
            if(numbers.get(i) == winNumbers[i]){
                //depth 2
                result[0]++;
            }
            if(numbers.get(i) == bonusNum){
                result[1]++; //보너스 번호와 당첨 번호는 다름
            }
        }
        return result;
    }

}
