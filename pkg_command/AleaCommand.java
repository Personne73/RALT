package pkg_command;

 

import pkg_game.GameEngine;
import pkg_room.Room;
import pkg_room.TransporterRoom;

/**
 * La classe AleaCommand représente la commande 'alea'
 *
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class AleaCommand extends Command
{

    /**
     * Constructeur pour créer une commande alea
     */
    public AleaCommand()
    {
        
    } // AleaCommand()

    /**
     * Fonction booléenne qui vérifie que la ligne de commande possède un second mot pour pouvoir
     * modifier le caractère aléatoire de la tranporter room, ou lui rendre ce caractère si elle
     * ne possède pas de second mot
     * @param pEngine le moteur du jeu pour accèder aux éléménts nécéssaires
     * @return toujours faux
     */
    @Override public boolean execute( final GameEngine pEngine )
    {

        if ( !pEngine.getFightMode() ) {
            pEngine.getGui().visibilityInventory(false);
            Room vRoom = pEngine.getRoom(4);
            TransporterRoom vNextRoom = (TransporterRoom) (vRoom);
            if (this.hasSecondWord()) {
                if (pEngine.getTestMode()) {
                    int vInt = Integer.valueOf(this.getSecondWord());
                    vNextRoom.setAleaInt(vInt);
                } else pEngine.getGui().println("Cette commande ne peut être utilisée qu'en mode test.");
            } else {
                if (pEngine.getTestMode()) {
                    vNextRoom.setAleaInt(17);
                } else pEngine.getGui().println("Cette commande ne peut être utilisée qu'en mode test.");
            }
        } else {
            pEngine.getGui().addTextFight("Cette commande n'est pas disponible en mode combat.");
        }
        return false;
    } // execute(.)
} // AleaCommand
