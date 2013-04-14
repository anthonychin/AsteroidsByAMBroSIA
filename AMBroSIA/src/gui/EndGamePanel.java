package gui;

import game.GameState;
import highscoreData.highScoreWriter;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
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
     */
    public EndGamePanel(Image img, GameState gameState, boolean playerOneTurn, boolean Esc) {
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
                if (highscoreP1 >= highscoreP2) {
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
                } else {
                    player = "Enter Your Name Here, Player2";
                    highscore = String.valueOf(highscoreP2);
                    asteroidsDestroyed = String.valueOf(gameState.getP2asteroidDestroyed());
                    aliensDestroyed = String.valueOf(gameState.getP2alienDestroyed());
                    killDeathRatio = String.valueOf((double) gameState.getP2cleanShot() / (double) gameState.getP1deaths());
                    if (gameState.getP1deaths() == 0) {
                        killDeathRatio = String.valueOf(0);
                    }
                    level = String.valueOf(gameState.getPlayer2Level());
                    bombs = String.valueOf(gameState.getP2BombUsed());
                    shootingAccuracy = String.valueOf(100.0 * (double) gameState.getP2asteroidDestroyed() / (double) gameState.getP1shootCounter());
                    if (gameState.getP1shootCounter() == 0) {
                        shootingAccuracy = String.valueOf(0);
                    }
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
                if (gameState.getP1deaths() == 0) {
                    killDeathRatio = String.valueOf(0);
                }
                level = String.valueOf(gameState.getLevel());
                bombs = String.valueOf(gameState.getP1BombUsed());
                shootingAccuracy = String.valueOf(100 * (double) gameState.getP1cleanShot() / (double) gameState.getP1shootCounter());
                if (gameState.getP1shootCounter() == 0) {
                    shootingAccuracy = String.valueOf(0);
                }
            } else {
                player = "Enter Your Name Here, Player2";
                highscore = String.valueOf(gameState.getPlayer2Score());
                asteroidsDestroyed = String.valueOf(gameState.getP2asteroidDestroyed());
                aliensDestroyed = String.valueOf(gameState.getP2alienDestroyed());
                killDeathRatio = String.valueOf((double) gameState.getP2cleanShot() / (double) gameState.getP1deaths());
                if (gameState.getP1deaths() == 0) {
                    killDeathRatio = String.valueOf(0);
                }
                level = String.valueOf(gameState.getPlayer2Level());
                bombs = String.valueOf(gameState.getP2BombUsed());
                shootingAccuracy = String.valueOf(100.0 * (double) gameState.getP2asteroidDestroyed() / (double) gameState.getP1shootCounter());
                if (gameState.getP1shootCounter() == 0) {
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
                if (!customName.equals("Enter Your Name Here, Player1") && !customName.equals("Enter Your Name Here, Player2")) {
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
}
