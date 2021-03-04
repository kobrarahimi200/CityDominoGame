/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * this is logging the file
 *
 * @author Kobra
 */
public class Log {

    FileOutputStream f;
    OutputStreamWriter o;

    public Log(String s) {
        try {
            this.f = new FileOutputStream(s);
            this.o = new OutputStreamWriter(this.f);
        } catch (IOException ex) {

        }
    }

    /**
     * logging all given parameters
     *
     * @param player
     * @param next
     * @param current
     * @param pos
     */
    public void logging(Players player, Choose next, Domino current, Position pos) {
        try {
            String toprint = "";
            toprint += player.getName() + " chose " + next.toString() + " " + player.getName();
            if (pos != null) {
                if (current.getRotation() % 2 == 0) {
                    toprint += " put " + current.toString() + " horizontal to " + pos.toString() + "\n";
                } else {
                    toprint += " put " + current.toString() + " vertically to " + pos.toString() + "\n";
                }

            } else {
                toprint += " did not use " + current.toString() + "\n";
            }

            System.out.println(toprint);
            o.append(toprint);
            o.flush();
        } catch (IOException ex) {

        }
    }

    /**
     * closing the log file
     */
    public void closeLog() {
        try {
            if (f != null) {
                f.close();
            }
            if (o != null) {
                o.close();
            }
        } catch (IOException ex) {

        }

    }
}
