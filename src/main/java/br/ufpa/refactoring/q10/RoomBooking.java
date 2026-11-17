package br.ufpa.refactoring.q10;

/* Encapsula todos os dados referentes à solicitação de reserva de livros */
class BookingRequest {
    private String building;
    private int floor;
    private String room;
    private int startHour;
    private int endHour;
    private boolean projector;
    private boolean sound;
    private int attendees;

    BookingRequest(String building, int floor, String room,
                          int startHour, int endHour,
                          boolean projector, boolean sound, int attendees) {
        this.building = building;
        this.floor = floor;
        this.room = room;
        this.startHour = startHour;
        this.endHour = endHour;
        this.projector = projector;
        this.sound = sound;
        this.attendees = attendees;
    }

    public String getBuilding() {
        return building;
    }

    public int getFloor() {
        return floor;
    }

    public String getRoom() {
        return room;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public boolean hasProjector() {
        return projector;
    }

    public boolean hasSound() {
        return sound;
    }

    public int getAttendees() {
        return attendees;
    }
}

public class RoomBooking {
    
    /* Agora, o método quote passa a receber apenas uma instância de BookingRequest,
       eliminando a longa lista de parâmetros individuais
    */
    public double quote(BookingRequest request) {
        double base = (request.getEndHour() - request.getStartHour()) * 50.0;
        
        if (request.hasProjector()) {
            base += 30.0;
        }
        if (request.hasSound()) {
            base += 20.0;
        }
        if (request.getAttendees() > 40) {
            base *= 1.15;
        }

        if ("A".equals(request.getBuilding()) && request.getFloor() >= 3) {
            base += 10.0;
        }
        if ("LAB".equals(request.getRoom())) {
            base += 40.0;
        }

        return Math.round(base * 100.0) / 100.0;
    }
}