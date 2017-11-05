public class Snake extends Monster
{
    public Snake()
    {
        super("snake", 18, 5, 15, (int)(Math.random()*3+1), 15, 20+(int)(Math.random()*6));
    }
}