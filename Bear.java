public class Bear extends Monster
{
    public Bear()
    {
        super("bear", 16, 8, 13, (int)(Math.random()*3+1), 25, 30+(int)(Math.random()*6));
    }
}