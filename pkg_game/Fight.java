package pkg_game;

import pkg_character.MonsterCharacter;
import pkg_item.Item;
import pkg_item.Weapon;

/**
 *
 */
public class Fight
{

    private MonsterCharacter aMonsterCharacter;
    private GameEngine aEngine;
    private boolean aPlayerHasAttacked;

    /**
     *
     */
    public Fight( final MonsterCharacter pMonsterCharacter, final GameEngine pEngine )
    {
        this.aMonsterCharacter = pMonsterCharacter;
        this.aEngine = pEngine;
        this.aPlayerHasAttacked = false;
    } // Fight()

    /**
     * Accesseur qui retourne la valeur de l'attribut du booléen
     * @return la valeur de l'attribut booléen
     */
    public boolean getPlayerHasAttacked()
    {
        return this.aPlayerHasAttacked;
    } // getPlayerHasAttacked()

    /**
     *
     */
    public void playerHandsAttack()
    {
        Player vPlayer = this.aEngine.getPlayer();
        int vNewMonsterLife = this.aMonsterCharacter.getMonsterLife() - 80;
        this.aMonsterCharacter.setMonsterLife(vNewMonsterLife);
        this.aEngine.getGui().addTextFight("Vous avez infligé " + 80 + " points de dégâts avec vos poings." );
        this.aEngine.getGui().addTextFight("Il reste " + this.aMonsterCharacter.getMonsterLife() + " points de vie à votre adversaire.");
        this.aEngine.getGui().addTextFight("Il vous reste " + this.aEngine.getPlayer().getPlayerLife() + " points de vie.");
    } // playerHandAttack()

    /**
     * Fonction qui permet de faire attaquer le joueur et diminuer la santé de son adversaire
     * @param pWeaponName le nom de l'arme
     */
    public void playerAttack( final String pWeaponName )
    {
        Player vPlayer = this.aEngine.getPlayer();
        Item vPlayerItem = vPlayer.getInventoryItem(pWeaponName);

        if ( vPlayerItem != null ) {
            if ( vPlayerItem instanceof Weapon ) {
                Weapon vPlayerWeapon = (Weapon)(vPlayerItem);
                int vWeaponDamage = vPlayerWeapon.getWeaponDamage();
                if ( vPlayerWeapon.getWeaponRank().equals("Divin") ){
                    vWeaponDamage = vWeaponDamage*2 + (int)(0.3*this.aMonsterCharacter.getMonsterLife());
                } else if ( vPlayerWeapon.getWeaponRank().equals("Légendaire") ){
                    vWeaponDamage += (int)(0.5*vWeaponDamage);
                } else if ( vPlayerWeapon.getWeaponRank().equals("Suprême") ){
                    vWeaponDamage += (int)(0.4*vWeaponDamage);
                } else if ( vPlayerWeapon.getWeaponRank().equals("Or") ){
                    vWeaponDamage += (int)(0.2*vWeaponDamage);
                } else if ( pWeaponName.equals("Piques") ){
                    vWeaponDamage = 240;
                }
                int vNewMonsterLife = this.aMonsterCharacter.getMonsterLife() - vWeaponDamage;
                this.aMonsterCharacter.setMonsterLife(vNewMonsterLife);
                this.aEngine.getGui().addTextFight("Vous avez infligé " + vWeaponDamage + " points de dégâts avec l'arme : " + pWeaponName );
                this.aEngine.getGui().addTextFight("Il reste " + this.aMonsterCharacter.getMonsterLife() + " points de vie à votre adversaire.");
                this.aEngine.getGui().addTextFight("Il vous reste " + this.aEngine.getPlayer().getPlayerLife() + " points de vie.");
                this.aPlayerHasAttacked = true;
            } else {
                this.aEngine.getGui().addTextFight("Vous essayez d'attaquer avec un objet qui se trouve ne pas être une arme ! ");
                this.aPlayerHasAttacked = false;
            }
        } else {
            this.aEngine.getGui().addTextFight("Vous ne possédez pas l'item avec lequel vous voulez attaquer.");
            this.aPlayerHasAttacked = false;
        }
    } // playerAttack(.)

    /**
     * Procédure qui permet de faire attaquer l'adversaire du joueur
     */
    public void monsterAttack()
    {
        int vNewPlayerLife = this.aEngine.getPlayer().getPlayerLife() - this.aMonsterCharacter.getAttackDamage();
        this.aEngine.getPlayer().setPlayerLife(vNewPlayerLife);
        this.aEngine.getGui().addTextFight("Votre adversaire vous attaque avec : " + this.aMonsterCharacter.getAttackName());
        this.aEngine.getGui().addTextFight("Il vous a infligé " + this.aMonsterCharacter.getAttackDamage() + " points de dégâts.");
        this.aEngine.getGui().addTextFight("Il vous reste " + this.aEngine.getPlayer().getPlayerLife() + " points de vie.");
        this.aEngine.getGui().addTextFight("Il reste " + this.aMonsterCharacter.getMonsterLife() + " points de vie à votre adversaire.");
    } // monsterAttack()

    /**
     * Fonction booléenne qui retourne vrai si le joueur a perdu sinon faux
     * @return true si le joueur a perdu le combat sinon retourne faux
     */
    public boolean endFight()
    {
        if ( this.aMonsterCharacter.getMonsterLife() <= 0 ){
            this.aEngine.setFightMode(false);
            this.aEngine.getGui().fightVisibilityPane(false);
            this.aEngine.getGui().enableButtons();
            this.aEngine.getGui().setEnableAttackB(false);
            this.aEngine.getGui().println("\nVous avez vaincu : " + this.aMonsterCharacter.getCharacterName());
            this.aEngine.getGui().println("La bague de l'Illusion réagis à cette fin de combat : elle efface de la tour le corps de votre adversaire !");
            this.aEngine.getPlayer().getCurrentRoom().removeCharacter(this.aMonsterCharacter.getCharacterName());
            this.aEngine.getGui().println("Il vous reste " + this.aEngine.getPlayer().getPlayerLife() + " points de vie.\n");
            this.aEngine.printLocationInfo();
            return false;
        } else if ( this.aEngine.getPlayer().getPlayerLife() <= 0 ){
            this.aEngine.getGui().fightVisibilityPane(false);
            this.aEngine.getGui().setEnableAttackB(false);
            this.aEngine.getGui().println("\nVous vous sentez faible, votre vue devient trouble : vous avez été vaincu par "
                    + this.aMonsterCharacter.getCharacterName() + "\n\n");
            this.aEngine.endGame();
            return true;
        }
        return false;
    } // endFight()
} // Fight
