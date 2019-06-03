package entity;
import java.awt.Image;
import java.awt.Point;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import contract.IEntity;
import contract.IMap;
import contract.IEntity;

/**
 * <h1>The Class Map provides a facade of the Model component.</h1>
 * @author Paul Combaldieu
 */
public class Map extends Observable implements IMap,IEntity {

	/** The width */
  private int width;
  
  /** The height */
  private int height;
  
  /** The elements */
  private IEntity[][] map;
  
  /** The pawn list */
  private ArrayList<IMobile> pawns;
  
  /** The myCharacter */
  private IMobile myCharacter = null;
  
  /** The diamond count */
  private int diamondCount = 0;

  /**
   * Default constructor.
   * 
   * @param newWidth
   *          the new width
   * @param newHeight
   *          the new height
   * @param newMap
   *          the new map
   * @throws SQLException
   *           expection
   */
  public Map(final int newWidth, final int newHeight, final IEntity[][] newMap) throws SQLException {
    super();
    this.map = newMap;

    this.width = newWidth;
    this.height = newHeight;
    this.pawns = new ArrayList<IMobile>();
  }

  public void setMyCharacter(final IMobile newChara) {
    this.myCharacter = newChara;
  }

  public void decreaseDiamondCount() {
    this.diamondCount--;
  }

  public void addDiamondCount() {
    this.diamondCount++;
  }

  public int getDiamondCount() {
    return this.diamondCount;
  }

  /**
   * @return the map in ASCII character
   */
  @Override
  public final String toString() {
    String temp = new String();
    for (int y = 0; y < this.getHeight(); y++) {
      for (int x = 0; x < this.getWidth(); x++) {
        temp += map[x][y].getSprite().getConsoleImage();
      }
      temp += '\n';
    }
    return temp;
  }

  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  public IEntity getOnTheMapXY1(final int x, final int y) {
    if(x >= 0 && x < this.getWidth() && y >= 0 && y < this.getHeight())
    	return this.map[x][y];
    else
    	return this.map[0][0];
  }

  @Override
  public Observable getObservable() {
    return this;
  }

  @Override
  public final void setMobileHasChanged() {
    this.setChanged();
    this.notifyObservers();
  }

  public void setOnTheMapXY1(int x, int y, IEntity elem) {
    this.map[x][y] = elem;
  }

  public void addPawn(IMobile pawn) {
    this.pawns.add(pawn);
  }

  public IMobile getMyCharacter() {
    return this.myCharacter;
  }

  public ArrayList<IMobile> getPawns() {
    return this.pawns;
  }

  /**
   * Returns the permeability of an element on the map. Note that this will return
   * the permeability of a pawn if one is there at the given coordonates.
   * @param x
   * @param y
   * @return x and y
   */
  @Override
  public Permeability getSquareIsOccupiedXY(final int x, final int y) {
    Point point = new Point(x, y);
    for(IMobile pawn : this.getPawns()) {
      if (pawn.getPosition().equals(point))
        return pawn.getPermeability();
    }
    
    if(this.getMyCharacter().getPosition().equals(point))
    	return this.getMyCharacter().getPermeability();
    
    return this.getOnTheMapXY1(x, y).getPermeability();
  }

@Override
public IEntity getOnTheMapXY(int x, int y) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void setOnTheMapXY(int x, int y, IEntity entity) {
	// TODO Auto-generated method stub
	
}

public void decreaseDiamoundsCount() {
	// TODO Auto-generated method stub
	
}

public void addDiamoundsCount() {
	// TODO Auto-generated method stub
	
}

public int getDiamoudsCount() {
	// TODO Auto-generated method stub
	return 0;
}

public void setCharacter(IMobile character) {
	// TODO Auto-generated method stub
	
}

@Override
public Sprite getSprite() {
	// TODO Auto-generated method stub
	return null;
}

public Permeability getPermability() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Image getImage() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Permeability getPermeability() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Permeability getPermeability1() {
	// TODO Auto-generated method stub
	return null;
}
}