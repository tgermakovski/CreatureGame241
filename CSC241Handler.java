
package com.company;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

public class CSC241Handler extends DefaultHandler {

    private Room[] rooms = new Room[100];

     public void printRoomViaName(String n) {
        boolean found = false;
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] != null && n.equals(rooms[i].getName()) && i < rooms.length) {
                System.out.println(rooms[i].toString());
                i = rooms.length;
                found = true;
            }
        }
        if(found == false) {
            System.out.println("Such a room does not exist. Perhaps you are at the wrong campus?");
        }
    }

    public Room getRoomViaName(String n) {
        Room room = new Room(null);
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].getName() == n && i < rooms.length) {
                room = rooms[i];
                i = rooms.length;
            }
            if (room.getName() == null && i == rooms.length) {
                System.out.println("Such a room does not exist. Perhaps you are at the wrong campus?");
            }
        }
        return room;
    }

    private static PC pc;
    public static PC getPC() {
        return pc;
    }

    private String latestRoomName;
    Room latestRoom = new Room(null);

    @Override
    public void startDocument() {
        System.out.println("The parsing was started!");
    }

    @Override
    public void endDocument() {
        System.out.println("Parsing completed!");
    }

    @Override
    public void startElement(String ignore1, String ignore2, String tagName, Attributes attr) {
        System.out.println("--*--");
        if(tagName.equals("room")||tagName.equals("animal")||tagName.equals("NPC")||tagName.equals("PC"))
        System.out.println("Reading tag: " + tagName);

        if (tagName.equals("room")) {
            System.out.println("It's a room!");
            this.latestRoomName = attr.getValue("name");
            boolean exists = false;

            //check for existing duplicates
            for (int i = 0; i < rooms.length; i++) {
                if (rooms[i] != null && rooms[i].getName().equals(attr.getValue("name"))) {
                    //if duplicate exists, add state and description
                    rooms[i].setState(attr.getValue("state"));
                    rooms[i].setDescription(attr.getValue("description"));
                    latestRoom = rooms[i];
                    exists = true;
                    i = rooms.length;
                }
            }

            //if duplicates do not exist, create room
            if(exists==false){
                Room r1 = new Room(attr.getValue("name"));
                r1.setState(attr.getValue("state"));
                r1.setDescription(attr.getValue("description"));
                latestRoom = r1;
                //add room to array
                    for (int i = 0; i <= rooms.length; i++) {
                        if (rooms[i] == null && i < rooms.length) {
                            rooms[i] = r1;
                            i = rooms.length;
                        }
                    }

            }

            System.out.println("This room's name is: " + attr.getValue("name") + ", and its description is: " + attr.getValue("description"));

            //north neighbor
            exists = false;
            String north = attr.getValue("north");
            Room nn = new Room(null);
            if (north == null)
                System.out.println("This room has no neighbor to the north.");
            else {
                System.out.println("This room's north neighbor is " + north);
                //check for already existing duplicates
                for (int i = 0; i < rooms.length; i++) {
                    if (rooms[i] != null && north.equals(rooms[i].getName())) {
                        latestRoom.setNorth(rooms[i]);
                        exists = true;
                        i = rooms.length;
                    }
                }
                if (exists == false) {
                    nn.setName(north);
                    latestRoom.setNorth(nn);
                    //add neighbor to rooms[]
                    for (int i = 0; i < rooms.length; i++) {
                        if (rooms[i] == null && i < rooms.length) {
                            rooms[i] = nn;
                            i = rooms.length;
                        }
                    }
                }
            }

            //east neighbor
            exists = false;
            String east = attr.getValue("east");
            Room en = new Room(null);
            if (east == null)
                System.out.println("This room has no neighbor to the east.");
            else {
                System.out.println("This room's east neighbor is " + east);
                //check for already existing duplicates
                for (int i = 0; i < rooms.length; i++) {
                    if (rooms[i] != null && east.equals(rooms[i].getName())) {
                        latestRoom.setEast(rooms[i]);
                        exists = true;
                        i = rooms.length;
                    }
                }
                if (exists == false) {
                    en.setName(east);
                    latestRoom.setEast(en);
                    //add neighbor to rooms[]
                    for (int i = 0; i < rooms.length; i++) {
                        if (rooms[i] == null && i < rooms.length) {
                            rooms[i] = en;
                            i = rooms.length;
                        }
                    }
                }
            }

            //south neighbor
            exists = false;
            String south = attr.getValue("south");
            Room sn = new Room(null);
            if (south == null)
                System.out.println("This room has no neighbor to the south.");
            else {
                System.out.println("This room's south neighbor is " + south);
                //check for already existing duplicates
                for (int i = 0; i < rooms.length; i++) {
                    if (rooms[i] != null && south.equals(rooms[i].getName())) {
                        latestRoom.setSouth(rooms[i]);
                        exists = true;
                        i = rooms.length;
                    }
                }
                if (exists == false) {
                    sn.setName(east);
                    latestRoom.setSouth(sn);
                    //add neighbor to rooms[]
                    for (int i = 0; i < rooms.length; i++) {
                        if (rooms[i] == null && i < rooms.length) {
                            rooms[i] = sn;
                            i = rooms.length;
                        }
                    }
                }
            }

            //west neighbor
            exists = false;
            String west = attr.getValue("west");
            Room wn = new Room(null);
            if (west == null)
                System.out.println("This room has no neighbor to the west.");
            else {
                System.out.println("This room's west neighbor is " + west);
                //check for already existing duplicates
                for (int i = 0; i < rooms.length; i++) {
                    if (rooms[i] != null && west.equals(rooms[i].getName())) {
                        latestRoom.setWest(rooms[i]);
                        exists = true;
                        i = rooms.length;
                    }
                }
                if (exists == false) {
                    wn.setName(west);
                    latestRoom.setWest(wn);
                    //add neighbor to rooms[]
                    for (int i = 0; i < rooms.length; i++) {
                        if (rooms[i] == null && i < rooms.length) {
                            rooms[i] = wn;
                            i = rooms.length;
                        }
                    }
                }
            }



        }

            //creatures
            if (tagName.equals("animal") || tagName.equals("NPC") || tagName.equals("PC")) {

                if (tagName.equals("animal")) {
                    System.out.println("It's an " + tagName + "!");
                    Animal a1 = new Animal(attr.getValue("name"), attr.getValue("description"));
                    a1.setRoom(latestRoom); //<-- this is needed so temp isn't null in addCreature()
                    System.out.println("The animal's name is: "+ a1.getName());

                    for (int i = 0; i < rooms.length; i++) {
                        if (rooms[i].getName().equals(latestRoomName)) {
                            System.out.println("It should be added to the room called: " + latestRoomName);
                            rooms[i].addCreature(a1);
                            i = rooms.length;
                        }
                    }
                }

                if (tagName.equals("NPC")) {
                    System.out.println("It's an " + tagName + "!");
                    NPC npc1 = new NPC(attr.getValue("name"), attr.getValue("description"));
                    //npc1.setRoom(latestRoom); <-- this is needed so temp isn't null in addCreature()
                    System.out.println("The NPC's name is: "+ npc1.getName());

                    for (int i = 0; i < rooms.length; i++) {
                        if (rooms[i].getName().equals(latestRoomName)) {
                            System.out.println("It should be added to the room called: " + latestRoomName);
                            rooms[i].addCreature(npc1);
                            i = rooms.length;
                        }
                    }
                }

                if (tagName.equals("PC")) {
                    System.out.println("It's a " + tagName + "!");
                    PC pc1 = new PC(attr.getValue("name"), attr.getValue("description"), 40);
                    this.pc = pc1;
                    //pc1.setRoom(latestRoom); <-- this is needed so temp isn't null in addCreature()
                    System.out.println("The PC's name is: "+ pc1.getName());

                    for (int i = 0; i < rooms.length; i++) {
                        if (rooms[i].getName().equals(latestRoomName)) {
                            System.out.println("It should be added to the room called: " + latestRoomName);
                            rooms[i].addCreature(pc1);
                            i = rooms.length;
                        }
                    }
                }

                //System.out.println(latestRoom.toStringCreatures());


            }

    }
}