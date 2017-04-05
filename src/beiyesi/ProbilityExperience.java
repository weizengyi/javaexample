package beiyesi;
/**
 * 根据过往数据统计得出的概率
 * 
 */

public class ProbilityExperience {
	
	public static double BUGISTRUE = 0.15;
	public static double BUGISFALSE = 1-BUGISTRUE;
	
	public static double HUMENISTRUE = 0.15;
	public static double HUMENISFALSE = 1- HUMENISTRUE;
	
	public static double INFISTRUE = 0.001;
	public static double INFISFALSE = 1- INFISTRUE;
	
	
	public static  double TT = 0.9;
	public static  double TF = 0.7;
	public static  double FT = 0.2;
	public static  double FF = 0.1;
	
	public static  double TTT = 1;
	public static  double TTF = 0.9;
	
	public static  double TFT = 1;
	public static  double TFF = 0.7;
	
	public static  double FTT = 1;
	public static  double FTF = 0.2;
	
	public static  double FFT = 1;
	public static  double FFF = 0.1;
	

}
