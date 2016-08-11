package rules;

import model.GameModel;
import resource.ChessPiece;
import resource.PieceType;
import ui.GridView;

/**
 * A static Class that verify if a move is legal.
 * Created by JiajunChen on 16/8/9.
 */
public class Referee {

    public static boolean isValidMove(ChessPiece pPiece, GridView pGrid){
            if(pGrid.getChildren().size() == 0){
                return isValidRockMove(pPiece, pGrid);
            }


        return false;
    }


    private static boolean canMoveToEmptyGrid(ChessPiece pPiece, GridView pGrid){
        return true;
    }



    private static boolean isValidRockMove(ChessPiece pPiece, GridView pGrid){
        if(pPiece.getX() == pGrid.getX()){
            int begin = pGrid.getY();
            int end = pPiece.getY();
            if(pGrid.getY() > pPiece.getY()){
                begin = pPiece.getY();
                end = pGrid.getY();
            }
            for(int i = begin; i < end; i++){
                ChessPiece piece = GameModel.getInstance().getChessPiece(pPiece.getX(),i);
                if(piece != null){
                    return false;
                }
            }
            return true;

        }else if(pPiece.getY() == pGrid.getY()) {
            int begin = pGrid.getX();
            int end = pPiece.getX();
            if(pGrid.getY() > pPiece.getX()){
                begin = pPiece.getX();
                end = pGrid.getX();
            }
            for(int i = begin; i < end; i++){
                ChessPiece piece = GameModel.getInstance().getChessPiece(i, pPiece.getX());
                if(piece != null){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
