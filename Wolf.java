public class Wolf extends Monster
{
    public Wolf()
    {
        super("wolf", 14, 6, 11, (int)(Math.random()*3+1), 10, 15+(int)(Math.random()*6));
    }
}