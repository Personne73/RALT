package pkg_room;

import java.util.ArrayList;
/**
 * Cette classe permet de créer des pièces transporteuses qui déplacent 
 * le joueur aléatoirement dans une autre pièce
 * 
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class TransporterRoom extends Room
{
    private RoomRandomizer aRandomRoom;
    private ArrayList<Room> aRoomList;
    private int aAleaInt;
    
    /**
     * Constructeur qui permet d'initialiser les différents attributs
     * @param pDescription la description de la pièce 
     * @param pImageName le nom de l'image et son extension
     * @param pRoomList la liste des pièces dans lesquels le joueur sera transporté
     */
    public TransporterRoom( final String pDescription, final String pImageName, final ArrayList pRoomList )
    {
        super(pDescription, pImageName);
        this.aRoomList = pRoomList;
        this.aRandomRoom = new RoomRandomizer(pRoomList);
        this.aAleaInt = 17;
    } // TransporterRoom(...)
    
    /**
     * Accesseur pour récupérer une pièce aléatoirement
     * @param pDirection la direction sous forme de chaine de caractère
     * @return la pièce aléatoire qui sera utilisé pour transporter le joueur
     */
    @Override public Room getExit( final String pDirection )
    {
        if ( this.aAleaInt == 17 ) return this.findRandomRoom();
        else return this.aRoomList.get(this.aAleaInt);
    } // getExit(.)
    
    /**
     * Modificateur qui permet de modifier la valeur de l'attribut aAleaInt par l'entier passé en paramètre
     * @param pAleaInt l'entier qui modifiera l'attribut
     */
    public void setAleaInt( final int pAleaInt )
    {
       this.aAleaInt =  pAleaInt;
    } // setAleaInt(.)

    /**
     * Procédure privée qui permet d'avoir une pièce prise de manière aléatoire
     * @return un pièce de façon random
     */
    private Room findRandomRoom()
    {
        return this.aRandomRoom.findRandomRoom();
    } // findRandomRoom()
    
} // TransporterRoom
