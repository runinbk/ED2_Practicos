
package ListaV2;


public class NodoS{
    int data;
    NodoS link;
    
    public NodoS(){
        this.data = 0;
        link = null;
    }
    
    public NodoS(int data){
        this.data = data;
        this.link = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public NodoS getLink() {
        return link;
    }

    public void setLink(NodoS link) {
        this.link = link;
    }
    
    
    
    
    
    
}
