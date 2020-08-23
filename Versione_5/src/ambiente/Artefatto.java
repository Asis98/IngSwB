package ambiente;

public class Artefatto extends UnitaDomotica{

	private static final long serialVersionUID = 1L;
	
	private String stanza;
	

	public Artefatto(String unitName)
	{
		super(unitName);
	}
	
	public Artefatto(String nomeArtefatto, String nomeStanza) 
	{
		super(nomeArtefatto);
		this.stanza = nomeStanza;
	}


	public void setStanza(String _stanza)
	{
		stanza=_stanza;
	}

	public String getStanza()
	{
		return stanza;
	}
}
