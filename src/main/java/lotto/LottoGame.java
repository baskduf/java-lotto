package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class LottoGame {

    private static LottoGame lottoGame = new LottoGame();
    private Lotto[] lottos;

    private int bonusNum;

    private LottoGame() {}

    public static LottoGame getInstance() {
        return lottoGame;
    }

    public void start(){
        makeLottos();
    }

    private void printLottos(){
        for(Lotto element : lottos){
            element.showNumbers();
        }
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
        lottos = new Lotto[buyMoney / 1000];
        for(int i = 0; i < buyMoney/1000; i++){
            lottos[i] = new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6));
        }
        System.out.println(buyMoney / 1000 +"개를 구매했습니다.");
        printLottos();
        makeWinNumbers();
    }

    private void makeWinNumbers() //로또 번호를 만드는 함수
    {
        System.out.println("당첨 번호를 입력해 주세요.");
        String winNumberStr = Console.readLine();
        String[] winNumbers = winNumberStr.split(",");
        try{
            LottoException.getInstance().checkWinNumbers(winNumbers);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            return;
        }
        makeBonusNumber();
    }

    private void makeBonusNumber()
    {
        System.out.println("보너스 번호를 입력해 주세요.");
        String bonusNumStr = Console.readLine();
        try{
            LottoException.getInstance().checkBonusNumber(bonusNumStr);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return;
        }
        bonusNum = Integer.parseInt(bonusNumStr);
    }
}
