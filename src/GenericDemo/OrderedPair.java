package GenericDemo;


public class OrderedPair <K, V> implements Pair<K, V> { 
    //public class OrderedPair<K extends String, V extends Integer> implements Pair<K, V> { 
	private final K key; 
	private final V value; 

	public OrderedPair(K key, V value) { 
		this.key = key; 
		this.value = value; 
	} 

        @Override
	public K getKey() { return key; } 
        
        @Override
	public V getValue() { return value; } 
        
        //Notera att detta kommer inte att funka, utan krockar med befintlig getValue()!
        //Det gäller ÄVEN om vi avgränsar K och V så att de aldrig kommer att kunna vara av samma typ
	//public K getValue() { return key; } 
} 
