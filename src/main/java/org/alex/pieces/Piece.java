package org.alex.pieces;

import org.alex.Board;
import org.alex.Color;
import org.alex.Coordinates;

import java.util.HashSet;
import java.util.Set;

public abstract class Piece {
    private final Color color;
    private Coordinates coordinates;

    public Piece(Color color, Coordinates coordinates) {
        this.color = color;
        this.coordinates = coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Color getColor() {
        return color;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Set<Coordinates> getAvailableSquare(Board board) {
        Set<Coordinates> result = new HashSet<>();
        for (CoordinatesShift shift: getShift()) {
        if (coordinates.canShift(shift)) {
            Coordinates newCoordinates = coordinates.getShiftCoordinates(shift);
            if (squareAvailableForMove(newCoordinates, board)) {
                result.add(newCoordinates);
            }
        }
      }
        return result;
    }

    private boolean squareAvailableForMove(Coordinates newCoordinates, Board board) {
        return board.isSquareEmpty(newCoordinates) || board.getPiese(coordinates).getColor() != color;
    }

    protected abstract Set<CoordinatesShift> getShift();
}
