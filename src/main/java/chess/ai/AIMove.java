package chess.ai;

import chess.core.Move;
import chess.core.Team;

public class AIMove extends Move implements Comparable<AIMove>
{
	int priority;
	
	public AIMove(int x1, int y1, int x2, int y2, Team team, int priority) {
		super(x1, y1, x2, y2, team);
		this.priority = priority;
	}
	
	/**
	 * The higher the priority, the smaller it is compared.
	 * All the high prioritys are on the beginning and the low ones on the end
	 * @return 1 when priority is smaller, 0 when equal and -1 when bigger
	 */
	@Override
	public int compareTo(AIMove aiMove)
	{
		return Integer.compare(aiMove.priority, this.priority);
	}
	
	public String toString() {
		return x1 + ", " + y1 + ", " + x2 + ", " + y2 + " [" + priority + "]";
	}
}
