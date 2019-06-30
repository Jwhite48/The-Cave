import cs1.*;
import java.io.*;
//import sun.audio.*;
public class Main
{
    public static void main(String[]args)
    {
        Player p=new Player(giveName());
        Map m=new Map();
        Sound wav=new Sound("walk.wav"); wav.loop(); Sound wav2=new Sound("battle.wav"); 
        boolean inn=false;
        while(true)
        {
            m.printMap();
            System.out.println("*****************\nCurrent Tile: "+m.displayTile());
            System.out.print("*****************\n1-Up\n2-Down\n3-Right\n4-Left\n5-Player Status\n6-Items\n");
            if(m.displayTile().equals("Inn"))
            {
                System.out.print("7-Enter Inn\n");
            }
            else if(m.displayTile().equals("The Cave"))
            {
                System.out.print("7-Enter The Cave\n");
            }
            System.out.print("0-Quit\n*****************\nWhat would you like to do? ");
            int a=Keyboard.readInt();
            clear();
            if(m.playerMovement(a, p.getNoBoat()))
            {
                chance(p,m,wav,wav2);
                continue;
            }
            else if(a==5)
            {
                p.playerStatus();
                System.out.print("Hit any key and enter when you are done. ");
                String ans=Keyboard.readString();
                clear();
                continue;
            }
            else if(a==6)
            {
                items(p);
                continue;
            }
            else if(m.displayTile().equals("Inn")&&a==7)
            {
                wait(500);
                System.out.println("Your health is replenished!");
                p.setHp();
                if(((int)(Math.random()*10))==0&&!inn)
                {
                    System.out.println("You turn to leave when you bump into a woman who was standing behind you.\n"+ 
                        "You start to apologize when she says, 'You'll need something to defend yourself if you want to beat him.'\n"+
                        "She turns and walks out of the inn. You hurry after her out of the inn, but she is no where to be seen."); 
                    inn=true;
                }
                System.out.print("Hit any key and enter when you are done. ");
                String ans=Keyboard.readString();
                clear();
                continue;
            }
            else if(m.displayTile().equals("The Cave")&&a==7)
            {
                bossFight(wav, p);
            }
            else if(a==0)
            {
                System.exit(0);
            }
            else if(a<0 || a>6)
            {
                System.err.println("INVALID OPTION!");
                wait(1000);
                clear();
                continue;
            }
            System.err.println("INVALID MOVEMENT!");
            wait(1000);
            clear();
        }
    }

    public static String giveName()
    {
        System.out.print("What would you like to name your character? (3-8 chracters) ");
        String n=Keyboard.readString().toUpperCase();
        while(n.length()<3 || n.length()>8)
        {
            System.err.println("NOT A VALID NAME!");
            wait(1000);
            clear();
            System.out.print("What would you like to name your character? (3-8 chracters) ");
            n=Keyboard.readString().toUpperCase();
        }
        clear();
        wait(500);
        return n;
    }

    public static void bossFight(Sound wav2, Player p)
    {
        clear(); wait(500);
        wav2.stop(); Sound wav3=new Sound("boss.wav"); Sound wav4=new Sound("credits.wav"); wav3.loop();
        Boss b=new Boss(); b.print();
        wait(2000); clear();
        boolean charging=false;
        boolean defending=false;
        while(true)
        {
            if(charging)
            {
                if(defending)
                {
                    System.out.println(b.getName()+" unleashes a beam of energy!");
                    wait(750);
                    System.out.println("You deflect it back at "+b.getName()+".");
                    wait(750);
                    System.out.println(b.getName()+ " takes "+b.takeDMG(50)+" damage!");
                    if(b.isDead())
                    {
                        clear();
                        wav3.stop(); wav4.loop();
                        System.out.println("With a final blow, "+b.getName()+" crumbles to the floor, revealing a bright light behind it.\n"+
                            "You walk towards it...and emerge back where your ship wrecked, but instead it is now fixed! You can go home!");
                        wait(4000); clear();
                        System.out.print("Created By: Jesse White");
                        wait(2000); clear();
                        System.out.print("Thank you to Tyler Thomas for the code for sound clips!");
                        wait(2000); clear();
                        System.out.print("Shout out to SAB Records for composing music for this game!");
                        wait(2000); clear();
                        System.out.print("Press any key and enter to quit! ");
                        String end=Keyboard.readString();
                        System.exit(0);
                    }
                    System.out.println(b.getHp()+" health remains!");
                    wait(1000);
                    clear();
                }
                else
                {
                    System.out.println(b.getName()+" unleashes a beam of energy!");
                    wait(750);
                    System.out.println(b.getName()+ " does "+p.takeDMG(50)+" damage!");
                    wait(750);
                    if(p.isDead())
                    {
                        clear();
                        System.out.print("GAME OVER!");
                        System.exit(0);
                    }
                }
                charging=false;
                defending=false;
            }
            if((int)(Math.random()*100)<=29&&!charging)
            {
                System.out.println(b.getName()+" is charging up!");
                charging=true;
                wait(750);
            }
            else
            {
                System.out.println(b.getName()+" attacks!");
                wait(750);
                System.out.println(b.getName()+ " does "+p.takeDMG(b.fight())+" damage!");
                wait(750);
                if(p.isDead())
                {
                    clear();
                    System.out.print("GAME OVER!");
                    System.exit(0);
                }
            }
            System.out.print("**************\n1-Fight\n");
            if(charging&&!p.getNoShield())
            {
                System.out.print("2-Defend\n");
            }
            p.playerStatus();
            System.out.print("What would you like to do? ");
            int opt=Keyboard.readInt();
            if(charging&&!p.getNoShield())
            {
                while(opt<1 || opt>2)
                {
                    System.err.print("INVALID OPTION!");
                    wait(1000);
                    clear();
                    System.out.println("**************\n1-Fight\n2-Defend");
                    p.playerStatus();
                    System.out.print("What would you like to do? ");
                    opt=Keyboard.readInt();
                }
            }
            else
            {
                while(opt!=1)
                {
                    System.err.print("INVALID OPTION!");
                    wait(1000);
                    clear();
                    System.out.println("**************\n1-Fight");
                    p.playerStatus();
                    System.out.print("What would you like to do? ");
                    opt=Keyboard.readInt();
                }
            }
            clear();
            if(opt==1)
            {
                System.out.println("You attack!");
                wait(750);
                System.out.println(b.getName()+ " takes "+b.takeDMG(p.fight())+" damage!");
                wait(750);
                if(b.isDead())
                {
                    clear();
                    wav3.stop(); wav4.loop();
                    System.out.println("With a final blow, "+b.getName()+" crumbles to the floor, revealing a bright light behind it.\n"+
                        "You walk towards it...and emerge back where your ship wrecked, but instead it is now fixed! You can go home!");
                    wait(4000); clear();
                    System.out.print("Created By: Jesse White");
                    wait(2000); clear();
                    System.out.print("Thank you to Tyler Thomas for the code for sound clips!");
                    wait(2000); clear();
                    System.out.print("Shout out to SAB Records for composing music for this game!");
                    wait(2000); clear();
                    System.out.print("Press any key and enter to quit! ");
                    String end=Keyboard.readString();
                    System.exit(0);
                }
                System.out.println(b.getHp()+" health remains!");
                wait(1000);
                clear();
            }
            else if(opt==2)
            {
                System.out.print("You raise your shield in defense for the upcoming attack!");
                defending=true;
                wait(1000);
                clear();
            }
        }
    }

    public static void items(Player p)
    {
        while(true)
        {
            System.out.print(p.getInv()+"1-Craft\n2-Remove Item\n0-Return\n**************\nWhat would you like to do? ");
            int a=Keyboard.readInt();
            while(a<0||a>2)
            {
                System.err.println("INVALID OPTION!");
                wait(1000);
                clear();
                System.out.print(p.getInv()+"1-Craft\n2-Remove Item\n0-Return\n**************\nWhat would you like to do? ");
                a=Keyboard.readInt();
            }
            if(a==1)
            {
                clear();
                System.out.print(p.getInv());
                System.out.print("First item: ");
                String a1=Keyboard.readString();
                System.out.print("Second item: ");
                String b=Keyboard.readString();
                System.out.print("Third item: ");
                String c=Keyboard.readString();
                clear();
                int it=p.craftable(a1,b,c);
                if(it==1)
                {
                    System.out.print("You made a sword! Your attack increased by 2!");
                    wait(1000);
                    clear();
                    continue;
                }
                else if(it==2)
                {
                    System.out.print("You made a shield!");
                    wait(1000);
                    clear();
                    continue;
                }
                else if(it==3)
                {
                    System.out.print("You made a boat! You can now cross water!");
                    wait(1000);
                    clear();
                    continue;
                }
                System.out.print("Nothing was made!");
                wait(750);
                clear();
                continue;
            }
            else if(a==2)
            {
                clear();
                System.out.print(p.getInv()+"What item would you like to drop? ");
                String a2=Keyboard.readString();
                clear();
                if(p.removeInv(a2))
                {
                    System.out.print("You succesfully dropped the item!");
                    wait(750);
                    clear();
                    continue;
                }
                System.out.print("You weren't succesfull in dropping the item!");
                wait(750);
                clear();
                continue;
            }
            else if(a==0)
            {
                clear();
                return;
            }
        }
    }

    public static void chance(Player p, Map m, Sound wav3, Sound wav4)
    {
        int rnd=(int)(Math.random()*10);
        int drop=(int)(Math.random()*10);
        if(m.displayTile().equals("Forest")&&rnd<=2)
        {
            wav3.stop(); wav4.loop();
            int rndW=(int)(Math.random()*10);
            int rndB=(int)(Math.random()*10);
            if(rndW<=6)
            {  
                if(battle(p, new Wolf())==1&&drop<=3&&p.addInv(new Claw()))
                {
                    System.out.print("You picked up a claw!");
                    wait(750);
                }
            }
            else
            {
                if(battle(p, new Bear())==1&&drop<=1&&p.addInv(new Pelt()))
                {
                    System.out.print("You picked up a pelt!");
                    wait(750);
                }
            }
            wav4.stop(); wav3.loop();
        }
        else if(m.displayTile().equals("Desert")&&rnd<=1)
        {
            wav3.stop(); wav4.loop();
            if(battle(p, new Snake())==1&&drop<=1&&p.addInv(new Scale()))
            {
                System.out.print("You picked up a scale!");
                wait(750);
            }
            wav4.stop(); wav3.loop();
        }
        clear();
    }

    public static int battle(Player p, Monster mon)
    {
        while(mon.getSpd()>p.getSpd())
        {
            System.out.println("The wild "+mon.getName()+" attacks!");
            wait(750);
            System.out.println("The "+mon.getName()+ " does "+p.takeDMG(mon.fight())+" damage!");
            wait(750);
            if(p.isDead())
            {
                clear();
                System.out.print("GAME OVER!");
                System.exit(0);
            }
            System.out.println("**************\n1-Fight\n2-Run");
            p.playerStatus();
            System.out.print("What would you like to do? ");
            int opt=Keyboard.readInt();
            while(opt<1 || opt>2)
            {
                System.err.print("INVALID OPTION!");
                wait(1000);
                clear();
                System.out.println("**************\n1-Fight\n2-Run");
                p.playerStatus();
                System.out.print("What would you like to do? ");
                opt=Keyboard.readInt();
            }
            clear();
            if(opt==1)
            {
                System.out.println("You attack!");
                wait(750);
                System.out.println("The "+mon.getName()+ " takes "+mon.takeDMG(p.fight())+" damage!");
                wait(750);
                if(mon.isDead())
                {
                    System.out.println("You have bested the beast!");
                    wait(750);
                    System.out.println("You gain "+mon.getExp()+" exp!");
                    wait(750);
                    if(p.gainAndLevel(mon.getExp()))
                    {
                        clear();
                        System.out.print("You leveled up!");
                        wait(750);
                    }
                    clear();
                    return 1;
                }
                System.out.println(mon.getHp()+" health remains!");
                wait(1000);
                clear();
            }
            else if(opt==2)
            {
                int rnd=(int)(Math.random()*10);
                int chance=9-(mon.getSpd()-p.getSpd());
                if(chance>=rnd)
                {
                    System.out.print("You succesfully ran away!");
                    wait(750);
                    clear();
                    return 0;
                }
                System.out.print("You couldn't run away!");
                wait(750);
                clear();
                continue;
            }
        }
        System.out.println("A wild "+mon.getName()+" appears!");
        wait(750);
        while(p.getSpd()>=mon.getSpd())
        {
            System.out.println("**************\n1-Fight\n2-Run");
            p.playerStatus();
            System.out.print("What would you like to do? ");
            int opt=Keyboard.readInt();
            while(opt<1 || opt>2)
            {
                System.err.print("INVALID OPTION!");
                wait(1000);
                clear();
                System.out.println("**************\n1-Fight\n2-Run");
                p.playerStatus();
                System.out.print("What would you like to do? ");
                opt=Keyboard.readInt();
            }
            clear();
            if(opt==1)
            {
                System.out.println("You attack!");
                wait(750);
                System.out.println("The "+mon.getName()+ " takes "+mon.takeDMG(p.fight())+" damage!");
                wait(750);
                if(mon.isDead())
                {
                    System.out.println("You have bested the beast!");
                    wait(750);
                    System.out.println("You gain "+mon.getExp()+" exp!");
                    wait(750);
                    if(p.gainAndLevel(mon.getExp()))
                    {
                        clear();
                        System.out.print("You leveled up!");
                        wait(750);
                    }
                    clear();
                    return 1;
                }
                System.out.println(mon.getHp()+" health remains!");
                wait(1000);
                clear();
            }
            else if(opt==2)
            {
                int rnd=(int)(Math.random()*10);
                int chance=8;
                if(chance>=rnd)
                {
                    System.out.print("You succesfully ran away!");
                    wait(750);
                    clear();
                    return 0;
                }
                System.out.print("You couldn't run away!");
                wait(750);
                clear();
            }
            System.out.println("The wild "+mon.getName()+" attacks!");
            wait(750);
            System.out.println("The "+mon.getName()+ " does "+p.takeDMG(mon.fight())+" damage!");
            wait(750);
            if(p.isDead())
            {
                clear();
                System.out.print("GAME OVER!");
                System.exit(0);
            }
        }
        return 0;
    }

    public static void clear()
    {
        System.out.print('\u000c');
    }

    public static void wait(int x)
    {
        try 
        {
            Thread.sleep(x);                 
        } 
        catch(InterruptedException ex) 
        {
            Thread.currentThread().interrupt();
        }
    }
}
