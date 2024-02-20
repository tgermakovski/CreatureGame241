package com.company;
import java.util.Scanner;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Main {
	public static void main(String[] a) throws Exception {

		Scanner scan = new Scanner(System.in);
		System.out.println("enter XML file name");
		String file = scan.nextLine();

		File inputFile = new File("C:\\Users\\tgerm\\MyJavaProjects\\ProjectPantaleev\\src\\com\\company\\" + file); //"C:\\Users\\tgerm\\ProjectPantaleev\\src\\com\\company\\" + file
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		CSC241Handler handler = new CSC241Handler();
		saxParser.parse(inputFile, handler);

		//prompt user to pick a starting room?
		//System.out.println("enter room name");
		//String user_input = scan.nextLine();
		//handler.printRoomViaName(user_input);

		handler.getPC().play(scan);
/*
		for(int i = 0; i < 1; i++){
			System.out.println("enter room name");
			String user_input = scan.nextLine();
			handler.printRoomViaName(user_input);
			System.out.println();
			System.out.println("if you want to input another room name, type in yes");
			System.out.println("if you want exit this cycle and go touch grass, type in no, or some other non-yes nonesense");
			user_input = scan.nextLine();
			if(user_input.equals("yes")){
				i = -1;
			}
		}
		*/

		//search thru rooms[] for room of that name and respond accordingly
		//Room room = new Room(null);
		//room = handler.getRoomViaName(user_input);
		//if(room.getName()!=null) {
			//System.out.println(room.toString());
		//}
	}
}