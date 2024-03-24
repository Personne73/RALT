package pkg_command;

 

import pkg_game.GameEngine;

/**
 * Cette classe représente la command inventaire.
 *
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class InventoryCommand extends Command
{
   

    /**
     * Constructeur pour créer une commande inventaire
     */
    public InventoryCommand()
    {
        
    } // InventoryCommand()

    /**
     * Fonction booléenne qui affiche l'inventaire du joueur en appellant
     * @param pEngine le moteur du jeu pour accèder aux éléménts nécéssaires
     * @return toujours faux
     */
    @Override public boolean execute( final GameEngine pEngine)
    {
        if ( !pEngine.getFightMode() ) {
            if (this.hasSecondWord()) {
                if (this.hasThirdWord()) {
                    pEngine.getGui().println("Cette commande ne prend pas de troisème mot.");
                    return false;
                }
                pEngine.getGui().println("La commande inventaire n'accepte pas de second mot.");
                return false;
            }
            pEngine.getPlayer().inventory();
        } else {
            pEngine.getGui().writeFight("La commande inventaire n'est pas disponible en combat, seul " + "\"" +  "regarder armes" +
                    "\"" + " est possible !\n");
            pEngine.getGui().setEnableInventoryB(false);
        }
        return false;
    } // execute(.)
} // InventoryCommand
