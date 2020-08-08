package categorie;

public interface Valore<T>  {
		
	/**
	 * Gets the valore.
	 * 
	 * @pre: -
	 * @post: -
	 * @return the valore
	 */
	public T getValore();
	
	
	/**
	 * Sets the valore.
	 *
	 * @pre: valore!=null
	 * @param valore the new valore
	 */
	public void setValore(T valore);
	
}
