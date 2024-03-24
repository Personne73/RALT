package pkg_item;

/**
 * Cette classe permet la création des items du jeu
 * 
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class Item
{
    private String aItemName;
    private String aItemDescription;
    private int aItemWeight; // en blocs pour l'instant
    private int aItemPrice; // Monnaie d'échange : Coin 
    
    /**
     * Constructeur naturel pour créer un nouvelle item
     * @param pItemName le nom de l'item
     * @param pItemDescription la description de l'item
     * @param pItemWeight le poids de l'item
     * @param pItemPrice le prix de l'item
     */
    public Item( final String pItemName, final String pItemDescription, 
                final int pItemWeight, final int pItemPrice )
    {
        this.aItemName = pItemName;
        this.aItemDescription = pItemDescription;
        this.aItemWeight = pItemWeight;
        this.aItemPrice = pItemPrice;
    } // Item(....)

    /**
     * Accesseur pour accéder à la description de l'item
     * @return la description de l'item
     */
    public String getItemDescription()
    {
        return this.aItemDescription;
    } // getItemDesctiprion()
    
    /**
     * Accesseur qui retourne une description complète de l'item
     * @return la longue description de l'item
     */
    public String getLongItemDescription()
    {
        return "- " + this.getItemName() + "\n" + 
        this.getItemDescription() + "\nPoids : " + 
        this.getItemWeight() + " blocs  |  Prix : "
        + this.getItemPrice() + " Coins";
    } // getLongItemDescription()

    /**
     * Accesseur pour accéder au nom de l'item
     * @return le nom de l'item
     */
    public String getItemName()
    {
        return this.aItemName;
    } // getItemName()
    
    /**
     * Accesseur retourne le poids de l'item
     * @return le poids de l'item
     */
    public int getItemWeight()
    {
        return this.aItemWeight;
    } // getItemWeight()

    /**
     * Accesseur qui retourne le prix de l'item
     * @return le prix de l'item
     */
    public int getItemPrice()
    {
        return this.aItemPrice;
    } // getItemPrice()
    
} // Item
