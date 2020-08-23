package categorie;

import java.util.ArrayList;

import costanti.Costanti;


public class CategoriaAttuatori extends Categoria{

	
	private static final long serialVersionUID = 1L;
	

	private ArrayList<ModalitaOperativa> listaModOperative;
	

	public CategoriaAttuatori(String nomeCategoria, String descrizione, ArrayList<ModalitaOperativa> listModOperativa){
		super(nomeCategoria, descrizione);
		if(listModOperativa.isEmpty())
			throw new IllegalStateException("Non può essere vuota");
		this.listaModOperative = listModOperativa;				
	}

	public CategoriaAttuatori()
	{
		this.listaModOperative = new ArrayList<>();

	}
	

	public CategoriaAttuatori(String nomeCategoria,String descrizione)
	{
		super(nomeCategoria, descrizione);
		this.listaModOperative = new ArrayList<>();
	}
	

	public void setNomeCategoria(String nome)
	{
		super.setNomeCategoria(nome);
	}
	

	public String getNomeCategoria()
	{
		return super.getNomeCategoria();
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------------------------
	 * METODI LISTA MODALITA' OPERATIVE
	 * ------------------------------------------------------------------------------------------------------------------------------------------------
	 */	
	
	
	public boolean addModalitaOperativa(ModalitaOperativa modalitaOp)
	{
		return (listaModOperative.contains(modalitaOp)) ? false : listaModOperative.add(modalitaOp);
	}

	public boolean removeModalitaOperativa(ModalitaOperativa modalitaOp)
	{
		return (listaModOperative.contains(modalitaOp)) ? false : listaModOperative.remove(modalitaOp);
	}

	public String stampaModalitaOperative()
	{
		StringBuilder sb = new StringBuilder();
		int i=0;
		for(ModalitaOperativa nomeModOperativa: listaModOperative)
		{
			sb.append(i);
			sb.append(Costanti.TRATTINO);
			sb.append(nomeModOperativa.getNomeModOperativa());
			sb.append("\n");
			i++;
		}
		
		return sb.toString();
	}

	public ModalitaOperativa getModalitaOperative(int i)
	{
		return listaModOperative.get(i);
	}
	

	public int size()
	{
		return listaModOperative.size();
	}
	
	public boolean modalitaGiaPresente(String nomeModalita)
	{
		for(int i=0;i<listaModOperative.size();i++)
		{
			if(nomeModalita.equalsIgnoreCase(listaModOperative.get(i).getNomeModOperativa()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public ArrayList<ModalitaOperativa> getListaModalitaOperative()
	{
		return listaModOperative;
	}

	public void setListaModalitaOperative (ArrayList<ModalitaOperativa>lista)
	{
		listaModOperative = lista;
	}
	
	public ModalitaOperativa cercaModOp(String nome)
	{
		for (int i=0;i< listaModOperative.size(); i++)
		{
			//verifica che il nome della stanza esista e che l'elemento in analisi sia di tipo Stanza, ho aggiunto getName perchè se no restituiva solo true o false
			if(listaModOperative.get(i).getNomeModOperativa().equalsIgnoreCase(nome))
				return listaModOperative.get(i);
		}
		
		return null;
	}
	
	public String getNomeParametroModOp(int numeroModOp, int numeroParametro)
	{
		return listaModOperative.get(numeroModOp).getNomeParametro(numeroParametro);
	}
	
}

