package pkg_command;

 

import pkg_game.GameEngine;
/**
 * La classe Command permet de créer des commandes
 * 
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public abstract class Command
{
    private String aSecondWord;
    private String aThirdWord;

    /**
     * Constructeur naturel pour initialiser le second mot a null
     */
    public Command ()
    {
        this.aSecondWord = null;
        this.aThirdWord = null;
    } //pkg_command.Command(.)

    /**
     * Accesseur pour récupérer le second mot
     * @return le second mot 
     */  
    public String getSecondWord()
    {
        return this.aSecondWord;
    } //getSecondWord()

    /**
     *
     */
    public String getThirdWord()
    {
        return this.aThirdWord;
    }

    /**
     * Modificateur pour modifier la valeur du second mot
     * @param pSecondWord le nouveau second mot
     */
    public void setSecondWord( final String pSecondWord )
    {
        this.aSecondWord = pSecondWord;
    } //setSecondWord()

    /**
     *
     */
    public void setThirdWord( final String pThirdWord )
    {
        this.aThirdWord = pThirdWord;
    }
    /**
     * Booléen qui vérifie l'existence du second mot
     * @return retourne true si la commande possède un 
     * second mot sinon retourne faux
     */
    public boolean hasSecondWord()
    {
        return this.aSecondWord != null;
    } //hasSecondWord()

    /**
     *
     */
    public boolean hasThirdWord()
    {
        return this.aThirdWord != null;
    }

    /**
     * Fonction booléenne abstraite qui permettra d'éxécuter la commande
     * @param pEngine le moteur du jeu
     * @return faux si le jeu doit continuer sinon vrai
     */
    public abstract boolean execute( final GameEngine pEngine );
} // pkg_command.Command