package pkg_command;

 

import java.util.HashMap;
import java.util.Set;
/**
 * La classe CommandWords permet d'ajouter des mots de commandes aux commandes valides acceptées dans le jeu
 * 
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class CommandWords
{
    private HashMap<String, Command> aValidCommands;

    /**
     * Constructeur par défaut qui initialise les mots de commandes
     */
    public CommandWords()
    {
        this.aValidCommands = new HashMap<String, Command>();
        this.aValidCommands.put("aller", new GoCommand());
        this.aValidCommands.put("aide", new HelpCommand(this));
        this.aValidCommands.put("quitter", new QuitCommand());
        this.aValidCommands.put("regarder", new LookCommand());
        this.aValidCommands.put("manger", new EatCommand());
        this.aValidCommands.put("retour", new BackCommand());
        this.aValidCommands.put("test", new TestCommand());
        this.aValidCommands.put("prendre", new TakeCommand());
        this.aValidCommands.put("deposer", new DropCommand());
        this.aValidCommands.put("inventaire", new InventoryCommand());
        this.aValidCommands.put("inspecter", new InspectCommand());
        this.aValidCommands.put("utiliser", new UseCommand());
        this.aValidCommands.put("charger", new ChargeCommand());
        this.aValidCommands.put("assembler", new BuildCommand());
        this.aValidCommands.put("alea", new AleaCommand());
        this.aValidCommands.put("ouvrir", new OpenCommand());
        this.aValidCommands.put("carte", new MapCommand());
        this.aValidCommands.put("fermer", new CloseCommand());
        this.aValidCommands.put("parler", new TalkCommand());
        this.aValidCommands.put("donner", new GiveCommand());
        this.aValidCommands.put("acheter", new BuyCommand());
        this.aValidCommands.put("vendre", new SaleCommand());
        this.aValidCommands.put("attaquer", new AttackCommand());
    } // CommandWords()

    /**
     * Accesseur qui permet de récupérer la commande qui possède pour nom celui
     * passé en paramètre
     * @param pCommandWord le nom de la commande à récupérer
     * @return la commande associé au nom rentré en paramètre
     */
    public Command getCommand( final String pCommandWord )
    {
        return this.aValidCommands.get(pCommandWord);
    } // getCommand(.)

    /**
     * Méthode qui renvoie une chaine de carractère 
     * des commandes disponibles
     * @return les commandes sous forme de chaine de carectère
     */
    public String getCommandList() // fut showAll pendant un moment
    {
        String vCommandString = "";
        Set<String> vCommandWords = this.aValidCommands.keySet();
        for (String vCommandWord : vCommandWords ){
            vCommandString += vCommandWord + " ";
        }
        return vCommandString;
    } // getCommandList()
} // CommandWords
