package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import game.GameState;

/**
 * The leaderboard, showing score information for past (and current) players
 *
 * @author Haisin Yip
 */
public class LeaderBoardPanel extends JPanel {

    
    JTable table;
    GameState gamestate;
    
    //basic column/row definitions
    String[] columns = {"Player", "Highscore", "Number of Lives", "Asteroid Destroyed", "Aliens destroyed", "Total deaths", "Kill-Death Ratio", "Level reached", "Bombs Used", "Shooting Accuracy"};
    String[][] rowdata = {{"p1", "highscore", "lives", " ", "2", "3", "1.4", "3", "3", "3"}};
    JScrollPane scrollPane;

    //set up panel
    public LeaderBoardPanel(int width, int height) {
        makeComponents(width, height);
        makeLayout();
    }

    private void makeComponents(int w, int h) {
        table = new JTable(rowdata, columns);
        table.setPreferredScrollableViewportSize(new Dimension(w - 40, h - 110));
        table.setFillsViewportHeight(true);
    }

    private void makeLayout() {
        setLayout(new FlowLayout());
        add(table);
        add(new JScrollPane(table));
    }
}
