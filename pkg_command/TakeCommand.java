package pkg_command;

 

import pkg_game.GameEngine;

/**
 * Cette classe représente la commande prendre
 *
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class TakeCommand extends Command
{

    /**
     * Constructeur pour créer une commande prendre
     */
    public TakeCommand()
    {
       
    } // TakeCommand()

    /**
     * Fonction booléenne qui vérifie que la commande possède un second mot
     * avant de prendre l'objet
     * @param pEngine le moteur du jeu pour accèder aux éléménts nécéssaires
     * @return toujours faux
     */
    @Override public boolean execute( final GameEngine pEngine)
    {

        if ( !pEngine.getFightMode() ) {
            pEngine.getGui().visibilityInventory(false);
            if (this.hasSecondWord()) {
                if (this.hasThirdWord()) {
                    pEngine.getGui().println("Cette commande ne prend pas de troisème mot.");
                    return false;
                }
                pEngine.getPlayer().take(this.getSecondWord());
                if (pEngine.getPlayer().getInventoryItem("Bague") != null) {
                    pEngine.getGui().println("Vous avez rempli votre mission. Vous rentrez à présent livrer la relique à vos employeurs américains.");
                    pEngine.endGame();
                    return true;
                }
                if (pEngine.getPlayer().getInventoryItem("Carte") != null) {
                    pEngine.getGui().setEnableMapB(true);
                }
            } else {
                pEngine.getGui().println("Vous devez spécifier l'item que vous voulez prendre.");
            }
        } else {
                pEngine.getGui().addTextFight("Cette commande n'est pas disponible en mode combat.");
            }
        return false;
    } // execute(.)
} // TakeCommand
