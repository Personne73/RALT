package pkg_item;

/**
 *
 */
public class Weapon extends Item
{
    private int aWeaponDamage;
    private String aWeaponRank;

    /**
     *
     */
    public Weapon( final String pItemName, final String pItemDescription,
                   final int pItemWeight, final int pItemPrice, final int pWeaponDamage, final String pWeaponRank )
    {
        super(pItemName, pItemDescription, pItemWeight, pItemPrice);
        this.aWeaponDamage = pWeaponDamage;
        this.aWeaponRank = pWeaponRank;
    } // Weapon(.....)

    /**
     *
     */
    public int getWeaponDamage() {
        return aWeaponDamage;
    } // getWeaponDamage()

    /**
     *
     */
    public String getWeaponRank()
    {
        return this.aWeaponRank;
    }

} // Weapon