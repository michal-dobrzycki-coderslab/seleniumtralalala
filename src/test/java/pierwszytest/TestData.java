package pierwszytest;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestData {
    static List<String> userName = Arrays.asList("Ola", "Ala", "Ania", "Iga", "Zosia", "Maja", "Lena", "Nela", "Kasia", "Hania");


    public static String getRandomName(){
        Random random = new Random();
        int userInteger = random.nextInt(100);
        int usersNum = userInteger % userName.size();

        return userName.get(usersNum);
    }
}
