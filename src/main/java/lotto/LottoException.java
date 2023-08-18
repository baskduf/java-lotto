package lotto;

public class LottoException {
    //로또 예외처리 클래스

    final static private LottoException lottoException = new LottoException();
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

    public void checkWinNumbers(String[] winNumbers) {
        for(String element : winNumbers){
            int number;
            try{
                number = Integer.parseInt(element);
            }
            catch(NumberFormatException e){
                throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식입니다.");
            }
            if(number < 1 || number > 45)
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1~45까지 입력 되어야 합니다.");
        }
        if(winNumbers.length != 6)
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개 입력 되어야 합니다.");
    }

    public void checkBonusNumber(String bonusNumber) {
        int number;
        try{
            number = Integer.parseInt(bonusNumber);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식입니다.");
        }
        if(number < 1 || number > 45)
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1~45까지 입력 되어야 합니다.");
    }

}
