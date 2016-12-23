package backend.recording;

import backend.chess.ChessPiece;
import backend.chess.Color;

public interface Command {
	public void execute();
	public void undo();
	public Color getColor();

}
