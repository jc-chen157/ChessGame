package backend.recording;

import backend.chess.ChessPiece;
import backend.chess.Color;
import backend.chess.PieceType;
import middleware.engine.GameModel;

public class BasicMoveCommand implements Command {

	private ChessPiece aSelectPiece;
	private ChessPiece aEatenPiece;
	private int oldX;
	private int oldY;
	private int newX;
	private int newY;

	public BasicMoveCommand(ChessPiece pPiece, int pNewX, int pNewY) {
		aSelectPiece = pPiece;
		oldX = pPiece.getX();
		oldY = pPiece.getY();
		newX = pNewX;
		newY = pNewY;
		aEatenPiece = GameModel.getInstance().getChessPiece(pNewX, newY);
	}

	@Override
	public void execute() {
		GameModel.getInstance().removeChessPiece(oldX, oldY);
		aSelectPiece.setPosition(newX, newY);
		GameModel.getInstance().addChessPiece(aSelectPiece, newX, newY);
		aSelectPiece.moved();

		if (aSelectPiece.getType() == PieceType.PAWN &&
				(newX == 0 || newX == 7)) {
			GameModel.getInstance().removeChessPiece(newX, newY);
			GameModel.getInstance().addChessPiece(
					new ChessPiece(aSelectPiece.getColor(), PieceType.QUEEN), newX, newY);
			GameModel.getInstance().getChessPiece(newX, newY).
					setPosition(newX, newY);
		}
	}

	@Override
	public void undo() {
		GameModel.getInstance().removeChessPiece(newX, newY);
		aSelectPiece.setPosition(oldX, oldY);
		GameModel.getInstance().addChessPiece(aSelectPiece, oldX, oldY);
		aSelectPiece.moved();
		if (aEatenPiece != null) {
			GameModel.getInstance().addChessPiece(aEatenPiece, newX, newY);
		}
		if (aSelectPiece.getType() == PieceType.PAWN &&
				(newX == 0 || newX == 7)) {
			GameModel.getInstance().removeChessPiece(newX, newY);
			GameModel.getInstance().addChessPiece(aSelectPiece, newX, newY);
			GameModel.getInstance().getChessPiece(newX, newY).setPosition(newX, newY);
		}
	}

	@Override
	public Color getColor() {
		return aSelectPiece.getColor();
	}


	@Override
	public String toString() {
		if (aEatenPiece == null) {
			if (aSelectPiece.getType() == PieceType.PAWN) {
				return getHorizantalIndex(newY) + getVerticalIndex(newX);
			} else {
				return getPieceAbbreviation(aSelectPiece) +
						getHorizantalIndex(newY) + getVerticalIndex(newX);
			}
		}
		return getPieceAbbreviation(aSelectPiece) + "x" +
				getHorizantalIndex(newY) + getVerticalIndex(newX);
	}

	private String getHorizantalIndex(int pInt) {
		return String.valueOf(((char) (pInt + 97)));
	}

	private String getVerticalIndex(int pInt) {
		return "" + (8 - pInt);
	}

	private String getPieceAbbreviation(ChessPiece pPiece) {
		switch (pPiece.getType()) {
			case PAWN:
				return "P";
			case ROOK:
				return "R";
			case KNIGHT:
				return "N";
			case BISHOP:
				return "B";
			case QUEEN:
				return "Q";
			case KING:
				return "K";
			default:
				return "";
		}
	}
}