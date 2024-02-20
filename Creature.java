package com.company;
import java.util.Random;

public abstract class Creature {

    String type;
    Room room;
    String name;
    String description;
    Random rand = new Random();

    Creature(String a, String b){
        this.name = a;
        this.description = b;
    }

    public String getName(){
        return this.name;
    }

    public Room getRoom(){
        return this.room;
    }

    public String getType(){
        return this.type;
    }

    public String getDescription(){
        return this.description;
    }

    public String toString(){
        return this.name;
    }

    public void setRoom(Room r){
        this.room = r;
    }

    public String room() { return this.room.name; }

    public void modState() {}

    public void modStateForce(int dir) {}

    public void notifyAndReact(String a, int b, int c){}

    public void content(){}

    public void discontent(){}

    public boolean move(Room new_room){
        boolean moved = false;
        boolean full = true;
        //check if room is full.
        for(int i = 0 ; i < new_room.creatures.length ; i++){
            if(new_room.creatures[i] == null){
                full = false;
                i = new_room.creatures.length;
            }
        }
        //if so, call remove
        if(full==false){
            this.room.removeCreature(this);
            new_room.addCreature(this);
            moved = true;
        }
        // if not
        if(full){
            System.out.println("Jesse, that's physically impossible!");
            System.out.println("(As a side note, I have become South America!)");
        }
        return moved;
    }

    public void drill(){
        System.out.println(name + ": ight, imma head out.");
        System.out.println(". ");
        System.out.println(". ");
        System.out.println(". ");
        System.out.println("  oooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        System.out.println("  oooooooooooooooooooo-------------ooooooooooooooooooooo");
        System.out.println("  ooooooooooooo--------ooooooooooo--------oooooooooooooo");
        System.out.println("  oooooooooo----oooooooooooooooooooooooo----oooooooooooo");
        System.out.println("  oooooooo---oooooooooooooooooooooooooooooo---oooooooooo");
        System.out.println("  ooooooo--oooooooooooooooooooooooooooooooooo--ooooooooo");
        System.out.println("  oooooo--oooooooooo-------------------ooooooo--oooooooo");
        System.out.println("  oooooo--oooooooo--ooooooooooooooooooo-----ooo--ooooooo");
        System.out.println("  ooooo--ooooooooo--oooooooooooooooooooooooo--oo-ooooooo");
        System.out.println("  ooooo-ooooooooo--ooooo---oooooooo---ooooo-oooo-ooooooo");
        System.out.println("  ooooo-ooooooooo-ooooo-ooo-oooooo-ooo-oooo-oooo-ooooooo");
        System.out.println("  ooooo-oooooo---ooooooo---ooo---oo---oooo-ooooo-ooooooo");
        System.out.println("  --ooo-oooo--oo-ooooooooooooooo---ooooooo---ooo-ooooooo");
        System.out.println("  oo--o-oo--ooo-oo-----------ooooo----ooo-ooo--o-oo----o");
        System.out.println("  oooo----ooooo-oooo---o---oooooooooooooo-ooooo--o----oo");
        System.out.println("  ooooo----ooo-ooooooooooooooooooooooooo-ooooooo----oooo");
        System.out.println("  -oo--oooo--o-ooooooooooooooooooooooooo-ooooo--oooo--oo");
        System.out.println("  oo---oooo--oo--------------------------ooooo--oooo--oo");
        System.out.println("  ooooo----ooo--ooooo---oo--oo---oooooo--ooooooo----oooo");
        System.out.println("  oooooooooooo--ooooooooooo-ooooooooooo--oooooooooo-oooo");
        System.out.println("  oooooooooooo---------------------------oooooooooo-oooo");
        System.out.println("  -oooooooooooooooo--oooooooooo---ooooooooooooooooo-oooo");
        System.out.println("  o--ooooooooooooooo--ooooooooooo--oooooooooooooo--ooooo");
        System.out.println("  ooo--ooooooooooooo--oooooooooooo--ooooooooooooo--ooooo");
        System.out.println("  ooooo--ooooooooooo--oooooooooooo--ooooooooooo---oooooo");
        System.out.println("  oooooo---oooooooo--oooooooooooo--ooooooooo---ooooooooo");
        System.out.println("  oooooooo------ooo--ooooooooooo-oooo-------oooooooooooo");
        System.out.println("  ooooooooooooo------------------------ooooooooooooooooo");
        System.out.println("  oooooooooooooooooo------ooooooo------ooooooooooooooooo");
        System.out.println("  oooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        System.out.println(". ");
        System.out.println(". ");
        System.out.println(". ");
        System.out.println(this.name + " has drilled a hole in the ceiling and left the simulation.");
        this.room.removeCreature(this);
        for (int i = 0; i < this.room.creatures.length; i++) {
            if(this.room.creatures[i] != null) {
                this.room.creatures[i].discontent();
            }
        }
    }

}
