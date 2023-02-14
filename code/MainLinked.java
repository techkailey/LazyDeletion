/**
* @author Kailey Bergeron 
* @version 11.0.8
* @since September 26th, 2022
* Main class for LinkedTester
*/

import java.util.Scanner;

public class MainLinked {


    public static void main(String[] args) {

        MyLinkedList<String> myList = new MyLinkedList<String>();

        myList.add("Rick");
        myList.add("Morty");
        myList.add("Pickle Rick");
        myList.add("Jerry");
        myList.add("Summer");

        //for (int i=0; i<100; i++) 
        //    myList.add("Mr. Meeseeks number " + i);
        
        myList.add("Beth");

        for (int i=0; i<myList.size(); i++) {
            System.out.println(i + " : " +myList.get(i));
        }

        System.out.println("Adding Birdperson at index 4");
        myList.add("Birdperson",4);
        System.out.println("New state of the list:");

        for (int i=0; i<myList.size(); i++) {
            System.out.println(i + " : " + myList.get(i));
        } 
        System.out.println("Deleting " + myList.lazyRemove(2));
        System.out.println("New state of the list:");

        for (int i=0; i<myList.size(); i++) {
            System.out.println(i + " : " + myList.get(i));
        }
        System.out.println("Deleting " + myList.lazyRemove(1));
        System.out.println("New state of the list:");

        for (int i=0; i<myList.size(); i++) {
            System.out.println(i + " : " + myList.get(i));
        } 
        System.out.println("Adding Jessica at index 1");
        myList.add("Jessica",1);
        System.out.println("New state of the list:");

        for (int i=0; i<myList.size(); i++) {
            System.out.println(i + " : " + myList.get(i));
        }

        System.out.println("Deleting " + myList.lazyRemove(3));
        System.out.println("New state of the list:");

        for (int i=0; i<myList.size(); i++) {
            System.out.println(i + " : " + myList.get(i));
        }
        
        // MyLinkedList<Scanner> anotherList = new MyLinkedList<Scanner>();
        // this should not work:
        // anotherList.add("Joe Blow");


        // in general with an iterator, you want this sort of
        // construction to work

        System.out.println("Using an iterator:");

        MyLinkedList.MyIterator iter = myList.iterator();         

        while (iter.hasNext())
            System.out.println(iter.next() );

    }
}
