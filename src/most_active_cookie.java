import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class most_active_cookie {
    public static void processCookies(ArrayList<String> cookieLogs, String targetDate){
        if(cookieLogs.isEmpty()){
            System.out.println("NO COOKIE WERE PROVIDED. PLEASE CHECK INPUT.");
            return;
        }
        //Map to store all the cookies obtained in a specific date
        HashMap<String, ArrayList<String>> cookieDateMap = new HashMap<>();

        //Store all cookies with their associated date
        for(String s : cookieLogs){
            String cookie = s.substring(0,s.indexOf(','));
            String date = s.substring(s.indexOf(',')+1,s.indexOf(':')-3);

            if (cookieDateMap.get(date) == null) {
                cookieDateMap.put(date, new ArrayList<String>());
            }
                cookieDateMap.get(date).add(cookie);
        }

        //Map to store how active a cookie was on a given date
        HashMap<String, Integer> cookieFrequencyMap = new HashMap<>();
        int maxFrequency = 0;

        if(!cookieDateMap.containsKey(targetDate)){
            System.out.println("NO COOKIES AVAILABLE FOR THIS DATE. CHECK INPUT");
            return;
        }

        //Add each cookie with a max frequency to array list
        ArrayList<String> mostFrequentCookies = new ArrayList<>();
        for(String cookie : cookieDateMap.get(targetDate)){
            cookieFrequencyMap.put(cookie, cookieFrequencyMap.getOrDefault(cookie,0)+1);

            int currentFrequency = cookieFrequencyMap.get(cookie);

            if(currentFrequency > maxFrequency){
                maxFrequency = currentFrequency;
                mostFrequentCookies.clear();
                mostFrequentCookies.add(cookie);
            } else if(currentFrequency == maxFrequency){
                mostFrequentCookies.add(cookie);
            }
        }

        //Print most active cookie(s)
        for(String cookie : mostFrequentCookies){
            System.out.println(cookie);
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 3) {
            throw new IllegalArgumentException("INVALID ARUGMENTS");
        }

        Scanner input = new Scanner(new File(args[0]));
        String dateTarget = new String(args[2]);

	    ArrayList<String> cookieLogs = new ArrayList<>();
        while(input.hasNextLine()){
            String inpt = input.nextLine();
            if(!inpt.equals("cookie,timestamp")){
                cookieLogs.add(inpt);
            }
        }
        processCookies(cookieLogs, dateTarget);
    }
}