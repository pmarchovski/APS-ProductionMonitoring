package com.mdrain.variables;

public class Variables {

	private static final int WORK_CLOTHES_LEAT_TIME  = 60;
	private static final int DAYS_WITHOUT_PRODUCTION = 150;
	private static final String OPERATOR_MECH_ASS    = "Оператор механичен монтаж";
	private static final String[] WORK_CENTER        = {"AUD0001", "IND0001", "IND0002", "IND0003", "IND0004", "IND0005"};
	private static final String[] PRODUCTION_LINE    = {"BR21", "BR22", "AL31", "AL32"};
	private static final String AL31_TEAM_LEADER     = "Ренета Кондарева";
	private static final String AL32_TEAM_LEADER     = "Валя Колева";
	private static final String BR21_TEAM_LEADER     = "Румяна Димитрова";
	private static final String BR22_TEAM_LEADER     = "Петър Гацовски";
	private static final long NUMBER_YEARS_DAYS      = 365;
	private static final double LABOR_COST_PER_MINUTE = 0.18;
	
	public final String VISUAL_CONTROL      = "Визуален контрол";
	public final String OTHER               = "Други";
	public final String EL_ASS              = "Ел. монтаж";
	public final String PREPARE_PCB         = "Заготовка платки";
	public final String PREPARE_ROLLER      = "Заготовка ролки";
	public final String MECH_ASS_ONE        = "Механичен монтаж 1";
	public final String MECH_ASS_TWO        = "Механичен монтаж 2";
	public final String MECH_ASS_THREE      = "Механичен монтаж 3";
	public final String MECH_ASS_FOUR       = "Механичен монтаж 4";
	public final String PACKAGING           = "Опаковка";
	public final String CLEAN_PLASTIC_PARTS = "Почистване на пластмасови тела";
	public final String CHIP_SET            = "Програмиране на чипове";
	public final String TEST                = "Тест";
	public final String GLOBAL_MECH_ASS     = "Мех. монтаж";
	public final String GLOBAL_ELL_ASS      = "Ел. монтаж";
	public final String GLOBAL_TEST         = "Тест";
	public final String YES                 = "Да";
	public final String NO                  = "Не";
	public final String AUTO                = "AUTO";
	public final String INDUSTRIAL          = "INDUSTRIAL";
	public final String PLASTIC             = "PLASTIC";
	public final String TPH                 = "TPH";
	public final String OTHERS              = "OTHERS";
	
	public static Integer getWorkClothesLeatTyme() {
		return WORK_CLOTHES_LEAT_TIME;
		
	}
	
	public static Integer getDaysWithoutProduction() {
		return DAYS_WITHOUT_PRODUCTION;
		
	}
	
	public static String[] getWorkCenter() {
		
		return WORK_CENTER;
		
	}
	
	public static String[] getProductionLine() {
		return PRODUCTION_LINE;
		
	}
	
	public static String[] getTeamLeader() {
		
		String[] teamLeadersCollection = {BR21_TEAM_LEADER, BR22_TEAM_LEADER, AL31_TEAM_LEADER, AL32_TEAM_LEADER};
		return teamLeadersCollection;
	}

	public static long getNumberYersDays() {
		return NUMBER_YEARS_DAYS;
		
	}
	
	public static double getLabourCostPerMinute() {
		return LABOR_COST_PER_MINUTE;
		
	}
}
