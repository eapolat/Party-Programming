import java.util.Arrays;

import java.util.Scanner;

public class Friends {

    static String [] names = {"Ela","Eren","Alona","Jen","Mark","Mel","Ender"};

    static int [] arrival_times = {9,5,8,7,9,7,8};

    static int [] departure_times = {12,7,11,12,10,9,10};

    public static void main(String[] args) {
    
    Scanner myscan = new Scanner(System.in);

    System.out.print("Enter time interval you are available: ");

    String input = myscan.nextLine();

    String [] numbers = input.split(" ");

    int start = Integer.valueOf(numbers[0]);
    int end = Integer.valueOf(numbers[1]);

    int [] result_array = new int [10];

    result_array = friendsAttending(start, end);

    int time = start;
    int max_friends = 1;
    int max_friends_time = 0;

    System.out.println();

    for (int i = 0; i < (end - start) ; i++) {

        System.out.println("At " + time + "pm, " + result_array[i] + " friends will be at the party");
    
        if (result_array[i] > max_friends) {

            max_friends = result_array[i];
            max_friends_time = time;
        }

        time ++;

    }
    System.out.println("Best time to attend is " + max_friends_time);

    System.out.println();

    System.out.print("Enter friend you wish to avoid: ");

    String friendto_avoid = myscan.nextLine();

    System.out.print("Enter time interval you are available: ");

    String input_2 = myscan.nextLine();

    String [] numbers_2 = input.split(" ");

    int start_2 = Integer.valueOf(numbers[0]);
    int end_2 = Integer.valueOf(numbers[1]);

    int result = bestTimeToAttend_2(start_2, end_2, friendto_avoid);

    System.out.println("Best time to attend to avoid " + friendto_avoid + " and to see the most friends is " + result);

    myscan.close();
}


public static int [] friendsAttending(int start, int end) {

int [] counts = new int [10];

int count;
int index= 0;

for (int i = start; i < end ; i++) {

    count = 0;

    for (int j = 0; j < names.length ; j++) {

        if (i >= arrival_times[j]) {

            if ( i < departure_times[j]) {

                count++;
                
            }
        }
    }

    counts[index] = count;
    index++;

}

return counts;

}

public static int bestTimeToAttend(int start, int end, String friendto_avoid) {

    int index_of_friendto_avoid = 0;

    for(int i = 0; i < names.length; i++) {

        if (friendto_avoid.equals(names[i])) {

            index_of_friendto_avoid = i;

        }
    }

    int time = start;
    int max_friends = 1;
    int max_friends_time = 0;
    int [] result_array_2 = friendsAttending(start, end);    


    for (int i = 0; i < (end - start) ; i++) {


        if (result_array_2[i] > max_friends) {

            max_friends = result_array_2[i];
            max_friends_time = i+start;
        }

        time ++;

    }

    boolean test_arrive = (max_friends_time >= arrival_times[index_of_friendto_avoid]);
    boolean test_departure = (max_friends_time < departure_times[index_of_friendto_avoid]);

    int control = max_friends_time;

    while (test_arrive && test_departure) {

        int temp_max_friends = 0;

        for (int i = 0; i < (end - start) ; i++) {
    
            if (result_array_2[i] < max_friends) {

                for (int j=0; j<(end-start); j++) {
                
                    if ((result_array_2[i] > result_array_2[j]) && (result_array_2[i] > temp_max_friends) && (result_array_2[i] != 0)) {

                        temp_max_friends = result_array_2[i];

                        max_friends_time = i + start;

                    }

                }    
        }

    }
    if (control == max_friends_time) {

        return -1;
    }
    test_arrive = (max_friends_time >= arrival_times[index_of_friendto_avoid]);
    test_departure = (max_friends_time < departure_times[index_of_friendto_avoid]);

}
return max_friends_time;

}

public static int bestTimeToAttend_2(int start, int end, String friendToAvoid) {
    int indexToAvoid = -1;
    for (int i = 0; i < names.length; i++) {
        if (names[i].equals(friendToAvoid)) {
            indexToAvoid = i;
        }
    }

    int bestTime = -1;
    int maxFriends = 0;

    for (int time = start; time < end; time++) {
        int numFriends = 0;
        for (int i = 0; i < names.length; i++) {
            if (i == indexToAvoid) {
                continue;
            }

            if (time >= arrival_times[i] && time < departure_times[i]) {
            numFriends++;
            }
        }
        if (numFriends > maxFriends) {
            maxFriends = numFriends;
            bestTime = time;
        }
    }

    if (maxFriends > 0) {
        return bestTime;
    } else {
        return -1;
    }
}
}
