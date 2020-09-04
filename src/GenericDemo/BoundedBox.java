package GenericDemo;

// A small class that showcases generics


public class BoundedBox<T extends String> { // T stands for "Type" 
	private T t; 
	
        public void set(T t) { 
		this.t = t; 
	} 
	public T get() { 
		return t; 
	} 
        
        public T doubleUp() { 
		return (T) (t.concat(t)); 
	} 
        
        
        public static void main(String[] args){
            
            BoundedBox<String> b2 = new BoundedBox<>();
            b2.set("Meddelande");
            System.out.println(b2.get());
            System.out.println(b2.doubleUp());
            
            //Not working
            //BoundedBox<Integer> b1 = new BoundedBox<>();
           
        }
        
} 

