/*
 * 
 */
package zaidimasakmuozirklespopierius;

import java.util.Scanner;

/**
 *
 * @author Rokas
 */
public class ZaidimasAkmuoZirklesPopierius {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//        Laimėtojas nustatomas pagal žaidimo taisykles:
//
//        Akmuo laimi prieš žirkles (akmuo bukina ar laužo žirkles)
//        Žirklės laimi prieš popierių (žirklės karpo popierių)
//        Popierius laimi prieš akmenį (popierius uždengią akmenį)
//        Jei žaidėjai parodo vienodus ženklus, tai laikoma lygiosiomis ir žaidžiama iš naujo.
//      
        
        String[] pasirinkimas = {"", "Akmuo", "Zirkles", "Popierius"};

        int zaidimoStilius;
        int partijuSkaicius, zaidimuSkaicius; // partijuSkaicius - pasirenkamas dydis, zaidimuSkaicius - skaiciuojamas ivyusiu zaidimu skaicius iki naujo etapo

        Scanner sc = new Scanner(System.in);

        
        // renkames zadimo buda: user VS compiuter, compiuter VS compiuter
        do {
            int player1, player2;
            String player1Name = "player1"; // default

            // {lygiosios, laimejoPlayer1, laimejoPlayer2}
            int[] zaidejoLaimejimuSkaicius = {0,0,0};
            
            // {            {player1}               ,           {player2}                }
            // {{sumAkmuo, sumZirkles, sumPopierius},{sumAkmuo, sumZirkles, sumPopierius}}
            // kiek kartu buvo pasirinktas
            int[][] zenkluPasirinkimoSkaicius = {{0,0,0},{0,0,0}};
            // kiek kartu laimejo
            int[][] zenkluZaidejuLaimejimuSkaicius = {{0,0,0},{0,0,0}};

            partijuSkaicius = 0;
            zaidimuSkaicius = 0;
            
            // ar rinksimes zenkla skaiciusime laimetoja
            int arSkaiciuosime = 0; // 0 - ne; 1 - taip

            System.out.println("\nPasirinkite zaidimo stiliu:");
            System.out.println("1 - pries kompiuteri \n2 - kompiuteris1 pries kompiuteri2 \n0 - Iseiti");
            zaidimoStilius = sc.nextInt();

            if (zaidimoStilius == 1 || zaidimoStilius == 2) {

                if (zaidimoStilius == 1) {

                    int vardoIvedimuKiekis = 0;

                    do {

                        System.out.print("\n > ivesti varda (iki 10 simboliu): ");

                        if (vardoIvedimuKiekis == 0) {
                            sc.nextLine(); // butina, kad veiktu nextLine() po nextInt(), nes po nextInt() ivedimo, kursorius buna uz ivesto skaiciaus ('2_'), todel nextLine() bandydamas skaityti dali po skaiciaus mato '\n' ir taip pasiima '\n'
                            vardoIvedimuKiekis = 1;
                        }

                        player1Name = sc.nextLine();

                        System.out.println("ivestas vardas: " + player1Name);

                    } while (player1Name.length() > 10);

                } else {
                    System.out.println("Kiek partiju zais kompiuteris1 VS kompiuteris2 ?");
                    partijuSkaicius = sc.nextInt();
                    
                    if (partijuSkaicius > 0) {
                        arSkaiciuosime = 1;
                    } else {
                        arSkaiciuosime = 0;
                    }
                    
                }

                
                // atliekami pasirinkimai
                do {

                    String laimetojas = "";

                    if (zaidimoStilius == 1) {
                        System.out.println("\nPasirinkite zenkla: | 1 - Akmuo | 2 - Zirkles | 3 - popierius | 0 - Pabaiga |");
                        // zmogus
                        player1 = sc.nextInt();
                        
                        if (player1 == 0) {
                            arSkaiciuosime = 0;
                        } else {
                            arSkaiciuosime = 1;
                        }
                        
                    } else {
                        // kompiuteris kaip player1
                        player1 = (int) (Math.random() * 3 + 1);
                        
                    }

                    if (player1 < 4) {

                        if (arSkaiciuosime != 0) {
                            
                            player2 = (int) (Math.random() * 3 + 1); // kompo pasirinkimas

                            zenkluPasirinkimoSkaicius[0][player1 - 1]++;
                            zenkluPasirinkimoSkaicius[1][player2 - 1]++;
                            
                            if (zaidimuSkaicius == 0 || partijuSkaicius == 0) {
                                System.out.printf("\n|%5s |%12s |%12s |%12s |\n", "Nr", player1Name, "player2", "laimetojas");
                            }
                            
                            if (player1 == player2) {
                                laimetojas = "lygiosios";
                                zaidejoLaimejimuSkaicius[0]++;
                            } else if (player1 - player2 == -1 || player1 - player2 == 2) {
                                laimetojas = player1Name;
                                zaidejoLaimejimuSkaicius[1]++;
                                zenkluZaidejuLaimejimuSkaicius[0][player1 - 1]++;
                            } else {
                                laimetojas = "player2";
                                zaidejoLaimejimuSkaicius[2]++;
                                zenkluZaidejuLaimejimuSkaicius[1][player2 - 1]++;
                            }
                            System.out.printf("|%5s |%12s |%12s |%12s |\n", "#" + zaidimuSkaicius, pasirinkimas[player1], pasirinkimas[player2], laimetojas);

                            zaidimuSkaicius++;
                        }

                    } else {
                        System.out.println(">> Pakartokite savo pasirinkima, galimas intervalas: [0;3] !!! <<");
                    }

                    if (zaidimoStilius == 2 && partijuSkaicius == zaidimuSkaicius) {
                        arSkaiciuosime = 0;
                    }
                    
                    
                } while ( arSkaiciuosime != 0);


                System.out.println("\n>-- STATISTIKA --<");
                System.out.printf("|%10s |%20s |%15s |%20s |%20s |%10s |%18s |\n", "ZaidSkaic", "win " + player1Name, "win player2", "win " + player1Name + " (%/100)", "win player2 (%/100)", "lygiosios", "lygiosios (%/100)");
                System.out.printf("|%10s |%20s |%15s |%17s |%17s |%10s |%15s |\n", zaidimuSkaicius, zaidejoLaimejimuSkaicius[1], zaidejoLaimejimuSkaicius[2], String.format("%.2f", zaidejoLaimejimuSkaicius[1]/(float)zaidimuSkaicius), String.format("%.2f",zaidejoLaimejimuSkaicius[2]/(float)zaidimuSkaicius), zaidejoLaimejimuSkaicius[0], String.format("%.2f",zaidejoLaimejimuSkaicius[0]/(float)zaidimuSkaicius));

                System.out.println("\n-< Zenklu pasirinkimo kiekis >-");
                System.out.printf("|%34s |%34s |%34s |\n", "Bendras", player1Name, "player2");
                System.out.printf("|%8s |%10s |%12s |%8s |%10s |%12s |%8s |%10s |%12s |\n", "akmuo", "zirkles", "popierius", "akmuo1", "zirkles1", "popierius1", "akmuo2", "zirkles2", "popierius2");
                System.out.printf("|%8s |%10s |%12s |%8s |%10s |%12s |%8s |%10s |%12s |\n", zenkluPasirinkimoSkaicius[0][0] + zenkluPasirinkimoSkaicius[1][0], zenkluPasirinkimoSkaicius[0][1] + zenkluPasirinkimoSkaicius[1][1], zenkluPasirinkimoSkaicius[0][2] + zenkluPasirinkimoSkaicius[1][2], zenkluPasirinkimoSkaicius[0][0], zenkluPasirinkimoSkaicius[0][1], zenkluPasirinkimoSkaicius[0][2], zenkluPasirinkimoSkaicius[1][0], zenkluPasirinkimoSkaicius[1][1], zenkluPasirinkimoSkaicius[1][2]);

                System.out.println("\n-< Zenklu pasirinkimo kiekis (%/100), pagal zaidimu kieki >-");
                System.out.printf("|%34s |%34s |%34s |\n", "Bendras", player1Name, "player2");
                System.out.printf("|%8s |%10s |%12s |%8s |%10s |%12s |%8s |%10s |%12s |\n", "akmuo", "zirkles", "popierius", "akmuo1", "zirkles1", "popierius1", "akmuo2", "zirkles2", "popierius2");
                System.out.printf("|%8s |%10s |%12s |%8s |%10s |%12s |%8s |%10s |%12s |\n", "-", "-", "-", String.format("%.2f", zenkluPasirinkimoSkaicius[0][0]/(float)zaidimuSkaicius), String.format("%.2f", zenkluPasirinkimoSkaicius[0][1]/(float)zaidimuSkaicius), String.format("%.2f", zenkluPasirinkimoSkaicius[0][2]/(float)zaidimuSkaicius), String.format("%.2f", zenkluPasirinkimoSkaicius[1][0]/(float)zaidimuSkaicius), String.format("%.2f", zenkluPasirinkimoSkaicius[1][1]/(float)zaidimuSkaicius), String.format("%.2f", zenkluPasirinkimoSkaicius[1][2]/(float)zaidimuSkaicius) );
                
                System.out.println("\n-< Kiek kartu laimejo zenklas >-");
                System.out.printf("|%34s |%34s |%34s |\n", "Bendras", player1Name, "player2");
                System.out.printf("|%8s |%10s |%12s |%8s |%10s |%12s |%8s |%10s |%12s |\n", "akmuo", "zirkles", "popierius", "akmuo1", "zirkles1", "popierius1", "akmuo2", "zirkles2", "popierius2");
                System.out.printf("|%8s |%10s |%12s |%8s |%10s |%12s |%8s |%10s |%12s |\n", zenkluZaidejuLaimejimuSkaicius[0][0] + zenkluZaidejuLaimejimuSkaicius[1][0], zenkluZaidejuLaimejimuSkaicius[0][1] + zenkluZaidejuLaimejimuSkaicius[1][1], zenkluZaidejuLaimejimuSkaicius[0][2] + zenkluZaidejuLaimejimuSkaicius[1][2], zenkluZaidejuLaimejimuSkaicius[0][0], zenkluZaidejuLaimejimuSkaicius[0][1], zenkluZaidejuLaimejimuSkaicius[0][2], zenkluZaidejuLaimejimuSkaicius[1][0], zenkluZaidejuLaimejimuSkaicius[1][1], zenkluZaidejuLaimejimuSkaicius[1][2]);

                System.out.println("\n-< Kiek kartu laikmejo zenklas (%/100), pagal zaidimu kieki >-");
                System.out.printf("|%34s |%34s |%34s |\n", "Bendras", player1Name, "player2");
                System.out.printf("|%8s |%10s |%12s |%8s |%10s |%12s |%8s |%10s |%12s |\n", "akmuo", "zirkles", "popierius", "akmuo1", "zirkles1", "popierius1", "akmuo2", "zirkles2", "popierius2");
                System.out.printf("|%8s |%10s |%12s |%8s |%10s |%12s |%8s |%10s |%12s |\n", String.format("%.2f", (zenkluZaidejuLaimejimuSkaicius[0][0] + zenkluZaidejuLaimejimuSkaicius[1][0])/(float)zaidimuSkaicius), String.format("%.2f", (zenkluZaidejuLaimejimuSkaicius[0][1] + zenkluZaidejuLaimejimuSkaicius[1][1])/(float)zaidimuSkaicius), String.format("%.2f", (zenkluZaidejuLaimejimuSkaicius[0][2] + zenkluZaidejuLaimejimuSkaicius[1][2])/(float)zaidimuSkaicius), String.format("%.2f", zenkluZaidejuLaimejimuSkaicius[0][0]/(float)zaidimuSkaicius), String.format("%.2f", zenkluZaidejuLaimejimuSkaicius[0][1]/(float)zaidimuSkaicius), String.format("%.2f", zenkluZaidejuLaimejimuSkaicius[0][2]/(float)zaidimuSkaicius), String.format("%.2f", zenkluZaidejuLaimejimuSkaicius[1][0]/(float)zaidimuSkaicius), String.format("%.2f", zenkluZaidejuLaimejimuSkaicius[1][1]/(float)zaidimuSkaicius), String.format("%.2f", zenkluZaidejuLaimejimuSkaicius[1][2]/(float)zaidimuSkaicius));
                
                System.out.println("\n-< Kiek kartu laikmejo zenklas (%/100), pagal zenklo pasirinkimo kieki zaidejui atskirai >-");
                System.out.printf("|%34s |%34s |%34s |\n", "Bendras", player1Name, "player2");
                System.out.printf("|%8s |%10s |%12s |%8s |%10s |%12s |%8s |%10s |%12s |\n", "akmuo", "zirkles", "popierius", "akmuo1", "zirkles1", "popierius1", "akmuo2", "zirkles2", "popierius2");
                System.out.printf("|%8s |%10s |%12s |%8s |%10s |%12s |%8s |%10s |%12s |\n", String.format("%.2f", (zenkluZaidejuLaimejimuSkaicius[0][0] + zenkluZaidejuLaimejimuSkaicius[1][0])/(float)(zenkluPasirinkimoSkaicius[0][0] + zenkluPasirinkimoSkaicius[1][0])), String.format("%.2f", (zenkluZaidejuLaimejimuSkaicius[0][1] + zenkluZaidejuLaimejimuSkaicius[1][1])/(float)(zenkluPasirinkimoSkaicius[0][1] + zenkluPasirinkimoSkaicius[1][1])), String.format("%.2f", (zenkluZaidejuLaimejimuSkaicius[0][2] + zenkluZaidejuLaimejimuSkaicius[1][2])/(float)(zenkluPasirinkimoSkaicius[0][2] + zenkluPasirinkimoSkaicius[1][2])), String.format("%.2f", zenkluZaidejuLaimejimuSkaicius[0][0]/(float)zenkluPasirinkimoSkaicius[0][0]), String.format("%.2f", zenkluZaidejuLaimejimuSkaicius[0][1]/(float)zenkluPasirinkimoSkaicius[0][1]), String.format("%.2f", zenkluZaidejuLaimejimuSkaicius[0][2]/(float)zenkluPasirinkimoSkaicius[0][2]), String.format("%.2f", zenkluZaidejuLaimejimuSkaicius[1][0]/(float)zenkluPasirinkimoSkaicius[1][0]), String.format("%.2f", zenkluZaidejuLaimejimuSkaicius[1][1]/(float)zenkluPasirinkimoSkaicius[1][1]), String.format("%.2f", zenkluZaidejuLaimejimuSkaicius[1][2]/(float)zenkluPasirinkimoSkaicius[1][2]));
                
            }

            
            if (zaidimoStilius > 2) {
                System.out.println(">> Pakartokite savo pasirinkima, galimas intervalas: [0;2] !!! <<");
            }

            
        } while (zaidimoStilius != 0);

        System.out.println("Zaidimo pabaiga...");

    }

}
