package pkg_character;

import pkg_room.Room;
import pkg_room.RoomRandomizer;
import java.util.ArrayList;
/**
 *
 */
public class MovingCharacter extends Character
{
    private RoomRandomizer aRandomRoom;
    private Room aCurrentRoom;
    private ArrayList<Room> aRoomList;

    /**
     *
     */
    public MovingCharacter( final String pCharacterName, final String pTextSentences, final Room pCurrentRoom , final ArrayList pRoomList)
    {
        super(pCharacterName, pTextSentences);
        this.aCurrentRoom = pCurrentRoom;
        this.aRandomRoom = new RoomRandomizer(pRoomList);
        this.aRoomList = pRoomList;
    }

    /**
     *
     */
    public MovingCharacter( final String pCharacterName, final String pTextSentences , final ArrayList pRoomList )
    {
        this(pCharacterName, pTextSentences, null, pRoomList);
        this.setCurrentRoom(this.findRandomRoom());
    }

    /**
     *
     */
    public Room getCurrentRoom()
    {
        return this.aCurrentRoom;
    }

    /**
     *
     */
    public void setCurrentRoom( final Room pCurrentRoom )
    {
        this.aCurrentRoom = pCurrentRoom;
    }

    /**
     * Procédure privée qui permet d'avoir une pièce prise de manière aléatoire dans la liste des pièces
     * @return un pièce de façon random
     */
    private Room findRandomRoom()
    {
        int vRandomNumber = this.aRandomRoom.getRandomNumber();
        return this.aRoomList.get(vRandomNumber);
    } // findRandomRoom()

    /**
     *
     */
    public void moveCharacter()
    {
        this.aCurrentRoom.removeCharacter(this.getCharacterName());
        this.setCurrentRoom(this.findRandomRoom());
        this.aCurrentRoom.addCharacter(this);
    }
} // MovingCharacter
