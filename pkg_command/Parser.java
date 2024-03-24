package pkg_command;

 
import pkg_command.Command;
import pkg_command.CommandWords;
import java.util.StringTokenizer;
/**
 * La classe Parser permet de créer les lignes de commmandes contenant ce que
 * l'utilisateur rentre pour permettre leur lecture et interprétation
 * 
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class Parser 
{
    private CommandWords aValidCommands;

    /**
     * Constructeur par defaut qui intialise l'attribut de type 
     * CommandWords pour lui mettre les mots de commandes
     */
    public Parser() 
    {
        this.aValidCommands = new CommandWords();        
    } // Parser()

    /**
     * Prend la prochaine commande rentrée par l'utilisateur
     * @param pInputLine la lgine de commande
     * @return la prochaine commande.
     */
    public Command getCommand( final String pInputLine )
    {
        // pInputLine contiendra toute la ligne tapée
        String vWord1;
        String vWord2;
        String vWord3;

        // cherche jusqu'à 2 mots dans la ligne tapee
        StringTokenizer vTokenizer = new StringTokenizer( pInputLine );
        if ( vTokenizer.hasMoreTokens() ) {
            vWord1 = vTokenizer.nextToken(); // recupere le premier mot
        } else vWord1 = null;

        if ( vTokenizer.hasMoreTokens() ) {
            vWord2 = vTokenizer.nextToken();  // recupere le deuxieme mot

        } else vWord2 = null;

        if ( vTokenizer.hasMoreTokens() ) {
            vWord3 = vTokenizer.nextToken(); // récupère le troisième mot
            // note : on ignore tout le reste de la ligne tapee !
        } else vWord3 = null;

        /* Verifie si le premier mot est une commande connue. 
        Si oui, cree une Command avec.
        Sinon, cree une commande vide avec "null" 
        (pour dire 'commande inconnue'). */
        
        Command vCommand = this.aValidCommands.getCommand(vWord1);
        if ( vCommand != null) {
            vCommand.setSecondWord(vWord2);
            vCommand.setThirdWord(vWord3);
        }
        return vCommand;
    } // getCommand(.)

    /**
     * Méthode qui retourne une liste des commandes valides
     * @return les commandes valides en String
     */
    public String getCommands() //anciennement showCommands()
    {
        return this.aValidCommands.getCommandList();
    } // getCommands() 
} // Parser
