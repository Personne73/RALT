package pkg_room;

 

import pkg_item.Item;
/**
 * La classe Door permet de créer des portes dans notre jeu
 * 
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class Door
{
    private boolean aCanBeCrossed;
    private Item aKeyDoor;
    
    /**
     * Constructeur naturel qui permet d'initialiser les attributs de la classe
     * @param pCanBeCrossed vaut vrai si la porte peut être franchi, sinon faux
     * @param pKeyDoor représente la clé de la porte
     */
    public Door( final boolean pCanBeCrossed, final Item pKeyDoor )
    {
        this.aCanBeCrossed = pCanBeCrossed;
        this.aKeyDoor = pKeyDoor;
    } // Door(..)
    
    /**
     * Accesseur qui permet d'avoir la valeur contenu dans l'attribut 
     * aCanBeCrossed
     * @return la valeur de l'attribut aCanBeCrossed
     */
    public boolean getCanBeCrossed()
    {
        return this.aCanBeCrossed;
    } // getCanBeCrossed()
    
    /**
     * Accesseur qui permet de savoir si la porte possède une clé ou non
     * @return la valeur de l'attribut aCanBeCrossed
     */
    public Item getKeyDoor()
    {
        return this.aKeyDoor;
    }  // getKeyDoor()
    
    /**
     * Modificateur qui permet de changer la valeur contenue dans l'attribut
     * aCanBeCrossed
     * @param pCanBeCrossed la nouvelle valeur de l'attribut canBeCrossed
     */
    public void setCanBeCrossed( final boolean pCanBeCrossed )
    {
        this.aCanBeCrossed = pCanBeCrossed;
    } // setCanBeCrossed(.)
} // Door
