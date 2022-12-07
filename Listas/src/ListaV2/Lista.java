package ListaV2;

public interface Lista {

    public boolean vacio();

    public void add(int data);
    
    public void addLast(int data);
    
    public void addFirst(int data);

    public void delete(int pos);
    
    public void deleteFirst();
    
    public void deleteLast();

    public int get(int pos);
    
    public int length();

    @Override
    public String toString();

}
