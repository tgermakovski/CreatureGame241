package com.company;
import java.util.Scanner;


public class PC extends Creature {

    int respect = 40;
    Scanner scan = new Scanner(System.in);

        PC(String a, String b, int c) {
        super(a,b);
        this.respect = c;
        this.type = "PC";
    }

    public void setRespect(int a){
            this.respect = this.respect + a;
    }

    public void play(Scanner s) {
        String command;
        do {
            System.out.print("Please enter a command: ");
            command = s.nextLine();
            System.out.println("--*--");

            if(command.equals("help") || command.equals("halp") || command.equals("???") || command.equals("list of commands")) {
                System.out.println("List of commands:");
                System.out.println("help: list of commands.");
                System.out.println("look: outputs all relevant info about your current location.");
                System.out.println("respect: outputs your current level of respect.");
                System.out.println("clean: cleans the room.");
                System.out.println("dirty: uncleans the room.");
                System.out.println("north: transports you to an adjacent room north of your current location, given that it exists.");
                System.out.println("take a wild guess how to go east, south, or west.");
                System.out.println("CreatureName:clean: forces creature of your choice to clean the room.");
                System.out.println("CreatureName:dirty: forces creature of your choice to unclean the room.");
                System.out.println("CreatureName:north: forces a creatures of your choice to go to an adjacent room north of your current location, given that it exists.");
                System.out.println("take a wild guess how to force a creature to go east, south, or west.");
                System.out.println("exit/quit: ends the playthrough.");
            }

            if(command.equals("look")){
                System.out.println(this.room.toString());
            }

            if(command.equals("respect 79")){
                this.respect = 79;
                System.out.println("You now have 79 respect points.");
            }

            if(command.equals("respect 1")){
                this.respect = 1;
                System.out.println("You now have 1 respect point.");
            }

            if(command.equals("win")){
                this.respect = 80;
                System.out.println(this.name + ": *wins*");
            }

            if(command.equals("lose")){
                this.respect = 0;
                System.out.println(this.name + ": *loses*");
            }

            if(command.equals("respect")){
                if(this.respect<20)
                    System.out.println("You have " + this.respect + " respect point(s), and if you don't stop being a clown, I'll deduce another 20");
                if(this.respect>=20)
                    System.out.println("You have " + this.respect + " respect point(s)");
            }

            if(command.equals("clean")){
                this.room.setState(1, true);
            }

            if(command.equals("dirty")){
                this.room.setState(-1, true);
            }

            if(command.contains(":clean")){
                boolean cleansed = false;
                String cleaner = command.substring(0, command.length() - 6);

                if(cleaner.equals(CSC241Handler.getPC().getName())){
                    System.out.println("You can't force yourself to do something. That just doesn't make any damn sense");
                } else {

                    int xyz = this.room.binarySearchByName2(this.room.creatures, 0, this.room.creatures.length, cleaner);
                    if(xyz != -1){
                        this.room.creatures[xyz].modStateForce(1);
                        cleansed = true;
                    }


                    /*
                    for(int i = 0 ; i < this.room.creatures.length; i++){
                        if(this.room.creatures[i] != null && this.room.creatures[i].getName().equals(cleaner)){
                            this.room.creatures[i].modStateForce(1);
                            cleansed = true;
                            i=this.room.creatures.length;
                        }
                    }
                    */

                    if(cleansed == false){
                        System.out.println("That creature does not exist in this room.");
                    }

                }
            }

            if(command.contains(":dirty")){
                boolean uncleansed = false;
                String uncleaner = command.substring(0, command.length() - 6);

                if(uncleaner.equals(CSC241Handler.getPC().getName())){
                    System.out.println("You can't force yourself to do something. That just doesn't make any damn sense");
                } else {

                    int xyz = this.room.binarySearchByName2(this.room.creatures, 0, this.room.creatures.length, uncleaner);
                    if(xyz != -1){
                        this.room.creatures[xyz].modStateForce(-1);
                        uncleansed = true;
                    }

                    if(uncleansed == false){
                    System.out.println("That creature does not exist in this room.");
                    }
                }
            }

            if(command.equals("north")){
                if(this.room.north != null) {
                    this.move(this.room.north);
                    //this.room.removeCreature(this);
                    //this.room.north.addCreature(this);
                } else {
                    System.out.println("There is no neighbor to the north");
                }
            }

            if(command.equals("east")){
                if(this.room.east != null) {
                    this.move(this.room.east);
                    //this.room.removeCreature(this);
                    //this.room.east.addCreature(this);
                } else {
                    System.out.println("There is no neighbor to the east");
                }
            }

            if(command.equals("south")){
                if(this.room.south != null) {
                    this.move(this.room.south);
                    //this.room.removeCreature(this);
                    //this.room.south.addCreature(this);
                } else {
                    System.out.println("There is no neighbor to the south");
                }
            }

            if(command.equals("west")){
                if(this.room.west != null) {
                    this.move(this.room.west);
                    //this.room.removeCreature(this);
                    //this.room.west.addCreature(this);
                } else {
                    System.out.println("There is no neighbor to the west");
                }
            }

            if(command.contains(":north")){
                boolean banished = false;
                String outlaw = command.substring(0, command.length() - 6);
                if(outlaw.equals(CSC241Handler.getPC().getName())){
                    System.out.println("You can't banish yourself. That's not how any of this works.");
                } else {
                    if (this.room.north == null) {
                        System.out.println("There exists no neighbor to the North");
                    } else {
                            int xyz = this.room.binarySearchByName2(this.room.creatures, 0, this.room.creatures.length, outlaw);
                            if(xyz != -1){
                                banished = this.room.creatures[xyz].move(this.room.north);
                            }
                            if(banished)
                                System.out.println(outlaw + " has been banished");
                    }
                }
            }

            if(command.contains(":east")){
                boolean banished = false;
                String outlaw = command.substring(0, command.length() - 5);
                if(outlaw.equals(CSC241Handler.getPC().getName())){
                    System.out.println("You can't banish yourself. That's not how any of this works.");
                } else {
                    if (this.room.east == null) {
                        System.out.println("There exists no neighbor to the East");
                    } else {
                        int xyz = this.room.binarySearchByName2(this.room.creatures, 0, this.room.creatures.length, outlaw);
                        if(xyz != -1){
                            banished = this.room.creatures[xyz].move(this.room.east);
                        }
                        if(banished)
                            System.out.println(outlaw + " has been banished");
                    }
                }
            }

            if(command.contains(":south")){
                boolean banished = false;
                String outlaw = command.substring(0, command.length() - 6);
                if(outlaw.equals(CSC241Handler.getPC().getName())){
                    System.out.println("You can't banish yourself. That's not how any of this works.");
                } else {
                    if (this.room.south == null) {
                        System.out.println("There exists no neighbor to the South");
                    } else {
                        int xyz = this.room.binarySearchByName2(this.room.creatures, 0, this.room.creatures.length, outlaw);
                        if(xyz != -1){
                            banished = this.room.creatures[xyz].move(this.room.south);
                        }
                        if(banished)
                            System.out.println(outlaw + " has been banished");
                    }
                }
            }

            if(command.contains(":west")){
                boolean banished = false;
                String outlaw = command.substring(0, command.length() - 5);
                if(outlaw.equals(CSC241Handler.getPC().getName())){
                    System.out.println("You can't banish yourself. That's not how any of this works.");
                } else {
                    if (this.room.west == null) {
                        System.out.println("There exists no neighbor to the West");
                    } else {
                        int xyz = this.room.binarySearchByName2(this.room.creatures, 0, this.room.creatures.length, outlaw);
                        if(xyz != -1){
                            banished = this.room.creatures[xyz].move(this.room.west);
                        }
                        if(banished)
                            System.out.println(outlaw + " has been banished");
                    }
                }
            }

            //this command is under construction. do not use
            if(command.contains(":index")){
                boolean found = false;
                String desired_name = command.substring(0, command.length() - 6);
                for (int i = 0; i < this.room.creatures.length; i++) {
                    if (this.room.creatures[i] != null && this.room.creatures[i].getName().equals(desired_name)) {
                        found = true;
                        System.out.println(getRoom().binarySearch2(getRoom().creatures, 0, getRoom().creatures.length, this.room.creatures[i]));
                        i = this.room.creatures.length;
                    }
                }
                if(!found){
                    System.out.println("not found");
                }
            }


            if(command.equals("exit")||command.equals("quit")){
                System.out.println("*mission abort*");
            }

            if(!command.equals("help") && !command.equals("halp") && !command.equals("???")&& !command.equals("list of commands")&& !command.equals("look")&& !command.equals("respect") && !command.equals("clean") && !command.equals("clean your damn room!") && !command.equals("dirty")&& !command.equals("force clean")&& !command.equals("force dirty") && !command.equals("north")&& !command.equals("east")&& !command.equals("south")&& !command.equals("west")&& !command.equals("banish")&& !command.equals("exit")&& !command.equals("quit")&& !command.equals("respect 79")&& !command.equals("win")&& !command.equals("respect 1")&& !command.equals("lose")&& !command.contains(":clean")&& !command.contains(":dirty")&& !command.contains(":north")&& !command.contains(":east")&& !command.contains(":south")&& !command.contains(":west")){
                System.out.println("Rodger has a stroke trying to execute that and had to be hospitalized. Please restate your request");
            }

            System.out.println("--*--");

        } while (!command.equals("exit") && !command.equals("quit") && this.respect>0 && this.respect<80);

        if(this.respect<1){
            System.out.println("You have lost all respect points! позор! идиот, просто идиот!");
        }

        if(this.respect>79){
            System.out.println("Congratulations, you have gained " + respect + " points and won the game! слава советскому союзу! ленин красавчик.");
        }

            System.out.println("Thank you for playing");

        System.out.println("--*--");
    }

}
