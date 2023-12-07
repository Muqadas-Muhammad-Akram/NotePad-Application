package Notepad;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    JFrame window;
    //TEXT AREA COMPONENTS
    JTextArea textArea;
    JScrollPane scrollPane;
    boolean wordWrapOn=false;
    //TOP MENU BAR
    JMenuBar menuBar;
    JMenu menuFile,menuEdit,menuFormat,menuView,menuHelp,menuColor;
    //FILE MENU
    JMenuItem iNew,iOpen,iSave,iSaveAs,iExit;
    //FORMAT MENU
    JMenuItem iWrap,iFontArial,iFontCSMS, iFontTNR,iFontSize8, iFontSize12,iFontSize16,iFontSize20,iFontSize24,iFontSize28;
    JMenu menuFont,menuFontSize;
    // COLOR MENU
    JMenuItem iColor1,iColor2,iColor3,iColor4;
    //EDIT MENU
    JMenuItem Undo,Redo;
    Function_File functionFile;  // Add this line to declare an instance of Function_File
    Function_File file=new Function_File(this);
    Function_Edit edit=new Function_Edit(this);
    Function_Format format=new Function_Format(this);
    Function_Color color=new Function_Color(this);

    KeyHandler kHandler=new KeyHandler(this);


    UndoManager um=new UndoManager();


    public static void main(String[] args) {
        new GUI();
    }

    public GUI(){
        createWindow();
        createTextArea();
        createMenuBar();
        createFileMenu();
        createEditMenu();
        createFormatMenu();
        createColorMenu();

        format.selectedFont="Arial";
        format.createFont(16);
        format.wordWrap();
        color.changeColor("White");
        window.setVisible(true);


    }
    public void createWindow(){
        window=new JFrame("Notepad");
        window.setSize(800,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void createTextArea(){
        textArea =new JTextArea();
        textArea.setFont(format.arial);

        textArea.addKeyListener(kHandler);

        textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                um.addEdit(e.getEdit());
            }
        });

        scrollPane=new JScrollPane(textArea ,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        window.add(scrollPane);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
    }

    public void createMenuBar() {
        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        menuFile = new JMenu("File");
        menuBar.add(menuFile);

        menuEdit = new JMenu("Edit");
        menuBar.add(menuEdit);

        menuFormat = new JMenu("Format");
        menuBar.add(menuFormat);

        menuColor=new JMenu("Color");
        menuBar.add(menuColor);

JMenu menuHelp=new JMenu("Help");
menuBar.add(menuHelp);


    }

    public void createFileMenu(){
        iNew=new JMenuItem("New");
        iNew.addActionListener(this);
        iNew.setActionCommand("New");
        menuFile.add(iNew);

        iOpen=new JMenuItem("Open");
        iOpen.addActionListener(this);
        iOpen.setActionCommand("Open");
        menuFile.add(iOpen);

        iSave=new JMenuItem("Save");
        iSave.addActionListener(this);
        iSave.setActionCommand("Save");
        menuFile.add(iSave);

        iSaveAs=new JMenuItem("SaveAs");
        iSaveAs.addActionListener(this);
        iSaveAs.setActionCommand("SaveAs");
        menuFile.add(iSaveAs);

        iExit=new JMenuItem("Exit");
        iExit.addActionListener(this);
        iExit.setActionCommand("Exit");
        menuFile.add(iExit);
    }

    public void createEditMenu(){
        JMenuItem iUndo=new JMenuItem("Undo");
        iUndo.addActionListener(this);
        iUndo.setActionCommand("Undo");
        menuEdit.add(iUndo);

        JMenuItem iRedo=new JMenuItem("Redo");
        iRedo.addActionListener(this);
        iRedo.setActionCommand("Redo");
        menuEdit.add(iRedo);

        JMenuItem iFind = new JMenuItem("Find");
        iFind.addActionListener(this);
        iFind.setActionCommand("Find");
        menuEdit.add(iFind);

        JMenuItem iReplace = new JMenuItem("Replace");
        iReplace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit.replace();
            }
        });
        menuEdit.add(iReplace);


    }
    public void createFormatMenu(){

        iWrap=new JMenuItem("Word Wrap:Off");
        iWrap.addActionListener(this);
        iWrap.setActionCommand("Word Wrap");
        menuFormat.add(iWrap);

        menuFont=new JMenu("Font");
        menuFormat.add(menuFont);

        iFontArial=new JMenuItem("Arial");
        iFontArial.addActionListener(this);
        iFontArial.setActionCommand("Arial");
        menuFont.add(iFontArial);

        iFontCSMS=new JMenuItem("Comic Sans MS");
        iFontCSMS.addActionListener(this);
        iFontCSMS.setActionCommand("Comic Sans MS");
        menuFont.add(iFontCSMS);

        iFontTNR=new JMenuItem("Times New Roman");
        iFontTNR.addActionListener(this);
        iFontTNR.setActionCommand("Times New Roman");
        menuFont.add(iFontTNR);


        menuFontSize=new JMenu("Font Size");
        menuFormat.add(menuFontSize);

        iFontSize8=new JMenuItem("8");
        iFontSize8.addActionListener(this);
        iFontSize8.setActionCommand("size8");
        menuFontSize.add(iFontSize8);

        iFontSize12=new JMenuItem("12");
        iFontSize12.addActionListener(this);
        iFontSize12.setActionCommand("size12");
        menuFontSize.add(iFontSize12);

        iFontSize16=new JMenuItem("16");
        iFontSize16.addActionListener(this);
        iFontSize16.setActionCommand("size16");
        menuFontSize.add(iFontSize16);

        iFontSize20=new JMenuItem("20");
        iFontSize20.addActionListener(this);
        iFontSize20.setActionCommand("size20");
        menuFontSize.add(iFontSize20);

        iFontSize24=new JMenuItem("24");
        iFontSize24.addActionListener(this);
        iFontSize24.setActionCommand("size24");
        menuFontSize.add(iFontSize24);

        iFontSize28=new JMenuItem("28");
        iFontSize28.addActionListener(this);
        iFontSize28.setActionCommand("size28");
        menuFontSize.add(iFontSize28);

    }

    public void createColorMenu(){
        iColor1=new JMenuItem("White");
        iColor1.addActionListener(this);
        iColor1.setActionCommand("White");
        menuColor.add(iColor1);

        iColor2=new JMenuItem("Black");
        iColor2.addActionListener(this);
        iColor2.setActionCommand("Black");
        menuColor.add(iColor2);

        iColor3=new JMenuItem("Blue");
        iColor3.addActionListener(this);
        iColor3.setActionCommand("Blue");
        menuColor.add(iColor3);

        iColor4=new JMenuItem("Pink");
        iColor4.addActionListener(this);
        iColor4.setActionCommand("Pink");
        menuColor.add(iColor4);
    }

    public void actionPerformed(ActionEvent e){
        String command=e.getActionCommand();
        switch (command){
            case "New":file.newFile();
                break;

            case "Open":file.open();
                break;

            case "Save":file.save();
                break;

            case "SaveAs":file.saveAs();
                break;

            case"Exit":file.exit();
                break;

            case"Undo":edit.undo();
                break;

            case"Redo":edit.redo();
                break;

            case "Find":
                String searchText = JOptionPane.showInputDialog(window, "Enter text to find:");
                if (searchText != null && !searchText.isEmpty()) {
                    int occurrences = edit.findText(searchText);
                    JOptionPane.showMessageDialog(window, "Found " + occurrences + " occurrences.", "Find Results", JOptionPane.INFORMATION_MESSAGE);
                }
                break;

            case"Word Wrap":format.wordWrap();
                break;

            case"Arial":format.setFont(command);
                break;

            case"Comic Sans MS":format.setFont(command);
                break;

            case"Times New Roman":format.setFont(command);
                break;

            case"size8":format.createFont(8);
                break;

            case"size12":format.createFont(12);
                break;

            case"size16":format.createFont(16);
                break;

            case"size20":format.createFont(20);
                break;

            case"size24":format.createFont(24);
                break;

            case"size28":format.createFont(28);
                break;

            case"White":color.changeColor(command);
                break;

            case"Black":color.changeColor(command);
                break;

            case"Blue":color.changeColor(command);
                break;

            case"Pink":color.changeColor(command);
                break;

        }
    }

    public void removeHighlights() {
        textArea.getHighlighter().removeAllHighlights();
    }

    public void highlightText(int start, int end) {
        DefaultHighlighter.DefaultHighlightPainter highlightPainter =
                new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);

        try {
            textArea.getHighlighter().addHighlight(start, end, highlightPainter);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }
}


