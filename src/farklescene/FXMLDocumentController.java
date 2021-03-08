/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farklescene;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Wilson Krueger
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button buttonRoll;
    
    @FXML
    private Button buttonPass;
    
    @FXML
    private ImageView imageRollDie1;
    
    @FXML
    private ImageView imageRollDie2;
    
    @FXML
    private ImageView imageRollDie3;
    
    @FXML
    private ImageView imageRollDie4;
    
    @FXML
    private ImageView imageRollDie5;
    
    @FXML
    private ImageView imageRollDie6;
        
    @FXML 
    private Label labelRollStatus;
    
    @FXML 
    private Label labelPlayerTurn;
    
    @FXML 
    private Label labelPointsPlayer1;
    
    @FXML 
    private Label labelPointsPlayer2;
    
    @FXML 
    private Label labelPointsPlayer3;
    
    @FXML 
    private Label labelPointsPlayer4;
    
    //Initialize Variables
    private int playerNumber;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private int turnScore;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Create Variables
        players = new ArrayList();
        playerNumber = 0;
        turnScore = 0;
        
        //Create 4 players
        Player player = new Player("Player 1");
        players.add(player);
        player = new Player("Player 2");
        players.add(player);
        player = new Player("Player 3");
        players.add(player);
        player = new Player("Player 4");
        players.add(player);
        
        //Set currentPlayer
        currentPlayer = players.get(playerNumber);
                
        //Binding for pass button
        buttonPass.disableProperty().bind(buttonRoll.pressedProperty());
            
    }    
    
    @FXML
    private void buttonRollAction(ActionEvent event) {
        
        //Disable button to allow for one roll
        buttonRoll.setDisable(true);
        
        //Create API call
        String json = callApi("http://roll.diceapi.com/json/6d6");
        
        // Set up the Gson object 
        Gson gson = new Gson();
        
        //Initialize die object
        Die die;
        
        //If json is valid
        if(json.startsWith("{"))
        {
            // Convert to Dice object 
            Dice dice = gson.fromJson(json, new TypeToken<Dice>(){}.getType());
            
            //Create 6 dice
            Die die1 = (Die) dice.getDice().get(0);
            Die die2 = (Die) dice.getDice().get(1);
            Die die3 = (Die) dice.getDice().get(2);
            Die die4 = (Die) dice.getDice().get(3);
            Die die5 = (Die) dice.getDice().get(4);
            Die die6 = (Die) dice.getDice().get(5);
            
            //Set images on UI
            Image image = new Image("http://roll.diceapi.com/images/poorly-drawn/d6/" + die1.getValue() + ".png");
            imageRollDie1.setImage(image);
            image = new Image("http://roll.diceapi.com/images/poorly-drawn/d6/" + die2.getValue() + ".png");
            imageRollDie2.setImage(image);
            image = new Image("http://roll.diceapi.com/images/poorly-drawn/d6/" + die3.getValue() + ".png");
            imageRollDie3.setImage(image);
            image = new Image("http://roll.diceapi.com/images/poorly-drawn/d6/" + die4.getValue() + ".png");
            imageRollDie4.setImage(image);
            image = new Image("http://roll.diceapi.com/images/poorly-drawn/d6/" + die5.getValue() + ".png");
            imageRollDie5.setImage(image);
            image = new Image("http://roll.diceapi.com/images/poorly-drawn/d6/" + die6.getValue() + ".png");
            imageRollDie6.setImage(image);      
            
            //Set the turn score
            for(int i = 0; i < dice.getDice().size(); i++) 
            {
                die = (Die) dice.getDice().get(i);
                turnScore += die.getWorth();
            }
        }

        //If json is invalid
        else
        {
            // Display error to the user
            System.out.println(json);
        }
        
        //Print farkle if applicable 
        if(turnScore == 0) 
        {
            labelRollStatus.setText("Farkle!");
        }
        else
        {
            //Add status
            labelRollStatus.setText("Dice Rolled... Score added to " + currentPlayer);
        }
        
        //Add score
        currentPlayer.addToPlayerScore(turnScore);

        //Update player scores
        switch (playerNumber) {
            case 0:
                labelPointsPlayer1.setText("" + currentPlayer.getPlayerScore());
                break;
            case 1:
                labelPointsPlayer2.setText("" + currentPlayer.getPlayerScore());
                break;
            case 2:
                labelPointsPlayer3.setText("" + currentPlayer.getPlayerScore());
                break;
            case 3:
                labelPointsPlayer4.setText("" + currentPlayer.getPlayerScore());
                break;            
            default:
                labelRollStatus.setText("Error!");
                break;
        }
        
        //Change label
        //Check for winner
        if(players.get(0).getPlayerScore() >= 1500)
        {
            labelRollStatus.setText("Player 1 Wins!");
        }

        else if(players.get(1).getPlayerScore() >= 1500)
        {
            labelRollStatus.setText("Player 2 Wins!");
        }

        else if(players.get(2).getPlayerScore() >= 1500)
        {
            labelRollStatus.setText("Player 3 Wins!");
        }

        else if(players.get(3).getPlayerScore() >= 1500)
        {
            labelRollStatus.setText("Player 4 Wins!");
        }
        
    }
    
    @FXML 
    private void buttonPassAction(ActionEvent event) {
             
        //Disable button
        buttonRoll.setDisable(false);
        
        //Set new player
        if(playerNumber < 3) 
        {
            playerNumber += 1;
        }
        
        else
        {
            playerNumber = 0;
        }
        currentPlayer = players.get(playerNumber);
        
        //Set label on screen
        labelPlayerTurn.setText("" + currentPlayer);
        
        //Reset turnScore
        turnScore = 0;
        
    }
    
    public static String callApi(String apiUrl)
    {
        // Set up variables to call the URL and read the result.
        URL url;
        InputStream inputStream;
        InputStreamReader inputStreamReader;
        BufferedReader reader = null;
        String jsonResult = "";

        try
        {
            // Create the URL with the address to the server.
            url = new URL(apiUrl);
            
            // Call the url and create a Buffered Reader on the result.
            inputStream = url.openStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
                
            // Keep reading lines while more still exist.
            // Append the result to a String.  Should use a StringBuilder if we
            // are expecting a lot of lines.
            while (line != null) {
                jsonResult += line;
                line = reader.readLine();
            }
        }
        catch (MalformedURLException malformedURLException) {
            // URL was bad.
            jsonResult = malformedURLException.getMessage();
        }
        catch (IOException ioException) {
            // An error occurred during the reading process.
            jsonResult = ioException.getMessage();
        }
        finally
        {
            // Close the reader and the underlying objects.
            try
            {
                if (reader != null)
                {
                    reader.close();
                }
            }
            catch (IOException ioException) {
                jsonResult += ioException.getMessage();
            }
        }
        
        return jsonResult;
    }
}
