package com.notepad;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.notepad.ui.MenuBar;
import com.notepad.ui.NotepadFrame;

import javafx.scene.layout.Border;

public class NotepadApp{
    public static void main(String[] args) {
        JFrame frame = new NotepadFrame();
        frame.setTitle("Notepad");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }
}