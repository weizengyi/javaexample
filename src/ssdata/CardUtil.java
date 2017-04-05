package ssdata;

import java.util.Random;

public class CardUtil {
	
	private static Random r = new Random();
	
	public static FreakCardEntity getNewFreakCardEntity(FreakCardEntity oldFreakCardEntity){
		FreakCardEntity newFreakCardEntity = new FreakCardEntity();
		newFreakCardEntity.setCardNo(oldFreakCardEntity.getCardNo());
		newFreakCardEntity.setCardPre(getCardPre(oldFreakCardEntity.getCardNo()));
		newFreakCardEntity.setCardLast(getCardLast(oldFreakCardEntity.getCardNo()));
		newFreakCardEntity.setCvv(getCvv());
		newFreakCardEntity.setDate(getDate());
		newFreakCardEntity.setPhone(getPhone(oldFreakCardEntity.getPhone()));
		newFreakCardEntity.setPin(getPin());
		return newFreakCardEntity;
	}
	
	private static String LuhnMod10(String cardNumber, int size) {
		int[][] table = { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 },
				{ 0, 2, 4, 6, 8, 1, 3, 5, 7, 9 } };
		;
		int sum = 0;
		for (int i = size - 1, odd = 0; i >= 0; i--) {
			sum += table[(odd = 1 - odd)][cardNumber.charAt(i) - '0'];
		}
		sum = sum % 10;
		return ( cardNumber+(sum == 0 ? 0 : 10 - sum)); 
	}

	private static String getCardNo(String cardNo){
		String cardPre = (String) cardNo.subSequence(0, 15);
		return LuhnMod10(cardPre+r.nextInt(10)+r.nextInt(10)+r.nextInt(10)+r.nextInt(10),18);
	}
	
	private static String getCardPre(String cardNo){
		return cardNo.substring(0, 6);
	}
	
	private static String getCardLast(String cardNo){
		return cardNo.substring(cardNo.length()-5, cardNo.length()-1);
	}
	
	private static String getCvv(){
		return r.nextInt(10)+r.nextInt(10)+r.nextInt(10)+"";
	}
	
	private static String getDate(){
		int i =  r.nextInt(2) ;
		if(i==1)
			return "1"+ r.nextInt(3)+ r.nextInt(10)+ r.nextInt(10);
		else
			return "0"+ r.nextInt(10)+ r.nextInt(10)+ r.nextInt(10);
	}
	
	private static String getPin(){
		return ""+r.nextInt(10)+r.nextInt(10)+r.nextInt(10)+r.nextInt(10)+r.nextInt(10)+r.nextInt(10);
	}
	
	private static String getPhone(String phone){
		return phone.substring(0, 8)+r.nextInt(10)+r.nextInt(10);
	}
	

}
