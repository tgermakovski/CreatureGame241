package com.company;

public class Room {

    Room north = null;
    Room east = null;
    Room south = null;
    Room west = null;

    String name;
    String state;
    String description;

    boolean all_null;
    boolean some_null;
    boolean none_null;
    boolean not_null_2;
    boolean not_null_3;

    Room(String a){
        this.name = a;
    }

    public void setName(String n){
        this.name = n;
    }

    public void setState(String s){
        this.state = s;
    }

    public void setDescription(String d){
        this.description = d;
    }

    public void setNorth(Room n){
        this.north = n;
    }

    public void setEast(Room e){
        this.east = e;
    }

    public void setSouth(Room s){
        this.south = s;
    }

    public void setWest(Room w){
        this.west = w;
    }


    public String getName() {
        return this.name;
    }

    Creature[] creatures = new Creature[10];

    public void addCreature(Creature a, Creature b, Creature c, Creature d, Creature e, Creature f, Creature g, Creature h, Creature i, Creature j){
        this.creatures[0] = a;
        this.creatures[1] = b;
        this.creatures[2] = c;
        this.creatures[3] = d;
        this.creatures[4] = e;
        this.creatures[5] = f;
        this.creatures[6] = g;
        this.creatures[7] = h;
        this.creatures[8] = i;
        this.creatures[9] = j;
    }

    public boolean addCreature(Creature a){
        boolean added = false;
        for (int i = 0; i <= creatures.length; i++) {
            if(creatures[i] == null && i < creatures.length){
                creatures[i] = a;
                creatures[i].setRoom(this);
                System.out.println(creatures[i].getName() + " is now in " + this.getName());
                added = true;
                creatures[i].notifyAndReact(this.state, 0, 0);
                i = creatures.length;
            }
        }
        if(added == false) {
            System.out.println("Jesse, that's physically impossible!");
            System.out.println("As a side note, I have become South America!");
        } else {
            quickSort3(creatures, 1, creatures.length-1);
        }
        return added;
    }

    public boolean removeCreature(Creature a){
        boolean removed = false;

        //remove
        int i = this.binarySearch2(this.creatures, 0, creatures.length, a);
        if(i != -1){
            creatures[i] = null;
            removed = true;
            //System.out.println("Ladies and Gentlemen, we got him!");
        }

        //if failed to find creature a
        if(removed == false) {
            System.out.println("The suspect is not here, sir! We suspect that he may have fled to theVirginIslands[i]");
        } else {
            quickSort3(creatures, 1, creatures.length-1);
        }
        return removed;
    }

    public String toString(){
        String output = "the name of the room is: " + this.name + " -*- the state of the room is: " + this.state + " -*- the room's description is: " + this.description;

        if(this.north != null)
            output = output + " -*- the room's north neighbor is: " + north.getName();
        if(this.east != null)
            output = output + " -*- the room's east neighbor is: " + east.getName();
        if(this.south != null)
            output = output + " -*- the room's south neighbor is: " + south.getName();
        if(this.west != null)
            output = output + " -*- the room's west neighbor is: " + west.getName();

        for (int i = 0; i < this.creatures.length; i++) {
            if(creatures[i] != null){
                output = output + " -*- creature found: " + creatures[i].toString() + " -*- its type is: " + creatures[i].getType() + " -*- its description is: " + creatures[i].getDescription();
            }
        }
        return output;
    }

    public String toStringCreatures(){
        String output = "this room contains the following creature(s):";
        for (int i = 0; i < this.creatures.length; i++) {
            if(creatures[i] != null){
                output = output + " -*- creature found: " + creatures[i].toString() + " -*- its type is: " + creatures[i].getType() + " -*- its description is: " + creatures[i].getDescription();
            }
        }
        return output;
    }

    public String toStringCreaturesMinusPC(){
        String output = "this room contains the following creature(s):";
        for (int i = 0; i < this.creatures.length; i++) {
            if(creatures[i] != null && creatures[i] != CSC241Handler.getPC()){
                output = output + " -*- creature found: " + creatures[i].toString() + " -*- its type is: " + creatures[i].getType() + " -*- its description is: " + creatures[i].getDescription();
            }
        }
        return output;
    }

    public String toStringNeighbors(){
        String output = "the neighbor(s) of this room are/is: ";

        if(this.north != null)
            output = output + " -*- the room's north neighbor is: " + north.getName();
        if(this.east != null)
            output = output + " -*- the room's east neighbor is: " + east.getName();
        if(this.south != null)
            output = output + " -*- the room's south neighbor is: " + south.getName();
        if(this.west != null)
            output = output + " -*- the room's west neighbor is: " + west.getName();

        return output;
    }

    public void setState(int s, boolean from_pc){

        int x = 0;

        if(this.state.equals("clean")){
            x=1;
        }
        if(this.state.equals("half-dirty")){
            x=0;
        }
        if(this.state.equals("dirty")){
            x=-1;
        }
        if((x+s)>1){
            System.out.println("Room is already clean, smooth brain");
            s = 69;
        }
        if((x+s)<-1){
            System.out.println("Room is already dirty, smooth brain");
            s = 69;
        }
        if((x+s)<2 && (x+s)>-2){
            x=(x+s);
        }
        if(x==1){
            this.state="clean";
            //System.out.println("the room is now " + this.state); <-- this should only print out if the pc is in the room
        }
        if(x==0){
            this.state="half-dirty";
            //System.out.println("the room is now " + this.state); <-- this should only print out if the pc is in the room
        }
        if(x==-1){
            this.state="dirty";
            //System.out.println("the room is now " + this.state); <-- this should only print out if the pc is in the room
        }
        if(!from_pc){
            s=69; //this way creatures won't react with discotent()/content() when other creatures mod room
        }

        for (int i = 0; i < this.creatures.length; i++) {
            if(creatures[i] != null){
                creatures[i].notifyAndReact(this.state, 1, s);
            }
        }
    }

    public void quickSort3(Creature array[], int left, int right){

        all_null = true;
        some_null = false;
        none_null = false;
        not_null_2 = false;
        not_null_3 = false;

        if(left<right) {

            //check if all/some/none nulls
        for (int i = 0; i < array.length; i++) {

            /*
            if (array[i] != null) {
                this.all_null = false;
            }
            if (array[i] == null) {
                this.some_null = true;
            }
            if (array[i] != null && all_null == false) {
                this.many_not_null = true;
            }
            */

            if(array[i] == null){
                this.some_null = true;
            }else{
                if(not_null_2){
                    not_null_3 = true;
                }else{
                    if(all_null == false){
                        not_null_2 = true;
                    }else{
                        all_null = false;
                    }
                }
            }

        }
        if (this.some_null != true) {
            none_null = true;
        }

        //if all_null, stop here

        //if some_null, first sort nulls to the left
        if(some_null) {

            for (int i = 0; i < array.length; i++) {

                //move first non-null to the far left
                if (array[i] != null) {

                    Creature swap = array[0];
                    array[0] = array[i];
                    array[i] = swap;

                    int ii = 0;

                    //group nulls to the left
                    for (int j = 1; j < array.length; j++) {
                        if (array[j] == null) {
                            //swap
                            swap = array[j];
                            array[j] = array[ii];
                            array[ii] = swap;
                            ii++;
                        }
                    }

                    //having herded all the nulls to the far left in true communist fashion...

                    //if there is only 1 element in the array, do not sort

                    //if there are at least 3 elements in the array, use quicksort
                    if(not_null_3){
                        int partition_i = partition3(array, ii + 1, right);
                        quickSort3(array, ii + 1, partition_i - 1);
                        quickSort3(array, partition_i + 1, right);
                    }

                    //if there are only 2 elements, simply compare them and sort if needed
                    if(not_null_2 && not_null_3 == false){
                        //if [8] > [9], swap
                        if(array[array.length-2].getName().compareTo(array[array.length-1].getName())>0){
                            swap = array[array.length-1];
                            array[array.length-1] = array[array.length-2];
                            array[array.length-2] = swap;
                        }
                    }

                    //break out of the loop
                    i = array.length;
                }
            }

        }

        //if none_null, just sort
        if(none_null){
            int partition_i = partition3(array, left, right);
            quickSort3(array, left, partition_i - 1);
            quickSort3(array, partition_i + 1, right);
        }

        }
    }

    public int partition3(Creature array[], int left, int right){

        int i = (left-1); // index of smaller element

        //if [i] is greater than [right], swap them, and make [i] the pivot

        if(array[i].getName().compareTo(array[right].getName())>0){
            Creature temp = array[i];
            array[i] = array[right];
            array[right] = temp;
        }

        Creature pivot = array[right];

        for (int j=left; j<=right; j++) // changing it to ;j<=right; doesn't seem to mess with the program
        {
            // If current element is smaller than or
            // equal to pivot
            if (array[j].getName().compareTo(pivot.getName())<0)
            {
                //i++;

                // swap array[i] and array[j]
                Creature temp = array[i];
                array[i] = array[j];
                array[j] = temp;

                i++;

            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        Creature temp = array[i+1];
        array[i+1] = array[right];
        array[right] = temp;

        return i+1;

    }

    //this method is unfinished
    public int binarySearch(Creature array[], Creature crt){

        int i;
        int n = array.length;
        int lower_bound = 0;
        int upper_bound = n-1;
        int remainder;
        boolean found = false;
        boolean here = true;

        do{
            //check if upper_bound = 2k or 2k-1, and set i accordingly
            remainder = upper_bound % 2;
            if(remainder == 0){
                i = lower_bound + ((upper_bound - lower_bound)/2);
            } else {
                i = lower_bound + ((upper_bound - lower_bound + 1)/2);
            }

            //first check how small the gap is


            if(upper_bound - lower_bound == 2){

            }

            //if there is no gap, just compare?
            if(upper_bound - lower_bound == 1){

            }

            //if both ifs fail, binary search



                //check if < or > or =
            if(array[i].getName().compareTo(crt.getName())<0){
                lower_bound = i;
            }
            if(array[i].getName().compareTo(crt.getName())>0){
                upper_bound = i;
            }
            if(array[i].getName().compareTo(crt.getName())==0){
                found = true;
            }

        }while(!found && here);

        //if !here, i = -1
        return i;
    }

    public int binarySearch2(Creature array[], int lower_bound, int upper_bound, Creature crt){

        //if lower bound is null, push lower bound up to first non null.
        if (creatures[lower_bound] == null) {
            for (int i = 0; i < upper_bound; i++) {
                if (creatures[i] != null) {
                    lower_bound = i;
                    i = upper_bound;
                }
            }
        }

        if (upper_bound >= lower_bound) {
            int mid = lower_bound + (upper_bound - lower_bound) / 2;

            // If the element is present at the
            // middle itself
            if (array[mid] == crt)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left subarray
            if (array[mid].getName().compareTo(crt.getName())>0)
                return binarySearch2(array, lower_bound, mid - 1, crt);

            // Else the element can only be present
            // in right subarray
            return binarySearch2(array, mid + 1, upper_bound, crt);
        }

        // We reach here when element is not present
        // in array
        return -1;
    }

    public int binarySearchByName2(Creature array[], int lower_bound, int upper_bound, String str) {

        //if lower bound is null, push lower bound up to first non null.
        if (creatures[lower_bound] == null) {
            for (int i = 0; i < upper_bound; i++) {
                if (creatures[i] != null) {
                    lower_bound = i;
                    i = upper_bound;
                }
            }
        }

            //if some not null, binary search
            if (upper_bound >= lower_bound) {

                int mid = lower_bound + (upper_bound - lower_bound) / 2;

                // If the element is present at the
                // middle itself
                if (array[mid].getName().equals(str)){
                    return mid;
                }

                // If element is smaller than mid, then
                // it can only be present in left subarray
                if (array[mid].getName().compareTo(str) > 0)
                    return binarySearchByName2(array, lower_bound, mid - 1, str);

                // Else the element can only be present
                // in right subarray
                return binarySearchByName2(array, mid + 1, upper_bound, str);
            }

        // We reach here when element is not present
        // in array
        return -1;
    }


}
