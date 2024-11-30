package com.notepad.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.notepad.settings.PreferencesManager;

public class PreferencesDialog extends JDialog{
    JTextField fontSizeField;
    JComboBox<String> themeComboBox;
    private PreferencesManager preferencesManager;
    private EditorPanel editorPanel;

    public PreferencesDialog(EditorPanel editorPanel){
        this.setTitle("Preferences");
        this.setLayout(new BorderLayout());
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.editorPanel = editorPanel;
        this.preferencesManager = PreferencesManager.getInstance();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(new JLabel("Font Size"));
        fontSizeField = new JTextField();
        fontSizeField.setText(String.valueOf(this.preferencesManager.getFontSize()));
        panel.add(fontSizeField);

        panel.add(new JLabel("Theme"));
        themeComboBox = new JComboBox<>(new String[] {"Light", "Dark"});
        themeComboBox.setSelectedItem(this.preferencesManager.getTheme());
        panel.add(themeComboBox);

        JPanel buttonPanel = new JPanel();
        JButton applyButton = new JButton("Apply");
        buttonPanel.add(applyButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                applyPreferences();
            }
        });

    }

    public void applyPreferences(){
        int fontSize = Integer.parseInt(this.fontSizeField.getText());
        String theme = this.themeComboBox.getSelectedItem().toString();

        preferencesManager.setFontSize(fontSize);
        preferencesManager.setTheme(theme);

        this.editorPanel.updatePreferences();

        dispose();
    }
}
