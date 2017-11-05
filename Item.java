public class Item
{
    private String name;
    private int weight;
    public Item(String n, int w)
    {
        name=n;
        weight=w;
    }
    
    public int getWeight()
    {
        return weight;
    }
    
    public String getName()
    {
        return name;
    }
}