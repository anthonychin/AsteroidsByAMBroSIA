package gui;

import game.GameState;
import highscoreData.highScoreWriter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * The
 * <code>EndGamePanel</code> class displays game over screen.
 *
 * @author Haisin Yip
 */
public class EndGamePanel extends JPanel {

    // private properties
    private GameState gameState;
    private JTable StatisticsTable;
    private JScrollPane scrollPane;
    private Image img;
    private boolean playerOneWins;
    private boolean playerTwoWins;
    private boolean singleP;
    private boolean Esc;
    String player, highscore, asteroidsDestroyed, aliensDestroyed, killDeathRatio, level, bombs, shootingAccuracy;

    /**
     * Creates EndGamePanel using given parameters. It initializes size, layout
     * and informative display.
     *
     * @param img image for EndGamePanel
     * @param gameState current game state
     * @param playerOneTurn boolean value representing which player is playing
     * in two player mode, and in single player mode, it is true by default
     * @param Esc boolean is for checking if the escape key is pressed
     * @param singleP boolean for representing whether the game is in single player mode or not
     */
    public EndGamePanel(Image img, GameState gameState, boolean playerOneTurn, boolean Esc, boolean singleP) {
        this.gameState = gameState;

        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);

        makeComponents(getWidth(), getHeight(), playerOneTurn, Esc);
        makeLayout();

        this.singleP = singleP;
        this.Esc = Esc;
    }

    // construct the main components and informative content which is displayed at the end of the game
    private void makeComponents(int width, int height, boolean playerOneTurn, boolean Esc) {

        // if escape key is not pressed
        if (!Esc) {
            // when single player, display player's information
            if (playerOneTurn) {
                player = "Enter Your Name Here, Player1";
                highscore = String.valueOf(gameState.getCurrentScore());
                asteroidsDestroyed = String.valueOf(gameState.getP1asteroidDestroyed());
                aliensDestroyed = String.valueOf(gameState.getP1alienDestroyed());
                killDeathRatio = String.valueOf((double) gameState.getP1asteroidDestroyed() / (double) gameState.getP1deaths());
                if (gameState.getP1deaths() == 0) {
                    killDeathRatio = String.valueOf(0);
                }
                level = String.valueOf(gameState.getLevel());
                bombs = String.valueOf(gameState.getP1BombUsed());
                shootingAccuracy = String.valueOf(100 * (double) gameState.getP1cleanShot() / (double) gameState.getP1shootCounter());
                if (gameState.getP1shootCounter() == 0) {
                    shootingAccuracy = String.valueOf(0);
                }
            } //two player mode
            else {
                //player 1, player 2 scores
                int highscoreP1 = gameState.getPlayer1Score();
                int highscoreP2 = gameState.getPlayer2Score();

                //display winner's score by comparing the player1's highscore and player2's highscore
                if (highscoreP1 > highscoreP2) {
                    player = "Enter Your Name Here, Player1";
                    highscore = String.valueOf(highscoreP1);
                    asteroidsDestroyed = String.valueOf(gameState.getP1asteroidDestroyed());
                    aliensDestroyed = String.valueOf(gameState.getP1alienDestroyed());
                    killDeathRatio = String.valueOf((double) gameState.getP1asteroidDestroyed() / (double) gameState.getP1deaths());
                    if (gameState.getP1deaths() == 0) {
                        killDeathRatio = String.valueOf(0);
                    }
                    level = String.valueOf(gameState.getPlayer1Level());
                    bombs = String.valueOf(gameState.getP1BombUsed());
                    shootingAccuracy = String.valueOf(100 * (double) gameState.getP1cleanShot() / (double) gameState.getP1shootCounter());
                    if (gameState.getP1shootCounter() == 0) {
                        shootingAccuracy = String.valueOf(0);
                    }
                    playerOneWins = true;
                    playerTwoWins = false;
                } else if (highscoreP1 < highscoreP2) {
                    player = "Enter Your Name Here, Player2";
                    highscore = String.valueOf(highscoreP2);
                    asteroidsDestroyed = String.valueOf(gameState.getP2asteroidDestroyed());
                    aliensDestroyed = String.valueOf(gameState.getP2alienDestroyed());
                    killDeathRatio = String.valueOf((double) gameState.getP2cleanShot() / (double) gameState.getP2deaths());
                    if (gameState.getP1deaths() == 0) {
                        killDeathRatio = String.valueOf(0);
                    }
                    level = String.valueOf(gameState.getPlayer2Level());
                    bombs = String.valueOf(gameState.getP2BombUsed());
                    shootingAccuracy = String.valueOf(100.0 * (double) gameState.getP2asteroidDestroyed() / (double) gameState.getP2shootCounter());
                    if (gameState.getP2shootCounter() == 0) {
                        shootingAccuracy = String.valueOf(0);
                    }
                    playerOneWins = false;
                    playerTwoWins = true;
                } else {
                    player = "Enter Your Name Here";
                    highscore = String.valueOf(highscoreP2);
                    asteroidsDestroyed = String.valueOf(gameState.getP2asteroidDestroyed());
                    aliensDestroyed = String.valueOf(gameState.getP2alienDestroyed());
                    killDeathRatio = String.valueOf((double) gameState.getP2cleanShot() / (double) gameState.getP2deaths());
                    if (gameState.getP1deaths() == 0) {
                        killDeathRatio = String.valueOf(0);
                    }
                    level = String.valueOf(gameState.getPlayer2Level());
                    bombs = String.valueOf(gameState.getP2BombUsed());
                    shootingAccuracy = String.valueOf(100.0 * (double) gameState.getP2asteroidDestroyed() / (double) gameState.getP2shootCounter());
                    if (gameState.getP2shootCounter() == 0) {
                        shootingAccuracy = String.valueOf(0);
                    }
                    playerOneWins = false;
                    playerTwoWins = false;
                }
            }
            // if escape key is pressed, then check which player is currently playing, and display the player's information 
        } else {
            if (playerOneTurn) {
                player = "Enter Your Name Here, Player1";
                highscore = String.valueOf(gameState.getCurrentScore());
                asteroidsDestroyed = String.valueOf(gameState.getP1asteroidDestroyed());
                aliensDestroyed = String.valueOf(gameState.getP1alienDestroyed());
                killDeathRatio = String.valueOf((double) gameState.getP1asteroidDestroyed() / (double) gameState.getP1deaths());
                // if the player's number of death is 0, assign 0 as killDeathRatio value to avoid division by 0
                if (gameState.getP1deaths() == 0) {
                    killDeathRatio = String.valueOf(0);
                }
                level = String.valueOf(gameState.getLevel());
                bombs = String.valueOf(gameState.getP1BombUsed());
                shootingAccuracy = String.valueOf(100 * (double) gameState.getP1cleanShot() / (double) gameState.getP1shootCounter());
                // if the player's number of shots is 0, assign 0 as shootingAccuracy value to avoid division by 0
                if (gameState.getP1shootCounter() == 0) {
                    shootingAccuracy = String.valueOf(0);
                }
            } else {
                player = "Enter Your Name Here, Player2";
                highscore = String.valueOf(gameState.getPlayer2Score());
                asteroidsDestroyed = String.valueOf(gameState.getP2asteroidDestroyed());
                aliensDestroyed = String.valueOf(gameState.getP2alienDestroyed());
                killDeathRatio = String.valueOf((double) gameState.getP2cleanShot() / (double) gameState.getP2deaths());
                // if the player's number of death is 0, assign 0 as killDeathRatio value to avoid division by 0
                if (gameState.getP2deaths() == 0) {
                    killDeathRatio = String.valueOf(0);
                }
                level = String.valueOf(gameState.getPlayer2Level());
                bombs = String.valueOf(gameState.getP2BombUsed());
                shootingAccuracy = String.valueOf(100.0 * (double) gameState.getP2asteroidDestroyed() / (double) gameState.getP2shootCounter());
                // if the player's number of shots is 0, assign 0 as killDeathRatio value to avoid division by 0
                if (gameState.getP2shootCounter() == 0) {
                    shootingAccuracy = String.valueOf(0);
                }
            }
        }
        // JTable header
        String[] columnData = {"", ""};
        // JTable rows
        String[][] rowData = {{"Player name", player}, {"Highscore", highscore}, {"Asteroids Destroyed", asteroidsDestroyed}, {"Aliens Destroyed", aliensDestroyed}, {"Kill-Death ratio", killDeathRatio}, {"Last level", level}, {"Bombs used", bombs}, {"Shooting Accuracy", shootingAccuracy + "%"}};

        // make cells uneditable except the player name cell
        StatisticsTable = new JTable(rowData, columnData) {
            @Override
            public boolean isCellEditable(int rowData, int columnData) {
                if (rowData == 0 && columnData == 1) {
                    return true;
                } else {
                    return false;
                }
            }
        };

        StatisticsTable.getModel().addTableModelListener(
                new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                // get cell update
                int row = e.getFirstRow();
                int column = e.getColumn();
                TableModel model = (TableModel) e.getSource();
                String columnName = model.getColumnName(column);
                Object data = model.getValueAt(row, column);
                String customName = data.toString();

                // write to file if the player name is changed to something different than the default "Enter Your Name Here, Player1(2)"
                if (!customName.equals("Enter Your Name Here, Player1") && !customName.equals("Enter Your Name Here, Player2") && !customName.equals("Enter Your Name Here")) {
                    String[] newScoreData = {customName + " ", highscore + " ", asteroidsDestroyed + " ", aliensDestroyed + " ", killDeathRatio + " ", level + " ", bombs + " ", shootingAccuracy};
                    highScoreWriter writer = new highScoreWriter(newScoreData, System.getProperty("user.dir") + "/scoreInfo.txt");
                    writer.writeToFile();
                }
            }
        });

        //disable selecting for all cells in table
        StatisticsTable.setRowSelectionAllowed(false);
        StatisticsTable.setColumnSelectionAllowed(false);
        StatisticsTable.setCellSelectionEnabled(false);
        StatisticsTable.setPreferredScrollableViewportSize(new Dimension(width / 2, height / 6));
        StatisticsTable.setFillsViewportHeight(true);
    }

    // set the layout with a scrollable table
    private void makeLayout() {
        setLayout(new FlowLayout());
        add(StatisticsTable);
        add(new JScrollPane(StatisticsTable));
    }

    /**
     * Sets endgame background image.
     *
     * @param graphic image for background
     */
    @Override
    public void paintComponent(Graphics graphic) {
        graphic.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }

    /**
     * Prints the victory or tie message at the end of the game if escape key was not pressed
     *
     * @param graphics graphic that needs to be painted
     */
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        // if escape key was not pressed, then print the victory message or tie message
        if (!Esc) {
            g2d.setFont(new Font("SansSerif", Font.BOLD, 20));
            
            if(!singleP){
                g2d.setColor(Color.white);
                g2d.drawString("Player 1 Score: " + gameState.getPlayer1Score(), this.getWidth()/2 - 117, this.getHeight()/2+50);
                g2d.drawString("Player 2 Score: " + gameState.getPlayer2Score(), this.getWidth()/2 - 117, this.getHeight()/2+80);
            }
            if (playerOneWins && !singleP) {
                g2d.setColor(Color.red);
                g2d.drawString("PLAYER 1 IS VICTORIOUS!", this.getWidth() / 2 - 117, this.getHeight() / 2);
            } else if (playerTwoWins && !singleP) {
                g2d.setColor(Color.blue);
                g2d.drawString("PLAYER 2 IS VICTORIOUS!", this.getWidth() / 2 - 117, this.getHeight() / 2);
            } else if (!playerOneWins && !playerTwoWins && !singleP) {
                g2d.setColor(Color.WHITE);
                g2d.drawString("IT'S A DRAW!", this.getWidth() / 2 - 113, this.getHeight() / 2);
            }
            // reset the graphics color to black
            g2d.setColor(Color.black);
        }

    }
}
