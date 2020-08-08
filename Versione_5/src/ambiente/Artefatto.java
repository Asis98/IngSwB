package ambiente;

public class Artefatto extends UnitaDomotica{

	private static final long serialVersionUID = 1L;
	
	private String stanza;
	
	/**
	 * Instantiates a new artefatto.
	 *
	 * @param unitName the unit name
	 * 
	 * @pre unitName != NULL
	 * @post: -
	 * @invariant: stanza!=null
	 */
	public Artefatto(String unitName)
	{
		super(unitName);
	}
	
	/**
	 * Sets the stanza.
	 *
	 * @param _stanza the new stanza
	 * @pre _stanza != Null
	 * @post stanza != Null
	 * 
	 */
	public void setStanza(String _stanza)
	{
		stanza=_stanza;
	}
	
	/**
	 * Gets the stanza.
	 *
	 * @pre: -
	 * @post: -
	 * @return the stanza
	 */
	public String getStanza()
	{
		return stanza;
	}
}
