package chess.ai;

import chess.core.Chessboard;
import chess.core.Chessman;
import chess.core.Game;
import chess.core.Move;
import chess.core.Player;

public class AIPlayer extends Player
{
	
	private long seed;
	
	private final AIMoveSelection moveSelection = new AIMoveSelection(this.seed);
	
	private final int size;
	
	public AIPlayer(Game game, int seed)
	{
		super(game);
		this.seed = seed;
		this.size = game.getChessboard().getSize();
	}
	//Notes eventually break the rating dou to a dangerous move that would be stupid
	@Override
	public Move makeMove() {
		//when looking for danger, look for any of own team and use the highest as referenz, (esample: Rook moves enemy Rook out of the way and reveals the queen for the enemy. Dont forget checkmated)
		moveSelection.clear();
		Chessboard chessboard = getGame().getChessboard();
		for (int xChessman = 0; xChessman < size; xChessman++) {
			for (int yChessman = 0; yChessman < size; yChessman++) {
				for (int xMoveTo = 0; xMoveTo < size; xMoveTo++) {
					for (int yMoveTo = 0; yMoveTo < size; yMoveTo++) {
						if (getGame().isMoveValid(new Move(xChessman, yChessman, xMoveTo, yMoveTo, getGame().getPlayingTeam()))) {
							int priority = 0;
							//TODO if (Checkmated) return new Move
							Chessman movingChessman = chessboard.getChessman(xChessman, yChessman, getGame().getPlayingTeam());
							priority += getTier(xMoveTo, yMoveTo);
							if (movingChessman.inDanger()) {
								priority += getTier(xChessman, yChessman);
							}
							if (movingChessman.inDangerAt(xMoveTo, yMoveTo)) {
								priority -= movingChessman.getType().getTier();
							} else {
								//future Moves
								for (int xFutureMove = 0; xFutureMove < size; xFutureMove++) {
									for (int yFutureMove = 0; yFutureMove < size; yFutureMove++) {
									
									}
								}
							}
							moveSelection.addMove(xChessman, yChessman, xMoveTo, yMoveTo, getGame(), priority);
						}
					}
				}
			}
		}
		return moveSelection.getRandomMove();
	}
	
	private int getTier(int x, int y) {
		if (getGame().getChessboard().getChessman(x, y, getGame().getPlayingTeam()) == null) {
			return 0;
		}
		Chessman chessman = getGame().getChessboard().getChessman(x, y, getGame().getPlayingTeam());
		return chessman.getType().getTier();
		
	}
}
