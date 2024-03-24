package pkg_command;

 

import pkg_character.Character;
import pkg_character.TraderCharacter;
import pkg_game.GameEngine;

/**
 * Cette classe représente la commande inspecter
 *
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class InspectCommand extends Command
{
    

    /**
     * Constructeurpour créer une commande inspecter
     */
    public InspectCommand()
    {
    
    } // InspectCommand()

    /**
     * Fonction booléenne qui vérifie que la ligne de commande possède
     * un second mot avant d'inspecter l'objet dans l'inventaire du joueur
     * @param pEngine le moteur du jeu pour accèder aux éléménts nécéssaires
     * @return toujours faux
     */
    @Override public boolean execute( final GameEngine pEngine )
    {
        if ( this.hasSecondWord() ){
            if ( this.hasThirdWord() ){
                Character vPnj = pEngine.getPlayer().getCurrentRoom().getCharacter(this.getThirdWord());
                if ( vPnj != null ){
                    if ( vPnj.getCharacterName().equals("Marchand") ){
                        TraderCharacter vMarchand = (TraderCharacter)(vPnj);
                        vMarchand.inspectTraderItem(this.getSecondWord(), pEngine);
                    } else {
                        pEngine.getGui().println("Vous pouvez seulement regarder les détails d'un item que le Marchand possède.");
                    }
                } else {
                    pEngine.getGui().println("L'Habitant à qui vous voulez avoir les détails d'un de ses objets n'existe pas dans cette pièce.");
                }
                return false;
            }
            pEngine.getPlayer().inspectItem(this.getSecondWord());
        } else {
            if ( pEngine.getFightMode() ){
                pEngine.getGui().fightVisibilityPane(true);
                pEngine.getGui().addTextFight("Quel item voulez-vous inspecter ?");

            } else {
                pEngine.getGui().println("Quel item voulez-vous inspecter ?");
                pEngine.getGui().visibilityInventory(false);
            }
        }
        return false;
    } // execute(.)
} // InspectCommand
