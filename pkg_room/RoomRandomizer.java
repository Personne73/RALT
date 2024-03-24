package pkg_room;

import java.util.Random;
import java.util.ArrayList;
/**
 * Cette classe permet l'obtendtion un pièce de manière pseudo aléatoire
 */
public class RoomRandomizer
{
    private ArrayList<Room> aRoomList;
    private Random aRandom;

    /**
     * Constructeur qui permet d'initialiser le générateur de nombre pseudo aléatoires
     * ainsi que la liste de pièce
     * @param pRoomList la liste des pièces
     */
    public RoomRandomizer( final ArrayList pRoomList )
    {
        this.aRoomList = pRoomList;
        this.aRandom = new Random();
    } // RoomRandomizer(.)

    /**
     * Méthode qui permet d'avoir une pièce prise de manière aléatoire dans la liste des pièces
     * @return un pièce de façon random de la liste des pièces
     */
    public Room findRandomRoom()
    {
        int vRandomNumber = this.aRandom.nextInt(4);
        return this.aRoomList.get(vRandomNumber);
    } // findRandomRoom()

    /**
     *
     */
    public int getRandomNumber()
    {
        return this.aRandom.nextInt(this.aRoomList.size());
    }

} // RoomRandomizer
