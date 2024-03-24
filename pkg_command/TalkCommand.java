package pkg_command;

import pkg_game.GameEngine;
import pkg_room.Room;
import pkg_character.Character;
import pkg_item.Item;
/**
 * Cette classe représente la commande Parler
 *
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class TalkCommand extends Command
{
    
    /**
     * Constructeur pour créer une commande parler
     */
    public TalkCommand()
    {
        
    } // TalkCommand()
    
    /**
     * Fonction booléenne qui vérifie que la commande possède un second mot avant de parler
     * à la personne qui a pour nom ce mot
     * @param pEngine  le moteur du jeu pour accéder aux éléments nécéssaires
     * @return toujours faux
     */
    @Override public boolean execute( final GameEngine pEngine )
    {
        if ( !pEngine.getFightMode() ) {
            pEngine.getGui().visibilityInventory(false);
            if (this.hasSecondWord()) {
                if (this.hasThirdWord()) {
                    pEngine.getGui().println("Cette commande ne prend pas de troisème mot.");
                    return false;
                }
                Room vCurrentRoom = pEngine.getPlayer().getCurrentRoom();
                if (vCurrentRoom.getCharacter(this.getSecondWord()) != null) {
                    Character vPnj = vCurrentRoom.getCharacter(this.getSecondWord());
                    pEngine.getGui().println(vPnj.getTalkSentences());
                    if ( vPnj.getCharacterName().equals("Joël") ){
                        Item vItem = vPnj.getInventoryItem("Genesis");
                        pEngine.getPlayer().addInventoryItem(vItem);
                        vPnj.removeInventoryItem("Genesis");
                        vPnj.setTalkSentences("Que le Créateur de la Tour vous accorde sa bénédiction !");
                    }
                    if ( vPnj.getCharacterName().equals("Forgeron") ){
                        Item vItem = vPnj.getInventoryItem("Potion");
                        pEngine.getPlayer().addInventoryItem(vItem);
                        vPnj.removeInventoryItem("Potion");
                        vPnj.setTalkSentences("Je suis peut-être trop bienveillant...");
                    }
                } else {
                    pEngine.getGui().println("Ce personnage ne se trouve pas dans la même pièce que vous");
                }
            } else {
                pEngine.getGui().println("A quel habitant voulez-vous parler ?");
            }
        } else {
            pEngine.getGui().addTextFight("Cette commande n'est pas disponible en mode combat.");
        }
        return false;
    } // execute(.)
} // TakeCommand