package utils;

import javax.swing.*;

public class JOptionUtils {
    public static void message(String texto){
        JOptionPane.showMessageDialog(null, texto);
    }
    public static String input(String texto){
        return JOptionPane.showInputDialog(texto);
    }

    public static int dialogInt(String texto) {
        return Integer.parseInt(JOptionPane.showInputDialog(texto));
    }
}
