package chess.ai;

public enum AIComplexity
{
	COMPLEX(3), DUMB(1), NONE(0), SIMPLE(2);
	
	private final int value;
	
	static double attackMultiplier;
	
	static double defendMultiplier;
	
	static int foolishness;
	
	
	AIComplexity(int value) { this.value = value; }
	
	
	int getValue() {
		return this.value;
	}
	
	
	static void setFoolishness(int foolishness)
	{
		AIComplexity.foolishness = foolishness;
	}
	
	
	static int getFoolishness()
	{
		return foolishness;
	}
	
	
	static void setAttackMultiplier(double attackMultiplier)
	{
		AIComplexity.attackMultiplier = attackMultiplier;
	}
	
	
	static double getAttackMultiplier()
	{
		return attackMultiplier;
	}
	
	
	static void setDefendMultiplier(double attackMultiplier)
	{
		AIComplexity.attackMultiplier = attackMultiplier;
	}
	
	
	static double getDefendMultiplier()
	{
		return defendMultiplier;
	}
	
}
