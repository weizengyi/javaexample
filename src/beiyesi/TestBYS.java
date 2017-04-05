package beiyesi;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestBYS {
	
	
	@Test
	
	public void modelBugHumen(){
		
//		系统缺陷未检出的概率
		Condition CBUGISTRUE = new Condition(ConditionDescribe.BUGISTRUE,true,new Probility(ConditionDescribe.BUGISTRUE,ProbilityExperience.BUGISTRUE));
//		系统缺陷被检出的概率
		Condition CBUGISFALSE = new Condition(ConditionDescribe.BUGISFALSE,true,new Probility(ConditionDescribe.BUGISFALSE,ProbilityExperience.BUGISFALSE));
//		人为操作失误的概率
		Condition CHUMENISTRUE = new Condition(ConditionDescribe.HUMENISTRUE,true,new Probility(ConditionDescribe.HUMENISTRUE,ProbilityExperience.HUMENISTRUE));
//		人为操作不失误的概率
		Condition CHUMENISFALSE = new Condition(ConditionDescribe.HUMENISFALSE,true,new Probility(ConditionDescribe.HUMENISFALSE,ProbilityExperience.HUMENISFALSE));
		
//		系统缺陷未检出且人为操作失误时，可能导致系统故障的概率
		List<Condition> TT = new ArrayList<Condition>();
		TT.add(CHUMENISTRUE);
		TT.add(CBUGISTRUE);
		ConditionProbility cbTT = new ConditionProbility(TT, new Probility("TT",ProbilityExperience.TT));
		
//		系统缺陷未检出且人为操作未失误时，可能导致系统故障的概率
        List<Condition> TF = new ArrayList<Condition>();
        TF.add(CBUGISTRUE);
        TF.add(CHUMENISFALSE);
        ConditionProbility cbTF= new ConditionProbility(TF, new Probility("TF",ProbilityExperience.TF));
        
//      系统缺陷被检出且人为操作失误时，可能导致系统故障的概率
        List<Condition> FT = new ArrayList<Condition>();
        FT.add(CBUGISFALSE);
        FT.add(CHUMENISTRUE);
        ConditionProbility cbFT= new ConditionProbility(FT, new Probility("FT",ProbilityExperience.FT));
			
//      系统缺陷被检且人为操作未失误时，可能导致系统故障的概率
        List<Condition> FF = new ArrayList<Condition>();
        FF.add(CBUGISFALSE);
        FF.add(CHUMENISFALSE);
        ConditionProbility cbFF= new ConditionProbility(FF, new Probility("FF",ProbilityExperience.FF));
			
//      系统故障的总概率＝所有条件概率之和
        List<ConditionProbility> cpl = new ArrayList<ConditionProbility>();
        cpl.add(cbFF);
        cpl.add(cbFT);
        cpl.add(cbTF);
        cpl.add(cbTT);
    	DecimalFormat df = new DecimalFormat("0.00000");   
        System.out.println(df.format(new ConditionProbilityList("故障概率",cpl).getConditionProbilitySUM()));
        
	}
	
	@Test
	public void modelBugHumenInf(){
		
//		系统缺陷未检出的概率
		Condition CBUGISTRUE = new Condition(ConditionDescribe.BUGISTRUE,true,new Probility(ConditionDescribe.BUGISTRUE,ProbilityExperience.BUGISTRUE));
//		系统缺陷被检出的概率
		Condition CBUGISFALSE = new Condition(ConditionDescribe.BUGISFALSE,true,new Probility(ConditionDescribe.BUGISFALSE,ProbilityExperience.BUGISFALSE));
		
//		人为操作失误的概率
		Condition CHUMENISTRUE = new Condition(ConditionDescribe.HUMENISTRUE,true,new Probility(ConditionDescribe.HUMENISTRUE,ProbilityExperience.HUMENISTRUE));
//		人为操作不失误的概率
		Condition CHUMENISFALSE = new Condition(ConditionDescribe.HUMENISFALSE,true,new Probility(ConditionDescribe.HUMENISFALSE,ProbilityExperience.HUMENISFALSE));

//		基础设施故障概率
		Condition CINFISTRUE = new Condition(ConditionDescribe.INFISTRUE,true,new Probility(ConditionDescribe.INFISTRUE,ProbilityExperience.INFISTRUE));
//		基础设施不故障概率
		Condition CINFISFALSE = new Condition(ConditionDescribe.INFISFALSE,true,new Probility(ConditionDescribe.INFISFALSE,ProbilityExperience.INFISFALSE));
		
//		系统缺陷未检出且人为操作失误且基础设施故障
		List<Condition> TTT = new ArrayList<Condition>();
		TTT.add(CINFISTRUE);
		TTT.add(CHUMENISTRUE);
		TTT.add(CBUGISTRUE);
		ConditionProbility cbTTT = new ConditionProbility(TTT, new Probility("TTT",ProbilityExperience.TTT));
		
//		系统缺陷未检出且人为操作失误且基础设施未故障
		List<Condition> TTF = new ArrayList<Condition>();
		TTF.add(CBUGISTRUE);
		TTF.add(CHUMENISTRUE);
        TTF.add(CINFISFALSE);
        ConditionProbility cbTTF= new ConditionProbility(TTF, new Probility("TTF",ProbilityExperience.TTF));
		
//      系统缺陷未检出且人为操作未失误且基础设施故障
        List<Condition> TFT = new ArrayList<Condition>();
        TFT.add(CBUGISTRUE);
        TFT.add(CHUMENISFALSE);
        TFT.add(CINFISTRUE);
        ConditionProbility cbTFT= new ConditionProbility(TFT, new Probility("TFT",ProbilityExperience.TFT));
		
//      系统缺陷未检出且人为操作未失误且基础设施未故障
        List<Condition> TFF = new ArrayList<Condition>();
        TFF.add(CBUGISTRUE);
        TFF.add(CHUMENISFALSE);
        TFF.add(CINFISFALSE);
        ConditionProbility cbTFF= new ConditionProbility(TFF, new Probility("TFF",ProbilityExperience.TFF));
		
//		系统缺陷检出且人为操作失误且基础设施故障
        List<Condition> FTT = new ArrayList<Condition>();
        FTT.add(CBUGISFALSE);
        FTT.add(CHUMENISTRUE);
        FTT.add(CINFISTRUE);
        ConditionProbility cbFTT= new ConditionProbility(FTT, new Probility("FTT",ProbilityExperience.FTT));
		
//		系统缺陷检出且人为操作失误且基础设施未故障
        List<Condition> FTF = new ArrayList<Condition>();
        FTF.add(CBUGISFALSE);
        FTF.add(CHUMENISTRUE);
        FTF.add(CINFISFALSE);
        ConditionProbility cbFTF= new ConditionProbility(FTF, new Probility("FTF",ProbilityExperience.FTF));
		
//		系统缺陷检出且人为操作未失误且基础设施故障
        List<Condition> FFT = new ArrayList<Condition>();
        FFT.add(CBUGISFALSE);
        FFT.add(CHUMENISFALSE);
        FFT.add(CINFISTRUE);
        ConditionProbility cbFFT= new ConditionProbility(FFT, new Probility("FFT",ProbilityExperience.FFT));

//		系统缺陷检出且人为操作未失误且基础设施未故障 
        List<Condition> FFF = new ArrayList<Condition>();
        FFF.add(CBUGISFALSE);
        FFF.add(CHUMENISFALSE);
        FFF.add(CINFISFALSE);
        ConditionProbility cbFFF= new ConditionProbility(FFF, new Probility("FFF",ProbilityExperience.FFF));
		
//      系统故障的总概率＝所有条件概率之和
        List<ConditionProbility> cpl = new ArrayList<ConditionProbility>();
        cpl.add(cbFFF);
        cpl.add(cbFFT);
        cpl.add(cbFTF);
        cpl.add(cbFTT);
        cpl.add(cbTFF);
        cpl.add(cbTFT);
        cpl.add(cbTTF);
        cpl.add(cbTTT);
        
    	DecimalFormat df = new DecimalFormat("0.00000");   
        System.out.println(df.format(new ConditionProbilityList("故障概率",cpl).getConditionProbilitySUM()));
        
	}

}
