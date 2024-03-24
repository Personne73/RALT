package pkg_command;

 

import pkg_game.GameEngine;
import pkg_item.Item;

/**
 * Cette classe représente la commande ouvrir
 *
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class OpenCommand extends Command
{

    /**
     * Constructeur pour créer une commande ouvrir
     */
    public OpenCommand()
    {
        
    } // OpenCommand()

    /**
     * Fonction booléenne qui vérifie que la ligne de commande passé en paramètre possède une second mot
     * pour pouvoir ouvrir la porte dans la direction contenu dans ce second mot
     * @param pEngine le moteur du jeu pour accèder aux éléménts nécéssaires
     * @return toujours faux
     */
    @Override public boolean execute( final GameEngine pEngine )
    {
        if ( !pEngine.getFightMode() ) {
            pEngine.getGui().visibilityInventory(false);
            if (this.hasSecondWord()) {
                if (this.getSecondWord().equals("porte")) {
                    if (this.hasThirdWord()) {
                        pEngine.getPlayer().openDoor(this.getThirdWord());
                    } else {
                        pEngine.getGui().println("La porte de quelle direction voulez-vous ouvrir ?");
                    }
                } else {
                    pEngine.getGui().println("Vous pouvez seulement ouvrir une porte !");
                }
            } else {
                pEngine.getGui().println("Vous devez ouvrir une porte !");
            }
        } else {
            pEngine.getGui().addTextFight("Cette commande n'est pas disponible en mode combat.");
        }
        return false;
    } // execute(.)
} // OpenCommand
