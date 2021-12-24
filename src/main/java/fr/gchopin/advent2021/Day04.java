package fr.gchopin.advent2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Day04 {

    public static int scoreOfWinningBoard(List<String> input) {
        List<Integer> drawnNumbers = Stream.of(input.get(0).split(",")).map(Integer::valueOf).toList();

        // Remove drawnNumbers and empty lines from input
        input.remove(0);
        input.removeIf(String::isEmpty);

        List<Board> boards = createBoards(input);

        int drawnNumberIndex = 0;
        Board winningBoard = null;
        while (drawnNumberIndex < drawnNumbers.size() && winningBoard == null) {
            // For each drawn number, mark the number on each board and check for a bingo
            for (Board board : boards) {
                board.markNumber(drawnNumbers.get(drawnNumberIndex));
                if (board.isWinning()) {
                    winningBoard = board;
                    break;
                }
            }
            drawnNumberIndex++;
        }

        // For the winning board, calculate the score
        if (winningBoard == null) {
            throw new IllegalStateException("There is no winning board");
        }
        return winningBoard.score(drawnNumbers.get(drawnNumberIndex - 1));
    }

    public static int scoreOfLastWinningBoard(List<String> input) {
        List<Integer> drawnNumbers = Stream.of(input.get(0).split(",")).map(Integer::valueOf).toList();

        // Remove drawnNumbers and empty lines from input
        input.remove(0);
        input.removeIf(String::isEmpty);

        List<Board> boards = createBoards(input);

        int drawnNumberIndex = 0;
        List<Board> winningBoards = new ArrayList<>();
        List<Board> notWinningBoards = new ArrayList<>(boards);
        while (drawnNumberIndex < drawnNumbers.size() && !notWinningBoards.isEmpty()) {
            // For each drawn number, mark the number on each board and check for a bingo
            for (Board board : notWinningBoards) {
                board.markNumber(drawnNumbers.get(drawnNumberIndex));
                if (board.isWinning()) {
                    winningBoards.add(board);
                }
            }
            notWinningBoards.removeAll(winningBoards);

            drawnNumberIndex++;
        }

        // For the last winning board, calculate the score
        return winningBoards.get(winningBoards.size() - 1)
                            .score(drawnNumbers.get(drawnNumberIndex - 1));
    }

    private static List<Board> createBoards(List<String> input) {
        List<Board> boards = new ArrayList<>();
        Board newBoard = new Board();

        // Create boards
        for (int i = 0; i < input.size(); i++) {

            newBoard.addNumbers(Arrays.stream(input.get(i).split(" "))
                                      .filter(Predicate.not(String::isEmpty))
                                      .map(Integer::valueOf)
                                      .toList());

            // Last line for the current board
            if ((i + 1) % Board.BOARD_SIZE == 0) {
                boards.add(newBoard);
                newBoard = new Board();
            }
        }
        return boards;
    }

    static class Board {
        private static final int BOARD_SIZE = 5;

        List<Integer> numbers = new ArrayList<>();
        List<Boolean> markedNumbers = new ArrayList<>();

        void addNumbers(List<Integer> newNumbers) {
            numbers.addAll(newNumbers);
            for (int i = 0; i < newNumbers.size(); i++) {
                markedNumbers.add(false);
            }
        }

        void markNumber(Integer number) {
            int index = numbers.indexOf(number);
            if (index != -1) {
                markedNumbers.set(index, true);
            }
        }

        boolean isWinning() {
            // Check rows
            for (int rowIndex = 0; rowIndex < BOARD_SIZE; rowIndex++) {
                boolean isWinning = true;
                for (int columnIndex = 0; columnIndex < BOARD_SIZE; columnIndex++) {

                    boolean isMarked = markedNumbers.get(rowIndex * BOARD_SIZE + columnIndex);
                    isWinning = isWinning && isMarked;
                }
                if (isWinning) {
                    return true;
                }
            }

            // Check columns
            for (int columnIndex = 0; columnIndex < BOARD_SIZE; columnIndex++) {
                boolean isWinning = true;
                for (int rowIndex = 0; rowIndex < BOARD_SIZE; rowIndex++) {

                    boolean isMarked = markedNumbers.get(rowIndex * BOARD_SIZE + columnIndex);
                    isWinning = isWinning && isMarked;
                }
                if (isWinning) {
                    return true;
                }
            }
            return false;
        }

        int score(int lastNumberCalled) {
            int sumOfUnmarkedNumbers = 0;
            for (int i = 0; i < markedNumbers.size(); i++) {
                if (Boolean.FALSE.equals(markedNumbers.get(i))) {
                    sumOfUnmarkedNumbers += numbers.get(i);
                }
            }
            return lastNumberCalled * sumOfUnmarkedNumbers;
        }

        @Override
        public String toString() {
            return "\nBoard {" +
                "\n    numbers=" + numbers +
                "\n    markedNumbers=" + markedNumbers +
                "\n}";
        }
    }
}
