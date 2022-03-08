package com.yudjel;

/*

*/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Prediction {

    private final List<SongData> songs;

    public Prediction(List<SongData> songs) {
        this.songs = songs;
    }

    public void addSong(){
        System.out.println("------------------ADD SONG-----------------");

        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Title: ");
            String title = scanner.nextLine();
            System.out.print("Artist: ");
            String artist = scanner.nextLine();
            System.out.print("Pure Sales: ");
            int pureSales = scanner.nextInt();
            System.out.print("Paid Streams: ");
            int paidStreams = scanner.nextInt();
            System.out.print("Free Streams: ");
            int freeStreams = scanner.nextInt();
            System.out.print("Programmed Streams: ");
            int programmedStreams = scanner.nextInt();
            System.out.print("Radio: ");
            int radio = scanner.nextInt();

            if(songs.stream().noneMatch(songData -> (songData.getTitle().equals(title) && songData.getArtist().equals(artist)))){
                songs.add(new SongData(title,artist,pureSales,paidStreams,freeStreams,programmedStreams,radio));
            }else {
                System.out.println("Song already exists!");
            }

        } catch (InputMismatchException ime) {
            System.out.println("Enter valid values!");
        }
    }

    public void viewSongs() {
        System.out.println("----------------SONGS------------------");
        int i = 0;
        for(SongData song: songs){
            System.out.println(i + ". " + song.toString());
            System.out.println();
            i++;
        }
    }

    public void deleteSong(){
        System.out.println("----------------DELETE SONG------------------");
        int i = 0;
        for(SongData song: songs){
            System.out.println(i + ". " + song.toStringArtistAndTitle());
            i++;
        }

        System.out.println("Enter the INDEX of the song you want to DELETE: ");
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();

        if(index >=0 && index <songs.size()){
            songs.remove(index);
            System.out.println("Song deleted!");
        }else {
            System.out.println("Index " + i + " doesn't exist!");
        }
    }

    public void predict(){
        LinkedHashMap<String, Double> predictedSorted = new LinkedHashMap<>(predictionCalculated());

        System.out.print("Enter chart date: ");
        Scanner scanner = new Scanner(System.in);
        String chartDate = scanner.nextLine();

        System.out.println("------------------PREDICTION FOR THE WEEK OF: " + chartDate + " ------------------------");
        System.out.println();

        AtomicInteger i = new AtomicInteger(1);
        predictedSorted.forEach((k, v) -> System.out.println(i.getAndIncrement() + ". " + k + ": " + v + " points."));
    }


    private LinkedHashMap<String, Double> predictionCalculated() {

        HashMap<String, Double> songsCalculated = new HashMap<>();
        LinkedHashMap<String, Double> reverseSorted = new LinkedHashMap<>();


        for(SongData song: songs){
            double points;
            points = (((double) song.getPureSales()) / 500) +
                     (((double) song.getPaidStreams()) / 125_000) +
                     (((double) song.getFreeStreams()) / 187_500) +
                     (((double) song.getProgrammedStreams()) / 250_000) +
                     (((double) song.getRadio()) / 600_000);
            songsCalculated.put(song.getArtist() + " - " + song.getTitle(), round(points));
        }

         songsCalculated.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSorted.put(x.getKey(), x.getValue()));

        return reverseSorted;
    }

    private Double round(double points) {

        DecimalFormat df = new DecimalFormat("###.##");
        return Double.valueOf(df.format(points));
    }

    public void menu() {

        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true){
            System.out.println("--------------------MENU----------------------");
            System.out.println();
            System.out.println("                1. ADD A SONG");
            System.out.println("                2. DELETE A SONG");
            System.out.println("                3. VIEW SONGS");
            System.out.println("                4. MAKE A PREDICTION");
            System.out.println("                5. SAVE TO FILE");
            System.out.println("                6. EXIT");
            System.out.println();
            System.out.print("Enter your choice (1-6): ");

            choice = scanner.nextInt();

            switch(choice){
                case 1: addSong(); break;
                case 2: deleteSong(); break;
                case 3: viewSongs(); break;
                case 4: predict(); break;
                case 5: saveToFile(); break;
                case 6: System.exit(0); break;
                default:
                    System.out.println("Please enter a valid choice!");
                    break;
            }
        }
    }

    public void saveToFile() {

        File file = new File("prediction.txt");
        LinkedHashMap<String, Double> predictedSorted = new LinkedHashMap<>(predictionCalculated());
        System.out.print("Enter chart date: ");
        Scanner scanner = new Scanner(System.in);
        String chartDate = scanner.nextLine();

        try {
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            pw.println("------------------PREDICTION FOR THE WEEK OF: " + chartDate + " ------------------------");
            pw.println();

            AtomicInteger i = new AtomicInteger(1);
            predictedSorted.forEach((k, v) -> pw.println(i.getAndIncrement() + ". " + k + ": " + v + " points."));
            System.out.println("File " + file + " was successfully created!");

            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
