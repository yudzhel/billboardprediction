package com.yudjel;

/*

*/

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here

        List<SongData> songs = new ArrayList<>();
        Prediction prediction1 = new Prediction(songs);

        prediction1.menu();
    }
}
