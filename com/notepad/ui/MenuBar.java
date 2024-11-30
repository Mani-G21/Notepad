package com.notepad.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import com.notepad.util.CeaserCipher;
import com.notepad.util.EncryptionStrategy;
import com.notepad.util.FileHandler;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.undo.UndoManager;

public class MenuBar extends JMenuBar{
    private EditorPanel editorPanel;
    private UndoManager undoManager;
    private FileHandler fileHandler;
    private File currentFile;

    public MenuBar(EditorPanel editorPanel){
        this.editorPanel = editorPanel;
        this.undoManager = new UndoManager();
        editorPanel.getTextArea().getDocument().addUndoableEditListener(undoManager);
        EncryptionStrategy encryptionStrategy = new CeaserCipher(3);
        this.fileHandler = new FileHandler(encryptionStrategy);
        this.createOpenMenu();
        this.createEditMenu();
        this.createSettingsMenu();
    }

    private void createEditMenu(){
        JMenu editMenu = new JMenu("Edit");
        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem undoItem = new JMenuItem("Undo");
        JMenuItem redoItem = new JMenuItem("Redo");
        JMenuItem findItem = new JMenuItem("Find");
        JMenuItem replaceItem = new JMenuItem("Replace");
        JMenuItem pasteItem = new JMenuItem("Paste");
        
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.add(undoItem);
        editMenu.add(redoItem);
        editMenu.add(findItem);
        editMenu.add(replaceItem);
        add(editMenu);

        cutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorPanel.getTextArea().cut();
            }
        });

        copyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorPanel.getTextArea().copy();
            }
        });

        pasteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorPanel.getTextArea().paste();
            }
        });

        undoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(undoManager.canUndo())
                    undoManager.undo();     
            }
        });

        
        redoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(undoManager.canRedo())
                    undoManager.redo(); 
            }
        });

        findItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                FindReplaceDialog findReplaceDialog = new FindReplaceDialog(editorPanel, false);
                findReplaceDialog.setVisible(true);
            }
        });

        replaceItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                FindReplaceDialog findReplaceDialog = new FindReplaceDialog(editorPanel, true);
                findReplaceDialog.setVisible(true);
            }
        });
    }

    

    private void createOpenMenu(){
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem saveAsItem = new JMenuItem("Save AS");
        
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        add(fileMenu);

        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                openFile();
            }
        });
        
        saveAsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                saveFile(true);
            }
        });
        
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                saveFile(false);
            }
        });
    }

    private void createSettingsMenu(){
        JMenu settingsMenu = new JMenu("Settings");
        JMenuItem preferencesItem = new JMenuItem("Preferences");
        settingsMenu.add(preferencesItem);
        add(settingsMenu);

        preferencesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                PreferencesDialog preferencesDialog = new PreferencesDialog(editorPanel);
                preferencesDialog.setVisible(true);
            }
        });

    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            try{
                boolean shouldDecrypt = file.getName().endsWith(".sk");
                String content = fileHandler.openFile(file, shouldDecrypt);
                this.editorPanel.getTextArea().setText(content);
                this.currentFile = file;
            }
            catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,"Error while opening the file");
            }
        }
    }

    private void saveFile(Boolean saveAs){
        if(saveAs || currentFile == null){
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(this);
            if(result == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                if(!file.getName().endsWith(".sk")){
                    JOptionPane.showMessageDialog(this, "Only .sk files can be saved");
                    return;
                }
                this.fileHandler.saveFile(file, this.editorPanel.getTextArea().getText(), true);
                this.currentFile = file;
            }
        }
        else{
            boolean shouldEncrypt = currentFile.getName().endsWith(".sk");
            fileHandler.saveFile(currentFile, editorPanel.getTextArea().getText(), shouldEncrypt);
        }
    }

    
}
