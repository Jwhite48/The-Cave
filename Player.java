import java.util.*;
public class Player
{
    private String name;
    private int att;
    private int def;
    private int spd;
    private int luck;
    private int hp;
    private int topHp;
    private int exp;
    private int lvl;
    private ArrayList<Item> inv;
    private boolean noSword;
    private boolean noShield;
    private boolean noBoat;

    public Player(String n)
    {
        name=n;
        att=10;
        def=10;
        spd=10;
        luck=(int)(Math.random()*4+1);
        hp=75;
        topHp=75;
        exp=0;
        lvl=1;
        inv=new ArrayList<Item>();
        noSword=true;
        noShield=true;
        noBoat=true;
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

    public void playerStatus()
    {
        System.out.print("**************\nName: "+name+"\nAtt: "+att+"\nDef: "+def+"\nSpd: "+spd+"\nHp: "+hp+"\nLvl: "+lvl+"\nEquipment: ");
        String n="";
        if(noSword==false){n+="Sword, ";}
        if(noShield==false){n+="Shield, ";}
        if(noBoat==false){n+="Boat, ";}
        if(n.length()>0){n=n.substring(0, n.length()-2);}
        else{n="None";}
        System.out.print(n+"\n**************\n");
    }

    public boolean gainAndLevel(int e)
    {
        if(lvl==5)
        {
            return false;
        }
        exp+=e;
        if(exp>=(int)(30*(Math.pow((double)(lvl+1), 1.1))))
        {
            lvl++;
            exp=0;
            topHp+=5*(int)(Math.random()*2+1);
            hp=topHp;
            int rnd=(int)(Math.random()*3);
            if(rnd==0)
            {
                att+=2;
                def++;
                spd++;
            }
            else if(rnd==1)
            {
                att++;
                def+=2;
                spd++;
            }
            else if(rnd==2)
            {
                att++;
                def++;
                spd+=2;
            }
            return true;
        }
        return false;
    }

    public boolean addInv(Item a)
    {
        int total=0;
        for(Item z: inv)
        {
            total+=z.getWeight();
        }
        if(a.getWeight()+total<=60)
        {
            inv.add(a);
            return true;
        }
        return false;
    }

    public boolean removeInv(String n)
    {
        n=n.substring(0,1).toUpperCase()+n.substring(1).toLowerCase();
        for(int i=0; i<inv.size(); i++)
        {
            if(inv.get(i).getName().equals(n))
            {
                inv.remove(i);
                return true;
            }
        }
        return false;
    }

    public int craftable(String c1, String c2, String c3)
    {
        c1=c1.substring(0,1).toUpperCase()+c1.substring(1).toLowerCase(); c2=c2.substring(0,1).toUpperCase()+c2.substring(1).toLowerCase(); c3=c3.substring(0,1).toUpperCase()+c3.substring(1).toLowerCase();
        if((!c1.equals("Claw")&&!c1.equals("Pelt")&&!c1.equals("Scale"))||(!c2.equals("Claw")&&!c2.equals("Pelt")&&!c2.equals("Scale"))||(!c3.equals("Claw")&&!c3.equals("Pelt")&&!c3.equals("Scale")))
        {
            return 0;
        }
        ArrayList<String> arr=new ArrayList<String>(Arrays.asList(c1, c2, c3));
        int claw=0; int pelt=0; int scale=0;
        for(int i=0; i<arr.size(); i++)
        {
            if(arr.get(i).equals("Claw"))
            {
                claw++;
            }
            else if(arr.get(i).equals("Pelt"))
            {
                pelt++;
            }
            else if(arr.get(i).equals("Scale"))
            {
                scale++;
            }
        }
        int claw2=0; int pelt2=0; int scale2=0;
        for(int i=0; i<inv.size(); i++)
        {
            if(inv.get(i).getName().equals("Claw"))
            {
                claw2++;
            }
            else if(inv.get(i).getName().equals("Pelt"))
            {
                pelt2++;
            }
            else if(inv.get(i).getName().equals("Scale"))
            {
                scale2++;
            }
        }
        if(claw2>=claw&&pelt2>=pelt&&scale2>=scale)
        {
            if(noSword&&claw==2&&pelt==1)
            {
                removeInv("Claw"); removeInv("Claw"); removeInv("Pelt");
                att+=2;
                noSword=false;
                return 1;
            }
            else if(noShield&&scale==2&&pelt==1)
            {
                removeInv("Scale"); removeInv("Scale"); removeInv("Pelt");
                noShield=false;
                return 2;
            }
            else if(noBoat&&pelt==3)
            {
                removeInv("Pelt"); removeInv("Pelt"); removeInv("Pelt");
                noBoat=false;
                return 3;
            }
        }
        return 0;
    }

    public void setHp()
    {
        hp=topHp;
    }

    public String getInv()
    {
        if(inv.size()==0)
        {
            return "**************\nNone\n**************\n";
        }
        ArrayList<String> list=new ArrayList<String>();
        String ret="**************\n";
        int total=0;
        for(int i=0; i<inv.size(); i++)
        {
            list.add(inv.get(i).getName());
            total+=inv.get(i).getWeight();
        }
        Set<String> unique=new HashSet<String>(list);
        for(String key:unique)
        {
            ret+=key+" x"+Collections.frequency(list, key)+"\n";
        }
        ret+="(MAX 60) Total Weight: "+total+"\n**************\n";
        return ret;
    }

    public int getSpd()
    {
        return spd;
    }
    
    public boolean getNoBoat()
    {   
        return noBoat;
    }
    
    public boolean getNoShield()
    {
        return noShield;
    }
}