package ThreadExample;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Otomoto {
    public static void main(String[] args) throws Exception {
        Long start = System.currentTimeMillis ( );

        ExecutorService executorService = Executors.newFixedThreadPool (32);
        URL otomoto = new URL ("https://www.otomoto.pl/osobowe/mercedes-benz");
        BufferedReader in = new BufferedReader (
                new InputStreamReader (otomoto.openStream ( )));

        String inputLine;
        StringBuilder stringBuilder = new StringBuilder ( );

        while ((inputLine = in.readLine ( )) != null) {

            stringBuilder.append (inputLine);
            stringBuilder.append (System.lineSeparator ( ));
        }

        in.close ( );
        Set<String> stringSet = new TreeSet<> ( );
        String content = stringBuilder.toString ( );

        for (int i = 0; i < content.length ( ); i++) {
            i = content.indexOf ("https://www.otomoto.pl/oferta/", i);
            if (i < 0) break;
            String substring = content.substring (i);
            String link = substring.split (".html")[0];
            stringSet.add (link);

        }


        for (int i = 0; i < stringSet.size ( ); i++) {
            int finalI = i;
            executorService.submit (()-> {
                        try {
                            readWebsiyte (stringSet.toArray ( )[finalI].toString ( ), finalI + ".html");
                        } catch (IOException e) {
                            e.printStackTrace ( );
                        }
                    }
            );
        }
        executorService.shutdown ();


        stringSet.forEach (System.out::println);
        System.out.println ("Ilosc linkow " + stringSet.size ( ));

        long end = System.currentTimeMillis ( );
        System.out.println ("Time");
        System.out.println (end - start);

    }

    public static void readWebsiyte(String link, String fileName) throws IOException {
        // do odczytu
        URL otomoto = new URL ("https://www.otomoto.pl/osobowe/mercedes-benz");
        BufferedReader in = new BufferedReader (
                new InputStreamReader (otomoto.openStream ( )));

        String inputLine;
        StringBuilder stringBuilder = new StringBuilder ( );

        while ((inputLine = in.readLine ( )) != null) {

            stringBuilder.append (inputLine);
            stringBuilder.append (System.lineSeparator ( ));
        }

        in.close ( );

        //do zapisu
        BufferedWriter bw = new BufferedWriter (new FileWriter (fileName, false));
        bw.write (stringBuilder.toString ( ));
        bw.close ( );

    }


}
