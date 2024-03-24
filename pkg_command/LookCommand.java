package pkg_command;

 

import pkg_game.GameEngine;
import pkg_item.Item;

/**
 * Cette classe représente la commande regarder.
 *
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class LookCommand extends Command
{
    

    /**
     * Constructeur pour créer la commande regarder
     */
    public LookCommand()
    {
      
    } // LookCommand()

    /**
     * Fonction booléenne qui vérifie le second mot lorsqu'il existe pour regarder un item,
     * sinon pour regarder ou le joueur se trouve actuellement
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
                if (this.getSecondWord().equals("pv")) {
                    pEngine.getGui().println("Vos points de vie : " + pEngine.getPlayer().getPlayerLife() + " PV");
                    return false;
                }
                Item vItem = pEngine.getPlayer().getCurrentRoom().getItem(this.getSecondWord());
                if (vItem == null) {
                    pEngine.getGui().println("Je ne sais regarder qu'un item existant !");
                } else {
                    pEngine.getGui().println(vItem.getLongItemDescription());
                }

            } else {
                pEngine.printLocationInfo();
            }
        } else {
            if ( this.hasSecondWord() ){
                if ( this.getSecondWord().equals("armes") ){
                    pEngine.getPlayer().getWeaponList();
                } else {
                    pEngine.getGui().addTextFight("Vous pouvez seulement regarder les armes que vous avez pendant un combat");
                }
            } else {
                pEngine.getGui().addTextFight("Seul la commande " + "\"" +  "regarder armes" + "\"" + " est disponible en mode combat.");
            }
        }
        return false;
    } // execute(.)
} // LookCommand
