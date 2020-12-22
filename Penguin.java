package com.company;

class Main {
    public static void main(String[] args) {
        System.out.println("Anzahl Pinguine:");
        int pinguine = fragePinguine();
        while (pinguine < 2) {
            System.out.println("Anzahl Pinguine soll >1 sein:");
            pinguine = fragePinguine();
        }

        System.out.println("Anfangsfische pro Pinguin:");
        int fische = frageFische();
        while (fische < 1) {
            System.out.println("Anfangsfische soll >0 sein:");
            fische = frageFische();
        }

        int[] p = new int[pinguine];
        boolean[] playerquit = new boolean[pinguine];

        int x;
        for (x = 0; x < p.length; x++) {
            p[x] = fische;
            playerquit[x] = false;
        }

        int playingpinguins = pinguine;

        int[] felder = new int[12];
        int z;
        for (z = 0; z < 12; z++) {
            felder[z] = 0;
            //Feld 0 und 1 werden ignoriert, da sie nicht benutzt werden
        }

        int i;
        for (i = 0; i < p.length; i++) {
            if (!playerquit[i]) {
                maleSpielfeld(felder[3], felder[4], felder[5], felder[6], felder[7], felder[8], felder[9], felder[10], felder[11]);
                System.out.println("Pinguin " + i + " ist dran:");
                if (playingpinguins == 1) {
                    System.out.println("Sie sind der letzte mitspielende Pinguin! Sie gewinnen alle Fische auf dem Brett!");
                    int n;
                    int counter = 0;
                    for (n = 3; n < 12; n++)
                        if (n != 7) {
                            if (felder[n] == 1) {
                                counter = counter + 1;
                                felder[n] = 0;
                            }
                        } else {
                            counter = counter + felder[7];
                            felder[7] = 0;
                        }
                    p[i] = p[i] + counter;


                    int y;
                    int highscore = 0;
                    for (y = 0; y < p.length; y++) {
                        if (p[y] > highscore) {
                            highscore = p[y];
                        }
                    }
                    System.out.println("Die Gewinnerpinguine mit " + highscore + " Fischen:");
                    int yy;
                    for (yy = 0; yy < p.length; yy++) {
                        if (p[yy] == highscore) {
                            System.out.println("Pinguin " + yy);
                        }
                    }
                } else {
                    int w1 = wuerfeln();
                    int w2 = wuerfeln();
                    int sum = w1 + w2;

                    System.out.println(w1 + " + " + w2 + " = " + sum + " wurde gewürfelt.");

                    if (sum == 2) {
                        int n;
                        for (n = 3; n < 12; n++)
                            if (n != 7) {
                                if (felder[n] == 1) {
                                    felder[n] = 0;
                                    p[i] = p[i] + 1;
                                }
                            }
                        System.out.println("Glückspinguin! Sie gewinnen alle Fische auf dem Brett außer von F7!");
                    } else if (sum == 7) {
                        felder[7] = felder[7] + 1;
                        p[i] = p[i] - 1;
                        System.out.println("Hochzeit! Sie schenken einen Fisch und legen ihn auf F7.");
                    } else if (sum == 12) {
                        int n;
                        int counter = 0;
                        for (n = 3; n < 12; n++)
                            if (n != 7) {
                                if (felder[n] == 1) {
                                    counter = counter + 1;
                                    felder[n] = 0;
                                }
                            } else {
                                counter = counter + felder[7];
                                felder[7] = 0;
                            }
                        p[i] = p[i] + counter;
                        System.out.println("Königspinguin! Sie gewinnen alle Fische auf dem Brett!");
                    } else {
                        if (felder[sum] == 0) {
                            felder[sum] = 1;
                            p[i] = p[i] - 1;
                            System.out.println("Sie legen einen Fisch auf F" + sum + ".");
                        } else if (felder[sum] > 0) {
                            felder[sum] = 0;
                            p[i] = p[i] + 1;
                            System.out.println("Sie nehmen den Fisch von F" + sum + ".");
                        }
                    }
                    if (p[i] == 1) {
                        System.out.println("Sie haben jetzt 1 Fisch!");
                    } else {
                        System.out.println("Sie haben jetzt " + p[i] + " Fische!");
                    }
                    if (p[i] == 0) {
                        System.out.println("Sie haben alle Fische verloren, daher können Sie nicht mehr spielen!");
                        playerquit[i] = true;
                        playingpinguins = playingpinguins - 1;
                    } else {
                        int or = readInt("Geben Sie 1 ein, um jetzt das Spiel zu verlassen:");
                        if (or == 1) {
                            playerquit[i] = true;
                            playingpinguins = playingpinguins - 1;
                        }
                    }
                }
            }
        }
        if (i + 1 == p.length) {
            i = -1;

        }
    }

    private static void maleSpielfeld(int feld3, int feld4, int feld5, int feld6, int feld7, int feld8, int feld9, int feld10, int feld11) {
        System.out.println("┌─── o  o  o ───┐");
        System.out.println("│    │╲╱ ╲╱│    │");
        System.out.println("│    │_F12_│    |");
        System.out.println("├────┬─────┬────┤");
        System.out.println("│ F9 │ F10 │ F11│");
        System.out.println("│  " + feld9 + " │  " + feld10 + "  │  " + feld11 + " │");
        System.out.println("├────┼─────┼────┤");
        System.out.println("│ F6 │ F7  │ F8 │");
        System.out.println("│  " + feld6 + " │  " + feld7 + "  │  " + feld8 + " │");
        System.out.println("├────┼─────┼────┤");
        System.out.println("│ F3 │ F4  │ F5 │");
        System.out.println("│  " + feld3 + " │  " + feld4 + "  │  " + feld5 + " │");
        System.out.println("├────┴─────┴────┤");
        System.out.println("│ ('>       <') │");
        System.out.println("│ ╱/╲  F2   ╱\\╲ │");
        System.out.println("├─V_╱─┐   ┌─╲_V─┤");
        System.out.println("└─────┴───┴─────|");
    }

    private static int fragePinguine() {
        int pinguine = readInt("Anzahl Pinguine:");
        System.out.println(pinguine);
        return pinguine;
    }

    private static int frageFische() {
        int fische = readInt("Anfangsfische pro Pinguin:");
        System.out.println(fische);
        return fische;
    }

    private static int wuerfeln() {
        return dice();
    }
}