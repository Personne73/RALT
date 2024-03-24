package pkg_command;

 

import pkg_game.GameEngine;

/**
 * Cette classe représente la commande d'aide
 *
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class HelpCommand extends Command
{
    private CommandWords aCommandWords;

    /**
     * Constructeur pour créer une commande aide
     * @param pWords les mots de commandes à afficher
     */
    public HelpCommand( final CommandWords pWords )
    {
        this.aCommandWords = pWords;
    } // HelpCommand(.)

    /**
     * Focntion booléenne qui affiche les commandes disponibles lorsque le joueur demande de l'aide
     * @param pEngine le moteur du jeu pour accèder aux éléménts nécéssaires
     * @return toujours faux
     */
    @Override public boolean execute( final GameEngine pEngine )
    {

        if ( !pEngine.getFightMode() ) {
            pEngine.getGui().visibilityInventory(false);
            if ( this.hasSecondWord() ){
                pEngine.getGui().println("Cette commande n'accepte pas de second mot.");
                return false;
            }
            pEngine.getGui().println("Vous êtes perdu. Vous êtes seul.");
            pEngine.getGui().println("Vous errez dans la Tour Perdu.");
            pEngine.getGui().println("\n\nVos mots de commandes sont :\n");
            pEngine.getGui().println(pEngine.getParser().getCommands());
        } else {
            if ( this.hasSecondWord() ){
                pEngine.getGui().addTextFight("Cette commande n'accepte pas de second mot.");
                return false;
            }
            pEngine.getGui().addTextFight("Vos mots de commandes sont :\nattaquer regarder");
        }
        return false;
    } // execute(.)
} // HelpCommand
