package com.notepad.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FindReplaceDialog extends JDialog{
    private EditorPanel editorPanel;
    private JTextField findTextField;
    private JTextField replaceTextField;

    public FindReplaceDialog(EditorPanel editorPanel, Boolean withReplace){
        this.editorPanel = editorPanel;
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(withReplace ? 2 : 1, 2));

        setTitle(withReplace ? "Find & Replace" : "Find");
        setSize(300, 150);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel findLabel = new JLabel("Find");
        findTextField = new JTextField();

        panel.add(findLabel);
        panel.add(findTextField);

        panel.add(findLabel);
        panel.add(findTextField);

        if(withReplace){
           

            JLabel replaceLabel = new JLabel("Replace");
            replaceTextField = new JTextField();

            panel.add(replaceLabel);
            panel.add(replaceTextField);

        }


        JPanel buttonPanel = new JPanel();
        JButton findButton = new JButton("Find Next");
        buttonPanel.add(findButton);

        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                findNext();
            }
        });

        JButton replaceButton;
        JButton replaceAllButton;

        

        if(withReplace){
            replaceButton = new JButton("Replace");
            replaceAllButton = new JButton("Replace All");

            buttonPanel.add(replaceAllButton);
            buttonPanel.add(replaceButton);

            replaceButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    replace();
                }
            });

            
            replaceAllButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    replaceAll();
                }
            });
            
        }
        

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void findNext(){
        String findText = this.findTextField.getText();
        String text = this.editorPanel.getTextArea().getText();

        int caretPosition = this.editorPanel.getTextArea().getCaretPosition();
        int searchIndex = text.indexOf(findText, caretPosition);

        if(searchIndex != -1){
            this.editorPanel.getTextArea().setSelectionStart(searchIndex);
            this.editorPanel.getTextArea().setSelectionEnd(searchIndex + findText.length());
        }
        else{
            JOptionPane.showMessageDialog(this, "Text not found !");
        }
    }

    public void replace(){
        String findText = this.findTextField.getText();
        String replaceText = this.replaceTextField.getText();
        String selectedText = this.editorPanel.getTextArea().getSelectedText();

        if(selectedText != null && selectedText.equals(findText)){
            this.editorPanel.getTextArea().replaceSelection(replaceText);
        }

        this.findNext();
    }

    public void replaceAll(){
        String findText = this.findTextField.getText();
        String replaceText = this.replaceTextField.getText();
        String text = this.editorPanel.getTextArea().getText();
        text = text.replace(findText, replaceText);
        this.editorPanel.getTextArea().setText(text);
        JOptionPane.showMessageDialog(this, "All occurences have been replaced !");

    }
}

