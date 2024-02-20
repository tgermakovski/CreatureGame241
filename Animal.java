package com.company;

public class Animal extends Creature {

    Animal(String a, String b) {
        super(a,b);
        this.type = "Animal";
    }

    boolean x;
    boolean forced;
    boolean r = false;
    boolean u = false;
    boolean northtried = false;
    boolean easttried = false;
    boolean southtried = false;
    boolean westtried = false;

    public void modState() {

        //check if pc is in the room. if so, creature cannot mod state independently
        if(this.room.binarySearchByName2(this.room.creatures, 0, this.room.creatures.length, "Timofey") != -1){
            this.x = true;
        }
        //if pc is not in the room, the creature can mod state
            if(!x){this.room.setState(1, false);}
        }

    public void modStateForce(int dir){
        if(dir==1){
            this.content();
            this.content();
            this.room.setState(dir, true);
        }
        if(dir==-1){
            this.discontent();
            this.discontent();
            this.room.setState(dir, true);
        }
    }



    public void notifyAndReact(String a, int b, int c) {

        if(c==1){
            this.content();
        }

        if(c==-1){
            this.discontent();
        }

        if (a == "dirty"){
            if (b == 1) {
                //visit the neighbors
                do {
                    if (northtried && easttried && southtried && westtried) {
                        drill();
                        u = true;
                    }

                    int rand_int = rand.nextInt(4) + 1;
                    if (rand_int == 1 && u == false && northtried == false) {
                        if(this.room.north != null){
                        System.out.println(this.name + ": *tries relocating to " + this.room.north.getName() + " and claim refugee status.*");
                        r = this.move(this.room.north);}
                        //r = this.room.north.addCreature(this);
                        if (r == true) {
                            this.room = this.room.north;
                        }
                        northtried = true;
                    }
                    if (rand_int == 2 && u == false && easttried == false) {
                        if(this.room.east != null){
                        System.out.println(this.name + ": *tries relocating to " + this.room.east.getName() + " and claim refugee status.*");
                        r = this.move(this.room.east);}
                        //boolean r = this.room.east.addCreature(this);
                        if (r == true) {
                            this.room = this.room.east;
                        }
                        easttried = true;
                    }
                    if (rand_int == 3 && u == false && southtried == false) {
                        if(this.room.south != null){
                        System.out.println(this.name + ": *tries relocating to " + this.room.south.getName() + " and claim refugee status.*");
                        r = this.move(this.room.south);}
                        //boolean r = this.room.south.addCreature(this);
                        if (r == true) {
                            this.room = this.room.south;
                        }
                        southtried = true;
                    }
                    if (rand_int == 4 && u == false && westtried == false) {
                        if(this.room.west != null){
                        System.out.println(this.name + ": *tries relocating to " + this.room.west.getName() + " and claim refugee status.*");
                        r = this.move(this.room.west);}
                        //boolean r = this.room.west.addCreature(this);
                        if (r == true) {
                            this.room = this.room.west;
                        }
                        westtried = true;
                    }

                }while (r == false && u == false);

            } else {
                this.modState();
            }
        }

        }

    public void content(){
        System.out.println(this.name + " *licks face*");
        CSC241Handler.getPC().setRespect(1);
    }

    public void discontent(){
        System.out.println(this.name + " *growls*");
        CSC241Handler.getPC().setRespect(-1);
    }
}

