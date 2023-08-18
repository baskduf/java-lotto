package lotto;

public class LottoException {
    //로또 예외처리 클래스
    //싱글톤 설계

    static private LottoException lottoException = new LottoException();
    private LottoException() {}

    public static LottoException getInstance(){
        return lottoException;
    }
    public void checkBuyMoney(int buyMoney)
    {
        if(buyMoney % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 구입금액은 1000으로 나누어 떨어져야 합니다.");
        }
    }

    public void checkWinNumbers(String winNumbers) {

    }
}
