package ssdata;

public class GrepSSData {
	
	public static void main(String[] args){
		
		for(FreakCardEntity fce: FreakCardEntity.getFreakCardEntityList()){
			FreakCardEntity newFCE = CardUtil.getNewFreakCardEntity(fce);
			FreakCardEntity.setFreakCardEntity(newFCE);
		}
	}	
}
