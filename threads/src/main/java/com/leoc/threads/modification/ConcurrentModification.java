package com.leoc.threads.modification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ConcurrentModification {

    public static void main(String[] args) throws InterruptedException {
        Room theater = new Room(3, 3);
        UserSession session1 = new UserSession(theater, new TicketOwner("Bob", 3));
        UserSession session2 = new UserSession(theater, new TicketOwner("Ale", 4));
        UserSession session3 = new UserSession(theater, new TicketOwner("Cal", 5));
        UserSession session4 = new UserSession(theater, new TicketOwner("Tim", 5));
        UserSession session5 = new UserSession(theater, new TicketOwner("Sue", 6));
        UserSession session6 = new UserSession(theater, new TicketOwner("Rob", 5));
        UserSession session7 = new UserSession(theater, new TicketOwner("Ace", 1));
        ExecutorService service = Executors.newFixedThreadPool(10);
        service.execute(session1);
        service.execute(session2);
        service.execute(session3);
        service.execute(session4);
        service.execute(session5);
        service.execute(session6);
        service.execute(session7);

        while (true) {
            Thread.sleep(5000);
            printSessionInfo(session1, session2, session3, session4, session5, session6, session7);
        }
    }

    private static void printSessionInfo(UserSession... sessions) {
        Arrays.stream(sessions).forEach(it ->
                System.out.println(it.toString())
        );
        System.out.println("----------------------------------------------------------------------");
    }
}

class Room {
    private Seat[][] seats;

    public Room(int rows, int columns) {
        seats = new Seat[rows][columns];
        IntStream.range(0, rows)
                .forEach(row -> IntStream.range(0, columns)
                        .forEach(column -> seats[row][column] = new Seat(row, column)));
    }

    public Seat selectSeat(TicketOwner owner) {
        for (Seat[] seatsRow : seats) {//by row
            for (Seat seat : seatsRow) {// by column
                if (seat.take(owner)) {
                    return seat;
                }
            }
        }
        return null;
    }
}

class Seat {
    private final Integer row;
    private final Integer column;
    private TicketOwner owner;

    public Seat(Integer row, Integer column) {

        this.row = row;
        this.column = column;
    }

    public boolean take(TicketOwner owner) {
        if (this.owner != null) {

            return false;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.owner = owner;
        return true;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("(").append(row)
                .append(",").append(column)
                .append(')').toString();
    }
}

class TicketOwner {
    private final int numberOfSeats;
    private final String name;
    private List<Seat> seats;

    public TicketOwner(String name, int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
        this.name = name;
        seats = new ArrayList<>(numberOfSeats);
    }

    public void addSeat(Seat selected) {
        seats.add(selected);
    }

    public boolean isDone() {
        return seats.size() >= numberOfSeats;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(name)
                .append("(")
                .append(numberOfSeats)
                .append("):")
                .append(seats)
                .toString();
    }
}

class UserSession implements Runnable {
    private final Room room;
    private final TicketOwner owner;
    private boolean completed = false;

    public UserSession(Room room, TicketOwner owner) {
        this.room = room;
        this.owner = owner;
    }

    @Override
    public void run() {
        boolean continueSelection = true;

        while (continueSelection && !owner.isDone()) {
            Seat selected = room.selectSeat(owner);
            if (selected != null) {
                owner.addSeat(selected);

            }
            /*try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            continueSelection = selected != null;
        }
        completed = true;
    }

    @Override
    public String toString() {
        return owner.toString();
    }

    public boolean isCompleted() {
        return completed;
    }
}