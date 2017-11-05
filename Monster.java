public class Monster
{
    private String name;
    private int att;
    private int def;
    private int spd;
    private int luck;
    private int hp;
    private int exp;
    
    public Monster(String n, int a, int d, int s, int l, int h, int e)
    {
        name=n;
        att=a;
        def=d;
        spd=s;
        luck=l;
        hp=h;
        exp=e;
    }
    
    public int fight()
    {
        int rnd=(int)(Math.random()*luck+1);
        return att+rnd;
    }
    
    public int takeDMG(int a)
    {
        int rnd=(int)(Math.random()*luck+1);
        if(a-(def+rnd)>0)
        {
            hp-=a-(def+rnd);
            return a-(def+rnd);
        }
        return 0;
    }
    
    public boolean isDead()
    {
        return hp<=0;
    }
    
    public int getSpd()
    {
        return spd;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getHp()
    {
        return hp;
    }
    
    public int getExp()
    {
        return exp;
    }
}
