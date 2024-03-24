package pkg_command;

 

import pkg_game.GameEngine;

/**
 * Cette classe représente la commande charger
 *
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class ChargeCommand extends Command
{
    

    /**
     * Constructeur pour créer une commande charger
     */
    public ChargeCommand()
    {
        
    } // ChargeCommand()

    /**
     * Fonction booléenne qui vérifie que la ligne de commande passé en paramètre possède un second mot
     * pour pouvoir charger l'objet spécifier
     * @param pEngine le moteur du jeu pour accèder aux éléménts nécéssaires
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
                pEngine.getPlayer().chargeBeamer(this.getSecondWord());
            } else {
                pEngine.getGui().println("Vous devez spécifier le nom du téléporteur à charger.");
            }
        } else {
            pEngine.getGui().addTextFight("Cette commande n'est pas disponible en mode combat.");
        }
        return false;
    } // execute(.)
} // ChargeCommand
