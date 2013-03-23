package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Haisin Yip
 */

public class LeaderBoardPanel extends JPanel
{
    JTable table;
    //ArrayList<String> playerNames = new ArrayList<String>();
    String[] columns = {"Player", "HighScore", "Number of Lives", "Asteroid Destroyed", "Aliens destroyed", "Total deaths", "Kill-Death Ratio", "Level reached", "Bombs Used", "Shooting Accuracy"};
    String[][] rowdata = {{"P2", "3", "2", "3", "3", "3", "3", "34", "34", "34"},{"P2", "3", "2", "3", "3", "3", "3", "34", "34", "34"},{"P2", "3", "2", "3", "3", "3", "3", "34", "34", "34"},{"P2", "3", "2", "3", "3", "3", "3", "34", "34", "34"}};
    
    JScrollPane scrollPane;
    public LeaderBoardPanel()
    {
        makeComponents();
        makeLayout();
    }
    
    private void makeComponents()
    {
        table = new JTable(rowdata, columns);
        table.setPreferredScrollableViewportSize(new Dimension(500, 50));
        table.setFillsViewportHeight(true);
        
        scrollPane = new JScrollPane(table);  
    }
    
    private void makeLayout()
    {
        setLayout(new FlowLayout());
        add(table);
        add(scrollPane);
    }
}
