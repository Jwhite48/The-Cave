public class Map
{
    private String[][] m;
    private int row;
    private int col;
    private String spot;
    public Map()
    {
        m=new String[][]{   {"C","^","&","&","~","&","&","^","^"},
                            {"~","^","&","~","~","~","&","I","^"},
                            {"~","^","&","&","~","~","&","^","^"},
                            {"~","~","&","&","&","&","&","&","&"},
                            {"&","&","&","&","P","&","&","&","&"},
                            {"&","&","&","&","&","&","&","&","&"},
                            {"&","&","&","_","_","&","&","&","~"},
                            {"&","_","_","_","_","&","&","~","~"},
                            {"_","_","_","&","&","&","~","~","~"}};    
        row=4;
        col=4;
        spot="&";
    }
    
    public String displayTile()
    {
        if(spot.equals("&"))
        {
            return "Forest";
        }
        else if(spot.equals("_"))
        {
            return "Desert";
        }
        else if(spot.equals("I"))
        {
            return "Inn";
        }
        else if(spot.equals("C"))
        {
            return "The Cave";
        }
        return "Water";
    }
    
    public void printMap()
    {
        for(int r=0; r<m.length; r++)
        {
            for(int c=0; c<m[0].length; c++)
            {
                System.out.print(m[r][c]+"  ");
            }
            System.out.println();
        }
    }

    public boolean playerMovement(int a, boolean hasBoat)
    {
        if(a==1)
        {
            if(row-1<0)
            {
                return false;
            }
            else if((m[row-1][col].equals("~")&&hasBoat) || m[row-1][col].equals("^"))
            {
                return false;
            }
            m[row][col]=spot;
            row-=1;
            spot=m[row][col];
            m[row][col]="P";
            return true;
        }
        else if(a==2)
        {
            if(row+1==m.length)
            {
                return false;
            }
            else if((m[row+1][col].equals("~")&&hasBoat) || m[row+1][col].equals("^"))
            {
                return false;
            }
            m[row][col]=spot;
            row+=1;
            spot=m[row][col];
            m[row][col]="P";
            return true;
        }
        else if(a==3)
        {
            if(col+1==m[0].length)
            {
                return false;
            }
            else if((m[row][col+1].equals("~")&&hasBoat) || m[row][col+1].equals("^"))
            {
                return false;
            }
            m[row][col]=spot;
            col+=1;
            spot=m[row][col];
            m[row][col]="P";
            return true;
        }
        else if(a==4)
        {
            if(col-1<0)
            {
                return false;
            }
            else if((m[row][col-1].equals("~")&&hasBoat) || m[row][col-1].equals("^"))
            {
                return false;
            }
            m[row][col]=spot;
            col-=1;
            spot=m[row][col];
            m[row][col]="P";
            return true;
        }
        return false;
    }
}