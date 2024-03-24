package pkg_character;

/**
 *
 */
public class MonsterCharacter extends Character
{
    private int aMonsterLife;
    private String aAttackName;
    private int aAttackDamage;
    private boolean aTalkAttack;

    /**
     *
     */
    public MonsterCharacter( final String pCharacterName,  final String pTalkSentences, final int pMonsterLife,
                             final String pAttackName, final int pAttackDamage, final boolean pTalkAttack )
    {
        super( pCharacterName, pTalkSentences);
        this.aMonsterLife = pMonsterLife;
        this.aAttackName = pAttackName;
        this.aAttackDamage = pAttackDamage;
        this.aTalkAttack = pTalkAttack;
    } // MonsterCharacter(.....)

    /**
     *
     */
    public int getMonsterLife()
    {
        return this.aMonsterLife;
    } // getMonsterLife()

    /**
     *
     */
    public String getAttackName()
    {
        return this.aAttackName;
    } // getAttackName()

    /**
     *
     */
    public int getAttackDamage()
    {
        return this.aAttackDamage;
    } // getAttackDamage()

    /**
     *
     */
    public boolean getTalkAttack() { return this.aTalkAttack; } // getTalkAttack()

    /**
     *
     */
    public void setMonsterLife( final int pMonsterLife )
    {
        this.aMonsterLife = pMonsterLife;
    } // setMonsterLife

} // MonsterCharacter
