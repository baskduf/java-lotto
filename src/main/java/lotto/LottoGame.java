package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class LottoGame {

    final private static LottoGame lottoGame = new LottoGame();
    private Lotto[] lottos;
    private int bonusNum;
    private int[] winNumbers;
    private int buyMoney;
    private LottoGame() {}
    public static LottoGame getInstance() {
        return lottoGame;
    }

    private enum WinMoney {
        MATCH_THREE(5000L),
        MATCH_FOUR(50000L),
        MATCH_FIVE(1500000L),
        MATCH_FIVE_BONUS(30000000L),
        MATCH_SIX(2000000000L);

        private final long money;
        WinMoney(long money) {
            this.money = money;
        }

    }

    public void start(){
        makeLottos();
    }

    private void makeLottos() //로또를 만드는 함수
    {
        System.out.println("구입금액을 입력해 주세요.");
        buyMoney = Integer.parseInt(Console.readLine());
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
        String[] winNumberStr = Console.readLine().split(",");
        try{
            LottoException.getInstance().checkWinNumbers(winNumberStr);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            return;
        }
        winNumbers = new int[winNumberStr.length];
        for(int i = 0; i < winNumberStr.length; i++){
            winNumbers[i] = Integer.parseInt(winNumberStr[i]);
        }
        makeBonusNumber();
    }

    private void makeBonusNumber() //로또 보너스 번호를 만드는 함수
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
        process();
    }

    private void process() //로또 결과를 만드는 함수
    {
        int[][] result = new int[10][10];
        for(Lotto lotto : lottos) {
            int[] correct = lotto.checkNumbers(winNumbers, bonusNum);
            if(correct[0] >= 3){
                result[correct[0]][correct[1]] ++;
            }
        }
        printWinLotto(result);
        double profit = getWinLottoMoney(result) / buyMoney * 100;
        System.out.printf(String.format("총 수익률은 %.1f", profit));
        System.out.println("%입니다.");
    }

    private void printLottos(){ //로또 번호를 출력하는 함수
        for(Lotto lotto : lottos){
            lotto.showNumbers();
        }
    }

    private void printWinLotto(int [][] result){
        System.out.println("3개 일치 (5,000원) - " + result[3][0] + "개");
        System.out.println("4개 일치 (50,000원) - " + result[4][0] + "개");
        System.out.println("5개 일치 (1,500,000원) - " + result[5][0] + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + result[5][1] + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + result[6][0] + "개");
    }

    private double getWinLottoMoney(int [][] result){
        return (result[3][0] * WinMoney.MATCH_THREE.money + result[4][0] * WinMoney.MATCH_FOUR.money + result[5][0] * WinMoney.MATCH_FIVE.money + result[5][1] * WinMoney.MATCH_FIVE_BONUS.money + result[6][0] * WinMoney.MATCH_SIX.money);
    }

}
