package ssdata;

import java.util.ArrayList;
import java.util.List;

public class FreakCardEntity {
	
	public static List<FreakCardEntity> getFreakCardEntityList(){
		List<FreakCardEntity> freakCardEntityList = new ArrayList<FreakCardEntity>();
//		TODO get 1000first
		return freakCardEntityList;
	}
	
	public static FreakCardEntity getFreakCardEntity(){
		FreakCardEntity fce = new FreakCardEntity();
		fce.setCardNo("123456789098765432");
		fce.setCvv("123");
		fce.setDate("1234");
		fce.setPhone("188888888888");
		fce.setPin("123456");
		return fce ;
	}
	
	public static void setFreakCardEntity(FreakCardEntity newFreakCardEntity){
		FreakCardEntity fce = new FreakCardEntity();
		fce.setCardNo(newFreakCardEntity.getCardNo());
		fce.setCvv(newFreakCardEntity.getCvv());
		fce.setDate(newFreakCardEntity.getDate());
		fce.setPhone(newFreakCardEntity.getPhone());
		fce.setPin(newFreakCardEntity.getPin());
	}
	
	public FreakCardEntity buildFreakCardEntity(String cardNo,
			String cvv,String date,String pin,String phone,String last,String pre ){
		FreakCardEntity fce = new FreakCardEntity();
		fce.setCardNo(cardNo);
		fce.setCvv(cvv);
		fce.setDate(date);
		fce.setPhone(phone);
		fce.setPin(pin);
		fce.setCardLast(last);
		fce.setCardPre(pre);
		return fce ;
	}
	
	public String getCardPre() {
		return cardPre;
	}

	public void setCardPre(String cardPre) {
		this.cardPre = cardPre;
	}

	public String getCardLast() {
		return cardLast;
	}

	public void setCardLast(String cardLast) {
		this.cardLast = cardLast;
	}
	
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		cardNo = cardNo;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	private String cardNo;
	private String cvv ;
	private String date;
	private String pin ;
	private String phone ;
	private String cardPre;
	private String cardLast;

}
