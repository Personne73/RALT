package pkg_item;

 

import pkg_room.Room;
/**
 * La classe Beamer permet la création d'items considérer comme téléporteur
 * 
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class Beamer extends Item
{
    private Room aBeamerRoom;
    
    /**
     * Constructeur qui initialise les attributs
     * @param pItemName le nom de l'item
     * @param pItemDescription la description de l'item
     * @param pItemWeight le poids de l'item
     * @param pItemPrice le prix de l'item
     * @param pBeamerRoom la pièce à mémoriser par le beamer
     */
    public Beamer( final String pItemName, final String pItemDescription, 
                    final int pItemWeight, final int pItemPrice, final Room pBeamerRoom )
    {
        super(pItemName, pItemDescription, pItemWeight, pItemPrice);
        this.aBeamerRoom = pBeamerRoom;
    } // Beamer(.....)
    
    /**
     * Constructeur qui initialise les attributs de la super classe
     * ainsi que celui de la sous classe en le mettant à null
     * @param pItemName le nom de l'item
     * @param pItemDescription la description de l'item
     * @param pItemWeight le poids de l'item
     * @param pItemPrice le prix de l'item
     */
    public Beamer( final String pItemName, final String pItemDescription, 
                    final int pItemWeight, final int pItemPrice )
    {
        this(pItemName, pItemDescription, pItemWeight, pItemPrice, null);
    } // Beamer(....)
    
    /**
     * Accesseur à la valeur qui se trouve dans l'attribut aBeamerRoom
     * @return la valeur stocker dans l'attribut aBeamerRoom
     */
    public Room getBeamerRoom()
    {
        return this.aBeamerRoom;
    } // getBeamerRoom()
    
    /**
     * Modificateur qui modifie la valeur de l'attribut aBeamerRoom
     * @param pBeamerRoom la pièce utilisé pour modifier la valeur de l'attribut
     */
    public void setBeamerRoom( final Room pBeamerRoom )
    {
        this.aBeamerRoom = pBeamerRoom;
    } // setBeamerRoom(.)
    
} // Beamer