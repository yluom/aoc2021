package fr.lmo.aoc2021;

import java.util.stream.Stream;

public class D02 extends AoCLMO {

    public void run() {
        System.out.println("Test file increases : " + firstStar(stream(getTestInputPath())));
        System.out.println("Real file increases : " + firstStar((stream(getInputPath()))));
/*
        System.out.println("Test file increases window : " + findIncreases(stream(getTestInputPath(), Integer::valueOf), 3));
        System.out.println("Real file increases window : " + findIncreases(stream(getInputPath(), Integer::valueOf), 3));*/
    }

    public record Move(String direction, int number) {
    }

    private static class Position {
        public int x;
        public int y;

        Position(int x, int y) {
            x = x;
            y = y;
        }

    }

    private int firstStar(Stream<String> stream) {
        Stream<Move> moves = stream.map(line -> new Move(line.split(" ")[0], Integer.parseInt(line.split(" ")[1])));

        final Position p = new Position(0, 0);
        moves.forEach(m ->
        {
            if (m.direction.equals("forward")) {
                p.x = p.x + m.number;
            } else if (m.direction.equals("up")) {
                p.y = p.y - m.number;
            } else if (m.direction.equals("down")) {
                p.y = p.y + m.number;
            }
        });

        return p.x * p.y;
    }
}
