package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class LottoGame {

    //싱글톤 구현
    private static LottoGame lottoGame = new LottoGame();
    private Lotto[] lottos;

    private LottoGame() {}

    public static LottoGame getInstance() {
        return lottoGame;
    }

    public void start(){
        makeLottos();
        makeWinNumbers();
    }
    private void makeLottos() //로또를 만드는 함수
    {
        System.out.println("구입금액을 입력해 주세요.");
        int buyMoney = Integer.parseInt(Console.readLine());
        try{
            LottoException.getInstance().checkBuyMoney(buyMoney);
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        Lotto[] lottos = new Lotto[buyMoney / 1000];
        for(int i = 0; i < buyMoney/1000; i++){
            lottos[i] = new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6));
        }
    }

    private void makeWinNumbers() //로또 번호를 만드는 함수
    {
        System.out.println("당첨 번호를 입력해 주세요.");
        String winNumbers = Console.readLine();
    }
}
