package com.notepad.ui;

import java.awt.BorderLayout;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class NotepadFrame extends JFrame{
    private EditorPanel editorPanel;

    public NotepadFrame(){
        this.editorPanel = new EditorPanel();
        add(editorPanel, BorderLayout.CENTER);

        JMenuBar menuBar = new MenuBar(editorPanel);
        setJMenuBar(menuBar);
    }
}
