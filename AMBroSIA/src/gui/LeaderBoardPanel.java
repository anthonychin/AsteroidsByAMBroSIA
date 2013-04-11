package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import game.GameState;
import highscoreData.highScoreReader;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The
 * <code>LeaderBoardPanel</code> shows score information for past (and current)
 * players.
 *
 * @author Haisin Yip
 */
public class LeaderBoardPanel extends JPanel {

    // private properties
    private JTable table;
    private GameState gameState;
    private Image img;
    private String[] columns = {"Player", "Highscore", "Asteroid Destroyed", "Aliens destroyed", "Kill-Death Ratio", "Level reached", "Bombs Used", "Shooting Accuracy"};
    String[][] rowdata;
    private JScrollPane scrollPane;
    private int selectedColumn;

    /**
     * Creates LeaderBoardPanel using given parameters. It displays history of
     * highest scores.
     *
     * @param img image for leaderboard panel
     * @param gameState current game state
     */
    public LeaderBoardPanel(Image img, GameState gameState) {
        this.gameState = gameState;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);

        makeComponents(this.getWidth(), this.getHeight());
        makeLayout();
    }

    // construct the main components and informative content 
    private void makeComponents(int width, int height) {
        highScoreReader reader = new highScoreReader("./src/highscoreData/scoreInfo.txt");
        reader.openFile();
        rowdata = reader.readFile();
        table = new JTable(rowdata, columns)
        {
             @Override
             public boolean isCellEditable(int rowData, int columnData){
                return false;
            }
        };
        
         // listen for mouse clicks and indicates which column was pressed and sort accordingly
        table.getTableHeader().addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int tableColumn = table.columnAtPoint(e.getPoint());
                int index = table.convertColumnIndexToModel(tableColumn);
                        
                bblsort(rowdata, index);
                
            }
        });
        
        table.setPreferredScrollableViewportSize(new Dimension(width / 2, height));
        table.setFillsViewportHeight(true);
        
        table.setRowSelectionAllowed( false );  
        table.setColumnSelectionAllowed( false );  
        table.setCellSelectionEnabled( false );
    }

    // set the layout with a scrollable table
    private void makeLayout() {
        setLayout(new FlowLayout());
        add(table);
        add(new JScrollPane(table));
    }

    /**
     * Set leaderboard background image
     *
     * @param graphic leaderboard background image
     */
    @Override
    public void paintComponent(Graphics graphic) {
        graphic.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }

    /**
     * Bubble sort which sorts the highscores in descending orders.
     *
     * @param array array that needs to be sorted
     */
    public static void bblsort(String[][] array, int scoreType) {
        
        // if first column is returned, then sort names alphabeticaly
        if(scoreType == 0)
        {
            
        }
   
        // if column 1,2,3,5 or 6 is returned sort with decreasing order
        else if((scoreType >=1 && scoreType <= 3) || scoreType == 5 || scoreType == 6)
        {
            for (int i = 0; i < array.length; i++) {
                for (int j = 1; j < array.length - i; j++) {
                    // value at array[x][1] is the highscore which is used for comparison
                    if (Integer.parseInt(array[j - 1][scoreType]) < Integer.parseInt(array[j][1])) {
                        String[] tmp = array[j - 1];
                        array[j - 1] = array[j];
                        array[j] = tmp;
                    }
                }
            }
        }
        
        else
        {
            for (int i = 0; i < array.length; i++) {
                for (int j = 1; j < array.length - i; j++) {
                    // value at array[x][1] is the highscore which is used for comparison
                    if (Double.parseDouble(array[j - 1][scoreType]) < Double.parseDouble(array[j][1])) {
                        String[] tmp = array[j - 1];
                        array[j - 1] = array[j];
                        array[j] = tmp;
                    }
                }
            }
        }
    }
}
