package pkg_command;

import pkg_character.Character;
import pkg_game.GameEngine;
import pkg_character.TraderCharacter;
/**
 *
 */
public class SaleCommand extends Command
{

    /**
     *
     */
    public SaleCommand()
    {

    } // SaleCommand()

    /**
     *
     */
    @Override public boolean execute( final GameEngine pEngine ) {

        if ( !pEngine.getFightMode() ) {
            pEngine.getGui().visibilityInventory(false);
            if (this.hasSecondWord()) {
                if (this.hasThirdWord()) {
                    Character vPnj = pEngine.getPlayer().getCurrentRoom().getCharacter(this.getThirdWord());
                    if (vPnj != null) {
                        if (vPnj.getCharacterName().equals("Marchand")) {
                            TraderCharacter vMarchand = (TraderCharacter) (vPnj);
                            vMarchand.sale(this.getSecondWord(), pEngine);
                        } else {
                            pEngine.getGui().println("Vous pouvez seulement vendre des items au Marchand !");
                        }
                    } else {
                        pEngine.getGui().println("L'Habitant auquel vous voulez vendre cet item n'existe pas dans cette pièce.");
                    }
                } else {
                    pEngine.getGui().println("A qui voulez-vous vendre cet item ?");
                }
            } else {
                pEngine.getGui().println("Que voulez-vous vendre ?");
            }
        } else {
            pEngine.getGui().addTextFight("Cette commande n'est pas disponible en mode combat.");
        }
        return false;
    } // execute(.)
} // SaleCommand
