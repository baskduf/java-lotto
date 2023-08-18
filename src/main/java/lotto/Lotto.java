package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
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
}
