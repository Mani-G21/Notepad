package com.notepad.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.notepad.settings.PreferencesManager;

public class EditorPanel extends JPanel{

    private JTextArea textArea;
    private PreferencesManager preferencesManager;

    public EditorPanel(){
        this.preferencesManager = PreferencesManager.getInstance();
        setLayout(new BorderLayout());
        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        updatePreferences();
    }

    public JTextArea getTextArea(){
        return this.textArea;
    }

    public void updatePreferences(){
        int fontSize = this.preferencesManager.getFontSize();
        String theme = this.preferencesManager.getTheme();

        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, fontSize));

        if("Dark".equals(theme)){
            textArea.setBackground(Color.BLACK);
            textArea.setForeground(Color.WHITE);
        }
        else{
            textArea.setBackground(Color.WHITE);
            textArea.setForeground(Color.BLACK);
        }
    }
}
