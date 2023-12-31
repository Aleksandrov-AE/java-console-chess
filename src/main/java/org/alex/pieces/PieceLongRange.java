package org.alex.pieces;

import org.alex.board.Board;
import org.alex.board.BoardUtil;
import org.alex.Color;
import org.alex.Coordinates;
import java.util.List;
import java.util.Set;

public abstract class PieceLongRange  extends  Piece {

    public PieceLongRange(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    public Set<Coordinates> getAvailableSquare(Board board) {
        return super.getAvailableSquare(board);
    }

    @Override
    protected boolean squareAvailableForMove(Coordinates newCoordinates, Board board) {
        boolean result = super.squareAvailableForMove(newCoordinates, board);
        if (result) {
            result = squareAvailableForAttack(newCoordinates, board);
        }
        return  result;
    }

    @Override
    protected boolean squareAvailableForAttack(Coordinates newCoordinates, Board board) {
        List<Coordinates> list;
            if (this.getCoordinates().horizontal.ordinal() == newCoordinates.horizontal.ordinal()) {
                list = BoardUtil.getVerticalCoordinates(this.getCoordinates(), newCoordinates);

            } else {
                if (this.getCoordinates().vertical == newCoordinates.vertical) {
                    list = BoardUtil.getHorizontalCoordinates(this.getCoordinates(), newCoordinates);
                } else {
                    list = BoardUtil.getDiagonalBetween(this.getCoordinates(), newCoordinates);
                }
            }

            for (Coordinates moveCoordinates: list) {
                if (!board.isSquareEmpty(moveCoordinates)) {
                    return false;
                }
            }
        return true;
        }

}
