package chess.ai;

import chess.core.Game;
import chess.core.Move;

import java.util.ArrayList;
import java.util.Random;

class AIMoveSelection
{
	private final ArrayList<AIMove> moves = new ArrayList<>();
	
	private final Random random;
	
	AIMoveSelection(long seed) {
		this.random = new Random(seed);
	}
	
	void addMove(int xChessman, int yChessman, int xMoveTo, int yMoveTo, Game game, int priority) {
		moves.add(new AIMove(xChessman, yChessman, xMoveTo, yMoveTo, game.getPlayingTeam(), priority));
	}
	
	void clear() {
		moves.clear();
	}
	
	Move getRandomMove() {
		moves.sort(AIMove::compareTo);
		int highestPriority = moves.get(0).priority;
		int lowestPriority = highestPriority - AIComplexity.getFoolishness();
		boolean followingMovesAreLowerThanLowestPriority = false;
		int size = moves.size();
		for (int i = 0; i < size; i++)
		{
			if (followingMovesAreLowerThanLowestPriority) {
				moves.remove(moves.size()-1);
			}
			else if (moves.get(i).priority < lowestPriority)
			{
				followingMovesAreLowerThanLowestPriority = true;
				moves.remove(moves.size()-1);
			}
		}
		return moves.get(random.nextInt(moves.size()));
	}
}
